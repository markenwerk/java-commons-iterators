package net.markenwerk.commons.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.markenwerk.commons.interfaces.Predicate;

public abstract class Iterators {

	private Iterators() {
	}

	public static <Payload> int firstIndexOf(Iterator<? extends Payload> iterator, int firstIndex,
			Predicate<Payload> predicate) throws IllegalArgumentException, NoSuchElementException {
		if (null == iterator) {
			throw new IllegalArgumentException("The given iterator is null");
		} else if (firstIndex < 0) {
			throw new IllegalArgumentException("The given first index is negative: " + firstIndex);
		} else if (null == predicate) {
			throw new IllegalArgumentException("The given predicate is null");
		}
		int index = 0;
		while (iterator.hasNext()) {
			Payload payload = iterator.next();
			if (index >= firstIndex && predicate.test(payload)) {
				return index;
			}
			index += 1;
		}
		throw new NoSuchElementException("No payload value in the given iterator satisfies the given predicate");
	}

}
