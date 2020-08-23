package rzk.lib.mc.registry;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import rzk.lib.mc.block.IItemProvider;

import java.util.List;

public interface IBlockHolder
{
	List<Block> getBlocks();

	static Block registerBlock(String modid, Block block, String name)
	{
		BLOCKS.add(block.setRegistryName(modid, name));
		if (block instanceof IItemProvider)
		{
			BlockItem item = ((IItemProvider) block).createItem();
			if (item != IItemProvider.NO_ITEM)
				registerItem(modid, item, name);
		}
		return block;
	}
}
