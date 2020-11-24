package rzk.pcg;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import rzk.pcg.packet.PacketHandler;
import rzk.pcg.proxy.ClientProxy;
import rzk.pcg.registry.ModBlocks;
import rzk.pcg.registry.ModItems;
import rzk.pcg.registry.ModTiles;

import java.util.Comparator;

@Mod(PCGates.MOD_ID)
public class PCGates
{
	public static final String MOD_ID = "pcg";

	public static final ItemGroup ITEM_GROUP_PC_GATES = new ItemGroup(MOD_ID)
	{
		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack createIcon()
		{
			return new ItemStack(ModBlocks.GATE_AND_3.get());
		}
	};

	public PCGates()
	{
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

		ModBlocks.BLOCKS.register(eventBus);
		ModBlocks.ITEMS.register(eventBus);
		ModItems.ITEMS.register(eventBus);
		ModTiles.TILES.register(eventBus);

		eventBus.addListener(this::setup);
		eventBus.addListener(ClientProxy::clientSetup);
	}

	private void setup(FMLCommonSetupEvent event)
	{
		PacketHandler.registerMessages();
	}
}
