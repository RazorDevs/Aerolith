package io.github.razordevs.aerolith;

import com.aetherteam.aether.data.resources.builders.AetherBiomeBuilders;
import com.aetherteam.aether.data.resources.registries.AetherBiomes;
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

        BiomePlacementHelper.addAether(Biomes.BADLANDS,
                new Climate.ParameterPoint(
                        Climate.Parameter.span(-1.0f, -0.15f),
                        Climate.Parameter.span(-1.0f, -0.35f),
                        Climate.Parameter.span(0.3f, 1.0f),
                        Climate.Parameter.span(-0.375f, 0.05f),
                        Climate.Parameter.point(0.0f),
                        Climate.Parameter.span(0.0f, 1.0f),
                        0L));

        BiomePlacementHelper.replaceAether(AetherBiomes.SKYROOT_FOREST, Biomes.PLAINS);
    }

}
