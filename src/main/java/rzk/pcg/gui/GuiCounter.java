package rzk.pcg.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.DistExecutor;
import org.lwjgl.glfw.GLFW;
import rzk.lib.mc.gui.widgets.SizedButton;
import rzk.lib.mc.util.Utils;
import rzk.lib.mc.util.WorldUtils;
import rzk.pcg.PCGates;
import rzk.pcg.packet.PacketCounter;
import rzk.pcg.packet.PacketHandler;
import rzk.pcg.tile.TileCounter;

public class GuiCounter extends Screen
{
	public static final ResourceLocation GUI_TEXTURE = new ResourceLocation(PCGates.MOD_ID, "textures/gui/counter.png");
	int guiLeft;
	int guiTop;
	private int xSize;
	private int ySize;

	private TextFieldWidget maxCountField;
	private Button done;
	private Button buttonSubtract_1;
	private Button buttonSubtract_10;
	private Button buttonAdd_1;
	private Button buttonAdd_10;

	private int maxCount;
	private BlockPos pos;

	public GuiCounter(BlockPos pos)
	{
		super(new TranslationTextComponent("gui.pcg.counter"));
		maxCount = WorldUtils.mapTile(Minecraft.getInstance().world, pos, TileCounter.class, TileCounter::getMaxCount);
		this.pos = pos;
	}

	@Override
	protected void init()
	{
		xSize = 128;
		ySize = 80;
		guiLeft = (width - xSize) / 2;
		guiTop = (height - ySize) / 2;


		addButton(buttonSubtract_1 = new SizedButton(guiLeft + 6, guiTop + 16, 36, 16, "-1", this::buttonPressed));
		addButton(buttonSubtract_10 = new SizedButton(guiLeft + 6, guiTop + 36, 36, 16, "-10", this::buttonPressed));
		addButton(buttonAdd_1 = new SizedButton(guiLeft + 86, guiTop + 16, 36, 16, "+1", this::buttonPressed));
		addButton(buttonAdd_10 = new SizedButton(guiLeft + 86, guiTop + 36, 36, 16, "+10", this::buttonPressed));
		addButton(done = new SizedButton(guiLeft + 48, guiTop + 56, 32, 18, I18n.format("gui.done"), onPress -> sendCounterPacket()));

		maxCountField = new TextFieldWidget(font, guiLeft + 45, guiTop + 20, 38, 16, I18n.format("gui.pcg.counter.max_count"))
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

		maxCountField.setMaxStringLength(5);
		maxCountField.setText(String.valueOf(maxCount));
		maxCountField.setResponder(text ->
		{
			boolean textValid = text != null && !text.isEmpty();
			done.active = textValid;
			setMaxCount(textValid ?  Integer.parseInt(text) : 0);
		});
		children.add(maxCountField);
	}

	private void setMaxCount(int maxCount)
	{
		this.maxCount = Utils.constrain(maxCount, 0, 99999);
	}

	private void buttonPressed(Button button)
	{
		setMaxCount(maxCount + Integer.parseInt(button.getMessage()));
		maxCountField.setText(String.valueOf(maxCount));
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers)
	{
		switch (keyCode)
		{
			case GLFW.GLFW_KEY_LEFT_SHIFT:
			case GLFW.GLFW_KEY_RIGHT_SHIFT:
				buttonSubtract_1.setMessage("-100");
				buttonSubtract_10.setMessage("-1000");
				buttonAdd_1.setMessage("+100");
				buttonAdd_10.setMessage("+1000");
				break;
		}

		return maxCountField.keyPressed(keyCode, scanCode, modifiers) || maxCountField.canWrite() || super.keyPressed(keyCode, scanCode, modifiers);
	}

	@Override
	public boolean keyReleased(int keyCode, int scanCode, int modifiers)
	{
		switch (keyCode)
		{
			case GLFW.GLFW_KEY_LEFT_SHIFT:
			case GLFW.GLFW_KEY_RIGHT_SHIFT:
				buttonSubtract_1.setMessage("-1");
				buttonSubtract_10.setMessage("-10");
				buttonAdd_1.setMessage("+1");
				buttonAdd_10.setMessage("+10");
				break;
		}

		return super.keyReleased(keyCode, scanCode, modifiers);
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks)
	{
		renderBackground();
		drawGuiBackgroundTexture(mouseX, mouseY, partialTicks);
		maxCountField.render(mouseX, mouseY, partialTicks);
		font.drawString(title.getString(), guiLeft + (xSize - font.getStringWidth(title.getString())) / 2, guiTop + 5, 0x404040);
		font.drawString("max.", guiLeft + (xSize - font.getStringWidth(title.getString())) / 2, guiTop + 40, 0x404040);
		super.render(mouseX, mouseY, partialTicks);
	}

	private void drawGuiBackgroundTexture(int mouseX, int mouseY, float partialTicks)
	{
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		minecraft.getTextureManager().bindTexture(GUI_TEXTURE);
		blit(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	private void sendCounterPacket()
	{
		PacketHandler.INSTANCE.sendToServer(new PacketCounter(Integer.parseInt(maxCountField.getText()), pos));
		minecraft.player.closeScreen();
	}

	public static Runnable openGui(BlockPos pos)
	{
		return () -> Minecraft.getInstance().displayGuiScreen(new GuiCounter(pos));
	}
}
