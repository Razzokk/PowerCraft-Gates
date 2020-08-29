package rzk.pcg.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rzk.pcg.tile.TileCounter;

@OnlyIn(Dist.CLIENT)
public class CounterRenderer extends TileEntityRenderer<TileCounter>
{
	public CounterRenderer(TileEntityRendererDispatcher rendererDispatcher)
	{
		super(rendererDispatcher);
	}

	@Override
	public void render(TileCounter tile, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay)
	{
		matrixStack.push();
		FontRenderer fontrenderer = this.renderDispatcher.getFontRenderer();
		matrixStack.translate(0.5, 0.19, 0.5);
		matrixStack.rotate(new Quaternion(270, 0, 180 - tile.getBlockState().get(BlockStateProperties.HORIZONTAL_FACING).getHorizontalAngle(), true));
		matrixStack.scale(1f / 96, -1f / 96, 1f / 96);
		String s = String.valueOf(tile.getCounter());
		float f3 = (float) (-fontrenderer.getStringWidth(s) / 2);
		fontrenderer.renderString(s, f3, -4, 0, false, matrixStack.getLast().getMatrix(), buffer, false, 0, combinedLight);
		matrixStack.pop();
	}
}
