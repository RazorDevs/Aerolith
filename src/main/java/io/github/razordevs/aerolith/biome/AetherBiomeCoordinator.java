package io.github.razordevs.aerolith.biome;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.terraformersmc.biolith.impl.biome.BiomeCoordinator;
import com.terraformersmc.biolith.impl.config.BiolithState;
import io.github.razordevs.aerolith.Aerolith;
import io.github.razordevs.aerolith.mixin.accessor.DimensionBiomePlacementAccessor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.dimension.DimensionType;

import java.util.Optional;

public class AetherBiomeCoordinator extends BiomeCoordinator {
    public static final AetherBiomePlacement AETHER = new AetherBiomePlacement();

    private static BiolithState AETHER_STATE;

    public static void handleWorldStarting(ServerLevel world) {
        Optional<ResourceKey<DimensionType>> dimensionKey = world.dimensionTypeRegistration().unwrapKey();

        if (!isServerStarted()) {
            Aerolith.LOGGER.error("New world '{}' created when server is not running!", world.dimension().location());
        }

        if (dimensionKey.isPresent()) {
            if (AetherDimensions.AETHER_DIMENSION_TYPE.equals(dimensionKey.get())) {
                if (AETHER_STATE == null) {
                    AETHER_STATE = new BiolithState(world, "aether");
                    ((DimensionBiomePlacementAccessor) AETHER).invokeServerReplaced(AETHER_STATE, world);
                } else {
                    Aerolith.LOGGER.warn("More than one Aether dimension world created; cowardly ignoring '{}' in favor of '{}'", world.dimension().location(), AETHER_STATE.getWorldId());
                }
            } else {
                Aerolith.LOGGER.info("Ignoring world '{}'; unknown dimension type: {}", world.dimension().location(), dimensionKey.get().location());
            }
        } else {
            Aerolith.LOGGER.info("Ignoring world '{}'; world has no associated dimension", world.dimension().location());
        }
    }

    public static void handleServerStopped() {
        registryManager = null;

        AETHER_STATE = null;
        ((DimensionBiomePlacementAccessor) AETHER).invokeServerStopped();
    }
}
