package rzk.pcg.block;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class BlockGateBasic extends BlockGateBase
{
	private final Type type;

	public BlockGateBasic(Type type)
	{
		this.type = type;
	}

	@Override
	public boolean isInputSide(BlockState state, Direction side)
	{
		Direction facing = state.get(HORIZONTAL_FACING);
		switch (type)
		{
			case BUFFER: case BUFFER_ALL: case NOT: case NOT_ALL: return side == facing.getOpposite();
			case OR_2: case NOR_2: case AND_2: case NAND_2: case XOR_2: case XNOR_2: return side != facing & side != facing.getOpposite();
			case OR_3: case NOR_3: case AND_3: case NAND_3: case XOR_3: case XNOR_3: return side != facing;
		}
		return false;
	}

	@Override
	public boolean isOutputSide(BlockState state, Direction side)
	{
		Direction facing = state.get(HORIZONTAL_FACING);

		if (type == Type.BUFFER_ALL || type == Type.NOT_ALL)
			return side == facing || side == facing.rotateYCCW() || side == facing.rotateY();

		return side == facing;
	}

	@Override
	public boolean shouldBePowered(BlockState state, World world, BlockPos pos)
	{
		Direction facing = state.get(HORIZONTAL_FACING);
		boolean left = isPowered(world, pos, facing.rotateYCCW());
		boolean back = isPowered(world, pos, facing.getOpposite());
		boolean right = isPowered(world, pos, facing.rotateY());

		switch (type)
		{
			case BUFFER: case BUFFER_ALL: return back;
			case NOT: case NOT_ALL: return !back;
			case OR_2: return left || right;
			case OR_3: return left || back ||  right;
			case NOR_2: return !(left || right);
			case NOR_3: return !(left || back ||  right);
			case AND_2: return left && right;
			case AND_3: return left && back ||  right;
			case NAND_2: return !(left && right);
			case NAND_3: return !(left && back &&  right);
			case XOR_2: return left && !right || !left && right;
			case XOR_3: return left && !back && !right || !left && back && !right || !left && !back && right;
			case XNOR_2: return !(left && !right || !left && right);
			case XNOR_3: return !(left && !back && !right || !left && back && !right || !left && !back && right);
		}
		return false;
	}

	public enum Type
	{
		BUFFER,
		BUFFER_ALL,
		NOT,
		NOT_ALL,
		OR_2,
		OR_3,
		NOR_2,
		NOR_3,
		AND_2,
		AND_3,
		NAND_2,
		NAND_3,
		XOR_2,
		XOR_3,
		XNOR_2,
		XNOR_3
	}
}
