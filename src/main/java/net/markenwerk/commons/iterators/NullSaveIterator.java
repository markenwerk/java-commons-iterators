/*
 * Copyright (c) 2015 Torsten Krause, Markenwerk GmbH
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

/**
 * A {@link NullSaveIterator} is an {@link Iterator} that can be wrapped around
 * a given {@link Iterator} and always behaves correctly, even if the given
 * {@link Iterator} is {@literal null} (by behaving like an
 * {@link EmptyIterator}).
 * 
 * <p>
 * Calling any of the methods declared by {@link Iterator} will be relayed to
 * the given {@link Iterator}, if present.
 * 
 * <p>
 * For convenience, it is possible to constuct a {@link NullSaveIterator} from
 * an {@link Iterable} even if the given {@link Iterable} is {@literal null}.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class NullSaveIterator<Payload> implements Iterator<Payload> {

	private final Iterator<Payload> iterator;

	/**
	 * Creates a new {@link NullSaveIterator} from the given {@link Iterable}.
	 * 
	 * @param iterable
	 *            The {@link Iterable} to obtain an {@link Iterator} from,
	 *            around which the new {@link NullSaveIterator} will be wrapped,
	 *            or {@literal null}.
	 */
	public NullSaveIterator(Iterable<Payload> iterable) {
		this.iterator = null == iterable ? new EmptyIterator<Payload>() : iterable.iterator();
	}

	/**
	 * Creates a new {@link NullSaveIterator} from the given {@link Iterator}.
	 * 
	 * @param iterator
	 *            The {@link Iterator}, around which the new
	 *            {@link NullSaveIterator} will be wrapped, or {@literal null}.
	 */
	public NullSaveIterator(Iterator<Payload> iterator) {
		this.iterator = null == iterator ? new EmptyIterator<Payload>() : iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Payload next() {
		return iterator.next();
	}

	@Override
	public void remove() {
		iterator.remove();
	}

}
