package nooffhanduse;

import fi.dy.masa.malilib.event.InitializationHandler;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Load implements ModInitializer{
    public static final Logger LOGGER = LogManager.getLogger("NoOffHandUse");
    public static final String MOD_ID = "nooffhanduse";
    public static final String MOD_NAME = "NoOffHandUse";

    @Override
    public void onInitialize() {
        LOGGER.info("NoOffHandUse initialized");
        InitializationHandler.getInstance().registerInitializationHandler(new InitHandler());
    }
}
