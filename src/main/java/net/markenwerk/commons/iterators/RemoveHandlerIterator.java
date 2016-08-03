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

import net.markenwerk.commons.interfaces.Handler;

/**
 * A {@link RemoveHandlerIterator} is an {@link Iterator} that can be wrapped
 * around a given {@link Iterator} and intercepts every call to
 * {@linkplain RemoveHandlerIterator#remove()} and calls a given {@link Handler}
 * for the current value.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 2.2.0
 */
public final class RemoveHandlerIterator<Payload> implements Iterator<Payload> {

	private final Iterator<? extends Payload> iterator;

	private final Handler<? super Payload> removeHandler;

	private boolean nextCalled;

	private Payload current;

	private boolean currentRemoved;

	/**
	 * Creates a new {@link RemoveHandlerIterator}.
	 * 
	 * @param iterator
	 *            The {@link Iterator} to iterate over.
	 * @param removeHandler
	 *            The {@link Handler} to be used.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Iterator} is {@literal null} of if the
	 *             given {@link Handler} is {@literal null}.
	 */
	public RemoveHandlerIterator(Iterator<? extends Payload> iterator, Handler<? super Payload> removeHandler)
			throws IllegalArgumentException {
		if (null == iterator) {
			throw new IllegalArgumentException("The given iterator is null");
		} else if (null == removeHandler) {
			throw new IllegalArgumentException("The given handler is null");
		}
		this.iterator = iterator;
		this.removeHandler = removeHandler;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Payload next() throws NoSuchElementException {
		current = iterator.next();
		nextCalled = true;
		currentRemoved = false;
		return current;
	}

	@Override
	public void remove() throws IllegalStateException {
		if (!nextCalled) {
			throw new IllegalStateException("Method next() hasn't been called yet");
		} else if (currentRemoved) {
			throw new IllegalStateException("Method remove() has already been called");
		} else {
			currentRemoved = true;
			removeHandler.handle(current);
		}
	}

}
