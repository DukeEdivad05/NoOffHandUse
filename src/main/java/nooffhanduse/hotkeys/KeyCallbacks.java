package nooffhanduse.hotkeys;

import fi.dy.masa.malilib.hotkeys.*;
import net.minecraft.client.MinecraftClient;
import fi.dy.masa.malilib.gui.GuiBase;
import nooffhanduse.config.Configs;
import nooffhanduse.gui.GuiConfigs;

public class KeyCallbacks {

    public static void init() {
        Callbacks callback = new Callbacks();

        Configs.Generic.OPEN_CONFIG_GUI.getKeybind().setCallback(callback);
        Configs.Generic.MAIN_TOGGLE_KEYBIND.getKeybind().setCallback(new KeyCallbackToggleBooleanConfigWithMessage(Configs.Generic.MAIN_TOGGLE));
        Configs.ToggleHotkey.SHIELDENABLE_HOTKEY.getKeybind().setCallback(new KeyCallbackToggleBooleanConfigWithMessage(Configs.Toggle.SHIELDENABLE));
        Configs.ToggleHotkey.FOODENABLE_HOTKEY.getKeybind().setCallback(new KeyCallbackToggleBooleanConfigWithMessage(Configs.Toggle.FOODENABLE));
    }

    public static class Callbacks implements IHotkeyCallback
    {
        @Override
        public boolean onKeyAction(KeyAction action, IKeybind key)
        {
            MinecraftClient mc = MinecraftClient.getInstance();

            if (mc.player == null)
            {
                return false;
            }

            if (key == Configs.Generic.OPEN_CONFIG_GUI.getKeybind())
            {
                GuiBase.openGui(new GuiConfigs());
            }

            return true;
        }
    }
}
