package {{PACKAGE_NAME}}

import com.hypixel.hytale.codec.Codec
import com.hypixel.hytale.codec.KeyedCodec
import com.hypixel.hytale.codec.builder.BuilderCodec

class ServerConfig {

    companion object {

        @JvmField
        val CODEC: BuilderCodec<ServerConfig> =
            BuilderCodec.builder(ServerConfig::class.java, ::ServerConfig)

                .append(
                    KeyedCodec<String>("ModName", Codec.STRING),
                    { config, value, _ -> config.modName = value },
                    { config, _ -> config.modName }
                )
                .add()

                .build()
    }

    var modName: String = "{{PROJECT_NAME}}"
}