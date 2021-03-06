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

import net.markenwerk.commons.datastructures.Optional;

/**
 * An {@link OptionalIterator} is an {@link AbstractIndexedIterator} that
 * behaves like an {@link ObjectIterator} for a given payload object, or like an
 * empty {@link Iterator}, if the given payload object is {@literal null}.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.1.5
 */
public final class OptionalIterator<Payload> extends AbstractIndexedIterator<Payload> {

	private final Optional<Payload> value;

	/**
	 * Creates a new {@link OptionalIterator}.
	 * 
	 * @param value
	 *            The object to iterate over.
	 */
	public OptionalIterator(Optional<Payload> value) {
		super(0, length(value));
		this.value = value;
	}

	private static <Payload> int length(Optional<Payload> value) {
		if (null == value) {
			throw new IllegalArgumentException("The given optional is null");
		}
		return value.hasValue() ? 1 : 0;
	}

	@Override
	public Payload get(int index) {
		return value.getValue();
	}

}
