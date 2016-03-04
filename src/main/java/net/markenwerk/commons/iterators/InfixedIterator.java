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
 * An {@link InfixedIterator} is an {@link Iterator} that can be wrapped around
 * a given {@link Iterator} and yields some given infix values between every
 * value yielded by the given {@link Iterator}.
 * 
 * <p>
 * Calling {@link IntegerArrayIterator#remove()} calls {@link Iterator#remove()}
 * removed on the wrapped {@link Iterator}, if, and only iff, the last call to
 * {@link IntegerArrayIterator#next()} didn't return an infix value.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.1.6
 */
public final class InfixedIterator<Payload> implements Iterator<Payload> {

	private final Iterator<? extends Payload> iterator;

	private final Payload[] infixes;

	private boolean infixing;

	private int infixIndex = -1;

	private boolean nextCalled;

	private boolean currentRemovable;

	/**
	 * Creates a new {@link InfixedIterator} from the given {@link Iterator} and
	 * the given infix values.
	 * 
	 * @param iterator
	 *            The {@link Iterator}, around which the new
	 *            {@link NullFreeIterator} will be wrapped.
	 * @param infixes
	 *            The infixes to be yielded.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Iterator} is {@literal null}.
	 */
	public InfixedIterator(Iterator<? extends Payload> iterator, Payload... infixes) throws IllegalArgumentException {
		if (null == iterator) {
			throw new IllegalArgumentException("iterator is null");
		}
		if (null == infixes) {
			throw new IllegalArgumentException("infixes is null");
		}
		this.iterator = iterator;
		this.infixes = infixes;
	}

	@Override
	public boolean hasNext() {
		return infixing || iterator.hasNext();
	}

	@Override
	public Payload next() throws NoSuchElementException {
		if (!hasNext()) {
			throw new NoSuchElementException("InfixedIterator has no further element");
		} else {
			nextCalled = true;
			if (infixing) {
				infixing = ++infixIndex != infixes.length - 1;
				currentRemovable = false;
				return infixes[infixIndex];
			} else {
				Payload current = iterator.next();
				if (iterator.hasNext()) {
					infixIndex = -1;
					infixing = 0 != infixes.length;
				}
				currentRemovable = true;
				return current;
			}
		}
	}

	@Override
	public void remove() throws IllegalStateException {
		if (!nextCalled) {
			throw new IllegalStateException("next() hasn't been called yet");
		} else if (currentRemovable) {
			iterator.remove();
		}
	}

}
