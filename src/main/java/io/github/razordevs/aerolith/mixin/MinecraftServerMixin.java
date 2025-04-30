package io.github.razordevs.aerolith.mixin;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.google.common.collect.Streams;
import com.llamalad7.mixinextras.sugar.Local;
import com.terraformersmc.biolith.impl.mixin.MixinChunkGeneratorSettings;
import com.terraformersmc.biolith.impl.surface.SurfaceRuleCollector;
import io.github.razordevs.aerolith.surface.AetherSurfaceGeneration;
import net.minecraft.commands.arguments.DimensionArgument;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.progress.ChunkProgressListener;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.ChunkGenerators;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import net.minecraft.world.level.dimension.DimensionDefaults;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

    @Shadow
    @Final
    private Map<ResourceKey<Level>, ServerLevel> levels;

    @Inject(method = "createLevels", at = @At(value = "RETURN"))
        private void aerolith$prependSurfaceRules(ChunkProgressListener worldGenerationProgressListener, CallbackInfo ci, @Local Registry<LevelStem> dimensionOptionsRegistry) {
        SurfaceRules.RuleSource[] rulesType = new SurfaceRules.RuleSource[0];

        for (Level world : levels.values()) {
            LevelStem dimensionOptions = null;
            SurfaceRuleCollector surfaceRuleCollector = null;
            ResourceKey<DimensionType> dimensionKey = world.dimensionTypeRegistration().getKey();

            if (dimensionKey != null) {
                if (AetherDimensions.AETHER_DIMENSION_TYPE.equals(dimensionKey)) {
                    dimensionOptions = dimensionOptionsRegistry.get(AetherDimensions.AETHER_DIMENSION_TYPE.location());
                    surfaceRuleCollector = AetherSurfaceGeneration.AETHER;
                }
            }

            if (dimensionOptions != null && surfaceRuleCollector.getRuleCount() > 0) {
                ChunkGenerator chunkGenerator = dimensionOptions.generator();
                if (chunkGenerator instanceof NoiseBasedChunkGenerator noiseChunkGenerator) {
                    NoiseGeneratorSettings chunkGeneratorSettings = noiseChunkGenerator.generatorSettings().value();

                    ((MixinChunkGeneratorSettings)(Object) chunkGeneratorSettings).biolith$setSurfaceRule(
                            SurfaceRules.sequence(Streams.concat(
                                            Arrays.stream(surfaceRuleCollector.getAll()),
                                            Stream.of(chunkGeneratorSettings.surfaceRule()))
                                    .toList().toArray(rulesType)));
                }
            }
        }
    }
}
