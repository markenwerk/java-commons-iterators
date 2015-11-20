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

	private final Payload[] values;

	private final boolean removable;

	private final Payload replacement;

	private int index = -1;

	/**
	 * Creates a new {@link ArrayIterator} that iterates over the given payload
	 * array.
	 * 
	 * <p>
	 * If the given payload array is {@literal null}, the new
	 * {@link ArrayIterator} will behave, as if an empty payload array has been
	 * given.
	 * 
	 * @param values
	 *            The payload array to iterate over.
	 */
	public ArrayIterator(Payload[] values) {
		this(values, false, null);
	}

	/**
	 * Creates a new {@link ArrayIterator} that iterates over the given payload
	 * array.
	 * 
	 * <p>
	 * If the given payload array is {@literal null}, the new
	 * {@link ArrayIterator} will behave, as if an empty payload array has been
	 * given.
	 * 
	 * @param values
	 *            The payload array to iterate over.
	 * @param replacement
	 *            The value to replace removed values with.
	 */
	public ArrayIterator(Payload[] values, Payload replacement) {
		this(values, true, replacement);
	}

	@SuppressWarnings("unchecked")
	private ArrayIterator(Payload[] values, boolean removable, Payload replacement) {
		this.values = null == values ? (Payload[]) new Object[0] : values;
		this.removable = removable;
		this.replacement = replacement;
	}

	public boolean hasNext() {
		return values.length != index + 1;
	}

	public Payload next() {
		index++;
		return values[index];
	}

	public void remove() {
		if (removable) {
			values[index] = replacement;
		} else {
			throw new UnsupportedOperationException("Cannot remove from an array.");
		}
	}

}
