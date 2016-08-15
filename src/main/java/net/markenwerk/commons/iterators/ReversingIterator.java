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

public final class ReversingIterator<Payload> extends AbstractIndexedIterator<Payload> {

	private final IndexedIterator<? extends Payload> iterator;

	/**
	 * Creates a new {@link ReversingIterator}.
	 * 
	 * @param iterator
	 *           The {@link Reiterator} to iterate over.
	 * 
	 * @throws IllegalArgumentException
	 *            If the given {@link Reiterator} is {@literal null}.
	 */
	public ReversingIterator(IndexedIterator<? extends Payload> iterator) throws IllegalArgumentException {
		if (null == iterator) {
			throw new IllegalArgumentException("The given iterator is null");
		}
		this.iterator = iterator;
	}

	@Override
	public int maxIndex() {
		return iterator.maxIndex();
	}

	@Override
	public Payload get(int index) {
		return iterator.get(maxIndex() - index);
	}

}
