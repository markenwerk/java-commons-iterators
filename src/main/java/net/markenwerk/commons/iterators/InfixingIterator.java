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
package net.markenwerk.commons.iterators;

import java.util.Iterator;

import net.markenwerk.commons.interfaces.Predicate;

/**
 * An {@link InfixingIterator} is an {@link Iterator} that can be wrapped around
 * a given {@link Iterator} and yields a given infix between every object
 * yielded by the given {@link Iterator}.
 * 
 * <p>
 * Calling {@link IntegerArrayIterator#remove()} calls {@link Iterator#remove()}
 * removed on the wrapped {@link Iterator}, if, and only iff, the last call to
 * {@link IntegerArrayIterator#next()} didn't return the given infix.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.1.6
 */
public final class InfixingIterator<Payload> implements Iterator<Payload> {

	private final Iterator<? extends Payload> iterator;

	private final Payload infix;

	private boolean nextIsInfix;

	/**
	 * Creates a new {@link InfixingIterator} from the given {@link Iterator}
	 * and the given {@link Predicate}.
	 * 
	 * @param iterator
	 *            The {@link Iterator}, around which the new
	 *            {@link NullFreeIterator} will be wrapped.
	 * @param infix
	 *            The infix to be yielded.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Iterator} is {@literal null}.
	 */
	public InfixingIterator(Iterator<? extends Payload> iterator, Payload infix) throws IllegalArgumentException {
		if (null == iterator) {
			throw new IllegalArgumentException("iterator is null");
		}
		this.iterator = iterator;
		this.infix = infix;
	}

	@Override
	public boolean hasNext() {
		return nextIsInfix || iterator.hasNext();
	}

	@Override
	public Payload next() {
		if (nextIsInfix) {
			nextIsInfix = false;
			return infix;
		} else {
			Payload current = iterator.next();
			nextIsInfix = iterator.hasNext();
			return current;
		}
	}

	@Override
	public void remove() {
		if (nextIsInfix || !iterator.hasNext()) {
			iterator.remove();
		}
	}

}
