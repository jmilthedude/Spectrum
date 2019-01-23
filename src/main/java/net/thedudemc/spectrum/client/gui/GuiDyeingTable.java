package net.thedudemc.spectrum.client.gui;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.thedudemc.spectrum.common.Constants;
import net.thedudemc.spectrum.common.Spectrum;
import net.thedudemc.spectrum.common.network.CleanBlockPacket;
import net.thedudemc.spectrum.common.network.ColorPacket;
import net.thedudemc.spectrum.common.tileentity.Canister;
import net.thedudemc.spectrum.common.tileentity.ContainerDyeingTable;
import net.thedudemc.spectrum.common.tileentity.TileDyeingTableController;
import net.thedudemc.spectrum.common.util.EnumSpectrumDye;
import net.thedudemc.spectrum.common.util.NBTUtility;

public class GuiDyeingTable extends GuiContainer {
	public static final int WIDTH = 176;
	public static final int HEIGHT = 226;

	private static final ResourceLocation background = new ResourceLocation(Constants.MODID, "textures/gui/dyeing_table.png");
	private TileDyeingTableController te;
	private ContainerDyeingTable container;
	private GuiTextField hexInput;
	private GuiButton dyeButton;
	private GuiSlider redSlider;
	private GuiSlider greenSlider;
	private GuiSlider blueSlider;
	private int redValue;
	private int greenValue;
	private int blueValue;
	private String hex;

	public String getHex() {
		return hex;
	}

	public void setHex(String hex) {
		this.hex = hex;
	}

	public GuiDyeingTable(TileDyeingTableController tileEntity, ContainerDyeingTable container) {
		super(container);

		xSize = WIDTH;
		ySize = HEIGHT;
		this.te = tileEntity;
		this.container = container;
	}

	@Override
	public void initGui() {
		super.initGui();
		int x = 7;
		int y = 166;
		hexInput = new GuiTextField(100, mc.fontRenderer, 97, 32, 72, 18);
		buttonList.add(dyeButton = new GuiButton(101, guiLeft + 97, guiTop + 51, 72, 18, "Dye"));
		buttonList.add(redSlider = new GuiSlider(102, "Red", 0, guiLeft + x, guiTop + y, 255));
		y += 18;
		buttonList.add(greenSlider = new GuiSlider(103, "Green", 0, guiLeft + x, guiTop + y, 255));
		y += 18;
		buttonList.add(blueSlider = new GuiSlider(104, "Blue", 0, guiLeft + x, guiTop + y, 255));
		redSlider.setWidth(108);
		greenSlider.setWidth(108);
		blueSlider.setWidth(108);
		this.hexInput.setMaxStringLength(6);

	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		this.hexInput.textboxKeyTyped(typedChar, keyCode);

		if (!(keyCode == Keyboard.KEY_E && this.hexInput.isFocused()))
			super.keyTyped(typedChar, keyCode);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

		if (mouseX >= guiLeft + 97 && mouseX <= guiLeft + 168) {
			if (mouseY >= guiTop + 32 && mouseY <= guiTop + 50) {
				this.hexInput.setFocused(true);
			} else {
				this.hexInput.setFocused(false);
			}
		} else {
			this.hexInput.setFocused(false);
		}
		this.dyeButton.mousePressed(mc, mouseX, mouseY);
		this.redSlider.mousePressed(mc, mouseX, mouseY);
		this.greenSlider.mousePressed(mc, mouseX, mouseY);
		this.blueSlider.mousePressed(mc, mouseX, mouseY);

		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		if (this.selectedButton != null) {
			if (this.selectedButton.id == 102) {
				redValue = (int) ((redSlider.getSliderValue()) * 255);
			}
			if (this.selectedButton.id == 103) {
				greenValue = (int) ((greenSlider.getSliderValue()) * 255);
			}
			if (this.selectedButton.id == 104) {
				blueValue = (int) ((blueSlider.getSliderValue()) * 255);
			}
			setColorFromSliders();
		}
		super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
	}

	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		boolean clean = false;
		if (this.selectedButton != null) {
			if (this.selectedButton.id == 101) {
				if (this.hexInput.getText().isEmpty()) {
					clean = true;
				}
			}
			if (this.selectedButton.id == 102) {
				redValue = (int) ((redSlider.getSliderValue()) * 255);
			}
			if (this.selectedButton.id == 103) {
				greenValue = (int) ((greenSlider.getSliderValue()) * 255);
			}
			if (this.selectedButton.id == 104) {
				blueValue = (int) ((blueSlider.getSliderValue()) * 255);
			}
			setColorFromSliders();
			this.selectedButton.mouseReleased(mouseX, mouseY);
		}

		super.mouseReleased(mouseX, mouseY, state);
		if (clean) {
			this.hexInput.setText("");
			clean = false;
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.id == 101) {
			String text = this.hexInput.getText();
			if (isHex(text)) {
				Color color = new Color(Integer.parseInt(text, 16));
				int[] colorArray = new int[] { color.getRed(), color.getGreen(), color.getBlue() };
				NBTTagCompound compound = new NBTTagCompound();
				compound.setIntArray(NBTUtility.RGB_TAG, colorArray);
				Spectrum.PACKET.sendToServer(new ColorPacket(compound, this.te.getPos()));
			} else if (text.isEmpty()) {
				Spectrum.PACKET.sendToServer(new CleanBlockPacket(this.te.getPos()));
			}
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.hexInput.drawTextBox();
		if (this.hexInput.getText().length() == 6) {
			String input = this.hexInput.getText();
			if (isHex(input)) {
				int color = Integer.parseInt(input, 16);
				if (getBrightness(hex2Rgb("#" + input)) < 128) {
					drawRect(97, 32, 169, 50, 0xFFFFFFFF);
					this.fontRenderer.drawString(this.hexInput.getText(), 101, 37, color, false);
				}
				this.hexInput.setTextColor(color);

				Color c = new Color(color);
				redValue = c.getRed();
				greenValue = c.getGreen();
				blueValue = c.getBlue();
				if (this.hexInput.getText().length() == 6 && this.hexInput.isFocused() && isHex(this.hexInput.getText())) {
					setSlidersFromColor();
				}
			}
		} else {
			this.hexInput.setTextColor(-1);
		}

		if (redValue >= 0 && greenValue >= 0 && blueValue >= 0) {
			Color color = new Color(redValue, greenValue, blueValue);
			drawRect(116, 167, 167, 218, color.getRGB());
		}

		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		mc.getTextureManager().bindTexture(background);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		int x = 13;
		int textureX = 176;
		drawLevel(EnumSpectrumDye.RED.getInt(), x, textureX);
		x += 18;
		textureX += 6;
		drawLevel(EnumSpectrumDye.GREEN.getInt(), x, textureX);
		x += 18;
		textureX += 6;
		drawLevel(EnumSpectrumDye.BLUE.getInt(), x, textureX);
		x += 18;
		textureX += 6;
		drawWaterLevel();
		if (container.getSlot(3).getHasStack())
			drawProgressBar();

	}

	private void setColorFromSliders() {
		Color color = new Color(redValue, greenValue, blueValue);
		String hex = Integer.toHexString(color.getRGB() & 0x00FFFFFF);
		while (hex.length() < 6) {
			hex = "0" + hex;
		}
		this.hexInput.setText(hex);
	}

	private void setSlidersFromColor() {

		redSlider.setSliderValue((float) redValue / 255);
		greenSlider.setSliderValue((float) greenValue / 255);
		blueSlider.setSliderValue((float) blueValue / 255);

	}

	private void drawLevel(int index, int x, int textureX) {
		double amount = te.getDyeAmount()[index];
		int percentage = (int) ((amount / Canister.MAX_AMOUNT) * 41);
		drawTexturedModalRect(guiLeft + x, (guiTop + 68) - percentage, textureX, 41 - percentage, 6, percentage);
	}

	private void drawWaterLevel() {
		double amount = (te.getTank().getFluidAmount());
		int percentage = (int) ((amount / 10000) * 60);
		drawTexturedModalRect(guiLeft + 62, (guiTop + 68) - percentage, 176, 101 - percentage, 16, percentage);
	}

	void drawProgressBar() {
		double percentage = te.getClientProgress() / 100.0D;
		if ((percentage * 100) < 98)
			drawTexturedModalRect(guiLeft + 118, guiTop + 11, 195, 1, (int) (percentage * 31), 11);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);

		renderHoveredToolTip(mouseX, mouseY);
		int x = mouseX - guiLeft;
		int y = mouseY - guiTop;
		if (x >= 13 && x <= 18) {
			if (y >= 27 && y <= 67) {
				this.drawHoveringText(Integer.toString(te.getDyeAmount()[0]) + " Red Units", mouseX, mouseY);
			}
		}
		if (x >= 30 && x <= 36) {
			if (y >= 27 && y <= 67) {
				this.drawHoveringText(Integer.toString(te.getDyeAmount()[1]) + " Green Units", mouseX, mouseY);
			}
		}
		if (x >= 48 && x <= 54) {
			if (y >= 27 && y <= 67) {
				this.drawHoveringText(Integer.toString(te.getDyeAmount()[2]) + " Blue Units", mouseX, mouseY);
			}
		}
		if (x >= 62 && x <= 77) {
			if (y >= 8 && y <= 67) {
				this.drawHoveringText(Integer.toString(te.getTank().getFluidAmount()) + " mB of Water", mouseX, mouseY);
			}
		}

	}

	public static boolean isHex(String s) {
		return s.matches("[?0-9A-Fa-f]+");
	}

	private static int getBrightness(Color c) {
		return (int) Math.sqrt(c.getRed() * c.getRed() * .241 + c.getGreen() * c.getGreen() * .691 + c.getBlue() * c.getBlue() * .068);
	}

	public static Color hex2Rgb(String colorStr) {
		return new Color(Integer.valueOf(colorStr.substring(1, 3), 16), Integer.valueOf(colorStr.substring(3, 5), 16), Integer.valueOf(colorStr.substring(5, 7), 16));
	}

	@Override
	public void onResize(Minecraft mcIn, int w, int h) {
		String text = this.hexInput.getText();
		super.onResize(mcIn, w, h);
		this.hexInput.setText(text);
		setSlidersFromColor();
	}

}
