package rzk.pcg.registry;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rzk.pcg.PCGates;
import rzk.pcg.block.*;

import java.util.function.Function;
import java.util.function.Supplier;

public final class ModBlocks
{
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, PCGates.MOD_ID);
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, PCGates.MOD_ID);

	// Basic
	public static final RegistryObject<Block> GATE_BUFFER = registerBlock("gate_buffer", () -> new BlockGateBasic(BlockGateBasic.Type.BUFFER));
	public static final RegistryObject<Block> GATE_BUFFER_ALL = registerBlock("gate_buffer_all", () -> new BlockGateBasic(BlockGateBasic.Type.BUFFER_ALL));
	public static final RegistryObject<Block> GATE_NOT = registerBlock("gate_not", () -> new BlockGateBasic(BlockGateBasic.Type.NOT));
	public static final RegistryObject<Block> GATE_NOT_ALL = registerBlock("gate_not_all", () -> new BlockGateBasic(BlockGateBasic.Type.NOT_ALL));
	public static final RegistryObject<Block> GATE_OR_2 = registerBlock("gate_or_2", () -> new BlockGateBasic(BlockGateBasic.Type.OR_2));
	public static final RegistryObject<Block> GATE_OR_3 = registerBlock("gate_or_3", () -> new BlockGateBasic(BlockGateBasic.Type.OR_3));
	public static final RegistryObject<Block> GATE_NOR_2 = registerBlock("gate_nor_2", () -> new BlockGateBasic(BlockGateBasic.Type.NOR_2));
	public static final RegistryObject<Block> GATE_NOR_3 = registerBlock("gate_nor_3", () -> new BlockGateBasic(BlockGateBasic.Type.NOR_3));
	public static final RegistryObject<Block> GATE_AND_2 = registerBlock("gate_and_2", () -> new BlockGateBasic(BlockGateBasic.Type.AND_2));
	public static final RegistryObject<Block> GATE_AND_3 = registerBlock("gate_and_3", () -> new BlockGateBasic(BlockGateBasic.Type.AND_3));
	public static final RegistryObject<Block> GATE_NAND_2 = registerBlock("gate_nand_2", () -> new BlockGateBasic(BlockGateBasic.Type.NAND_2));
	public static final RegistryObject<Block> GATE_NAND_3 = registerBlock("gate_nand_3", () -> new BlockGateBasic(BlockGateBasic.Type.NAND_3));
	public static final RegistryObject<Block> GATE_XOR_2 = registerBlock("gate_xor_2", () -> new BlockGateBasic(BlockGateBasic.Type.XOR_2));
	public static final RegistryObject<Block> GATE_XOR_3 = registerBlock("gate_xor_3", () -> new BlockGateBasic(BlockGateBasic.Type.XOR_3));
	public static final RegistryObject<Block> GATE_XNOR_2 = registerBlock("gate_xnor_2", () -> new BlockGateBasic(BlockGateBasic.Type.XNOR_2));
	public static final RegistryObject<Block> GATE_XNOR_3 = registerBlock("gate_xnor_3", () -> new BlockGateBasic(BlockGateBasic.Type.XNOR_3));

	// Sensor
	public static final RegistryObject<Block> SENSOR_DAY = registerBlock("sensor_day", () -> new BlockSensor(BlockSensor.Type.DAY));
	public static final RegistryObject<Block> SENSOR_NIGHT = registerBlock("sensor_night", () -> new BlockSensor(BlockSensor.Type.NIGHT));
	public static final RegistryObject<Block> SENSOR_RAIN = registerBlock("sensor_rain", () -> new BlockSensor(BlockSensor.Type.RAIN));
	public static final RegistryObject<Block> SENSOR_THUNDER = registerBlock("sensor_thunder", () -> new BlockSensor(BlockSensor.Type.THUNDER));
	public static final RegistryObject<Block> SENSOR_CHEST_EMPTY = registerBlock("sensor_chest_empty", () -> new BlockSensor.Chest(BlockSensor.Type.CHEST_EMPTY));
	public static final RegistryObject<Block> SENSOR_CHEST_SPACE = registerBlock("sensor_chest_space", () -> new BlockSensor.Chest(BlockSensor.Type.CHEST_SPACE));
	public static final RegistryObject<Block> SENSOR_CHEST_FULL = registerBlock("sensor_chest_full", () -> new BlockSensor.Chest(BlockSensor.Type.CHEST_FULL));
	public static final RegistryObject<Block> SENSOR_RISING_EDGE = registerBlock("sensor_rising_edge", () -> new BlockSensor.Edge(BlockSensor.Type.RISING_EDGE));
	public static final RegistryObject<Block> SENSOR_FALLING_EDGE = registerBlock("sensor_falling_edge", () -> new BlockSensor.Edge(BlockSensor.Type.FALLING_EDGE));

	// Latch
	public static final RegistryObject<Block> LATCH_RS = registerBlock("latch_rs", () -> new BlockLatch(BlockLatch.Type.RS));
	public static final RegistryObject<Block> LATCH_SR = registerBlock("latch_sr", () -> new BlockLatch(BlockLatch.Type.SR));
	public static final RegistryObject<Block> LATCH_D = registerBlock("latch_d", () -> new BlockLatch.Edge(BlockLatch.Type.D));
	public static final RegistryObject<Block> LATCH_TOGGLE = registerBlock("latch_toggle", () -> new BlockLatch.Edge(BlockLatch.Type.TOGGLE));

	// Timer
	public static final RegistryObject<Block> TIMER = registerBlock("timer", BlockTimer::new);
	public static final RegistryObject<Block> TIMER_ON_DELAY = registerBlock("timer_on_delay", () -> new BlockTimer.Delay(BlockTimer.Delay.Type.ON));
	public static final RegistryObject<Block> TIMER_OFF_DELAY = registerBlock("timer_off_delay", () -> new BlockTimer.Delay(BlockTimer.Delay.Type.OFF));

	// Counter
	public static final RegistryObject<Block> COUNTER = registerBlock("counter", BlockCounter::new);

	public static RegistryObject<Block> registerBlock(String name, Supplier<Block> blockSupplier, Function<Block, BlockItem> itemProvider)
	{
		RegistryObject<Block> block = BLOCKS.register(name, blockSupplier);
		ITEMS.register(name, () -> itemProvider.apply(block.get()));
		return block;
	}

	public static RegistryObject<Block> registerBlock(String name, Supplier<Block> blockSupplier)
	{
		return registerBlock(name, blockSupplier, block -> new BlockItem(block, ModItems.defaultItemProperties()));
	}

	public static RegistryObject<Block> registerBlockNoItem(String name, Supplier<Block> blockSupplier)
	{
		return BLOCKS.register(name, blockSupplier);
	}
}
