package io.github.razordevs.aerolith;

import com.mojang.logging.LogUtils;
import io.github.razordevs.aerolith.biome.BiomePlacementHelper;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod("aerolith")
public class Aerolith {
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final String MODID = "aerolith";
    public static final String MOD_VERSION = "0.0.1";

    public Aerolith() {
        LOGGER.info("Aerolith Initialized");

        try {
            BiomePlacementHelper.addAether(Biomes.CRIMSON_FOREST,
                    new Climate.ParameterPoint(
                            Climate.Parameter.span(-1.0f, -0.15f),
                            Climate.Parameter.span(-1.0f, -0.35f),
                            Climate.Parameter.span(0.3f, 1.0f),
                            Climate.Parameter.span(-0.375f, 0.05f),
                            Climate.Parameter.point(0.0f),
                            Climate.Parameter.span(0.0f, 1.0f),
                            0L));
            LOGGER.info("Successfully added Crimson Forest biome to Aether");
        } catch (Exception e) {
            LOGGER.error("Failed to add Crimson Forest biome to Aether", e);
        }
    }

}
