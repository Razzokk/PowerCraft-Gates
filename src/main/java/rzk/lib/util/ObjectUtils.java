package rzk.lib.util;

import java.util.Optional;
import java.util.function.Consumer;

public final class ObjectUtils
{
	public static <T> Optional<T> cast(Object obj, Class<T> clazz)
	{
		if (clazz.isInstance(obj))
			return Optional.of(clazz.cast(obj));

		return Optional.empty();
	}

	public static <T> void castAndDo(Object obj, Class<T> clazz, Consumer<? super T> consumer)
	{
		cast(obj, clazz).ifPresent(consumer);
	}
}
