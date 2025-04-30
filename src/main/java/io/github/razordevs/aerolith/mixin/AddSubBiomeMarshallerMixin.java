package io.github.razordevs.aerolith.mixin;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.terraformersmc.biolith.api.biome.sub.Criterion;
import com.terraformersmc.biolith.impl.data.BiomePlacementMarshaller;
import io.github.razordevs.aerolith.biome.AetherBiomeCoordinator;
import io.github.razordevs.aerolith.biome.BiomePlacementHelper;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BiomePlacementMarshaller.AddSubBiomeMarshaller.class)
public class AddSubBiomeMarshallerMixin {

    @Inject(method = "unmarshall", at = @At("HEAD"), locals = LocalCapture.CAPTURE_FAILHARD)
    public void unmarshall(CallbackInfo ci, @Share("dimension") LocalRef<ResourceKey<DimensionType>> dimension,
                           @Share("target") LocalRef<ResourceKey<Biome>> target, @Share("biome") LocalRef<ResourceKey<Biome>> biome,
                           @Share("criterion") LocalRef<Criterion> criterion) {
        if(dimension.get().equals(AetherDimensions.AETHER_DIMENSION_TYPE))
            AetherBiomeCoordinator.AETHER.addSubBiome(target.get(), biome.get(), criterion.get(), true);    }
}
