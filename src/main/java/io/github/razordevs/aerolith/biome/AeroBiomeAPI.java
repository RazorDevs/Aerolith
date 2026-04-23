package io.github.razordevs.aerolith.biome;

import com.terraformersmc.biolith.api.biome.sub.Criterion;
import io.github.razordevs.aerolith.enums.AeroClimate;
import io.github.razordevs.aerolith.enums.AeroIslandPos;
import io.github.razordevs.aerolith.enums.AeroLayer;
import io.github.razordevs.aerolith.enums.AeroTerrainShape;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

public final class AeroBiomeAPI {
    private AeroBiomeAPI() {}

    /**
     * Initializes the Aether Biome Builder for the biome to be added.
     *
     * @param biomeKey      The biome to be placed
     */
    public static BiomeBuilder addBiome(ResourceKey<Biome> biomeKey) {
        return new BiomeBuilder(biomeKey);
    }

    /**
     * Remove a Aether biome from all mixed noise points.
     *
     * @param biomeKey The biome to be removed
     */
    public static void removeBiome(ResourceKey<Biome> biomeKey) {
        AetherBiomeCoordinator.AETHER.addRemoval(biomeKey, false);
    }


    /**
     * Completely replace a Aether biome with another biome.
     *
     * @param biomeKey The biome to be replaced
     * @param oBiomeKey  The replacement biome
     */
    public static void replaceBiome(ResourceKey<Biome> biomeKey, ResourceKey<Biome> oBiomeKey) {
        AetherBiomeCoordinator.AETHER.addReplacement(biomeKey, oBiomeKey, 1.0D, false);
    }

    /**
     * Partially replace a Aether biome with another biome.  The proportion must be a positive number,
     * and any number above 1.0d will result in complete replacement.
     *
     * @param biomeKey     The biome to be replaced
     * @param oBiomeKey      The replacement biome
     * @param proportion Approximate fraction of the target biome's volume to replace
     */
    public static void replaceBiome(ResourceKey<Biome> biomeKey, ResourceKey<Biome> oBiomeKey, double proportion) {
        AetherBiomeCoordinator.AETHER.addReplacement(biomeKey, oBiomeKey, proportion, false);
    }

    /**
     * Add a Aether biome which replaces matching regions of another biome.  Replacement will occur for any
     * location in the target biome where all conditions of the matcher evaluate a successful match.
     *
     * @param biomeKey  The biome to be replaced
     * @param nBiomeKey   The replacement biome
     * @param criterion Matching criteria for when to replace
     */
    public static void addSubBiome(ResourceKey<Biome> biomeKey, ResourceKey<Biome> nBiomeKey, Criterion criterion) {
        AetherBiomeCoordinator.AETHER.addSubBiome(biomeKey, nBiomeKey, criterion, false);
    }

    public static class BiomeBuilder {
        private final ResourceKey<Biome> biomeKey;
        private Climate.Parameter temperature;
        private Climate.Parameter humidity;
        private Climate.Parameter continentalness;
        private Climate.Parameter erosion;
        private Climate.Parameter depth;
        private Climate.Parameter weirdness;

        private BiomeBuilder(ResourceKey<Biome> biomeKey) {
            this.biomeKey = biomeKey;
        }

        public BiomeBuilder setLayer(AeroLayer layer) {
            this.depth = layer.getDepth();
            return this;
        }

        public BiomeBuilder setLayer(Climate.Parameter depth) {
            this.depth = depth;
            return this;
        }

        public BiomeBuilder setTerrainShape(AeroTerrainShape shape) {
            this.erosion = shape.getErosion();
            this.weirdness = shape.getWeirdness();
            return this;
        }

        public BiomeBuilder setTerrainShape(Climate.Parameter erosion, Climate.Parameter weirdness) {
            this.erosion = erosion;
            this.weirdness = weirdness;
            return this;
        }

        public BiomeBuilder setClimate(AeroClimate climate) {
            this.temperature = climate.getTemperature();
            this.humidity = climate.getHumidity();
            return this;
        }

        public BiomeBuilder setClimate(Climate.Parameter temperature, Climate.Parameter humidity) {
            this.temperature = temperature;
            this.humidity = humidity;
            return this;
        }

        public BiomeBuilder setIslandPos(AeroIslandPos pos) {
            this.continentalness = pos.getContinentalness();
            return this;
        }

        public BiomeBuilder setIslandPos(Climate.Parameter continentalness) {
            this.continentalness = continentalness;
            return this;
        }

        public void build() {
            if (temperature == null || humidity == null || continentalness == null ||
                    erosion == null || depth == null || weirdness == null)
                throw new IllegalStateException("Not all parameters have been set");

            AetherBiomeCoordinator.AETHER.addPlacement(biomeKey, new Climate.ParameterPoint(
                            temperature,
                            humidity,
                            continentalness,
                            erosion,
                            depth,
                            weirdness,
                            0L
                    ), false);
        }
    }
}
