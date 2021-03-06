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

/**
 * An {@link EmptyIterator} is an {@link AbstractIndexedIterator} that doesn't
 * yield any values.
 * 
 * <p>
 * Calling {@link EmptyIterator#hasNext()} always returns {@literal false} and
 * calling {@link EmptyIterator#next()} always returns {@literal null}.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class EmptyIterator<Payload> extends AbstractIndexedIterator<Payload> {

	/**
	 * Creates a new {@link EmptyIterator}.
	 */
	public EmptyIterator() {
		super(0, 0);
	}

	@Override
	public Payload get(int index) {
		throw new AssertionError("Encountered unexpected index: " + 0);
	}

}
