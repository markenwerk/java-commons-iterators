/*
 * Copyright (c) 2016 Torsten Krause, Markenwerk GmbH
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.markenwerk.commons.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An {@link PrefixedIterator} is an {@link Iterator} that can be wrapped around
 * a given {@link Iterator} and yields some given prefix values before yielding
 * all values yielded by the given {@link Iterator}.
 * 
 * <p>
 * Calling {@link IntegerArrayIterator#remove()} calls {@link Iterator#remove()}
 * removed on the wrapped {@link Iterator}, if, and only iff, the last call to
 * {@link IntegerArrayIterator#next()} didn't return a prefix value.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 2.1.0
 */
public final class PrefixedIterator<Payload> implements Iterator<Payload> {

	private final Iterator<? extends Payload> iterator;

	private final Payload[] prefixes;

	private boolean prefixing;

	private int prefixIndex = -1;

	private boolean nextCalled;

	private boolean currentRemovable;

	/**
	 * Creates a new {@link PrefixedIterator} from the given {@link Iterator}
	 * and the given prefix values.
	 * 
	 * @param iterator
	 *            The {@link Iterator}, around which the new
	 *            {@link NullFreeIterator} will be wrapped.
	 * @param prefixes
	 *            The prefixes to be yielded.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Iterator} is {@literal null}.
	 */
	public PrefixedIterator(Iterator<? extends Payload> iterator, Payload... prefixes) throws IllegalArgumentException {
		if (null == iterator) {
			throw new IllegalArgumentException("iterator is null");
		}
		if (null == prefixes) {
			throw new IllegalArgumentException("prefixes is null");
		}
		this.iterator = iterator;
		this.prefixes = prefixes;
		prefixing = 0 != prefixes.length;
	}

	@Override
	public boolean hasNext() {
		return prefixing || iterator.hasNext();
	}

	@Override
	public Payload next() throws NoSuchElementException {
		if (!hasNext()) {
			throw new NoSuchElementException("PrefixedIterator has no further element");
		} else {
			nextCalled = true;
			if (prefixing) {
				prefixing = ++prefixIndex != prefixes.length - 1;
				currentRemovable = false;
				return prefixes[prefixIndex];
			} else {
				Payload current = iterator.next();
				currentRemovable = true;
				return current;
			}
		}
	}

	@Override
	public void remove() throws IllegalStateException {
		if (!nextCalled) {
			throw new IllegalStateException("next() hasn't been called yet");
		} else if (currentRemovable) {
			iterator.remove();
		}
	}

}
