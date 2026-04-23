package io.github.razordevs.aerolith.enums;

import net.minecraft.world.level.biome.Climate;

public enum AeroIslandPos {
    EDGE(Climate.Parameter.span(-0.2F, 0.05F)),
    INTERIOR(Climate.Parameter.span(0.3F, 1.0F)),
    ANYWHERE(Climate.Parameter.span(-0.2F, 1.0F));

    final Climate.Parameter cont;

    AeroIslandPos(Climate.Parameter cont) {
        this.cont = cont;
    }

    public Climate.Parameter getContinentalness() {
        return cont;
    }
}
