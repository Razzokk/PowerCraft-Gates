package rzk.lib.mc.util;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class TaskScheduler
{
	private static final Object2ObjectMap<World, ObjectList<ScheduledTask>> scheduledWorldTasks = new Object2ObjectArrayMap<>();

	public static void scheduleTask(ScheduledTask scheduledTask)
	{
		if (scheduledTask != null && scheduledTask.task != null && scheduledTask.delay >= 0)
		{
			World world = scheduledTask.world;

			if (!scheduledWorldTasks.containsKey(world))
				scheduledWorldTasks.put(scheduledTask.world, new ObjectArrayList<>());

			scheduledWorldTasks.get(world).add(scheduledTask);
		}
	}

	public static void scheduleTask(World world, int delay, Runnable task)
	{
		scheduleTask(new ScheduledTask(world, delay, task));
	}

	public static void scheduleTask(World world, Runnable task)
	{
		scheduleTask(world, 0, task);
	}

	public static void onWorldTick(World world)
	{
		if (scheduledWorldTasks.containsKey(world))
		{
			for (ScheduledTask scheduledTask : scheduledWorldTasks.get(world))
			{
				if (world.getGameTime() >= scheduledTask.scheduledTime + scheduledTask.delay)
				{
					scheduledTask.task.run();
					scheduledWorldTasks.get(world).remove(scheduledTask);
				}
			}
		}
	}

	public static void onWorldUnload(World world)
	{
		scheduledWorldTasks.remove(world);
	}

	public static void onServerStop(MinecraftServer server)
	{
		scheduledWorldTasks.clear();
	}

	static class ScheduledTask
	{
		private final World world;
		private final int delay;
		private final Runnable task;
		private final long scheduledTime;

		public ScheduledTask(World world, int delay, Runnable task)
		{
			this.world = world;
			this.delay = delay;
			this.task = task;
			this.scheduledTime = world.getWorldInfo().getGameTime();
		}
	}
}
