package io.github.razordevs.aerolith.mixin;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.llamalad7.mixinextras.sugar.Local;
import com.terraformersmc.biolith.impl.surface.SurfaceRuleCollector;
import io.github.razordevs.aerolith.surface.AetherSurfaceGeneration;
import net.minecraft.commands.arguments.DimensionArgument;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.progress.ChunkProgressListener;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import net.minecraft.world.level.dimension.DimensionDefaults;

import java.util.Map;
import java.util.Optional;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

    ChunkGenerator
    @Shadow
    @Final
    private Map<ResourceKey<Level>, ServerLevel> levels;

    @Inject(method = "createLevels", at = @At(value = "RETURN"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void aerolith$prependSurfaceRules(ChunkProgressListener worldGenerationProgressListener, CallbackInfo ci, @Local Registry<DimensionArgument> dimensionOptionsRegistry) {
        for (Level world : levels.values()) {
            DimensionDefaults dimensionOptions = null;
            SurfaceRuleCollector surfaceRuleCollector = null;
            ResourceKey<DimensionType> dimensionKey = world.dimensionTypeRegistration().getKey();

            if (dimensionKey != null) {
                if (AetherDimensions.AETHER_DIMENSION_TYPE.equals(dimensionKey)) {
                    dimensionOptions = dimensionOptionsRegistry.get(AetherDimensions.);
                    surfaceRuleCollector = AetherSurfaceGeneration.AETHER;
                }
            }
        }
    }
}
