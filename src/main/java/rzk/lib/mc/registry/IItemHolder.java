package rzk.lib.mc.registry;

import net.minecraft.item.Item;

import java.util.List;

public interface IItemHolder
{
	List<Item> getItems();

	String getModID();
}
