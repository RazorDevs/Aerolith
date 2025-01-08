package io.github.razordevs.aerolith.mixin.accessor;

import com.terraformersmc.biolith.impl.biome.DimensionBiomePlacement;
import com.terraformersmc.biolith.impl.config.BiolithState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(DimensionBiomePlacement.class)
public interface DimensionBiomePlacementAccessor {
    @Invoker("serverReplaced")
    void invokeServerReplaced(BiolithState state, long seed);

    @Invoker("serverStopped")
    void invokeServerStopped();
}
