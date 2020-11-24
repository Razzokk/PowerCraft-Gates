package rzk.lib.mc.util;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class ObjectUtils
{
	public static <T> boolean isCastable(Object obj, Class<T> clazz)
	{
		return clazz != null && clazz.isInstance(obj);
	}

	@SuppressWarnings("unchecked")
	public static <T> T castOrElseUse(Object obj, Class<T> clazz, T elseObj)
	{
		if (isCastable(obj, clazz))
			return (T) obj;
		return elseObj;
	}

	public static <T> T castOrElseGet(Object obj, Class<T> clazz, Supplier<? extends T> supplier)
	{
		return castOrElseUse(obj, clazz, supplier.get());
	}

	public static <T> T cast(Object obj, Class<T> clazz)
	{
		return castOrElseUse(obj, clazz, null);
	}

	public static <T> boolean ifCastable(Object obj, Class<T> clazz, Consumer<? super T> consumer)
	{
		if (isCastable(obj, clazz))
		{
			consumer.accept(cast(obj, clazz));
			return true;
		}
		return false;
	}

	public static <T> boolean ifNotCastable(Object obj, Class<T> clazz, Runnable runnable)
	{
		if (!isCastable(obj, clazz))
		{
			runnable.run();
			return true;
		}
		return false;
	}

	public static <T> boolean ifCastableOrElse(Object obj, Class<T> clazz, Consumer<? super T> consumer, Runnable runnable)
	{
		if (ifCastable(obj, clazz, consumer))
			return true;
		runnable.run();
		return false;
	}

	public static <T, U> U mapIfCastableOrElseUse(Object obj, Class<T> clazz, Function<T, U> function, U elseObj)
	{
		T castObj = cast(obj, clazz);
		if (castObj != null)
			return function.apply(castObj);
		return elseObj;
	}

	public static <T, U> U mapIfCastableOrElseGet(Object obj, Class<T> clazz, Function<T, U> function, Supplier<? extends U> supplier)
	{
		return mapIfCastableOrElseUse(obj, clazz, function, supplier.get());
	}

	public static <T, U> U mapIfCastable(Object obj, Class<T> clazz, Function<T, U> function)
	{
		return mapIfCastableOrElseUse(obj, clazz, function, null);
	}
}
