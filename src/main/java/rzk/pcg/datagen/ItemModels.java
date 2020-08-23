package rzk.pcg.datagen;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import rzk.lib.mc.registry.IModRegistry;
import rzk.pcg.registry.ModItems;

public class ItemModels extends ItemModelProvider
{
	public ItemModels(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper)
	{
		super(generator, modid, existingFileHelper);
	}

	@Override
	protected void registerModels()
	{
		IModRegistry.BLOCKS.forEach(this::gate);
		simpleItem(ModItems.BASE_PLATE);
	}

	public void simpleItem(Item item)
	{
		String itemName = name(item);
		singleTexture(itemName, mcLoc("item/generated"), "layer0", modLoc("items/" + itemName));
	}

	public void gate(Block block)
	{
		Item item = block.asItem();
		withExistingParent(name(item), modLoc("block/" + name(item) + "_off"));
	}

	public static String name(Item item)
	{
		return item.getRegistryName().getPath();
	}

	@Override
	public String getName()
	{
		return "PCGates Item Model Provider";
	}
}
