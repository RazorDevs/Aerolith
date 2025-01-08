package io.github.razordevs.aerolith.mixin;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.Criterion;
import com.terraformersmc.biolith.impl.biome.BiomeCoordinator;
import io.github.razordevs.aerolith.biome.AetherBiomePlacement;
import io.github.razordevs.aerolith.mixin.accessor.AetherPlacementAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import net.minecraft.resources.ResourceKey;

import java.lang.reflect.Method;

@Mixin(BiomePlacement.class)
public class BiomePlacementMixin {

    /**
     * Place a Aether biome at the specified mixed noise point.
     *
     * @param biome      The biome to be placed
     * @param noisePoint A multi-noise point at which to place the biome
     */
    @Unique
    private static void addAether(ResourceKey<Biome> biome, Climate.ParameterPoint noisePoint) {
        AetherPlacementAccessor.invokeGetAetherPlacement().addPlacement(biome, noisePoint, false);
    }

    /**
     * Remove a Aether biome from all mixed noise points.
     *
     * @param biome The biome to be removed
     */
    @Unique
    private static void removeAether(ResourceKey<Biome> biome) {
        AetherPlacementAccessor.invokeGetAetherPlacement().addRemoval(biome, false);
    }


    /**
     * Completely replace a Aether biome with another biome.
     *
     * @param target The biome to be replaced
     * @param biome  The replacement biome
     */
    @Unique
    private static void replaceAether(ResourceKey<Biome> target, ResourceKey<Biome> biome) {
        AetherPlacementAccessor.invokeGetAetherPlacement().addReplacement(target, biome, 1.0D, false);
    }

    /**
     * Partially replace a Aether biome with another biome.  The proportion must be a positive number,
     * and any number above 1.0d will result in complete replacement.
     *
     * @param target     The biome to be replaced
     * @param biome      The replacement biome
     * @param proportion Approximate fraction of the target biome's volume to replace
     */
    @Unique
    private static void replaceAether(ResourceKey<Biome> target, ResourceKey<Biome> biome, double proportion) {
        AetherPlacementAccessor.invokeGetAetherPlacement().addReplacement(target, biome, proportion, false);
    }

    /**
     * Add a Aether biome which replaces matching regions of another biome.  Replacement will occur for any
     * location in the target biome where all conditions of the matcher evaluate a successful match.
     *
     * @param target  The biome to be replaced
     * @param biome   The replacement biome
     * @param criterion Matching criteria for when to replace
     */
    @Unique
    private static void addSubAether(ResourceKey<Biome> target, ResourceKey<Biome> biome, Criterion criterion) {
        AetherPlacementAccessor.invokeGetAetherPlacement().addSubBiome(target, biome, criterion, false);
    }
}
