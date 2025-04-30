package io.github.razordevs.aerolith.mixin;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.terraformersmc.biolith.api.biome.BiolithFittestNodes;
import com.terraformersmc.biolith.impl.biome.InterfaceBiomeSource;
import com.terraformersmc.biolith.impl.compat.VanillaCompat;
import com.terraformersmc.biolith.impl.mixin.MixinBiomeSource;
import io.github.razordevs.aerolith.Aerolith;
import io.github.razordevs.aerolith.biome.BiomePlacementHelper;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Mixin(value = MultiNoiseBiomeSource.class, priority = 800)
public abstract class MultiNoiseBiomeSourceMixin extends MixinBiomeSource implements InterfaceBiomeSource  {
    @Shadow
    protected abstract Climate.ParameterList<Holder<Biome>> parameters();

    private Climate.ParameterList<Holder<Biome>> aerolith$biomeParameterList;

    // Inject noise points the first time somebody requests them.
    @WrapOperation(
            method = "parameters",
            at = @At(value = "INVOKE", target = "Lcom/mojang/datafixers/util/Either;map(Ljava/util/function/Function;Ljava/util/function/Function;)Ljava/lang/Object;")
    )
    @SuppressWarnings("unused")
    private Object aerolith$injectParameterList(Either<Climate.ParameterList<Holder<Biome>>, Holder<MultiNoiseBiomeSourceParameterList>> instance,
                                                Function<Climate.ParameterList<Holder<Biome>>, Climate.ParameterList<Holder<Biome>>> leftMap,
                                                Function<Holder<MultiNoiseBiomeSourceParameterList>, Climate.ParameterList<Holder<Biome>>> rightMap, Operation<Object> original) {
        synchronized (this) {
            // Only compute this once, since our version is more expensive than Mojang's.
            Aerolith.LOGGER.debug("Injecting parameter list for {}", this.biolith$getDimensionType());
            if (aerolith$biomeParameterList == null) {
                // Mojang does the exact same cast on the return of this operation.
                //noinspection unchecked
                Climate.ParameterList<Holder<Biome>> originalParameterList =
                        (Climate.ParameterList<Holder<Biome>>) original.call(instance, leftMap, rightMap);

                Aerolith.LOGGER.debug("After first check");
                if (this.biolith$getDimensionType().location().equals(AetherDimensions.AETHER_DIMENSION_TYPE.location())) {
                    List<Pair<Climate.ParameterPoint, Holder<Biome>>> parameterList = new ArrayList<>(256);

                    // Remove any biomes matching removals
                    originalParameterList.values().stream()
                            .filter(BiomePlacementHelper.AETHER::removalFilter)
                            .forEach(parameterList::add);

                    Aerolith.LOGGER.debug("After second check");
                    // Add all biomes from additions, replacements, and sub-biome requests
                    BiomePlacementHelper.AETHER.writeBiomeEntries(parameterList::add);

                    aerolith$biomeParameterList = new Climate.ParameterList<>(parameterList);
                } else {
                    Aerolith.LOGGER.debug("Failed check");
                    aerolith$biomeParameterList = originalParameterList;
                }
            }
        } // synchronized (this)

        return aerolith$biomeParameterList;
    }

    // We calculate the vanilla/datapack biome, then we apply any overlays.
    @Inject(method = "getNoiseBiome(IIILnet/minecraft/world/level/biome/Climate$Sampler;)Lnet/minecraft/core/Holder;", at = @At("HEAD"), cancellable = true)
    private void biolith$getBiome(int x, int y, int z, Climate.Sampler noise, CallbackInfoReturnable<Holder<Biome>> cir) {
        Climate.TargetPoint noisePoint = noise.sample(x, y, z);
        BiolithFittestNodes<Holder<Biome>> fittestNodes = null;

        // Find the biome via Vanilla (including datapacks) if none was provided by TerraBlender.
        if (fittestNodes == null) {
            fittestNodes = VanillaCompat.getBiome(noisePoint, parameters());
        }

        // Apply biome overlays.
        if (this.biolith$getDimensionType().location().equals(AetherDimensions.AETHER_DIMENSION_TYPE.location())) {
            cir.setReturnValue(BiomePlacementHelper.AETHER.getReplacement(x, y, z, noisePoint, fittestNodes));
        } else {
            cir.setReturnValue(fittestNodes.ultimate().value);
        }
    }
}
