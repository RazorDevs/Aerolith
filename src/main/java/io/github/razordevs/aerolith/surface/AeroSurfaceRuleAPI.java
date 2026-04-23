package io.github.razordevs.aerolith.surface;

import com.terraformersmc.biolith.impl.surface.SurfaceRuleCollector;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.SurfaceRules;

public final class AeroSurfaceRuleAPI {
    public static final SurfaceRuleCollector AETHER = new SurfaceRuleCollector();

    private AeroSurfaceRuleAPI() {
        throw new UnsupportedOperationException();
    }

    /**
     * Add surface rules to The Aether.  Rules may optionally be pre-sequenced,
     * or Biolith will sequence rules together grouped by rulesOwner, prior to injection.
     * <p></p>
     *
     * @param rulesOwner    Rules will be grouped by rulesOwner during sequencing
     * @param materialRules The surface rules to be injected
     */
    public static void addSurfaceRules(ResourceLocation rulesOwner, SurfaceRules.RuleSource... materialRules) {
        AeroSurfaceRuleAPI.AETHER.addFromMods(rulesOwner, materialRules);
    }
}
