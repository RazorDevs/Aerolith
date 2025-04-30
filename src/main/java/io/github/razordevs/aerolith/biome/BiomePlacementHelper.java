package io.github.razordevs.aerolith.biome;

import com.terraformersmc.biolith.api.biome.sub.Criterion;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

public class BiomePlacementHelper {

    /**
     * Place a Aether biome at the specified mixed noise point.
     *
     * @param biome      The biome to be placed
     * @param noisePoint A multi-noise point at which to place the biome
     */
    public static void addAether(ResourceKey<Biome> biome, Climate.ParameterPoint noisePoint) {
        AetherBiomeCoordinator.AETHER.addPlacement(biome, noisePoint, false);
    }

    /**
     * Remove a Aether biome from all mixed noise points.
     *
     * @param biome The biome to be removed
     */
    public static void removeAether(ResourceKey<Biome> biome) {
        AetherBiomeCoordinator.AETHER.addRemoval(biome, false);
    }


    /**
     * Completely replace a Aether biome with another biome.
     *
     * @param target The biome to be replaced
     * @param biome  The replacement biome
     */
    public static void replaceAether(ResourceKey<Biome> target, ResourceKey<Biome> biome) {
        AetherBiomeCoordinator.AETHER.addReplacement(target, biome, 1.0D, false);
    }

    /**
     * Partially replace a Aether biome with another biome.  The proportion must be a positive number,
     * and any number above 1.0d will result in complete replacement.
     *
     * @param target     The biome to be replaced
     * @param biome      The replacement biome
     * @param proportion Approximate fraction of the target biome's volume to replace
     */
    public static void replaceAether(ResourceKey<Biome> target, ResourceKey<Biome> biome, double proportion) {
        AetherBiomeCoordinator.AETHER.addReplacement(target, biome, proportion, false);
    }

    /**
     * Add a Aether biome which replaces matching regions of another biome.  Replacement will occur for any
     * location in the target biome where all conditions of the matcher evaluate a successful match.
     *
     * @param target  The biome to be replaced
     * @param biome   The replacement biome
     * @param criterion Matching criteria for when to replace
     */
    public static void addSubAether(ResourceKey<Biome> target, ResourceKey<Biome> biome, Criterion criterion) {
        AetherBiomeCoordinator.AETHER.addSubBiome(target, biome, criterion, false);
    }
}
