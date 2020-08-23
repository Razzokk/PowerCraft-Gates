package rzk.pcg.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
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
public class TimerGui extends Screen
{
	public static final ResourceLocation GUI_TEXTURE = new ResourceLocation(PCGates.MODID, "textures/gui/timer.png");
	private TextFieldWidget delayField;
	private int delay;
	private BlockPos timerPos;
	private int xSize;
	private int ySize;
	int guiLeft;
	int guiTop;

	public TimerGui(int delay, BlockPos timerPos)
	{
		super(new TranslationTextComponent("gui.pcg.timer"));
		this.delay = delay;
		this.timerPos = timerPos;
	}

	@Override
	protected void init()
	{
		xSize = 128;
		ySize = 80;
		guiLeft = (width - xSize) / 2;
		guiTop = (height - ySize) / 2;
		delayField = new TextFieldWidget(font, width / 2 - 40, guiTop + 20, 48, 16, new TranslationTextComponent("gui.pcg.timer.delay"))
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
		delayField.setMaxStringLength(35);
		delayField.setText("" + delay);
		children.add(delayField);
		setListener(delayField);
		setFocusedDefault(delayField);
		addButton(new Button(width / 2 - 24, guiTop + ySize - 28, 48, 20, new TranslationTextComponent("gui.done"), onPress -> sendDelayPacket()));
	}

	public boolean keyPressed(int p_keyPressed_1_, int p_keyPressed_2_, int p_keyPressed_3_)
	{
		if (p_keyPressed_1_ == 256)
			this.minecraft.player.closeScreen();

		return delayField.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_) || delayField.canWrite() || super.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_);
	}

	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
	{
		renderBackground(matrixStack);
		drawGuiBackgroundTexture(matrixStack, mouseX, mouseY, partialTicks);
		delayField.render(matrixStack, mouseX, mouseY, partialTicks);
		font.drawString(matrixStack, title.getString(), guiLeft + (xSize - font.getStringWidth(title.getString())) / 2, guiTop + 6, 0x404040);
		font.drawString(matrixStack, "Ticks", guiLeft + 80, guiTop + 24, 0x404040);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
	}

	protected void drawGuiBackgroundTexture(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
	{
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		minecraft.getTextureManager().bindTexture(GUI_TEXTURE);
		blit(matrixStack, guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	private void sendDelayPacket()
	{
		PacketHandler.INSTANCE.sendToServer(new PacketTimer(Integer.parseInt(delayField.getText()), timerPos));
		minecraft.player.closeScreen();
	}
}
