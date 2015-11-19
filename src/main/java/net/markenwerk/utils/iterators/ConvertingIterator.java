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
package net.markenwerk.utils.iterators;

import java.util.Iterator;

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
public class ConvertingIterator<From, To> implements Iterator<To> {

	private final Iterator<From> iterator;

	private final Converter<From, To> converter;

	private boolean nextPrepared;

	private boolean nextDetected;

	private To next;

	/**
	 * Creates a new {@link ConvertingIterator} from the given {@link Iterator}
	 * and the given {@link Converter}.
	 * 
	 * @param iterator
	 *            The {@link Iterator}, around which the new
	 *            {@link NullFreeIterator} will be wrapped.
	 * @param converter
	 *            The {@link Converter} to {@link Converter#convert(Object)
	 *            convert} every value yielded by the given {@link Iterator}
	 *            with.
	 */
	public ConvertingIterator(Iterator<From> iterator, Converter<From, To> converter) {
		this.iterator = iterator;
		this.converter = converter;
	}

	@Override
	public boolean hasNext() {
		prepareNext();
		return nextDetected;
	}

	@Override
	public To next() {
		prepareNext();
		nextPrepared = false;
		return next;
	}

	@Override
	public void remove() {
		iterator.remove();
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
