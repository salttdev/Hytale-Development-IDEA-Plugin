package {{PACKAGE_NAME}};

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.util.Config;

import java.util.logging.Level;

public class {{MAIN_CLASS_NAME}} extends JavaPlugin {

    private static final HytaleLogger LOGGER =
            HytaleLogger.forEnclosingClass();

    private final Config<ServerConfig> config;

    public {{MAIN_CLASS_NAME}}(JavaPluginInit init) {
        super(init);

        this.config = this.withConfig(
                "config",
                ServerConfig.CODEC
        );
    }

    @Override
    protected void setup() {
        LOGGER.at(Level.INFO)
                .log("[" + config.get().getModName() + "] Setting up...");

        LOGGER.at(Level.INFO)
                .log("[" + config.get().getModName() + "] Setup complete!");
    }

    @Override
    protected void start() {
        LOGGER.at(Level.INFO)
                .log("[" + config.get().getModName() + "] Started!");
    }

    @Override
    protected void shutdown() {
        LOGGER.at(Level.INFO)
                .log("[" + config.get().getModName() + "] Shutting down...");
    }
}