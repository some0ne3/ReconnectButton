package me.someone3.reconnectbutton.mixin;

import me.someone3.reconnectbutton.ReconnectButtonMod;
import me.someone3.reconnectbutton.ReconnectButtonWidget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DisconnectedScreen.class)
public class DisconnectedScreensMixin extends Screen {
    private ButtonWidget reconnectButton;
    public DisconnectedScreensMixin(Text text) {
        super(text);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void constructor(Screen parent, Text title, Text reason, CallbackInfo info) {
        reconnectButton = new ReconnectButtonWidget(
                0, 0, 0, 20,
                Text.literal("Reconnect"),
                (btn) -> MinecraftClient.getInstance().execute(this::manualReconnect));
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void init(CallbackInfo info) {
        if (ReconnectButtonMod.getMod().getLastServerInfo() == null) {
            return;
        }
        ButtonWidget backButton = (ButtonWidget) children().get(0);
        reconnectButton.setX(backButton.getX());
        reconnectButton.setY(backButton.getY());
        reconnectButton.setWidth(backButton.getWidth());
        addDrawableChild(reconnectButton);
        backButton.setY(backButton.getY() + backButton.getHeight() + 4);
    }

    private void manualReconnect() {
        ReconnectButtonMod.LOGGER.info("[ReconnectButton] Attempting to reconnect...");
        ReconnectButtonMod.getMod().reconnect();
    }
}
