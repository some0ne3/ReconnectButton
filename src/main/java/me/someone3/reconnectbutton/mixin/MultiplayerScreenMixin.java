package me.someone3.reconnectbutton.mixin;

import me.someone3.reconnectbutton.ReconnectButtonMod;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.network.ServerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MultiplayerScreen.class)
public class MultiplayerScreenMixin {
    @Inject(at = @At("TAIL"), method = "connect(Lnet/minecraft/client/network/ServerInfo;)V")
    private void connect(ServerInfo entry, CallbackInfo info) {
        ReconnectButtonMod.LOGGER.info("[ReconnectButton] Setting last server info to " + entry.address);
        ReconnectButtonMod.getMod().setLastServerInfo(entry);
    }
}
