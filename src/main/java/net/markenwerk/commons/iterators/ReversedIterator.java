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

import java.util.NoSuchElementException;

/**
 * A {@link ReversedIterator} is a {@link BidirectionalIterator} that can be
 * wrapped around a given {@link BidirectionalIterator} and reverses the
 * directions of every directional method.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 3.1.1
 */
public final class ReversedIterator<Payload> implements BidirectionalIterator<Payload> {

	private final BidirectionalIterator<? extends Payload> iterator;

	/**
	 * Creates a new {@link ReversedIterator}.
	 * 
	 * @param iterator
	 *            The {@link BidirectionalIterator} to iterate over.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link BidirectionalIterator} is {@literal null}
	 *             .
	 */
	public ReversedIterator(BidirectionalIterator<? extends Payload> iterator) throws IllegalArgumentException {
		if (null == iterator) {
			throw new IllegalArgumentException("The given iterator is null");
		}
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasPrevious();
	}

	@Override
	public Payload next() {
		return iterator.previous();
	}

	@Override
	public boolean hasPrevious() {
		return iterator.hasNext();
	}

	@Override
	public Payload previous() throws NoSuchElementException {
		return iterator.next();
	}

	@Override
	public void remove() throws UnsupportedOperationException, IllegalStateException {
		iterator.remove();
	}

}
