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
 * A {@link CountDownIterator} is an {@link Iterator} that yields all integer
 * value between a given upper value and a given lower bound.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class CountDownIterator implements Iterator<Integer> {

	private final int target;

	private int next;

	/**
	 * Creates a new {@link CountDownIterator} from the given upper and lower
	 * bound.
	 * 
	 * <p>
	 * If {@code upper == lower}, only one value will be yielded. If
	 * {@code uper < lower}, no value will be yielded.
	 * 
	 * @param fromUpper
	 *            The upper bound and first value to be yielded.
	 * @param toLower
	 *            The lower bound and last value to be yielded.
	 */
	public CountDownIterator(int fromUpper, int toLower) {
		next = fromUpper;
		target = toLower;
	}

	@Override
	public boolean hasNext() {
		return next >= target;
	}

	@Override
	public Integer next() {
		return next--;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("Cannot remove from a counter.");
	}

}
