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

import net.markenwerk.commons.datastructures.Tuple;

/**
 * An {@link TupleIterator} is a {@link ProtectedIterator} that iterates over a
 * given {@link Tuple}.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since TODO
 */
public final class TupleIterator<Payload> implements ProtectedIterator<Payload> {

	private final Tuple<? extends Payload, ? extends Payload> tuple;

	private int index;

	/**
	 * Creates a new {@link TupleIterator} that iterates over the given {@link Tuple}.
	 * 
	 * @param tuple
	 *            The {@link Tuple} to iterate over.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Tuple} is {@literal null}.
	 */
	public TupleIterator(Tuple<? extends Payload, ? extends Payload> tuple) throws IllegalArgumentException {
		if (null == tuple) {
			throw new IllegalArgumentException("tuple is null");
		}
		this.tuple = tuple;
	}

	@Override
	public boolean hasNext() {
		return index < 2;
	}

	@Override
	public Payload next() throws NoSuchElementException {
		switch (index++) {
		case 0:
			return tuple.getFirst();
		case 1:
			return tuple.getSecond();
		default:
			throw new NoSuchElementException("TupleIterator has no further element");
		}
	}

	@Override
	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Cannot remove from a TupleIterator");
	}
	
}
