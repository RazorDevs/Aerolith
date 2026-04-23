package io.github.razordevs.aerolith.mixin;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.terraformersmc.biolith.impl.data.SurfaceGenerationMarshaller;
import io.github.razordevs.aerolith.surface.AeroSurfaceRuleAPI;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SurfaceGenerationMarshaller.SurfaceRuleMarshaller.class)
public class SurfaceRuleMarshallerMixin {

    @Inject(method = "unmarshall", at = @At("HEAD"))
    public void unmarshall(CallbackInfo ci) {
        SurfaceGenerationMarshaller.SurfaceRuleMarshaller marshaller = (SurfaceGenerationMarshaller.SurfaceRuleMarshaller) (Object) this;
        var dimension = marshaller.dimension();
        var rulesOwner = marshaller.rulesOwner();
        var materialRules = marshaller.materialRules();

        if(dimension.equals(AetherDimensions.AETHER_DIMENSION_TYPE))
            AeroSurfaceRuleAPI.AETHER.addFromData(rulesOwner, materialRules.toArray(new SurfaceRules.RuleSource[0]));
    }
}
