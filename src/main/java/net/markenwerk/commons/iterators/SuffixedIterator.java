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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An {@link SuffixedIterator} is an {@link Iterator} that can be wrapped around
 * a given {@link Iterator} and yields some given suffix values after yielding
 * all values yielded by the given {@link Iterator}.
 * 
 * <p>
 * Calling {@link IntegerArrayIterator#remove()} calls {@link Iterator#remove()}
 * removed on the wrapped {@link Iterator}, if, and only iff, the last call to
 * {@link IntegerArrayIterator#next()} didn't return a suffix value.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 2.1.0
 */
public final class SuffixedIterator<Payload> implements Iterator<Payload> {

	private final Iterator<? extends Payload> iterator;

	private final Payload[] suffixes;

	private boolean suffixing;

	private int suffixIndex = -1;

	private boolean nextCalled;

	private boolean currentRemovable;

	/**
	 * Creates a new {@link SuffixedIterator} from the given {@link Iterator}
	 * and the given suffix values.
	 * 
	 * @param iterator
	 *            The {@link Iterator}, around which the new
	 *            {@link NullFreeIterator} will be wrapped.
	 * @param suffixes
	 *            The suffixes to be yielded.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Iterator} is {@literal null}.
	 */
	public SuffixedIterator(Iterator<? extends Payload> iterator, Payload... suffixes) throws IllegalArgumentException {
		if (null == iterator) {
			throw new IllegalArgumentException("iterator is null");
		}
		if (null == suffixes) {
			throw new IllegalArgumentException("suffixes is null");
		}
		this.iterator = iterator;
		this.suffixes = suffixes;
		suffixing = !iterator.hasNext() && 0 != suffixes.length;
	}

	@Override
	public boolean hasNext() {
		return suffixing || iterator.hasNext();
	}

	@Override
	public Payload next() throws NoSuchElementException {
		if (!hasNext()) {
			throw new NoSuchElementException("PrefixedIterator has no further element");
		} else {
			nextCalled = true;
			if (suffixing) {
				suffixing = ++suffixIndex != suffixes.length - 1;
				currentRemovable = false;
				return suffixes[suffixIndex];
			} else {
				Payload current = iterator.next();
				if (!iterator.hasNext()) {
					suffixIndex = -1;
					suffixing = 0 != suffixes.length;
				}
				currentRemovable = true;
				return current;
			}
		}
	}

	@Override
	public void remove() {
		if (!nextCalled) {
			throw new IllegalStateException("next() hasn't been called yet");
		} else if (currentRemovable) {
			iterator.remove();
		}
	}

}
