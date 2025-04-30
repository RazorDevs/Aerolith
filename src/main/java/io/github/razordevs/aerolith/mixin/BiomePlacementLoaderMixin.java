package io.github.razordevs.aerolith.mixin;

import com.terraformersmc.biolith.impl.data.BiomePlacementLoader;
import com.terraformersmc.biolith.impl.data.BiomePlacementMarshaller;
import io.github.razordevs.aerolith.biome.BiomePlacementHelper;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = BiomePlacementLoader.class, remap = false)
public class BiomePlacementLoaderMixin {

    @Inject(method = "apply(Ljava/util/List;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V",
            at = @At(value = "INVOKE", target = "Lcom/terraformersmc/biolith/impl/biome/EndBiomePlacement;clearFromData()V", shift = At.Shift.AFTER))

    public void aerolith$onApply(List<BiomePlacementMarshaller> marshallers, ResourceManager manager, ProfilerFiller profiler, CallbackInfo ci) {
        BiomePlacementHelper.AETHER.clearFromData();
    }
}
