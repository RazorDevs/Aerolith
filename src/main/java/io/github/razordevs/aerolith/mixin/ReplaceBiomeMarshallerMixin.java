package io.github.razordevs.aerolith.mixin;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.terraformersmc.biolith.impl.data.BiomePlacementMarshaller;
import io.github.razordevs.aerolith.mixin.accessor.AetherPlacementAccessor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BiomePlacementMarshaller.ReplaceBiomeMarshaller.class)
public class ReplaceBiomeMarshallerMixin {

    @Inject(method = "unmarshall", at = @At("HEAD"), locals = LocalCapture.CAPTURE_FAILSOFT)
    public void unmarshall(CallbackInfo ci, ResourceKey<DimensionType> dimension, ResourceKey<Biome> target, ResourceKey<Biome> biome, double proportion) {
        if(dimension.equals(AetherDimensions.AETHER_DIMENSION_TYPE))
            AetherPlacementAccessor.invokeGetAetherPlacement().addReplacement(target, biome, proportion, true);    }
}
