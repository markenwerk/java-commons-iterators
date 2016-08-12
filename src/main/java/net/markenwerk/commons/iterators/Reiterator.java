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

public interface Reiterator<Payload> extends Iterator<Payload> {

	/**
	 * Returns whether this {@link Reiterator} has a previous element.
	 *
	 * @return Whether this {@link Reiterator} has a previous element.
	 */
	boolean hasPrevious();

	/**
	 * Returns the previous element.
	 *
	 * @return The previous element in the iteration.
	 * @exception NoSuchElementException
	 *                If this {@link Reiterator} has no previous element.
	 */
	Payload previous() throws NoSuchElementException;

	/**
	 * Removes the last returned (either by {@link Reiterator#next()} or by
	 * {@link Reiterator#previous()}) payload value from the underlying data
	 * structure.
	 *
	 * @exception UnsupportedOperationException
	 *                If this {@link Reiterator} doesn't support element
	 *                removal.
	 * @exception IllegalStateException
	 *                If neither {@link Reiterator#next()} nor
	 *                {@link Reiterator#previous()} has been called or if
	 *                {@link Reiterator#remove()} has already been called after
	 *                the last call to {@link Reiterator#next()} or
	 *                {@link Reiterator#previous()}.
	 */
	@Override
	void remove() throws UnsupportedOperationException, IllegalStateException;

}
