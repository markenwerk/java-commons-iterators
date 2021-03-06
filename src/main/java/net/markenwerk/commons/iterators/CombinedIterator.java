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
 * A {@link CombinedIterator} is an {@link Iterator} that wraps around a
 * sequence of given {@link Iterator Iterators} and combines them into a single
 * {@link Iterator} by iterating over all given {@link Iterator Iterators} in
 * the order they were given.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class CombinedIterator<Payload> implements Iterator<Payload> {

	private final Iterator<? extends Iterator<? extends Payload>> iterators;

	private Iterator<? extends Payload> currentIterator;

	private boolean nextPrepared;

	private boolean hasNext;

	private boolean nextCalled;

	/**
	 * Creates a new {@link CombinedIterator}.
	 * 
	 * @param iterators
	 *            The {@link Iterator Iterators} to iterate over.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given sequence of {@link Iterator Iterators} is
	 *             {@literal null}.
	 */
	public CombinedIterator(Iterator<? extends Payload>... iterators) throws IllegalArgumentException {
		if (null == iterators) {
			throw new IllegalArgumentException("The given array of iterators is null");
		}
		this.iterators = new ArrayIterator<Iterator<? extends Payload>>(iterators);
	}

	/**
	 * Creates a new {@link CombinedIterator}.
	 * 
	 * @param iterators
	 *            The {@link Iterator Iterators} to iterate over.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Iterable} of {@link Iterator Iterators}
	 *             is {@literal null}.
	 */
	public CombinedIterator(Iterable<? extends Iterator<? extends Payload>> iterators) throws IllegalArgumentException {
		if (null == iterators) {
			throw new IllegalArgumentException("The given iterable of iterators is null");
		}
		this.iterators = iterators.iterator();
	}

	/**
	 * Creates a new {@link CombinedIterator}.
	 * 
	 * @param iterators
	 *            The {@link Iterator Iterators} to iterate over.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Iterator} of {@link Iterator Iterators}
	 *             is {@literal null}.
	 */
	public CombinedIterator(Iterator<? extends Iterator<? extends Payload>> iterators) throws IllegalArgumentException {
		if (null == iterators) {
			throw new IllegalArgumentException("The given iterator of iterators is null");
		}
		this.iterators = iterators;
	}

	@Override
	public boolean hasNext() {
		prepareNext();
		return hasNext;
	}

	@Override
	public Payload next() throws NoSuchElementException {
		if (!hasNext()) {
			throw new NoSuchElementException("This iterator has no next element");
		} else {
			nextCalled = true;
			nextPrepared = false;
			return currentIterator.next();
		}
	}

	@Override
	public void remove() throws IllegalStateException, UnsupportedOperationException {
		if (!nextCalled) {
			throw new IllegalStateException("Method next() hasn't been called yet");
		} else {
			currentIterator.remove();
		}
	}

	private void prepareNext() {
		if (!nextPrepared) {
			hasNext = false;
			if (null != currentIterator && currentIterator.hasNext()) {
				hasNext = true;
			} else {
				while (!hasNext && iterators.hasNext()) {
					currentIterator = iterators.next();
					hasNext = currentIterator.hasNext();
				}
			}
			nextPrepared = true;
		}
	}

}
