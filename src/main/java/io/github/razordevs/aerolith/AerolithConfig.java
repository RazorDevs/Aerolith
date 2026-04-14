package io.github.razordevs.aerolith;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class AerolithConfig {
    private static final Pair<Common, ModConfigSpec> commonSpecPair = new ModConfigSpec.Builder().configure(Common::new);
    public static final ModConfigSpec COMMON_SPEC = commonSpecPair.getRight();
    public static final Common COMMON = commonSpecPair.getLeft();

    public static class Common {
        public final ModConfigSpec.ConfigValue<Integer> aetherReplacementScale;

        public Common(ModConfigSpec.Builder builder) {
            builder.push("World");
            aetherReplacementScale = builder
                    .comment("Handles biome replacement sizes in the Aether.")
                    .translation("config.aerolith.common.aetherReplacementScale")
                    .define("Aether Replacement Scale", 3);
            builder.pop();
        }
    }
}
