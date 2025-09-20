package io.github.razordevs.aerolith.biome;

import com.terraformersmc.biolith.impl.Biolith;
import com.terraformersmc.biolith.impl.biome.DimensionBiomePlacement;
import io.github.razordevs.aerolith.Aerolith;
import io.github.razordevs.aerolith.AerolithConfig;

public class AetherBiomePlacement extends DimensionBiomePlacement {
    private final double[] scale = new double[4];

    public AetherBiomePlacement() {
        super();

        int configScale = AerolithConfig.COMMON.aetherReplacementScale.get();
        scale[0] = 256 * configScale;
        scale[1] =  64 * configScale;
        scale[2] =  16 * configScale;
        scale[3] =   4 * configScale;
    }

    @Override
    public double getLocalNoise(int x, int y, int z) {
        double localNoise;

        // Four octaves to give some edge fuzz
        localNoise  = replacementNoise.sample((double)(x + seedlets[0]) / scale[0], (double)(z + seedlets[1]) / scale[0]);
        localNoise += replacementNoise.sample((double)(x + seedlets[2]) / scale[1], (double)(z + seedlets[3]) / scale[1]) / 4D;
        localNoise += replacementNoise.sample((double)(x + seedlets[4]) / scale[2], (double)(z + seedlets[5]) / scale[2]) / 16D;
        localNoise += replacementNoise.sample((double)(x + seedlets[6]) / scale[3], (double)(z + seedlets[7]) / scale[3]) / 32D;

        // Scale the result back to amplitude 1 and then normalize
        localNoise = normalize(localNoise / 1.3125D);
        return localNoise;
    }
}
