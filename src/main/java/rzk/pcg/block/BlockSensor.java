package rzk.pcg.block;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import rzk.lib.mc.util.Utils;

import java.util.Random;

import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class BlockSensor extends BlockGateBase
{
	private final Type type;

	public BlockSensor(Type type)
	{
		this.type = type;
	}

	@Override
	public boolean isOutputSide(BlockState state, Direction side)
	{
		return side.getHorizontalIndex() != -1;
	}

	@Override
	public boolean shouldBePowered(BlockState state, World world, BlockPos pos)
	{
		switch (type)
		{
			case DAY:
				return world.isDaytime();
			case NIGHT:
				return world.isNightTime();
			case RAIN:
				return world.isRaining();
			case THUNDER:
				return world.isThundering();
		}
		return false;
	}

	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand)
	{
		super.tick(state, world, pos, rand);
		scheduleTickIfNotScheduled(world, pos, 20);
	}

	public enum Type
	{
		DAY,
		NIGHT,
		RAIN,
		THUNDER,
		CHEST_EMPTY,
		CHEST_SPACE,
		CHEST_FULL,
		RISING_EDGE,
		FALLING_EDGE
	}

	public static class Chest extends BlockGateBase
	{
		private final Type type;

		public Chest(Type type)
		{
			this.type = type;
		}

		@Override
		public boolean isOutputSide(BlockState state, Direction side)
		{
			return side == state.get(HORIZONTAL_FACING);
		}

		@Override
		public boolean shouldBePowered(BlockState state, World world, BlockPos pos)
		{
			BlockPos blockpos = pos.offset(state.get(HORIZONTAL_FACING).getOpposite());
			BlockState blockstate = world.getBlockState(blockpos);

			if (blockstate.hasComparatorInputOverride())
			{
				int level = blockstate.getComparatorInputOverride(world, blockpos);
				switch (type)
				{
					case CHEST_EMPTY:
						return level == 0;
					case CHEST_SPACE:
						return level > 0 && level < 14;
					case CHEST_FULL:
						return level >= 14;
				}
			}
			return false;
		}

		@Override
		public void onNeighborChange(BlockState state, IWorldReader world, BlockPos pos, BlockPos neighbor)
		{
			if (!world.isRemote())
			{
				Direction side = Utils.getFromBlockPos(pos, neighbor);
				if (side == state.get(HORIZONTAL_FACING).getOpposite() && pos.getY() == neighbor.getY() && world instanceof World)
					scheduleTickIfNotScheduled((World) world, pos, 2);
			}
		}
	}

	public static class Edge extends BlockGateEdgeBase
	{
		private final Type type;

		public Edge(Type type)
		{
			this.type = type;
		}

		@Override
		public boolean isInputSide(BlockState state, Direction side)
		{
			return side == state.get(HORIZONTAL_FACING).getOpposite();
		}

		@Override
		public boolean isOutputSide(BlockState state, Direction side)
		{
			return side == state.get(HORIZONTAL_FACING);
		}

		@Override
		public void onInputHigh(BlockState state, World world, BlockPos pos, Direction side)
		{
			if (type == Type.RISING_EDGE)
			{
				setPoweredState(state, world, pos, true);
				scheduleTickIfNotScheduled(world, pos, 2);
			}
		}

		@Override
		public void onInputLow(BlockState state, World world, BlockPos pos, Direction side)
		{
			if (type == Type.FALLING_EDGE)
			{
				setPoweredState(state, world, pos, true);
				scheduleTickIfNotScheduled(world, pos, 2);
			}
		}

		@Override
		public boolean shouldBePowered(BlockState state, World world, BlockPos pos)
		{
			return false;
		}
	}
}
