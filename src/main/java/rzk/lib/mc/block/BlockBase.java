package rzk.lib.mc.block;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class BlockBase extends Block implements IItemProvider
{
	public BlockBase(Properties properties)
	{
		super(properties);
	}

	@Override
	public BlockItem createItem()
	{
		return new BlockItem(this, new Item.Properties());
	}
}
