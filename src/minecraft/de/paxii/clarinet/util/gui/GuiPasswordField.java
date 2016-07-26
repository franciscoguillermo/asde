package de.paxii.clarinet.util.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiTextField;

public class GuiPasswordField extends GuiTextField {
	public GuiPasswordField(int componentId, FontRenderer fontrendererObj, int x, int y, int par5Width, int par6Height) {
		super(componentId, fontrendererObj, x, y, par5Width, par6Height);
	}

	@Override
	public void drawTextBox() {
		if (this.getVisible()) {
			if (this.getEnableBackgroundDrawing()) {
				drawRect(this.xPosition - 1, this.yPosition - 1, this.xPosition + this.width + 1, this.yPosition + this.height + 1, -6250336);
				drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, -16777216);
			}

			int var1 = this.isEnabled ? this.enabledColor : this.disabledColor;
			int var2 = this.cursorPosition - this.lineScrollOffset;
			int var3 = this.selectionEnd - this.lineScrollOffset;
			String text = this.text.substring(this.lineScrollOffset);
			String hidden = "";

			for (int i = 0; i < text.length(); i++)
				hidden += "*";

			String var4 = this.fontRendererInstance.trimStringToWidth(hidden, this.getWidth());
			boolean var5 = var2 >= 0 && var2 <= var4.length();
			boolean var6 = this.isFocused && this.cursorCounter / 6 % 2 == 0 && var5;
			int var7 = this.enableBackgroundDrawing ? this.xPosition + 4 : this.xPosition;
			int var8 = this.enableBackgroundDrawing ? this.yPosition + (this.height - 8) / 2 : this.yPosition;
			int var9 = var7;

			if (var3 > var4.length()) {
				var3 = var4.length();
			}

			if (var4.length() > 0) {
				String var10 = var5 ? var4.substring(0, var2) : var4;
				var9 = this.fontRendererInstance.drawStringWithShadow(var10, var7, var8, var1);
			}

			boolean var13 = this.cursorPosition < this.text.length() || this.text.length() >= this.getMaxStringLength();
			int var11 = var9;

			if (!var5) {
				var11 = var2 > 0 ? var7 + this.width : var7;
			} else if (var13) {
				var11 = var9 - 1;
				--var9;
			}

			if (var4.length() > 0 && var5 && var2 < var4.length()) {
				this.fontRendererInstance.drawStringWithShadow(var4.substring(var2), var9, var8, var1);
			}

			if (var6) {
				if (var13) {
					Gui.drawRect(var11, var8 - 1, var11 + 1, var8 + 1 + this.fontRendererInstance.FONT_HEIGHT, -3092272);
				} else {
					this.fontRendererInstance.drawStringWithShadow("_", var11, var8, var1);
				}
			}

			if (var3 != var2) {
				int var12 = var7 + this.fontRendererInstance.getStringWidth(var4.substring(0, var3));
				this.drawCursorVertical(var11, var8 - 1, var12 - 1, var8 + 1 + this.fontRendererInstance.FONT_HEIGHT);
			}
		}
	}
}
