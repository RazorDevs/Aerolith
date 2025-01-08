package io.github.razordevs.aerolith.surface;

import com.terraformersmc.biolith.impl.surface.SurfaceRuleCollector;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.SurfaceRules;

public final class AetherSurfaceGeneration {
    public static final SurfaceRuleCollector AETHER = new SurfaceRuleCollector();

    private AetherSurfaceGeneration() {
        throw new UnsupportedOperationException();
    }

    /**
     * Add surface rules to The Aether.  Rules may optionally be pre-sequenced,
     * or Biolith will sequence rules together grouped by rulesOwner, prior to injection.
     * <p></p>
     * For TerraBlender compatibility, it is important the rulesOwner's namespace
     * should be the identical to the namespace of all biomes to which the rules apply.
     *
     * @param rulesOwner    Rules will be grouped by rulesOwner during sequencing
     * @param materialRules The surface rules to be injected
     */
    public static void addAetherSurfaceRules(ResourceLocation rulesOwner, SurfaceRules.RuleSource... materialRules) {
        AETHER.addFromMods(rulesOwner, materialRules);
    }
}
