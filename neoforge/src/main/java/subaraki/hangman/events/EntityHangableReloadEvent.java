package subaraki.hangman.events;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddServerReloadListenersEvent;
import subaraki.hangman.mod.HangManCommon;
import subaraki.hangman.util.EntityHangable;
import subaraki.hangman.util.EntityHangableListReader;

import java.util.HashMap;

@EventBusSubscriber(modid = HangManCommon.MODID, bus = EventBusSubscriber.Bus.GAME)
public class EntityHangableReloadEvent {

    private static final HashMap<ResourceLocation, EntityHangable> mappedEntities = new HashMap<>();
    private static final ResourceLocation KEY = ResourceLocation.fromNamespaceAndPath(HangManCommon.MODID, HangManCommon.LISTENER_STR);

    @SubscribeEvent
    public static void registerReloadListener(AddServerReloadListenersEvent event) {
        event.addListener(KEY, new EntityHangableListReader());
    }
}
