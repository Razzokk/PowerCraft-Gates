package rzk.lib.mc.gui.widgets;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;

public class SizedButton extends Button
{
	private final int textOffsetX;
	private final int textOffsetY;

	public SizedButton(int x, int y, int width, int height, String text, int textOffsetX, int textOffsetY, IPressable onPress)
	{
		super(x, y, width, height, text, onPress);
		this.textOffsetX = textOffsetX;
		this.textOffsetY = textOffsetY;
	}

	public SizedButton(int x, int y, int width, int height, String text, IPressable onPress)
	{
		this(x, y, width, height, text, 0, 0, onPress);
	}

	public void renderButton(int mouseX, int mouseY, float partialTicks)
	{
		Minecraft minecraft = Minecraft.getInstance();
		FontRenderer fontrenderer = minecraft.fontRenderer;
		minecraft.getTextureManager().bindTexture(WIDGETS_LOCATION);
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, alpha);
		int i = getYImage(isHovered());
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		blit(x, y, 0, 46 + i * 20, width / 2, height / 2);
		blit(x + width / 2, y, 200 - width / 2, 46 + i * 20, width / 2, height / 2);
		blit(x, y + height / 2, 0, 46 + i * 20 + 20 - height / 2, width / 2, height / 2);
		blit(x + width / 2, y + height / 2, 200 - width / 2, 46 + i * 20 + 20 - height / 2, width / 2, height / 2);
		renderBg(minecraft, mouseX, mouseY);
		int j = getFGColor();
		drawCenteredString(fontrenderer, getMessage(), x + textOffsetX + width / 2, y + textOffsetY + (height - 8) / 2, j | MathHelper.ceil(alpha * 255.0F) << 24);
	}
}
