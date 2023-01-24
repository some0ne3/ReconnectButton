package me.someone3.reconnectbutton;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReconnectButtonMod implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("reconnectbutton");
    private static ReconnectButtonMod mod;
    private ServerInfo lastServerInfo;

    @Override
    public void onInitializeClient() {
        mod = this;
    }

    public static ReconnectButtonMod getMod() {
        return mod;
    }

    public ServerInfo getLastServerInfo() {
        return lastServerInfo;
    }

    public void setLastServerInfo(ServerInfo serverInfo) {
        lastServerInfo = serverInfo;
    }

    public void reconnect() {
        if (lastServerInfo != null) {
            LOGGER.info("[ReconnectButton] Reconnecting to " + lastServerInfo.address);
        }

        ConnectScreen.connect(
                new MultiplayerScreen(new TitleScreen()),
                MinecraftClient.getInstance(),
                ServerAddress.parse(lastServerInfo.address),
                lastServerInfo);
    }

}
