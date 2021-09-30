package subaraki.hangman.util;

import com.google.common.collect.Lists;
import com.google.gson.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import subaraki.hangman.mod.HangMan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Mod.EventBusSubscriber(modid = HangMan.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EntityExceptionListReader extends SimplePreparableReloadListener<ArrayList<JsonObject>> {

    private static final HashMap<ResourceLocation, EntityHangException> mappedEntities = new HashMap<>();

    @SubscribeEvent
    public static void registerReloadListener(AddReloadListenerEvent event) {
        event.addListener(new EntityExceptionListReader());
    }

    public static EntityHangException get(EntityType<?> entityType) {

        ResourceLocation resLoc = Registry.ENTITY_TYPE.getKey(entityType);
        if (mappedEntities.containsKey(resLoc))
            return mappedEntities.get(resLoc);

        return null;
    }

    public static boolean has(EntityType<?> entityType) {
        ResourceLocation resLoc = Registry.ENTITY_TYPE.getKey(entityType);
        return mappedEntities.containsKey(resLoc);
    }

    @Override
    protected ArrayList<JsonObject> prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {

        ArrayList<JsonObject> theJsonFiles = Lists.newArrayList();

        Collection<ResourceLocation> jsonfiles = resourceManager.listResources("noose_entities", (filename) -> filename.endsWith(".json"));

        List<Resource> jsons = new ArrayList<>();

        for (ResourceLocation resLoc : jsonfiles) {
            try {
                jsons.addAll(resourceManager.getResources(resLoc));
            } catch (IOException e) {
                HangMan.LOG.warn("************************************");
                HangMan.LOG.warn("!*!*!*!*!");
                HangMan.LOG.warn("resource {} couldn't be loaded", resLoc);
                HangMan.LOG.warn(e.getMessage());
            }
        }

        Gson gson = new GsonBuilder().create();

        for (Resource res : jsons) {
            InputStream stream = res.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            JsonElement je = gson.fromJson(reader, JsonElement.class);
            JsonObject json = je.getAsJsonObject();

            if (json.has("hangable")) {
                theJsonFiles.add(json);
            }
        }
        return theJsonFiles;
    }

    @Override
    protected void apply(ArrayList<JsonObject> jsonFiles, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        if (!jsonFiles.isEmpty()) {

            Runnable run = () -> {
                for (JsonObject jsonFile : jsonFiles) {
                    JsonArray array = jsonFile.getAsJsonArray("hangable");
                    for (int i = 0; i < array.size(); i++) {
                        JsonObject jsonObject = array.get(i).getAsJsonObject();

                        String entity = jsonObject.get("entity").getAsString();
                        double offset = 0.35d;
                        if (jsonObject.has("offset")) {
                            offset = jsonObject.get("offset").getAsDouble();
                        }

                        mappedEntities.put(new ResourceLocation(entity), new EntityHangException(entity, offset));
                    }
                }
            };
            run.run();
        }
    }


}
