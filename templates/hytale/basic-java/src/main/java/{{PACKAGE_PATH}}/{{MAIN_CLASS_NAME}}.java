package {{PACKAGE_NAME}};

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.logger.HytaleLogger;

import java.util.logging.Level;

public class {{MAIN_CLASS_NAME}} extends JavaPlugin {

    private static final HytaleLogger LOGGER =
            HytaleLogger.forEnclosingClass();

    public {{MAIN_CLASS_NAME}}(JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        LOGGER.at(Level.INFO).log("[Template] Setting up...");
        LOGGER.at(Level.INFO).log("[Template] Setup complete!");
    }

    @Override
    protected void start() {
        LOGGER.at(Level.INFO).log("[Template] Started!");
    }

    @Override
    protected void shutdown() {
        LOGGER.at(Level.INFO).log("[Template] Shutting down...");
    }
}