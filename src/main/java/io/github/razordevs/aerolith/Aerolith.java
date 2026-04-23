package io.github.razordevs.aerolith;

import com.mojang.logging.LogUtils;
import io.github.razordevs.aerolith.biome.AeroBiomeAPI;
import io.github.razordevs.aerolith.biome.AetherBiomeCoordinator;
import io.github.razordevs.aerolith.enums.AeroClimate;
import io.github.razordevs.aerolith.enums.AeroIslandPos;
import io.github.razordevs.aerolith.enums.AeroLayer;
import io.github.razordevs.aerolith.enums.AeroTerrainShape;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;
import org.slf4j.Logger;

// Could this whole thing be done better? Yes.

@Mod(Aerolith.MODID)
public class Aerolith {
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final String MODID = "aerolith";

    public Aerolith(ModContainer mod, IEventBus bus) {
        NeoForge.EVENT_BUS.addListener((ServerStoppedEvent event) -> AetherBiomeCoordinator.handleServerStopped());
        mod.registerConfig(ModConfig.Type.COMMON, AerolithConfig.COMMON_SPEC);

        bus.addListener(this::commonSetup);
        LOGGER.info("Aerolith Initialized");
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            AeroBiomeAPI.addBiome(Biomes.DESERT)
                    .setIslandPos(AeroIslandPos.EDGE)
                    .setTerrainShape(AeroTerrainShape.HILLY)
                    .setClimate(AeroClimate.COLD)
                    .setLayer(AeroLayer.SKY)
                    .build();
        });
    }
}
