package net.thedudemc.spectrum.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class GuiSlider extends GuiButton {

	/**
	 * The text to display for the slider.
	 */
	private final String sliderName;

	/**
	 * The current percent value of the slider.
	 */
	private float sliderValue;

	/**
	 * Whether or not the player is currently dragging the slider.
	 */
	private boolean isDragging;

	/**
	 * The largest possible slider value.
	 */
	private final float maximum = 1.0f;

	/**
	 * The Integer value to display, if repAsInt is true.
	 */
	private final int intValue;

	/**
	 * Constructs a new GuiSlider.
	 *
	 * @param id:
	 *            The button ID for the slider.
	 * @param title:
	 *            The text to display on the slider.
	 * @param initialValue:
	 *            The initial value to set the slider at.
	 * @param xPos:
	 *            The X position to start rendering.
	 * @param yPos:
	 *            The Y position to start rendering.
	 * @param repAsInt:
	 *            Whether or not the value should be represented as an integer.
	 * @param repValue:
	 *            The total value to represent. For example if 100 us used, 15% of
	 *            the slider will show as 15, and if 1000 is used it will be 150.
	 */
	public GuiSlider(int id, String title, float initialValue, int xPos, int yPos, int repValue) {

		this(id, title, initialValue, xPos, yPos, repValue, false);
	}

	/**
	 * Constructs a new GuiSlider.
	 *
	 * @param id:
	 *            The button ID for the slider.
	 * @param title:
	 *            The text to display on the slider.
	 * @param initialValue:
	 *            The initial value to set the slider at.
	 * @param xPos:
	 *            The X position to start rendering.
	 * @param yPos:
	 *            The Y position to start rendering.
	 * @param repAsInt:
	 *            Whether or not the value should be represented as an integer.
	 * @param repValue:
	 *            The total value to represent. For example if 100 us used, 15% of
	 *            the slider will show as 15, and if 1000 is used it will be 150.
	 * @param invert:
	 *            Whether or not the value should be inverted.
	 */
	public GuiSlider(int id, String title, float initialValue, int xPos, int yPos, int intValue, boolean invert) {

		super(id, xPos, yPos, 55, 18, "");
		this.sliderName = title;
		this.setSliderValue(initialValue);
		this.intValue = intValue;
	}

	@Override
	protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {

		if (this.visible) {

			if (this.isDragging) {

				this.sliderValue = (float) (mouseX - (this.x + 4)) / (float) (this.width - 8);
				this.sliderValue = this.sliderValue < 0f ? 0f : this.sliderValue > 1f ? 1f : this.sliderValue;
			}

			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.drawTexturedModalRect(this.x + (int) (this.sliderValue * (this.width - 8)), this.y, 0, 66, 4, 18);
			this.drawTexturedModalRect(this.x + (int) (this.sliderValue * (this.width - 8)) + 4, this.y, 196, 66, 4, 18);

			this.updateDisplay();
		}
	}

	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {

		if (super.mousePressed(mc, mouseX, mouseY)) {

			this.sliderValue = (float) (mouseX - (this.x + 4)) / (float) (this.width - 8);
			this.sliderValue = this.sliderValue < 0f ? 0f : this.sliderValue > 1f ? 1f : this.sliderValue;
			this.isDragging = true;
			this.updateDisplay();

			return true;
		} else {
			return false;
		}
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY) {

		this.isDragging = false;
		this.updateDisplay();
	}

	@Override
	public int getHoverState(boolean p_146114_1_) {

		return 0;
	}

	/**
	 * Sets the value of the slider.
	 *
	 * @param value:
	 *            The value to set.
	 */
	public void setSliderValue(float value) {

		this.sliderValue = value / this.maximum;
		this.updateDisplay();
	}

	/**
	 * Retrieves the value of the slider.
	 *
	 * @return float: The value of the slider.
	 */
	public float getSliderValue() {

		return this.sliderValue * this.maximum;
	}

	/**
	 * Basic method for updating the display. If repAsInt is true, value will be an
	 * int. If not, it will be the actual value rounded to 2 decimal places.
	 */
	private void updateDisplay() {

		final String value = Integer.toString((int) (this.getSliderValue() * this.intValue));
		this.displayString = this.sliderName + ": " + value;
	}
}
