/*
 * Copyright (c) 2015, 2016 Torsten Krause, Markenwerk GmbH
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
 * A {@link NullFreeIterator} is an {@link Iterator} that can be wrapped around
 * a given {@link Iterator} and filters out {@literal null} values.
 * 
 * <p>
 * Calling {@link NullFreeIterator#next()} will never return {@literal null} and
 * calling {@link NullFreeIterator#hasNext()} will never return {@literal true},
 * unless a value that is not {@literal null} is available.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class NullFreeIterator<Payload> implements Iterator<Payload> {

	private final Iterator<? extends Payload> iterator;

	private boolean nextPrepared;

	private Payload next;

	/**
	 * Creates a new {@link NullFreeIterator} from the given {@link Iterator}.
	 * 
	 * @param iterator
	 *            The {@link Iterator}, around which the new
	 *            {@link NullFreeIterator} will be wrapped.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Iterator} is {@literal null}.
	 */
	public NullFreeIterator(Iterator<? extends Payload> iterator) throws IllegalArgumentException {
		if (null == iterator) {
			throw new IllegalArgumentException("iterator is null");
		}
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		prepareNext();
		return null != next;
	}

	@Override
	public Payload next() throws NoSuchElementException {
		if (!hasNext()) {
			throw new NoSuchElementException("NullFreeIterator has no further element");
		} else {
			nextPrepared = false;
			return next;
		}
	}

	@Override
	public void remove() {
		iterator.remove();
	}

	private void prepareNext() {
		if (!nextPrepared) {
			next = null;
			while (null == next && iterator.hasNext()) {
				next = iterator.next();
			}
			nextPrepared = true;
		}
	}

}
