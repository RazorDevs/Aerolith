package io.github.razordevs.aerolith;

import com.mojang.logging.LogUtils;
import io.github.razordevs.aerolith.biome.AetherBiomeCoordinator;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;
import org.slf4j.Logger;

@Mod("aerolith")
public class Aerolith {
    public static final Logger LOGGER = LogUtils.getLogger();
    private static final AerolithConfigManager CONFIG_MANAGER = new AerolithConfigManager();

    public static final String MODID = "aerolith";
    public static final String MOD_VERSION = "0.0.1";

    public Aerolith() {
        LOGGER.info("Aerolith Initialized");

        NeoForge.EVENT_BUS.addListener((ServerStoppedEvent event) -> AetherBiomeCoordinator.handleServerStopped());

        CONFIG_MANAGER.getAerolithConfig();
    }

    public static AerolithConfigManager getConfigManager() {
        return CONFIG_MANAGER;
    }
}
