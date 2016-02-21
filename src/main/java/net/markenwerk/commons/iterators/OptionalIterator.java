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

/**
 * An {@link OptionalIterator} is a {@link Iterator} that behaves like an
 * {@link ObjectIterator} for a given payload object, or like an empty
 * {@link Iterator}, if the given payload object is {@literal null}.
 * 
 * @param <Payload>
 *           The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.1.5
 */
public final class OptionalIterator<Payload> implements ProtectedIterator<Payload> {

	private final Payload value;

	private boolean visited;

	/**
	 * Creates a new {@link OptionalIterator} that iterates over the given
	 * payload object.
	 * 
	 * @param value
	 *           The payload object to iterate over.
	 */
	public OptionalIterator(Payload value) {
		this.value = value;
	}

	public boolean hasNext() {
		return null != value && !visited;
	}

	public Payload next() {
		visited = true;
		return value;
	}

	public void remove() {
		throw new UnsupportedOperationException("Cannot remove the payload object.");
	}

}
