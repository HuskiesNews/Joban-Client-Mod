package com.jsblock.config;

import com.google.gson.*;
import com.jsblock.Joestu;
import mtr.MTR;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ClientConfig {
    private static final String CONFIG_PATH = System.getProperty("user.dir") + "/config/" + "jsclient.json";
    private static boolean renderDisabled = true;
    private static String RVPIDSChinFont = MTR.MOD_ID + ":" + "mtr";
    private static String RVPIDSEngFont = MTR.MOD_ID + ":" + "mtr";
    private static String KCRSignChinFont = Joestu.MOD_ID + ":" + "kcr_chin";
    private static String KCRSignEngFont = Joestu.MOD_ID + ":" + "kcr_eng";

    public static void loadConfig() {
        if(!Files.exists(Paths.get(CONFIG_PATH))) {
            Joestu.LOGGER.warn("[Joestu Client] Config file not found, generating one...");
            writeConfig();
            return;
        }

        Joestu.LOGGER.info("[Joestu Client] Reading Config...");
        try {
            final JsonObject jsonConfig = new JsonParser().parse(String.join("", Files.readAllLines(Paths.get(CONFIG_PATH)))).getAsJsonObject();

            if(jsonConfig.has("renderDisabled")) {
                renderDisabled = jsonConfig.get("renderDisabled").getAsBoolean();
            }

            if(jsonConfig.has("RVPIDSChinFont")) {
                RVPIDSChinFont = jsonConfig.get("RVPIDSChinFont").getAsString();
            }

            if(jsonConfig.has("RVPIDSEngFont")) {
                RVPIDSEngFont = jsonConfig.get("RVPIDSEngFont").getAsString();
            }

            if(jsonConfig.has("KCRSignChinFont")) {
                KCRSignChinFont = jsonConfig.get("KCRSignChinFont").getAsString();
            }

            if(jsonConfig.has("KCRSignEngFont")) {
                KCRSignEngFont = jsonConfig.get("KCRSignEngFont").getAsString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            writeConfig();
        }
    }

    public static void writeConfig() {
        Joestu.LOGGER.info("[Joestu Client] Writing Config...");
        final JsonObject jsonConfig = new JsonObject();
        jsonConfig.addProperty("renderDisabled", renderDisabled);
        jsonConfig.addProperty("RVPIDSChinFont", RVPIDSChinFont == null ? "" : RVPIDSChinFont);
        jsonConfig.addProperty("RVPIDSEngFont", RVPIDSEngFont == null ? "" : RVPIDSEngFont);
        jsonConfig.addProperty("KCRSignChinFont", KCRSignChinFont);
        jsonConfig.addProperty("KCRSignEngFont", KCRSignEngFont);

        try {
            Files.write(Paths.get(CONFIG_PATH), Collections.singleton(new GsonBuilder().setPrettyPrinting().create().toJson(jsonConfig)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean getRenderDisabled() {
        return renderDisabled;
    }

    public static String getRVPIDSChinFont() {
        return RVPIDSChinFont;
    }

    public static String getRVPIDSEngFont() {
        return RVPIDSEngFont;
    }

    public static String getKCRSignChinFont() {
        return KCRSignChinFont;
    }

    public static String getKCRSignEngFont() {
        return KCRSignEngFont;
    }

    public static boolean setRenderDisabled(boolean disabled) {
        renderDisabled = disabled;
        return renderDisabled;
    }

    public static void setRVPIDSChinFont(String id) {
        RVPIDSChinFont = id;
    }

    public static void setRVPIDSEngFont(String id) {
        RVPIDSEngFont = id;
    }

    public static void setKCRSignChinFont(String id) {
        KCRSignChinFont = id;
    }

    public static void setKCRSignEngFont(String id) {
        KCRSignEngFont = id;
    }
}
