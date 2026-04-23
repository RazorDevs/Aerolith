package io.github.razordevs.aerolith.enums;

import net.minecraft.world.level.biome.Climate;

public enum AeroTerrainShape {
    FLAT(Climate.Parameter.span(0.45f, 1.0f), Climate.Parameter.span(-0.2f, 0.2f)),
    HILLY(Climate.Parameter.span(-0.2f, 0.4f), Climate.Parameter.span(-0.45f, 0.45f)),
    JAGGED(Climate.Parameter.span(-1.0f, -0.45f), Climate.Parameter.span(0.5f, 1.0f));

    final Climate.Parameter eros;
    final Climate.Parameter weird;

    AeroTerrainShape(Climate.Parameter eros, Climate.Parameter weird) {
        this.eros = eros;
        this.weird = weird;
    }

    public Climate.Parameter getErosion() {
        return eros;
    }

    public Climate.Parameter getWeirdness() {
        return weird;
    }
}
