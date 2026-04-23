package io.github.razordevs.aerolith.enums;

import net.minecraft.world.level.biome.Climate;

public enum AeroLayer {
    SURFACE(Climate.Parameter.span(0.1f, 1.0f)),
    UNDERSIDE(Climate.Parameter.span(-1.0f, -0.2f)),
    CORE(Climate.Parameter.span(-0.15f, 0.15f)),
    SKY(Climate.Parameter.span(-1.0f, 1.0f));

    final Climate.Parameter depth;

    AeroLayer(Climate.Parameter depth) {
        this.depth = depth;
    }

    public Climate.Parameter getDepth() {
        return depth;
    }
}
