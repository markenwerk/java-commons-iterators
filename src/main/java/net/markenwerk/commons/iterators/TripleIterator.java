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

import net.markenwerk.commons.datastructures.Triple;

/**
 * An {@link TripleIterator} is a {@link ProtectedIterator} that iterates over a
 * given {@link Triple}.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since TODO
 */
public final class TripleIterator<Payload> implements ProtectedIterator<Payload> {

	private final Triple<? extends Payload, ? extends Payload, ? extends Payload> triple;

	private int index = -1;

	/**
	 * Creates a new {@link TripleIterator} that iterates over the given {@link Triple}.
	 * 
	 * @param triple
	 *            The {@link Triple} to iterate over.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Triple} is {@literal null}.
	 */
	public TripleIterator(Triple<? extends Payload, ? extends Payload, ? extends Payload> triple)
			throws IllegalArgumentException {
		if (null == triple) {
			throw new IllegalArgumentException("triple is null");
		}
		this.triple = triple;
	}

	@Override
	public boolean hasNext() {
		return index < 2;
	}

	@Override
	public Payload next() throws NoSuchElementException {
		switch (++index) {
		case 0:
			return triple.getFirst();
		case 1:
			return triple.getSecond();
		case 2:
			return triple.getThird();
		default:
			throw new NoSuchElementException("TripleIterator has no further element");
		}
	}

	@Override
	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Cannot remove from a TripleIterator");
	}

}
