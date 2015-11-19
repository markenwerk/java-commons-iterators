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
 * A {@link BooleanArrayIterator} is a {@link Iterator} that iterates over a
 * given {@code boolean[]}.
 * 
 * <p>
 * Calling {@link BooleanArrayIterator#remove()} sets the array to
 * {@literal false} at the index that corresponds to the last value returned by
 * {@link BooleanArrayIterator#next()}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class BooleanArrayIterator implements Iterator<Boolean> {

	private final boolean[] values;

	private int index = -1;

	/**
	 * Creates a new {@linkplain BooleanArrayIterator} that iterates over the
	 * given {@code boolean[]}.
	 * 
	 * <p>
	 * If the given {@code boolean[]} is {@literal null}, the new
	 * {@link BooleanArrayIterator} will behave, as if an empty {@code boolean[]} has
	 * been given.
	 * 
	 * @param values
	 *            The {@code boolean[]} to iterate over.
	 */
	public BooleanArrayIterator(boolean[] values) {
		this.values = null == values ? new boolean[0] : values;
	}

	public boolean hasNext() {
		return values.length != index + 1;
	}

	public Boolean next() {
		index++;
		return values[index];
	}

	public void remove() {
		values[index] = false;
	}

}