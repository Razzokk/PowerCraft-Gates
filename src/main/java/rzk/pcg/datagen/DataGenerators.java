package rzk.pcg.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import rzk.pcg.PCGates;

import java.io.IOException;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators
{
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) throws IOException
	{
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper fileHelper = event.getExistingFileHelper();

		if (event.includeClient())
		{
			generator.addProvider(new BlockModels(generator, PCGates.MODID, fileHelper));
			generator.addProvider(new ItemModels(generator, PCGates.MODID, fileHelper));
		}

		if (event.includeServer())
		{
			generator.addProvider(new Recipes(generator));
		}
	}
}
