package subaraki.hangman.events;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import subaraki.hangman.mod.HangManCommon;
import subaraki.hangman.util.EntityHangable;
import subaraki.hangman.util.EntityHangableListReader;

import java.util.HashMap;

@EventBusSubscriber(modid = HangManCommon.MODID, bus = EventBusSubscriber.Bus.MOD)
public class EntityHangableReloadEvent {

    private static final HashMap<ResourceLocation, EntityHangable> mappedEntities = new HashMap<>();

    @SubscribeEvent
    public static void registerReloadListener(AddReloadListenerEvent event) {
        event.addListener(new EntityHangableListReader());
    }
}
