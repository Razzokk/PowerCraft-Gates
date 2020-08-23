package rzk.pcg.registry;

import net.minecraft.block.Block;
import net.minecraftforge.registries.IForgeRegistry;
import rzk.lib.mc.registry.IModRegistry;
import rzk.pcg.PCGates;
import rzk.pcg.block.BlockGateBasic;
import rzk.pcg.block.BlockLatch;
import rzk.pcg.block.BlockSensor;
import rzk.pcg.block.BlockTimer;

public final class ModBlocks implements IModRegistry
{
	// Basic
	public static final Block GATE_BUFFER = registerBlock(new BlockGateBasic(BlockGateBasic.Type.BUFFER), "gate_buffer");
	public static final Block GATE_BUFFER_ALL = registerBlock(new BlockGateBasic(BlockGateBasic.Type.BUFFER_ALL), "gate_buffer_all");
	public static final Block GATE_NOT = registerBlock(new BlockGateBasic(BlockGateBasic.Type.NOT), "gate_not");
	public static final Block GATE_NOT_ALL = registerBlock(new BlockGateBasic(BlockGateBasic.Type.NOT_ALL), "gate_not_all");
	public static final Block GATE_OR_2 = registerBlock(new BlockGateBasic(BlockGateBasic.Type.OR_2), "gate_or_2");
	public static final Block GATE_OR_3 = registerBlock(new BlockGateBasic(BlockGateBasic.Type.OR_3), "gate_or_3");
	public static final Block GATE_NOR_2 = registerBlock(new BlockGateBasic(BlockGateBasic.Type.NOR_2), "gate_nor_2");
	public static final Block GATE_NOR_3 = registerBlock(new BlockGateBasic(BlockGateBasic.Type.NOR_3), "gate_nor_3");
	public static final Block GATE_AND_2 = registerBlock(new BlockGateBasic(BlockGateBasic.Type.AND_2), "gate_and_2");
	public static final Block GATE_AND_3 = registerBlock(new BlockGateBasic(BlockGateBasic.Type.AND_3), "gate_and_3");
	public static final Block GATE_NAND_2 = registerBlock(new BlockGateBasic(BlockGateBasic.Type.NAND_2), "gate_nand_2");
	public static final Block GATE_NAND_3 = registerBlock(new BlockGateBasic(BlockGateBasic.Type.NAND_3), "gate_nand_3");
	public static final Block GATE_XOR_2 = registerBlock(new BlockGateBasic(BlockGateBasic.Type.XOR_2), "gate_xor_2");
	public static final Block GATE_XOR_3 = registerBlock(new BlockGateBasic(BlockGateBasic.Type.XOR_3), "gate_xor_3");
	public static final Block GATE_XNOR_2 = registerBlock(new BlockGateBasic(BlockGateBasic.Type.XNOR_2), "gate_xnor_2");
	public static final Block GATE_XNOR_3 = registerBlock(new BlockGateBasic(BlockGateBasic.Type.XNOR_3), "gate_xnor_3");

	// Sensor
	public static final Block SENSOR_DAY = registerBlock(new BlockSensor(BlockSensor.Type.DAY), "sensor_day");
	public static final Block SENSOR_NIGHT = registerBlock(new BlockSensor(BlockSensor.Type.NIGHT), "sensor_night");
	public static final Block SENSOR_RAIN = registerBlock(new BlockSensor(BlockSensor.Type.RAIN), "sensor_rain");
	public static final Block SENSOR_THUNDER = registerBlock(new BlockSensor(BlockSensor.Type.THUNDER), "sensor_thunder");
	public static final Block SENSOR_CHEST_EMPTY = registerBlock(new BlockSensor.Chest(BlockSensor.Type.CHEST_EMPTY), "sensor_chest_empty");
	public static final Block SENSOR_CHEST_SPACE = registerBlock(new BlockSensor.Chest(BlockSensor.Type.CHEST_SPACE), "sensor_chest_space");
	public static final Block SENSOR_CHEST_FULL = registerBlock(new BlockSensor.Chest(BlockSensor.Type.CHEST_FULL), "sensor_chest_full");
	public static final Block SENSOR_RISING_EDGE = registerBlock(new BlockSensor.Edge(BlockSensor.Type.RISING_EDGE), "sensor_rising_edge");
	public static final Block SENSOR_FALLING_EDGE = registerBlock(new BlockSensor.Edge(BlockSensor.Type.FALLING_EDGE), "sensor_falling_edge");

	// Latch
	public static final Block LATCH_RS = registerBlock(new BlockLatch(BlockLatch.Type.RS), "latch_rs");
	public static final Block LATCH_SR = registerBlock(new BlockLatch(BlockLatch.Type.SR), "latch_sr");
	public static final Block LATCH_D = registerBlock(new BlockLatch.Edge(BlockLatch.Type.D), "latch_d");
	public static final Block LATCH_TOGGLE = registerBlock(new BlockLatch.Edge(BlockLatch.Type.TOGGLE), "latch_toggle");

	// Timer
	public static final Block TIMER = registerBlock(new BlockTimer(), "timer");
	public static final Block TIMER_ON_DELAY = registerBlock(new BlockTimer.Delay(BlockTimer.Delay.Type.ON), "timer_on_delay");
	public static final Block TIMER_OFF_DELAY = registerBlock(new BlockTimer.Delay(BlockTimer.Delay.Type.OFF), "timer_off_delay");

	public static void registerBlocks(IForgeRegistry<Block> registry) {}

	public static Block registerBlock(Block block, String name)
	{
		return IModRegistry.registerBlock(PCGates.MODID, block, name);
	}
}
