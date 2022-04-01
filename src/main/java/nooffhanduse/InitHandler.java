package nooffhanduse;

import fi.dy.masa.malilib.config.ConfigManager;
import fi.dy.masa.malilib.event.InputEventHandler;
import fi.dy.masa.malilib.interfaces.IInitializationHandler;
import nooffhanduse.config.Configs;
import nooffhanduse.event.InputHandler;
import nooffhanduse.hotkeys.KeyCallbacks;

public class InitHandler implements IInitializationHandler {

    @Override
    public void registerModHandlers()
    {
        ConfigManager.getInstance().registerConfigHandler(Load.MOD_ID, new Configs());
        InputEventHandler.getKeybindManager().registerKeybindProvider(InputHandler.getInstance());
        InputEventHandler.getInputManager().registerMouseInputHandler(InputHandler.getInstance());

        KeyCallbacks.init();
    }
}
