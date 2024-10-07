package subaraki.hangman.events;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import subaraki.hangman.mod.HangManCommon;
import subaraki.hangman.util.EntityHangable;
import subaraki.hangman.util.EntityHangableListReader;

import java.util.HashMap;

@Mod.EventBusSubscriber(modid = HangManCommon.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EntityHangableReloadEvent {

    private static final HashMap<ResourceLocation, EntityHangable> mappedEntities = new HashMap<>();

    @SubscribeEvent
    public static void registerReloadListener(AddReloadListenerEvent event) {
        event.addListener(new EntityHangableListReader());
    }
}
