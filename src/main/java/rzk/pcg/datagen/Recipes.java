package rzk.pcg.datagen;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import rzk.pcg.registry.ModBlocks;
import rzk.pcg.registry.ModItems;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider
{
	public Recipes(DataGenerator generator)
	{
		super(generator);
	}

	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer)
	{
		ShapedRecipeBuilder.shapedRecipe(ModItems.BASE_PLATE, 8)
				.patternLine(" P ")
				.patternLine("PSP")
				.patternLine(" P ")
				.key('P', Blocks.SMOOTH_STONE_SLAB)
				.key('S', Blocks.STONE)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(Blocks.SMOOTH_STONE_SLAB, Blocks.STONE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.GATE_BUFFER)
				.patternLine("R")
				.patternLine("P")
				.patternLine("R")
				.key('R', Items.REDSTONE)
				.key('P', ModItems.BASE_PLATE)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.GATE_BUFFER_ALL)
				.patternLine(" R ")
				.patternLine("RPR")
				.patternLine(" R ")
				.key('R', Items.REDSTONE)
				.key('P', ModItems.BASE_PLATE)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.GATE_NOT)
				.patternLine("T")
				.patternLine("P")
				.patternLine("R")
				.key('T', Blocks.REDSTONE_TORCH)
				.key('R', Items.REDSTONE)
				.key('P', ModItems.BASE_PLATE)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.GATE_NOT_ALL)
				.patternLine(" T ")
				.patternLine("TPT")
				.patternLine(" R ")
				.key('T', Blocks.REDSTONE_TORCH)
				.key('R', Items.REDSTONE)
				.key('P', ModItems.BASE_PLATE)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.GATE_OR_2)
				.patternLine(" R ")
				.patternLine("RPR")
				.key('R', Items.REDSTONE)
				.key('P', ModItems.BASE_PLATE)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.GATE_OR_3)
				.patternLine(" R ")
				.patternLine(" P ")
				.patternLine("RRR")
				.key('R', Items.REDSTONE)
				.key('P', ModItems.BASE_PLATE)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.GATE_NOR_2)
				.patternLine(" T ")
				.patternLine("RPR")
				.key('R', Items.REDSTONE)
				.key('T', Blocks.REDSTONE_TORCH)
				.key('P', ModItems.BASE_PLATE)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.GATE_NOR_3)
				.patternLine(" T ")
				.patternLine(" P ")
				.patternLine("RRR")
				.key('R', Items.REDSTONE)
				.key('T', Blocks.REDSTONE_TORCH)
				.key('P', ModItems.BASE_PLATE)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.GATE_AND_2)
				.patternLine(" R ")
				.patternLine("PPP")
				.patternLine("R R")
				.key('R', Items.REDSTONE)
				.key('P', ModItems.BASE_PLATE)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.GATE_AND_3)
				.patternLine(" R ")
				.patternLine("PPP")
				.patternLine("RRR")
				.key('R', Items.REDSTONE)
				.key('P', ModItems.BASE_PLATE)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.GATE_NAND_2)
				.patternLine(" T ")
				.patternLine("PPP")
				.patternLine("R R")
				.key('T', Blocks.REDSTONE_TORCH)
				.key('R', Items.REDSTONE)
				.key('P', ModItems.BASE_PLATE)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.GATE_NAND_3)
				.patternLine(" T ")
				.patternLine("PPP")
				.patternLine("RRR")
				.key('T', Blocks.REDSTONE_TORCH)
				.key('R', Items.REDSTONE)
				.key('P', ModItems.BASE_PLATE)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.GATE_XOR_2)
				.patternLine(" R ")
				.patternLine("TPR")
				.key('T', Blocks.REDSTONE_TORCH)
				.key('R', Items.REDSTONE)
				.key('P', ModItems.BASE_PLATE)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.GATE_XOR_3)
				.patternLine(" R ")
				.patternLine(" P ")
				.patternLine("RTR")
				.key('T', Blocks.REDSTONE_TORCH)
				.key('R', Items.REDSTONE)
				.key('P', ModItems.BASE_PLATE)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.GATE_XNOR_2)
				.patternLine(" T ")
				.patternLine("TPR")
				.key('T', Blocks.REDSTONE_TORCH)
				.key('R', Items.REDSTONE)
				.key('P', ModItems.BASE_PLATE)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.GATE_XNOR_3)
				.patternLine(" T ")
				.patternLine(" P ")
				.patternLine("RTR")
				.key('R', Items.REDSTONE)
				.key('T', Blocks.REDSTONE_TORCH)
				.key('P', ModItems.BASE_PLATE)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.SENSOR_DAY)
				.patternLine(" Q ")
				.patternLine("GPG")
				.patternLine(" R ")
				.key('Q', Items.QUARTZ)
				.key('G', Items.GLOWSTONE_DUST)
				.key('P', ModItems.BASE_PLATE)
				.key('R', Items.REDSTONE)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.SENSOR_NIGHT)
				.patternLine(" Q ")
				.patternLine("GPG")
				.patternLine(" T ")
				.key('Q', Items.QUARTZ)
				.key('G', Items.GLOWSTONE_DUST)
				.key('P', ModItems.BASE_PLATE)
				.key('T', Blocks.REDSTONE_TORCH)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.SENSOR_RAIN)
				.patternLine(" L ")
				.patternLine("GPG")
				.patternLine(" R ")
				.key('L', Items.LAPIS_LAZULI)
				.key('G', Items.GLOWSTONE_DUST)
				.key('P', ModItems.BASE_PLATE)
				.key('R', Items.REDSTONE)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.SENSOR_THUNDER)
				.patternLine(" I ")
				.patternLine("GPG")
				.patternLine(" R ")
				.key('I', Items.IRON_INGOT)
				.key('G', Items.GLOWSTONE_DUST)
				.key('P', ModItems.BASE_PLATE)
				.key('R', Items.REDSTONE)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.SENSOR_CHEST_EMPTY)
				.patternLine("R")
				.patternLine("P")
				.patternLine("C")
				.key('R', Items.REDSTONE)
				.key('P', ModItems.BASE_PLATE)
				.key('C', Blocks.CHEST)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.SENSOR_CHEST_SPACE)
				.patternLine(" R ")
				.patternLine("CPC")
				.key('R', Items.REDSTONE)
				.key('P', ModItems.BASE_PLATE)
				.key('C', Blocks.CHEST)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.SENSOR_CHEST_FULL)
				.patternLine("T")
				.patternLine("P")
				.patternLine("C")
				.key('T', Blocks.REDSTONE_TORCH)
				.key('P', ModItems.BASE_PLATE)
				.key('C', Blocks.CHEST)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.SENSOR_RISING_EDGE)
				.patternLine(" R ")
				.patternLine("RPT")
				.patternLine(" Q ")
				.key('Q', Items.QUARTZ)
				.key('R', Items.REDSTONE)
				.key('P', ModItems.BASE_PLATE)
				.key('T', Blocks.REDSTONE_TORCH)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.SENSOR_FALLING_EDGE)
				.patternLine(" R ")
				.patternLine("TPR")
				.patternLine(" Q ")
				.key('Q', Items.QUARTZ)
				.key('R', Items.REDSTONE)
				.key('P', ModItems.BASE_PLATE)
				.key('T', Blocks.REDSTONE_TORCH)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.LATCH_RS)
				.patternLine(" L ")
				.patternLine("RPR")
				.patternLine(" R ")
				.key('L', Blocks.LEVER)
				.key('R', Items.REDSTONE)
				.key('P', ModItems.BASE_PLATE)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.LATCH_SR)
				.patternLine(" L ")
				.patternLine("RPR")
				.patternLine(" T ")
				.key('L', Blocks.LEVER)
				.key('R', Items.REDSTONE)
				.key('P', ModItems.BASE_PLATE)
				.key('T', Blocks.REDSTONE_TORCH)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.LATCH_D)
				.patternLine(" L ")
				.patternLine("RPQ")
				.patternLine(" R ")
				.key('L', Blocks.LEVER)
				.key('R', Items.REDSTONE)
				.key('P', ModItems.BASE_PLATE)
				.key('Q', Items.QUARTZ)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.LATCH_TOGGLE)
				.patternLine(" L ")
				.patternLine("RPR")
				.patternLine(" L ")
				.key('L', Blocks.LEVER)
				.key('R', Items.REDSTONE)
				.key('P', ModItems.BASE_PLATE)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.TIMER)
				.patternLine(" C ")
				.patternLine("RPR")
				.patternLine(" R ")
				.key('C', Items.CLOCK)
				.key('R', Items.REDSTONE)
				.key('P', ModItems.BASE_PLATE)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.TIMER_ON_DELAY)
				.patternLine(" C ")
				.patternLine("RPR")
				.patternLine(" D ")
				.key('C', Items.CLOCK)
				.key('R', Items.REDSTONE)
				.key('P', ModItems.BASE_PLATE)
				.key('D', Blocks.REPEATER)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModBlocks.TIMER_OFF_DELAY)
				.patternLine(" C ")
				.patternLine("TPT")
				.patternLine(" R ")
				.key('C', Items.CLOCK)
				.key('T', Items.REDSTONE_TORCH)
				.key('P', ModItems.BASE_PLATE)
				.key('R', Blocks.REPEATER)
				.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.BASE_PLATE))
				.build(consumer);
	}
}
