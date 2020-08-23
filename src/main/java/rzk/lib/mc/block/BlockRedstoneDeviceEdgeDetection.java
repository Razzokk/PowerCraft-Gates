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

import javax.annotation.Nullable;

public abstract class BlockRedstoneDeviceEdgeDetection extends BlockRedstoneDevice
{
	public BlockRedstoneDeviceEdgeDetection(Properties properties)
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

	public void onInputHigh(BlockState state, World world, BlockPos pos, Direction side) {}

	public void onInputLow(BlockState state, World world, BlockPos pos, Direction side) {}

	@Override
	public void onInputChanged(BlockState state, World world, BlockPos pos, Direction side)
	{
		TileEntity tile = world.getTileEntity(pos);

		if (tile instanceof ITileRedstoneStates)
		{
			ITileRedstoneStates tileStates = (ITileRedstoneStates) tile;
			boolean isPowered = isPowered(world, pos, side);
			if (isPowered != tileStates.getRedstoneState(side))
			{
				if (isPowered) onInputHigh(state, world, pos, side);
				else onInputLow(state, world, pos, side);
				tileStates.setRedstoneState(side, isPowered);
			}
		}
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack)
	{
		if (!world.isRemote)
		{
			TileEntity tile = world.getTileEntity(pos);

			if (tile instanceof ITileRedstoneStates)
			{
				ITileRedstoneStates tileStates = (ITileRedstoneStates) tile;
				for (Direction side : Direction.Plane.HORIZONTAL)
				{
					boolean isPowered = isPowered(world, pos, side);
					if (isPowered != tileStates.getRedstoneState(side))
					{
						if (isPowered) onInputHigh(state, world, pos, side);
						else onInputLow(state, world, pos, side);
						tileStates.setRedstoneState(side, isPowered);
					}
				}
			}
		}
	}
}
