package nooffhanduse;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Load implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("NoOffHandUse");

    @Override
    public void onInitialize() {
        LOGGER.info("NoOffHandUse initialized");
    }
}
