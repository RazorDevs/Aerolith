package io.github.razordevs.aerolith.mixin;

import com.terraformersmc.biolith.impl.data.BiomePlacementLoader;
import io.github.razordevs.aerolith.biome.BiomePlacementHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BiomePlacementLoader.class)
public class BiomePlacementLoaderMixin {

    @Inject(method = "apply*", at = @At(value = "INVOKE", target = "Lcom/terraformersmc/biolith/impl/biome/EndBiomePlacement;clearFromData()V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    public void apply(CallbackInfo ci) {
        BiomePlacementHelper.AETHER.clearFromData();
    }
}
