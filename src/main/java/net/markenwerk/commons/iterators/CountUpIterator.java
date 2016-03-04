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

import java.util.NoSuchElementException;

/**
 * A {@link CountUpIterator} is an {@link ProtectedIterator} that yields all integer
 * value between a given lower value and a given upper bound.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class CountUpIterator implements ProtectedIterator<Integer> {

	private final int target;

	private int next;

	/**
	 * Creates a new {@link CountUpIterator} from the given lower and upper
	 * bound.
	 * 
	 * <p>
	 * If {@code lower == upper}, only one value will be yielded. If
	 * {@code lower > upper}, no value will be yielded.
	 * 
	 * @param fromLower
	 *            The lower bound and first value to be yielded.
	 * @param toUpper
	 *            The upper bound and last value to be yielded.
	 */
	public CountUpIterator(int fromLower, int toUpper) {
		next = fromLower;
		target = toUpper;
	}

	@Override
	public boolean hasNext() {
		return next <= target;
	}

	@Override
	public Integer next() throws NoSuchElementException {
		if (!hasNext()) {
			throw new NoSuchElementException("CountUpIterator has no further element");
		} else {
			return next++;
		}
	}

	@Override
	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Cannot remove from a CountUpIterator");
	}

}
