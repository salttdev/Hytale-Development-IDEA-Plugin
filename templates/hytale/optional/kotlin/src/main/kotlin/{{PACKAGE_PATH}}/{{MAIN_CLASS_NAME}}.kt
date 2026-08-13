package {{PACKAGE_NAME}}

import com.hypixel.hytale.logger.HytaleLogger
import com.hypixel.hytale.server.core.plugin.JavaPlugin
import com.hypixel.hytale.server.core.plugin.JavaPluginInit
import com.hypixel.hytale.server.core.util.Config
import java.util.logging.Level

class {{MAIN_CLASS_NAME}}(
init: JavaPluginInit
) : JavaPlugin(init) {

    companion object {
        private val LOGGER =
            HytaleLogger.forEnclosingClass()
    }

    private val config: Config<ServerConfig> =
        withConfig(
            "config",
            ServerConfig.CODEC
        )

    override fun setup() {
        LOGGER.at(Level.INFO)
            .log("[${config.get().modName}] Setting up...")

        LOGGER.at(Level.INFO)
            .log("[${config.get().modName}] Setup complete!")
    }

    override fun start() {
        LOGGER.at(Level.INFO)
            .log("[${config.get().modName}] Started!")
    }

    override fun shutdown() {
        LOGGER.at(Level.INFO)
            .log("[${config.get().modName}] Shutting down...")
    }
}