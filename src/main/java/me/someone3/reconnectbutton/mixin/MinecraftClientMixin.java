package me.someone3.reconnectbutton.mixin;

import me.someone3.reconnectbutton.ReconnectButtonMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(method = "setScreen", at = @At("HEAD"))
    private void setScreen(Screen newScreen, CallbackInfo info) {
        if (newScreen instanceof TitleScreen) {
            ReconnectButtonMod.LOGGER.info("[ReconnectButton] Resetting last server info");
            ReconnectButtonMod.getMod().setLastServerInfo(null);
        }
    }
}