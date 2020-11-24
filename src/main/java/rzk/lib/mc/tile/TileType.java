package rzk.lib.mc.tile;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import org.apache.commons.lang3.ArrayUtils;

import java.util.function.Supplier;

public final class TileType<T extends TileEntity> extends TileEntityType<T>
{
	private final boolean hasValidBlocks;

	public TileType(Supplier<? extends T> factory)
	{
		super(factory, null, null);
		hasValidBlocks = false;
	}

	public TileType(Supplier<? extends T> factory, Block... validBlocks)
	{
		super(factory, ImmutableSet.copyOf(validBlocks), null);
		hasValidBlocks = ArrayUtils.isNotEmpty(validBlocks);
	}

	@Override
	public boolean isValidBlock(Block block)
	{
		if (hasValidBlocks)
			return super.isValidBlock(block);
		return true;
	}
}
