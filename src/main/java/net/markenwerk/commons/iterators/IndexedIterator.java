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

import net.markenwerk.commons.datastructures.Entry;

/**
 * An {@link IndexedIterator} is an {@link Iterator} that can be wrapped around
 * a given {@link Iterator} and every value yielded by the given
 * {@link Iterator} as an {@link Entry} whose key is the index of the yielded
 * element.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 2.2.0
 */
public final class IndexedIterator<Payload> implements Iterator<Entry<Integer, Payload>> {

	private final Iterator<? extends Payload> iterator;

	int index = -1;

	/**
	 * Creates a new {@link IndexedIterator} from the given {@link Iterator}.
	 * 
	 * @param iterator
	 *            The {@link Iterator}, around which the new
	 *            {@link NullFreeIterator} will be wrapped.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Iterator} is {@literal null}.
	 */
	public IndexedIterator(Iterator<? extends Payload> iterator) throws IllegalArgumentException {
		if (null == iterator) {
			throw new IllegalArgumentException("iterator is null");
		}
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Entry<Integer, Payload> next() throws NoSuchElementException {
		if (!hasNext()) {
			throw new NoSuchElementException("InfixedIterator has no further element");
		} else {
			return new Entry<Integer, Payload>(++index, iterator.next());
		}
	}

	@Override
	public void remove() throws IllegalStateException {
		if (-1 == index) {
			throw new IllegalStateException("next() hasn't been called yet");
		} else {
			iterator.remove();
		}
	}
	
}
