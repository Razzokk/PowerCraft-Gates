package rzk.pcg.packet;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;
import rzk.lib.mc.packet.Packet;
import rzk.lib.mc.util.WorldUtils;
import rzk.pcg.tile.TileCounter;

import java.util.function.Supplier;

public class PacketCounter extends Packet
{
	private int maxCount;
	private BlockPos pos;

	public PacketCounter(int maxCount, BlockPos pos)
	{
		this.maxCount = maxCount;
		this.pos = pos;
	}

	PacketCounter(PacketBuffer buffer)
	{
		super(buffer);
		maxCount = buffer.readInt();
		pos = BlockPos.fromLong(buffer.readLong());
	}

	@Override
	public void toBytes(PacketBuffer buffer)
	{
		buffer.writeInt(maxCount);
		buffer.writeLong(pos.toLong());
	}

	@Override
	public void handle(Supplier<NetworkEvent.Context> ctx)
	{
		ctx.get().enqueueWork(() ->
		{
			ServerPlayerEntity player = ctx.get().getSender();
			ServerWorld world;
			if (player != null && (world = player.getServerWorld()).isBlockLoaded(pos))
				WorldUtils.ifTilePresent(world, pos, TileCounter.class, tile -> tile.setMaxCount(maxCount));
		});
		ctx.get().setPacketHandled(true);
	}
}
