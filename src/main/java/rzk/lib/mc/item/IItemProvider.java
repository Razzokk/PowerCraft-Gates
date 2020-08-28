package rzk.lib.mc.item;

import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public interface IItemProvider
{
	BlockItem NO_ITEM = new BlockItem(Blocks.AIR, new Item.Properties());

	BlockItem createItem();
}
