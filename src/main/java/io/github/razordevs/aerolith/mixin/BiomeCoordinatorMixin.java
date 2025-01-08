package io.github.razordevs.aerolith.mixin;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.llamalad7.mixinextras.sugar.Local;
import com.terraformersmc.biolith.impl.Biolith;
import com.terraformersmc.biolith.impl.biome.BiomeCoordinator;
import com.terraformersmc.biolith.impl.config.BiolithState;
import io.github.razordevs.aerolith.biome.AetherBiomePlacement;
import io.github.razordevs.aerolith.mixin.accessor.DimensionBiomePlacementAccessor;
import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(BiomeCoordinator.class)
public class BiomeCoordinatorMixin {

    @Final
    @Unique
    private static final AetherBiomePlacement AETHER = new AetherBiomePlacement();

    @Unique
    private static BiolithState AETHER_STATE;

    @Inject(method = "handleWorldStarting", at = @At(value = "INVOKE", target = "Ljava/util/Optional;isPresent()Z", shift = At.Shift.AFTER))
    private static void handleWorldStarting(CallbackInfo ci, @Local Optional<?> dimensionKey, @Local ServerLevel world) {
        if(AetherDimensions.AETHER_DIMENSION_TYPE.equals(dimensionKey.get())){
            if(AETHER_STATE == null){
                AETHER_STATE = new BiolithState(world, "aether");
                ((DimensionBiomePlacementAccessor) AETHER).invokeServerReplaced(AETHER_STATE, world.getSeed());
            }else{
                Biolith.LOGGER.warn("More than one Aether dimension world created; cowardly ignoring '{}' in favor of '{}'", world.getDescriptionKey(), AETHER_STATE.getWorldId());
            }
        }
    }

    @Inject(method = "handleServerStopped", at = @At("TAIL"))
    private static void handleServerStopped(CallbackInfo ci) {
        AETHER_STATE = null;
        ((DimensionBiomePlacementAccessor) AETHER).invokeServerStopped();
    }

    @Unique
    private static AetherBiomePlacement getAetherPlacement() {
        return AETHER;
    }
}
