package rzk.pcg;

import com.google.common.collect.Ordering;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rzk.lib.mc.registry.IModRegistry;
import rzk.pcg.packet.PacketHandler;
import rzk.pcg.proxy.ClientProxy;
import rzk.pcg.proxy.IProxy;
import rzk.pcg.proxy.ServerProxy;
import rzk.pcg.registry.ModBlocks;

import java.util.Comparator;

@Mod(PCGates.MODID)
public class PCGates
{
	public static final String MODID = "pcg";
	public static final Logger LOGGER = LogManager.getLogger();

	public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

	public static Comparator<ItemStack> comparator;

	public static final ItemGroup ITEM_GROUP_PC_GATES = new ItemGroup("pc_gates") {

		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack createIcon()
		{
			return ModBlocks.GATE_AND_3.asItem().getDefaultInstance();
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public void fill(NonNullList<ItemStack> items)
		{
			super.fill(items);
			items.sort(comparator);
		}
	};

	public PCGates()
	{
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

		eventBus.register(IModRegistry.class);
		eventBus.addListener(this::preInit);
	}

	private void preInit(FMLCommonSetupEvent event)
	{
		PacketHandler.registerMessages();
		comparator = Ordering.explicit(IModRegistry.ITEMS).onResultOf(ItemStack::getItem);
	}
}
