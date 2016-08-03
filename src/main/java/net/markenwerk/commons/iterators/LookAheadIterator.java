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
 * A {@link LookAheadIterator} is an {@link ProtectedIterator} that can be
 * wrapped around a given {@link Iterator} and yields a {@link LookAhead}
 * container that holds the current and the next payload value of the given
 * {@link Iterator}.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.1.6
 */
public final class LookAheadIterator<Payload> extends AbstractProtectedIterator<LookAhead<Payload>> {

	private static class Entry<Payload> implements LookAhead<Payload> {

		private final Payload current;

		private final Payload next;

		private final boolean nextExists;

		private Entry() {
			this(null, null, false);
		}

		private Entry(Payload current, Payload next, boolean nextExists) {
			this.current = current;
			this.next = next;
			this.nextExists = nextExists;
		}

		protected Entry<Payload> shift(Payload next, boolean nextExists) {
			return new Entry<Payload>(this.next, next, nextExists);
		}

		@Override
		public Payload get() {
			return current;
		}

		@Override
		public Payload getNext() throws NoSuchElementException {
			if (!nextExists) {
				throw new NoSuchElementException("LookAhead has no further element");
			} else {
				return next;
			}
		}

		@Override
		public boolean hasNext() {
			return nextExists;
		}

		@Override
		public boolean isLast() {
			return !nextExists;
		}

		@Override
		public String toString() {
			return "LookAhead [current=" + current + ", next=" + next + ", hasNext=" + nextExists + "]";
		}

	}

	private final Iterator<? extends Payload> iterator;

	private Entry<Payload> lookAhead = new Entry<Payload>();

	private boolean nextPrepared;

	private boolean hasNext;

	/**
	 * Creates a new {@link LookAheadIterator}.
	 * 
	 * @param iterator
	 *            The {@link Iterator} to iterate over.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Iterator} is {@literal null}.
	 */
	public LookAheadIterator(Iterator<? extends Payload> iterator) throws IllegalArgumentException {
		if (null == iterator) {
			throw new IllegalArgumentException("The given iterator is null");
		}
		this.iterator = iterator;
		if (iterator.hasNext()) {
			lookAhead = lookAhead.shift(iterator.next(), true);
		}
	}

	@Override
	public boolean hasNext() {
		prepareNext();
		return hasNext;
	}

	@Override
	public LookAhead<Payload> next() throws NoSuchElementException {
		if (!hasNext()) {
			throw new NoSuchElementException("This iterator has no next element");
		} else {
			nextPrepared = false;
			return lookAhead;
		}
	}

	private void prepareNext() {
		if (!nextPrepared) {
			hasNext = lookAhead.hasNext();
			if (iterator.hasNext()) {
				lookAhead = lookAhead.shift(iterator.next(), true);
			} else {
				lookAhead = lookAhead.shift(null, false);
			}
			nextPrepared = true;
		}
	}

}
