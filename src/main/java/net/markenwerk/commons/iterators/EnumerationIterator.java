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

import java.util.Enumeration;
import java.util.NoSuchElementException;

/**
 * An {@link EnumerationIterator} is an {@link AbstractProtectedIterator} that
 * can be wrapped around a given {@link Enumeration} and yields all values
 * yielded by the given {@link Enumeration}.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.2.0
 */
public final class EnumerationIterator<Payload> extends AbstractProtectedIterator<Payload> {

	private final Enumeration<? extends Payload> enumeration;

	/**
	 * Creates a new {@link EnumerationIterator}.
	 * 
	 * @param enumeration
	 *            The {@link Enumeration} to iterate over.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Enumeration} is {@literal null}.
	 */
	public EnumerationIterator(Enumeration<? extends Payload> enumeration) throws IllegalArgumentException {
		if (null == enumeration) {
			throw new IllegalArgumentException("The given enumeration is null");
		}
		this.enumeration = enumeration;
	}

	@Override
	public boolean hasNext() {
		return enumeration.hasMoreElements();
	}

	@Override
	public Payload next() throws NoSuchElementException {
		if (!hasNext()) {
			throw new NoSuchElementException("This iterator has no next element");
		} else {
			return enumeration.nextElement();
		}
	}

}
