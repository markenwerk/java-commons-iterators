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
 * An {@link IntegerArrayIterator} is an {@link Iterator} that iterates over a
 * given {@code int[]}.
 * 
 * <p>
 * Calling {@link IntegerArrayIterator#remove()} sets the array to {@literal 0}
 * at the index that corresponds to the last value returned by
 * {@link IntegerArrayIterator#next()}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class IntegerArrayIterator implements Iterator<Integer> {

	private final int[] values;

	private int index = -1;

	/**
	 * Creates a new {@linkplain IntegerArrayIterator} that iterates over the
	 * given {@code int[]}.
	 * 
	 * <p>
	 * If the given {@code int[]} is {@literal null}, the new
	 * {@link IntegerArrayIterator} will behave, as if an empty {@code int[]}
	 * has been given.
	 * 
	 * @param values
	 *            The {@code int[]} to iterate over.
	 */
	public IntegerArrayIterator(int[] values) {
		this.values = null == values ? new int[0] : values;
	}

	public boolean hasNext() {
		return values.length != index + 1;
	}

	public Integer next() {
		index++;
		return values[index];
	}

	public void remove() {
		values[index] = 0;
	}

}