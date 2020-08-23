package rzk.pcg.block;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockCounter extends BlockGateEdgeBase
{
	@Override
	public boolean shouldBePowered(BlockState state, World world, BlockPos pos)
	{
		return false;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world)
	{
		return super.createTileEntity(state, world);
	}
}
