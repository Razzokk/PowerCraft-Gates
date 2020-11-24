package rzk.pcg.registry;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rzk.pcg.PCGates;

public final class ModItems
{
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, PCGates.MOD_ID);

	public static final RegistryObject<Item> BASE_PLATE = ITEMS.register("base_plate", () -> new Item(defaultItemProperties()));

	public static Item.Properties defaultItemProperties()
	{
		return new Item.Properties().group(PCGates.ITEM_GROUP_PC_GATES);
	}
}
