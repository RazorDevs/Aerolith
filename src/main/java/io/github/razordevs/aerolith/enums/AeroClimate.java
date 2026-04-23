package io.github.razordevs.aerolith.enums;

import net.minecraft.world.level.biome.Climate;

public enum AeroClimate {
    COLD(Climate.Parameter.span(-1.0f, -0.45f), Climate.Parameter.span(-1.0f, 1.0f)),
    TEMPERATE(Climate.Parameter.span(-0.4f, 0.4f), Climate.Parameter.span(-1.0f, 1.0f)),
    WARM(Climate.Parameter.span(0.45f, 1.0f), Climate.Parameter.span(-1.0f, 1.0f));

    final Climate.Parameter temp;
    final Climate.Parameter humi;

    AeroClimate(Climate.Parameter temp, Climate.Parameter humi) {
        this.temp = temp;
        this.humi = humi;
    }

    public Climate.Parameter getTemperature() {
        return temp;
    }

    public Climate.Parameter getHumidity() {
        return humi;
    }
}
