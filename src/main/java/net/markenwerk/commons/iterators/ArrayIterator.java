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
 * An {@link ArrayIterator} is a {@link Iterator} that iterates over a given
 * payload array.
 * 
 * <p>
 * Calling {@link ArrayIterator#remove()} may set the array to the given
 * replacement value at the index that corresponds to the last value returned by
 * {@link ArrayIterator#next()}.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class ArrayIterator<Payload> implements Iterator<Payload> {

	private final Payload[] array;

	private final boolean removable;

	private final Payload replacement;

	private int index = -1;

	/**
	 * Creates a new {@link ArrayIterator} that iterates over the given payload
	 * array.
	 * 
	 * @param array
	 *            The payload array to iterate over.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given payload array is {@literal null}.
	 */
	public ArrayIterator(Payload[] array) throws IllegalArgumentException {
		this(array, false, null);
	}

	/**
	 * Creates a new {@link ArrayIterator} that iterates over the given payload
	 * array.
	 * 
	 * @param array
	 *            The payload array to iterate over.
	 * @param replacement
	 *            The value to replace removed values with.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given payload array is {@literal null}.
	 */
	public ArrayIterator(Payload[] array, Payload replacement) throws IllegalArgumentException {
		this(array, true, replacement);
	}

	private ArrayIterator(Payload[] array, boolean removable, Payload replacement) throws IllegalArgumentException {
		if (null == array) {
			throw new IllegalArgumentException("array is null");
		}
		this.array = array;
		this.removable = removable;
		this.replacement = replacement;
	}

	@Override
	public boolean hasNext() {
		return array.length != index + 1;
	}

	@Override
	public Payload next() throws NoSuchElementException {
		if (!hasNext()) {
			throw new NoSuchElementException("ArrayIterator has no further element");
		} else {
			return array[index++];
		}
	}

	@Override
	public void remove() throws IllegalStateException, UnsupportedOperationException {
		if (-1 == index) {
			throw new IllegalStateException("next() hasn't been called yet");
		} else if (!removable) {
			throw new UnsupportedOperationException("Cannot remove from an ArrayIterator");
		} else {
			array[index] = replacement;
		}
	}

}
