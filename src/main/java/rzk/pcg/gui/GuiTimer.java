package rzk.pcg.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rzk.pcg.PCGates;
import rzk.pcg.packet.PacketHandler;
import rzk.pcg.packet.PacketTimer;

@OnlyIn(Dist.CLIENT)
public class GuiTimer extends Screen
{
	public static final ResourceLocation GUI_TEXTURE = new ResourceLocation(PCGates.MODID, "textures/gui/timer.png");
	private TextFieldWidget delayField;
	private int delay;
	private BlockPos pos;
	private int xSize;
	private int ySize;
	int guiLeft;
	int guiTop;

	public GuiTimer(int delay, BlockPos pos)
	{
		super(new TranslationTextComponent("gui.pcg.timer"));
		this.delay = delay;
		this.pos = pos;
	}

	@Override
	protected void init()
	{
		xSize = 128;
		ySize = 80;
		guiLeft = (width - xSize) / 2;
		guiTop = (height - ySize) / 2;
		delayField = new TextFieldWidget(font, width / 2 - 40, guiTop + 20, 48, 16, I18n.format("gui.pcg.timer.delay"))
		{
			@Override
			public void writeText(String textToWrite)
			{
				StringBuilder stringbuilder = new StringBuilder();

				for (char c0 : textToWrite.toCharArray())
					if (c0 >= 48 && c0 <= 57)
						stringbuilder.append(c0);

				super.writeText(stringbuilder.toString());
			}
		};
		delayField.setCanLoseFocus(false);
		delayField.changeFocus(true);
		delayField.setMaxStringLength(35);
		delayField.setText("" + delay);
		children.add(delayField);
		setFocusedDefault(delayField);
		addButton(new Button(width / 2 - 24, guiTop + ySize - 28, 48, 20, I18n.format("gui.done"), onPress -> sendDelayPacket()));
	}

	public boolean keyPressed(int p_keyPressed_1_, int p_keyPressed_2_, int p_keyPressed_3_)
	{
		if (p_keyPressed_1_ == 256)
			this.minecraft.player.closeScreen();

		return delayField.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_) || delayField.canWrite() || super.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_);
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks)
	{
		renderBackground();
		drawGuiBackgroundTexture(mouseX, mouseY, partialTicks);
		delayField.render(mouseX, mouseY, partialTicks);
		font.drawString(title.getFormattedText(), guiLeft + (xSize - font.getStringWidth(title.getFormattedText())) / 2, guiTop + 6, 0x404040);
		font.drawString("Ticks", guiLeft + 80, guiTop + 24, 0x404040);
		super.render(mouseX, mouseY, partialTicks);
	}

	protected void drawGuiBackgroundTexture(int mouseX, int mouseY, float partialTicks)
	{
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(GUI_TEXTURE);
		this.blit(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	private void sendDelayPacket()
	{
		PacketHandler.INSTANCE.sendToServer(new PacketTimer(Integer.parseInt(delayField.getText()), pos));
		minecraft.player.closeScreen();
	}
}
