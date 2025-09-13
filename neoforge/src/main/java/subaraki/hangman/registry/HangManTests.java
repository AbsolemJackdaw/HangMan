package subaraki.hangman.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.gametest.framework.GameTestHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import subaraki.hangman.mod.HangManCommon;
import subaraki.hangman.tests.HangmanTests;

import java.util.function.Consumer;

public class HangManTests {

    // Register our function for use
    //public static final DeferredRegister<Consumer<GameTestHelper>> TEST_REGISTRY = DeferredRegister.create(BuiltInRegistries.TEST_FUNCTION, HangManCommon.MODID);

    //public static final DeferredHolder<Consumer<GameTestHelper>, Consumer<GameTestHelper>> NOOSE_POLE = TEST_REGISTRY.register("noose_pole", () -> HangmanTests::noosePoleTest);
}
