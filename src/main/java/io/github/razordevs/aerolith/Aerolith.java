package io.github.razordevs.aerolith;

import com.aetherteam.aether.data.resources.builders.AetherBiomeBuilders;
import com.aetherteam.aether.data.resources.registries.AetherBiomes;
import com.mojang.logging.LogUtils;
import com.terraformersmc.biolith.impl.config.BiolithConfigManager;
import io.github.razordevs.aerolith.biome.BiomePlacementHelper;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod("aerolith")
public class Aerolith {
    public static final Logger LOGGER = LogUtils.getLogger();
    private static final AerolithConfigManager CONFIG_MANAGER = new AerolithConfigManager();

    public static final String MODID = "aerolith";
    public static final String MOD_VERSION = "0.0.1";

    public Aerolith() {
        LOGGER.info("Aerolith Initialized");
        CONFIG_MANAGER.getAerolithConfig();
    }

    public static AerolithConfigManager getConfigManager() {
        return CONFIG_MANAGER;
    }
}
