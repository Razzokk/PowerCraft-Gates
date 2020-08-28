package rzk.lib.mc.packet;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public abstract class Packet
{
	public Packet() {}

	public Packet(PacketBuffer buffer) {}

	public abstract void toBytes(PacketBuffer buffer);

	public abstract void handle(Supplier<NetworkEvent.Context> ctx);
}
