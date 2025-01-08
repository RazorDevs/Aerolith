package io.github.razordevs.aerolith.mixin;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.terraformersmc.biolith.impl.data.BiomePlacementMarshaller;
import io.github.razordevs.aerolith.mixin.accessor.AetherPlacementAccessor;
import io.github.razordevs.aerolith.surface.AetherSurfaceGeneration;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(BiomePlacementMarshaller.AddBiomeMarshaller.class)
public class AddBiomeMarshallerMixin {

    @Inject(method = "unmarshall", at = @At("HEAD"), locals = LocalCapture.CAPTURE_FAILSOFT)
    public void unmarshall(CallbackInfo ci, ResourceKey<DimensionType> dimension, ResourceKey<Biome> biome, Climate.ParameterPoint noisePoint) {
        if(dimension.equals(AetherDimensions.AETHER_DIMENSION_TYPE))
            AetherPlacementAccessor.invokeGetAetherPlacement().addPlacement(biome, noisePoint, true);    }
}
