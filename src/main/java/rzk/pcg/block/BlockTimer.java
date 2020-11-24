package rzk.pcg.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import rzk.lib.mc.util.WorldUtils;
import rzk.pcg.gui.GuiTimer;
import rzk.pcg.tile.TileTimer;

import javax.annotation.Nullable;

import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class BlockTimer extends BlockGateEdgeBase
{
	@Override
	public boolean shouldBePowered(BlockState state, World world, BlockPos pos)
	{
		return false;
	}

	@Override
	public boolean isInputSide(BlockState state, Direction side)
	{
		return side == state.get(HORIZONTAL_FACING).getOpposite();
	}

	@Override
	public boolean isOutputSide(BlockState state, Direction side)
	{
		Direction facing = state.get(HORIZONTAL_FACING);
		return side == facing || side == facing.rotateYCCW() || side == facing.rotateY();
	}

	protected void setEnabled(World world, BlockPos pos, boolean enabled)
	{
		if (!world.isRemote)
			WorldUtils.ifTilePresent(world, pos, TileTimer.class, tile -> tile.setEnabled(enabled));
	}

	@Override
	public void onInputHigh(BlockState state, World world, BlockPos pos, Direction side)
	{
		setEnabled(world, pos, false);
	}

	@Override
	public void onInputLow(BlockState state, World world, BlockPos pos, Direction side)
	{
		setEnabled(world, pos, true);
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack)
	{
		setEnabled(world, pos, !isPowered(world, pos, state.get(HORIZONTAL_FACING).getOpposite()));
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
	{
		if (world.isRemote)
			DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> GuiTimer.openGui(pos));
		return ActionResultType.SUCCESS;
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
		return new TileTimer();
	}

	public static class Delay extends BlockTimer
	{
		private final Type type;

		public Delay(Type type)
		{
			this.type = type;
		}

		public boolean isOnTimer()
		{
			return type == Type.ON;
		}

		@Override
		public boolean isOutputSide(BlockState state, Direction side)
		{
			return side == state.get(HORIZONTAL_FACING);
		}

		@Override
		public void onInputHigh(BlockState state, World world, BlockPos pos, Direction side)
		{
			setEnabled(world, pos, type == Type.ON);
			if (type == Type.OFF)
				setPoweredState(state, world, pos, true);
		}

		@Override
		public void onInputLow(BlockState state, World world, BlockPos pos, Direction side)
		{
			setEnabled(world, pos, type == Type.OFF);
			if (type == Type.ON)
				setPoweredState(state, world, pos, false);
		}

		@Override
		public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack)
		{
			boolean isPowered = isPowered(world, pos, state.get(HORIZONTAL_FACING).getOpposite());
			setEnabled(world, pos, (type == Type.ON && isPowered) || (type == Type.OFF && !isPowered));
		}

		public enum Type
		{
			ON,
			OFF
		}
	}
}
