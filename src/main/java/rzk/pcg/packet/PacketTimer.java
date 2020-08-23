package rzk.pcg.packet;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;
import rzk.lib.mc.packet.Packet;
import rzk.pcg.tile.TileTimer;

import java.util.function.Supplier;

public class PacketTimer extends Packet
{
	private int delay;
	private BlockPos timerPos;

	public PacketTimer(int delay, BlockPos timerPos)
	{
		this.delay = delay;
		this.timerPos = timerPos;
	}

	PacketTimer(PacketBuffer buffer)
	{
		super(buffer);
		delay = buffer.readInt();
		timerPos = BlockPos.fromLong(buffer.readLong());
	}

	@Override
	public void toBytes(PacketBuffer buffer)
	{
		buffer.writeInt(delay);
		buffer.writeLong(timerPos.toLong());
	}

	@Override
	public void handle(Supplier<NetworkEvent.Context> ctx)
	{
		ctx.get().enqueueWork(() ->
		{
			ServerPlayerEntity player = ctx.get().getSender();
			if (player != null)
			{
				ServerWorld world = player.getServerWorld();
				if (world.isBlockLoaded(timerPos) && world.getTileEntity(timerPos) instanceof TileTimer)
				{
					((TileTimer) world.getTileEntity(timerPos)).setDelay(delay);
					BlockState state = world.getBlockState(timerPos);
					world.notifyBlockUpdate(timerPos, state, state, 2);
				}
			}

		});
		ctx.get().setPacketHandled(true);
	}
}
