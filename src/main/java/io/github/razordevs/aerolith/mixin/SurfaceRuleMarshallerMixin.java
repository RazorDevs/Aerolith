package io.github.razordevs.aerolith.mixin;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.llamalad7.mixinextras.sugar.Local;
import com.terraformersmc.biolith.impl.data.SurfaceGenerationMarshaller;
import io.github.razordevs.aerolith.surface.AetherSurfaceGeneration;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(SurfaceGenerationMarshaller.SurfaceRuleMarshaller.class)
public class SurfaceRuleMarshallerMixin {

    @Inject(method = "unmarshall", at = @At("HEAD"), locals = LocalCapture.CAPTURE_FAILSOFT)
    public void unmarshall(CallbackInfo ci, ResourceKey<DimensionType> dimension, ResourceLocation rulesOwner, List<SurfaceRules.RuleSource> materialRules) {
        if(dimension.equals(AetherDimensions.AETHER_DIMENSION_TYPE))
            AetherSurfaceGeneration.AETHER.addFromData(rulesOwner, materialRules.toArray(new SurfaceRules.RuleSource[0]));
    }
}
