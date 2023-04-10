package me.someone3.reconnectbutton;

import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ReconnectButtonWidget extends ButtonWidget {
    public ReconnectButtonWidget(int x, int y, int width, int height, Text text, PressAction onPress) {
        super(x, y, width, height, text, onPress, (button, matrices, mouseX, mouseY) -> {});
    }
}
