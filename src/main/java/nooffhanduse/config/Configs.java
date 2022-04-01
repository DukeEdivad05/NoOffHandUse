package nooffhanduse.config;

import java.io.File;
import java.util.List;
import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.config.options.ConfigBoolean;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.util.FileUtils;
import fi.dy.masa.malilib.util.JsonUtils;
import nooffhanduse.Load;

public class Configs implements IConfigHandler{
    private static final String CONFIG_FILE_NAME = Load.MOD_ID + ".json";
    private static final int CONFIG_VERSION = 1;

    public static class Generic{
        public static final ConfigBoolean   MAIN_TOGGLE         = new ConfigBoolean("mainToggle", true, "Enable/disable the mod");
        public static final ConfigHotkey    OPEN_CONFIG_GUI     = new ConfigHotkey("openConfigGui", "N, H", "A hotkey to open the in-game Config GUI");
        public static final ConfigHotkey    MAIN_TOGGLE_KEYBIND = new ConfigHotkey("mainToggleKeybind", "", "A hotkey to enable/disable the mod");

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
                MAIN_TOGGLE,
                MAIN_TOGGLE_KEYBIND,
                OPEN_CONFIG_GUI
        );

        public static final List<ConfigHotkey> HOTKEY_LIST = ImmutableList.of(
                OPEN_CONFIG_GUI,
                MAIN_TOGGLE_KEYBIND
        );
    }

    public static class Toggle {
        public static final ConfigBoolean   SHIELDENABLE    = new ConfigBoolean("shieldEnable", false, "Enable/disable the functionality of shield");
        public static final ConfigBoolean   FOODENABLE      = new ConfigBoolean("foodEnable", false, "Enable/disable the offhand for eat");

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
                SHIELDENABLE,
                FOODENABLE
        );
    }

    public static class ToggleHotkey {
        public static final ConfigHotkey SHIELDENABLE_HOTKEY = new ConfigHotkey("shieldEnableKeybind", "", "A hotkey to enable/disable the functionality of shield");
        public static final ConfigHotkey FOODENABLE_HOTKEY = new ConfigHotkey("foodEnableKeybind", "", "A hotkey to enable/disable the offhand for eat");

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
                SHIELDENABLE_HOTKEY,
                FOODENABLE_HOTKEY
        );

        public static final List<ConfigHotkey> HOTKEY_LIST = ImmutableList.of(
                SHIELDENABLE_HOTKEY,
                FOODENABLE_HOTKEY
        );
    }

    public static void loadFromFile()
    {
        File configFile = new File(FileUtils.getConfigDirectory(), CONFIG_FILE_NAME);

        if (configFile.exists() && configFile.isFile() && configFile.canRead())
        {
            JsonElement element = JsonUtils.parseJsonFile(configFile);

            if (element != null && element.isJsonObject())
            {
                JsonObject root = element.getAsJsonObject();
                JsonObject objInfoLineOrders = JsonUtils.getNestedObject(root, "InfoLineOrders", false);

                ConfigUtils.readConfigBase(root, "Generic", Configs.Generic.OPTIONS);
                ConfigUtils.readConfigBase(root, "Toggle", Configs.Toggle.OPTIONS);
                ConfigUtils.readConfigBase(root, "ToggleHotkey", Configs.ToggleHotkey.OPTIONS);

                int version = JsonUtils.getIntegerOrDefault(root, "config_version", 0);
            }
        }
    }

    public static void saveToFile()
    {
        File dir = FileUtils.getConfigDirectory();

        if ((dir.exists() && dir.isDirectory()) || dir.mkdirs())
        {
            JsonObject root = new JsonObject();
            JsonObject objInfoLineOrders = JsonUtils.getNestedObject(root, "InfoLineOrders", true);

            ConfigUtils.writeConfigBase(root, "Generic", Configs.Generic.OPTIONS);
            ConfigUtils.writeConfigBase(root, "Toggle", Configs.Toggle.OPTIONS);
            ConfigUtils.readConfigBase(root, "ToggleHotkey", Configs.ToggleHotkey.OPTIONS);


            root.add("config_version", new JsonPrimitive(CONFIG_VERSION));

            JsonUtils.writeJsonToFile(root, new File(dir, CONFIG_FILE_NAME));
        }
    }

    @Override
    public void load()
    {
        loadFromFile();
    }

    @Override
    public void save()
    {
        saveToFile();
    }
}
