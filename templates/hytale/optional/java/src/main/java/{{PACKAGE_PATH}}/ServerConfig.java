package {{PACKAGE_NAME}};

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;

public class ServerConfig {

    public static final BuilderCodec<ServerConfig> CODEC =
            BuilderCodec.builder(ServerConfig.class, ServerConfig::new)

                    .append(
                            new KeyedCodec<String>("ModName", Codec.STRING),
                            (config, value, info) -> config.modName = value,
                            (config, info) -> config.modName
                    )
                    .add()

                    .build();

    private String modName = "{{PROJECT_NAME}}";

    public ServerConfig() {}

    public String getModName() {
        return modName;
    }

    public void setModName(String modName) {
        this.modName = modName;
    }
}