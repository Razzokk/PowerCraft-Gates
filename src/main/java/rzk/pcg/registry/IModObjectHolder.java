package rzk.pcg.registry;

import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.List;

public interface IModObjectHolder<T extends IForgeRegistryEntry<T>>
{
	List<T> getObjects();
}
