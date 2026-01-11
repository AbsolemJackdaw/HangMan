package subaraki.hangman.tests;

import net.minecraft.gametest.framework.GameTestAssertException;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import subaraki.hangman.entity.NooseEntity;

public class HangmanTests {

    public static void noosePoleTest(GameTestHelper helper) {
        var hanged = helper.getEntities(EntityType.SKELETON);
        byte sit = 0;
        byte stand = 0;
        if (hanged.size() == 2) {
            for (Skeleton skel : hanged) {
                if (!(skel.getVehicle() instanceof NooseEntity))
                    throw new GameTestAssertException(Component.literal("entity should be on noose"), 0);
                if (skel.hasPose(Pose.SITTING)) sit++;
                if (skel.hasPose(Pose.STANDING)) stand++;
            }
            if (sit != 1 && stand != 1)
                throw new GameTestAssertException(Component.literal("entities arent receiving the correct pose when hanged"), 0);

        } else throw new GameTestAssertException(Component.literal("not enough entities present to test nooses"), 0);
    }
}
