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
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BiomePlacementMarshaller.AddSubBiomeMarshaller.class)
public class AddSubBiomeMarshallerMixin {

    @Inject(method = "unmarshall", at = @At("HEAD"))
    public void unmarshall(CallbackInfo ci) {

        BiomePlacementMarshaller.AddSubBiomeMarshaller marshaller = (BiomePlacementMarshaller.AddSubBiomeMarshaller) (Object) this;
        ResourceKey<DimensionType> dimension = marshaller.dimension();
        ResourceKey<Biome> biome = marshaller.biome();
        ResourceKey<Biome> target = marshaller.target();
        Criterion criterion = marshaller.criterion();

        if(dimension.equals(AetherDimensions.AETHER_DIMENSION_TYPE))
            AetherBiomeCoordinator.AETHER.addSubBiome(target, biome, criterion, true);    }
}
