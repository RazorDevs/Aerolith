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

        localNoise  = replacementNoise.sample((double)(x + seedlets[0]) / scale[0], (double)(y + seedlets[0]) / scale[1], (double)(z + seedlets[1]) / scale[0]);
        localNoise += replacementNoise.sample((double)(x + seedlets[4]) / scale[2], (double)(y + seedlets[0]) / scale[3], (double)(z + seedlets[5]) / scale[2]) / 16D;
        localNoise += replacementNoise.sample((double)(x + seedlets[6]) / scale[3], (double)(y + seedlets[0]) / scale[4], (double)(z + seedlets[7]) / scale[3]) / 32D;

        localNoise = normalize(localNoise / 1.09375D);
        return localNoise;
    }
}
