package com.jsblock.config;

import com.google.gson.*;
import com.jsblock.Joestu;
import mtr.MTR;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ClientConfig {
    private static final String CONFIG_PATH = System.getProperty("user.dir") + "/config/" + "jsclient.json";
    private static boolean renderDisabled = false;
    private static String RVPIDSChinFont = MTR.MOD_ID + ":" + "mtr";
    private static String RVPIDSEngFont = MTR.MOD_ID + ":" + "mtr";
    private static String PIDS4ChinFont = Joestu.MOD_ID + ":" + "pids_4";
    private static String PIDS4EngFont = Joestu.MOD_ID + ":" + "pids_4";
    private static String KCRSignChinFont = Joestu.MOD_ID + ":" + "kcr_sign";
    private static String KCRSignEngFont = Joestu.MOD_ID + ":" + "kcr_sign";
    private static String depTimerFont = Joestu.MOD_ID + ":" + "deptimer";

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

            if(jsonConfig.has("PIDS4ChinFont")) {
                PIDS4ChinFont = jsonConfig.get("PIDS4ChinFont").getAsString();
            }

            if(jsonConfig.has("PIDS4EngFont")) {
                PIDS4EngFont = jsonConfig.get("PIDS4EngFont").getAsString();
            }

            if(jsonConfig.has("DepTimerFont")) {
                depTimerFont = jsonConfig.get("depTimerFont").getAsString();
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
        jsonConfig.addProperty("PIDS4ChinFont", PIDS4ChinFont);
        jsonConfig.addProperty("PIDS4EngFont", PIDS4EngFont);
        jsonConfig.addProperty("depTimerFont", depTimerFont);

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

    public static String getPIDS4ChinFont() {
        return PIDS4ChinFont;
    }

    public static String getPIDS4EngFont() {
        return PIDS4EngFont;
    }

    public static String getKCRSignChinFont() {
        return KCRSignChinFont;
    }

    public static String getKCRSignEngFont() {
        return KCRSignEngFont;
    }

    public static String getDepTimerFont() {
        return depTimerFont;
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

    public static void setPIDS4ChinFont(String id) {
        PIDS4ChinFont = id;
    }

    public static void setPIDS4EngFont(String id) {
        PIDS4EngFont = id;
    }

    public static void setKCRSignChinFont(String id) {
        KCRSignChinFont = id;
    }

    public static void setKCRSignEngFont(String id) {
        KCRSignEngFont = id;
    }

    public static void setDepTimerFont(String id) {
        depTimerFont = id;
    }
}
