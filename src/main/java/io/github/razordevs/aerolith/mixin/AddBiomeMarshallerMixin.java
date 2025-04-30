package io.github.razordevs.aerolith.mixin;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.terraformersmc.biolith.impl.data.BiomePlacementMarshaller;
import io.github.razordevs.aerolith.Aerolith;
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

@Mixin(BiomePlacementMarshaller.AddBiomeMarshaller.class)
public class AddBiomeMarshallerMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(ResourceKey<DimensionType> dimension, ResourceKey<Biome> biome,
                        Climate.ParameterPoint noisePoint, CallbackInfo ci) {
        Aerolith.LOGGER.info("AddBiomeMarshaller initialized with dimension: {}", dimension.location());
    }


    @Inject(method = "unmarshall", at = @At("HEAD"))
    public void unmarshall(CallbackInfo ci) {
        Aerolith.LOGGER.info("Adding biome");

        BiomePlacementMarshaller.AddBiomeMarshaller marshaller = (BiomePlacementMarshaller.AddBiomeMarshaller) (Object) this;
        ResourceKey<DimensionType> dimension = marshaller.dimension();
        ResourceKey<Biome> biome = marshaller.biome();
        Climate.ParameterPoint noisePoint = marshaller.noisePoint();

        Aerolith.LOGGER.info("Adding biome " + biome.location() + " to dimension " + dimension.location());
        if(dimension.equals(AetherDimensions.AETHER_DIMENSION_TYPE)) {
            BiomePlacementHelper.AETHER.addPlacement(biome, noisePoint, true);
            Aerolith.LOGGER.info("Added biome " + biome.location() + " to Aether dimension at " + noisePoint.toString());
        }
    }

    @Inject(method = "unmarshall", at = @At("RETURN"))
    public void afterUnmarshall(CallbackInfo ci) {
        Aerolith.LOGGER.info("unmarshall method completed");
    }

}
