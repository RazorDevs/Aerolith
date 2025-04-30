package io.github.razordevs.aerolith.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.terraformersmc.biolith.impl.biome.BiomeCoordinator;
import io.github.razordevs.aerolith.biome.AetherBiomeCoordinator;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.chunk.ChunkGeneratorStructureState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerLevel.class)
public class ServerLevelMixin {

    @WrapOperation(method = "<init>", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/level/ServerChunkCache;getGeneratorState()Lnet/minecraft/world/level/chunk/ChunkGeneratorStructureState;"
    ))
    @SuppressWarnings("unused")
    private ChunkGeneratorStructureState aerolith$serverWorldStarting(ServerChunkCache instance, Operation<ChunkGeneratorStructureState> original) {
        AetherBiomeCoordinator.handleWorldStarting((ServerLevel)(Object) this);
        return original.call(instance);
    }
}
