package io.github.razordevs.aerolith;

import com.terraformersmc.biolith.impl.config.BiolithGeneralConfig;
import net.minecraft.util.Mth;

public class AerolithGeneralConfig extends BiolithGeneralConfig {
    private int aetherReplacementScale = 4;

    public int getAetherReplacementScale() {
        if (aetherReplacementScale < 1 || aetherReplacementScale > 16) {
            Aerolith.LOGGER.warn("Aerolith Aether replacement noise scale is out of range; clamping to [1,16]...");
            aetherReplacementScale = Mth.clamp(aetherReplacementScale, 1, 16);
        }

        return aetherReplacementScale;
    }
}
