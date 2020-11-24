package rzk.pcg.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rzk.lib.mc.block.BlockRedstoneDeviceEdge;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;
import static net.minecraft.state.properties.BlockStateProperties.POWERED;

public abstract class BlockGateEdgeBase extends BlockRedstoneDeviceEdge
{
	public BlockGateEdgeBase()
	{
		super(Properties.create(Material.ROCK).hardnessAndResistance(0.5f).sound(SoundType.STONE));
		setDefaultState(getDefaultState().with(HORIZONTAL_FACING, Direction.NORTH));
	}

	public abstract boolean shouldBePowered(BlockState state, World world, BlockPos pos);

	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand)
	{
		boolean shouldBePowered = shouldBePowered(state, world, pos);
		if (state.get(POWERED) != shouldBePowered)
			setPoweredState(state, world, pos, shouldBePowered);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context)
	{
		return getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing());
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(POWERED, HORIZONTAL_FACING);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return BlockGateBase.GATE_SHAPE;
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder)
	{
		return Collections.singletonList(new ItemStack(this));
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		if (state.get(POWERED))
		{
			double d0 = (double) pos.getX() + 0.5D + (rand.nextDouble() - 0.5D) * 0.2D;
			double d1 = (double) pos.getY() + 0.3D + (rand.nextDouble() - 0.5D) * 0.2D;
			double d2 = (double) pos.getZ() + 0.5D + (rand.nextDouble() - 0.5D) * 0.2D;

			world.addParticle(RedstoneParticleData.REDSTONE_DUST, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
	}
}
