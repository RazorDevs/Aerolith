package io.github.razordevs.aerolith;

import com.aetherteam.aether.data.resources.registries.AetherBiomes;
import com.mojang.logging.LogUtils;
import com.terraformersmc.biolith.api.biome.BiomePlacement;
import io.github.razordevs.aerolith.biome.AetherBiomeCoordinator;
import io.github.razordevs.aerolith.biome.AetherBiomePlacement;
import io.github.razordevs.aerolith.biome.BiomePlacementHelper;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;
import org.slf4j.Logger;

@Mod("aerolith")
public class Aerolith {
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final String MODID = "aerolith";
    public static final String MOD_VERSION = "0.0.1";

    public Aerolith(ModContainer mod) {
        LOGGER.info("Aerolith Initialized");

        NeoForge.EVENT_BUS.addListener((ServerStoppedEvent event) -> AetherBiomeCoordinator.handleServerStopped());

        mod.registerConfig(ModConfig.Type.COMMON, AerolithConfig.COMMON_SPEC);
    }
}
