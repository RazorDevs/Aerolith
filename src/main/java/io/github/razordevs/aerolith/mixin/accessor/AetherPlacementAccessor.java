package io.github.razordevs.aerolith.mixin.accessor;

import io.github.razordevs.aerolith.biome.AetherBiomePlacement;
import io.github.razordevs.aerolith.mixin.BiomeCoordinatorMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(targets = "io.github.razordevs.aerolith.mixin.BiomeCoordinatorMixin")
public interface AetherPlacementAccessor {
    @Invoker
    static AetherBiomePlacement invokeGetAetherPlacement() {
        throw new AssertionError();
    }
}
