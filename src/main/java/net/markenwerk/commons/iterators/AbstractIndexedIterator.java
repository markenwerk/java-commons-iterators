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

import java.util.NoSuchElementException;

/**
 * An {@link AbstractIndexedIterator} is a base implementation for
 * {@link ProtectedReiterator} for indexed data structures.
 * 
 * @param <Payload>
 *           The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 3.1.1
 */
public abstract class AbstractIndexedIterator<Payload> extends AbstractProtectedReiterator<Payload>
		implements IndexedIterator<Payload> {

	private int index;

	/**
	 * Creates a new {@link AbstractIndexedIterator}.
	 */
	protected AbstractIndexedIterator() {
	}

	@Override
	public final int index() {
		return index;
	}

	@Override
	public final boolean hasNext() {
		return index < maxIndex();
	}

	@Override
	public final Payload next() throws NoSuchElementException {
		if (!hasNext()) {
			throw new NoSuchElementException("This iterator has no next element");
		} else {
			return get(index++);
		}
	}

	@Override
	public final boolean hasPrevious() {
		return index > 0;
	}

	@Override
	public final Payload previous() throws NoSuchElementException {
		if (!hasPrevious()) {
			throw new NoSuchElementException("This iterator has no previous element");
		} else {
			return get(--index);
		}
	}



}
