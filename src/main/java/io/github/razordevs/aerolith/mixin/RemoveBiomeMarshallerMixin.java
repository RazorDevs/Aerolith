package io.github.razordevs.aerolith.mixin;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.terraformersmc.biolith.impl.data.BiomePlacementMarshaller;
import io.github.razordevs.aerolith.biome.AetherBiomeCoordinator;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BiomePlacementMarshaller.RemoveBiomeMarshaller.class)
public class RemoveBiomeMarshallerMixin {

    @Inject(method = "unmarshall", at = @At("HEAD"))
    public void unmarshall(CallbackInfo ci) {

        BiomePlacementMarshaller.RemoveBiomeMarshaller marshaller = (BiomePlacementMarshaller.RemoveBiomeMarshaller) (Object) this;
        ResourceKey<DimensionType> dimension = marshaller.dimension();
        ResourceKey<Biome> biome = marshaller.biome();

        if(dimension.equals(AetherDimensions.AETHER_DIMENSION_TYPE))
            AetherBiomeCoordinator.AETHER.addRemoval(biome, true);
    }
}
