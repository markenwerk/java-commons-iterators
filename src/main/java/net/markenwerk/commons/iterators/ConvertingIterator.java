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

import net.markenwerk.commons.interfaces.Converter;

/**
 * A {@link ConvertingIterator} is an {@link Iterator} that can be wrapped
 * around a given {@link Iterator} and converts all values with a given
 * {@link Converter}.
 * 
 * @param <From>
 *            The type to convert values from.
 * @param <To>
 *            The type to convert values to.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class ConvertingIterator<From, To> implements Iterator<To> {

	private final Iterator<? extends From> iterator;

	private final Converter<? super From, ? extends To> converter;

	private boolean nextPrepared;

	private boolean nextDetected;

	private To next;

	private boolean nextCalled;

	/**
	 * Creates a new {@link ConvertingIterator}.
	 * 
	 * @param iterator
	 *            The {@link Iterator} to be used.
	 * @param converter
	 *            The {@link Converter} to be used.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Iterator} is {@literal null} or the given
	 *             {@link Converter} is {@literal null}.
	 */
	public ConvertingIterator(Iterator<? extends From> iterator, Converter<? super From, ? extends To> converter)
			throws IllegalArgumentException {
		if (null == iterator) {
			throw new IllegalArgumentException("The given iterator is null");
		} else if (null == converter) {
			throw new IllegalArgumentException("The given converter is null");
		}
		this.iterator = iterator;
		this.converter = converter;
	}

	@Override
	public boolean hasNext() throws NoSuchElementException {
		prepareNext();
		return nextDetected;
	}

	@Override
	public To next() throws NoSuchElementException {
		if (!hasNext()) {
			throw new NoSuchElementException("This iterator has no next element");
		} else {
			nextCalled = true;
			nextPrepared = false;
			return next;
		}
	}

	@Override
	public void remove() throws IllegalStateException, UnsupportedOperationException{
		if (!nextCalled) {
			throw new IllegalStateException("Method next() hasn't been called yet");
		} else {
			iterator.remove();
		}
	}

	private void prepareNext() {
		if (!nextPrepared) {
			next = null;
			nextDetected = false;
			while (!nextDetected && iterator.hasNext()) {
				From nextFrom = iterator.next();
				next = converter.convert(nextFrom);
				nextDetected = true;
			}
			nextPrepared = true;
		}
	}

}
