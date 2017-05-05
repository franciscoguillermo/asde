package de.paxii.clarinet.gui.ingame.panel.element.elements;

import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.gui.ingame.panel.element.PanelElement;
import de.paxii.clarinet.util.function.TwoArgumentsFunction;
import java.util.function.Function;
import lombok.Setter;
import net.minecraft.util.EnumActionResult;

/**
 * Created by Lars on 05.05.17.
 */
public class PanelKeyBindButton extends PanelElement {

  @Setter
  private String caption;
  private TwoArgumentsFunction<Boolean, Integer, PanelKeyBindButton> callback;
  private boolean listening;

  public PanelKeyBindButton(
      String caption,
      TwoArgumentsFunction<Boolean, Integer, PanelKeyBindButton> callback) {
    super(90, 12);

    this.caption = caption;
    this.callback = callback;
  }

  @Override
  public void mouseClicked(int mouseX, int mouseY, int buttonClicked) {
    this.listening = !this.listening;
    super.mouseClicked(mouseX, mouseY, buttonClicked);
  }

  @Override
  public EnumActionResult keyPressed(int keyCode) {
    if (this.listening) {
      if (!this.callback.apply(keyCode, this)) {
        this.listening = false;
      }

      return EnumActionResult.SUCCESS;
    }

    return EnumActionResult.PASS;
  }

  @Override
  public void drawElement(int elementX, int elementY, int mouseX, int mouseY) {
    Wrapper.getClickableGui()
        .getCurrentTheme()
        .drawButton(this.caption, elementX, elementY, this.getElementWidth(),
            this.getElementHeight(), this.listening, this.isMouseOverButton(mouseX, mouseY));

    super.drawElement(elementX, elementY, mouseX, mouseY);
  }
}
