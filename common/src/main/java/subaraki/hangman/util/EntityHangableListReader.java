package subaraki.hangman.util;


import com.google.common.collect.Lists;
import com.google.gson.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.EntityType;
import subaraki.hangman.config.ConfigReader;
import subaraki.hangman.mod.HangManCommon;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class EntityHangableListReader extends SimplePreparableReloadListener<ArrayList<JsonObject>> {

    private static final HashMap<Identifier, EntityHangable> mappedEntities = new HashMap<>();

    public static EntityHangable get(EntityType<?> entityType) {

        Identifier resLoc = BuiltInRegistries.ENTITY_TYPE.getKey(entityType);
        if (mappedEntities.containsKey(resLoc))
            return mappedEntities.get(resLoc);

        return null;
    }

    public static boolean has(EntityType<?> entityType) {
        Identifier resLoc = BuiltInRegistries.ENTITY_TYPE.getKey(entityType);
        return mappedEntities.containsKey(resLoc);
    }

    @Override
    protected ArrayList<JsonObject> prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {

        ArrayList<JsonObject> theJsonFiles = Lists.newArrayList();

        Collection<Identifier> jsonfiles = resourceManager.listResources("noose_entities", (filename) -> filename.getPath().endsWith(".json")).keySet();

        List<Resource> jsons = new ArrayList<>();

        for (Identifier resLoc : jsonfiles) {
            jsons.addAll(resourceManager.getResourceStack(resLoc));
        }

        Gson gson = new GsonBuilder().create();

        for (Resource res : jsons) {
            try {
                BufferedReader reader = res.openAsReader(); ;
                JsonElement je = gson.fromJson(reader, JsonElement.class);
                JsonObject json = je.getAsJsonObject();

                if (json.has("hangable")) {
                    theJsonFiles.add(json);
                }
            } catch (IOException e) {
                HangManCommon.LOG.warn("************************************");
                HangManCommon.LOG.warn("!*!*!*!*!");
                HangManCommon.LOG.warn("resource {} couldn't be loaded", res);
                HangManCommon.LOG.warn(e.getMessage());
            }
        }
        return theJsonFiles;
    }

    @Override
    protected void apply(ArrayList<JsonObject> jsonFiles, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        ConfigReader.reloadServer();
        if (!jsonFiles.isEmpty()) {

            Runnable run = () -> {
                for (JsonObject the_file : jsonFiles) {
                    JsonArray array = the_file.getAsJsonArray("hangable");
                    for (int i = 0; i < array.size(); i++) {
                        JsonObject jsonObject = array.get(i).getAsJsonObject();

                        String entity = jsonObject.get("entity").getAsString();
                        double offset = 0.35d;
                        boolean dmg = true;
                        if (jsonObject.has("offset")) {
                            offset = jsonObject.get("offset").getAsDouble();
                        }
                        if (jsonObject.has("takesDamage")) {
                            dmg = jsonObject.get("takesDamage").getAsBoolean();
                        }
                        mappedEntities.put(Identifier.parse(entity), new EntityHangable(entity, offset, dmg));
                    }
                }
            };
            run.run();
        }
    }
}
