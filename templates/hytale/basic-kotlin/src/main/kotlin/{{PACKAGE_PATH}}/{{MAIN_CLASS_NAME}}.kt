package {{PACKAGE_NAME}}

import com.hypixel.hytale.logger.HytaleLogger
import com.hypixel.hytale.server.core.plugin.JavaPlugin
import com.hypixel.hytale.server.core.plugin.JavaPluginInit
import java.util.logging.Level

class {{MAIN_CLASS_NAME}}(
init: JavaPluginInit
) : JavaPlugin(init) {

    private val logger =
        HytaleLogger.forEnclosingClass()

    override fun setup() {
        logger.at(Level.INFO).log("[{{PROJECT_NAME}}] Setting up...")
        logger.at(Level.INFO).log("[{{PROJECT_NAME}}] Setup complete!")
    }

    override fun start() {
        logger.at(Level.INFO).log("[{{PROJECT_NAME}}] Started!")
    }

    override fun shutdown() {
        logger.at(Level.INFO).log("[{{PROJECT_NAME}}] Shutting down...")
    }
}