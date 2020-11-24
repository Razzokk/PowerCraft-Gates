package rzk.lib.mc.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import rzk.lib.mc.tile.ITileRedstoneStates;
import rzk.lib.mc.tile.TileRedstoneDevice;
import rzk.lib.mc.util.WorldUtils;

import javax.annotation.Nullable;

public abstract class BlockRedstoneDeviceEdge extends BlockRedstoneDevice
{
	public BlockRedstoneDeviceEdge(Properties properties)
	{
		super(properties);
	}

	@Override
	public boolean hasTileEntity(BlockState state)
	{
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world)
	{
		return new TileRedstoneDevice();
	}

	protected void onInputHigh(BlockState state, World world, BlockPos pos, Direction side) {}

	protected void onInputLow(BlockState state, World world, BlockPos pos, Direction side) {}

	protected void updateRedstoneState(World world, BlockPos pos, BlockState state, ITileRedstoneStates tile, Direction... sides)
	{
		if (sides == null || sides.length == 0)
		{
			updateRedstoneState(world, pos, state, tile, Direction.values());
			return;
		}

		for (Direction side : sides)
		{
			boolean isPowered = isPowered(world, pos, side);
			if (isPowered != tile.getRedstoneState(side))
			{
				if (isPowered) onInputHigh(state, world, pos, side);
				else onInputLow(state, world, pos, side);
				tile.setRedstoneState(side, isPowered);
			}
		}
	}

	@Override
	public void onInputChanged(BlockState state, World world, BlockPos pos, Direction side)
	{
		if (!world.isRemote)
			WorldUtils.ifTilePresent(world, pos, ITileRedstoneStates.class, tile -> updateRedstoneState(world, pos, state, tile, side));
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack)
	{
		if (!world.isRemote)
			WorldUtils.ifTilePresent(world, pos, ITileRedstoneStates.class, tile -> Direction.Plane.HORIZONTAL.forEach(side -> updateRedstoneState(world, pos, state, tile, side)));
	}
}
