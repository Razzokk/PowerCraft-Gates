package rzk.pcg.packet;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import rzk.lib.mc.packet.Packet;
import rzk.pcg.PCGates;

import java.util.function.Function;

public class PacketHandler
{
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(PCGates.MOD_ID, "main_channel"),
			() -> PROTOCOL_VERSION,
			PROTOCOL_VERSION::equals,
			PROTOCOL_VERSION::equals
	);
	private static int id = 0;

	public static void registerMessages()
	{
		registerMessage(PacketTimer.class, PacketTimer::new);
		registerMessage(PacketCounter.class, PacketCounter::new);
	}

	public static <P extends Packet> void registerMessage(Class<P> packetType, Function<PacketBuffer, P> decoder)
	{
		INSTANCE.registerMessage(id++, packetType, Packet::toBytes, decoder, Packet::handle);
	}
}
