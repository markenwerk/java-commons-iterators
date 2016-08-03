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
import java.util.StringTokenizer;

/**
 * A {@link StringTokenizerIterator} is an {@link AbstractProtectedIterator} that can be wrapped
 * around a given {@link StringTokenizer} and yields all tokens yielded by the
 * given {@link StringTokenizer}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.2.0
 */
public final class StringTokenizerIterator extends AbstractProtectedIterator<String> {

	private final StringTokenizer tokenizer;

	/**
	 * Creates a new {@link StringTokenizerIterator}.
	 * 
	 * @param tokenizer
	 *            The {@link StringTokenizer} to iterate over.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link StringTokenizer} is {@literal null}.
	 */
	public StringTokenizerIterator(StringTokenizer tokenizer) throws IllegalArgumentException {
		if (null == tokenizer) {
			throw new IllegalArgumentException("The given string tokenizer is null");
		}
		this.tokenizer = tokenizer;
	}

	@Override
	public boolean hasNext() {
		return tokenizer.hasMoreElements();
	}

	@Override
	public String next() throws NoSuchElementException {
		if (!hasNext()) {
			throw new NoSuchElementException("This iterator has no next element");
		} else {
			return tokenizer.nextToken();
		}
	}



}
