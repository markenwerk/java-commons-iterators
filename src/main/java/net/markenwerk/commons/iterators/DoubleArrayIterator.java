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
 * A {@link DoubleArrayIterator} is an {@link Iterator} that iterates over a
 * given {@code double[]}.
 * 
 * <p>
 * Calling {@link DoubleArrayIterator#remove()} sets the array to {@literal 0}
 * at the index that corresponds to the last value returned by
 * {@link DoubleArrayIterator#next()}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class DoubleArrayIterator implements Iterator<Double> {

	private final double[] values;

	private final Double replacement;

	private int index = -1;

	/**
	 * Creates a new {@linkplain DoubleArrayIterator} that iterates over the
	 * given {@code double[]}.
	 * 
	 * <p>
	 * If the given {@code double[]} is {@literal null}, the new
	 * {@link DoubleArrayIterator} will behave, as if an empty {@code double[]}
	 * has been given.
	 * 
	 * @param values
	 *            The {@code double[]} to iterate over.
	 */
	public DoubleArrayIterator(double[] values) {
		this(values, null);
	}

	/**
	 * Creates a new {@linkplain DoubleArrayIterator} that iterates over the
	 * given {@code double[]}.
	 * 
	 * <p>
	 * If the given {@code double[]} is {@literal null}, the new
	 * {@link DoubleArrayIterator} will behave, as if an empty {@code double[]}
	 * has been given.
	 * 
	 * @param values
	 *            The {@code double[]} to iterate over.
	 * @param replacement
	 *            The value to replace removed values with.
	 */
	public DoubleArrayIterator(double[] values, double replacement) {
		this(values, Double.valueOf(replacement));
	}

	private DoubleArrayIterator(double[] values, Double replacement) {
		this.values = null == values ? new double[0] : values;
		this.replacement = replacement;
	}

	public boolean hasNext() {
		return values.length != index + 1;
	}

	public Double next() {
		index++;
		return values[index];
	}

	public void remove() {
		if (null != replacement) {
			values[index] = replacement;
		} else {
			throw new UnsupportedOperationException("Cannot remove from an array.");
		}
	}

}
