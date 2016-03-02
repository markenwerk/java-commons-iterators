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
import java.util.Map;

/**
 * An {@link Entry} is a simple immutable container class that holds a
 * key-value-pair. It is intended to be used for {@link Iterator Iterators} that
 * iterate over {@link Map}-like structures that have no specific
 * {@link Map.Entry Entry} implementation.
 * 
 * @param <Key>
 *            The key type.
 * @param <Value>
 *            The value type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 2.0.1
 */
public final class Entry<Key, Value> {

	private final Key key;

	private final Value value;

	/**
	 * Creates a new {@link Entry} for the given key and value.
	 * 
	 * @param key
	 *            The key to be used.
	 * @param value
	 *            The value to be used.
	 */
	public Entry(Key key, Value value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Returns the key this {@link Entry} has been created with.
	 * 
	 * @return The key.
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * Creates a new {@link Entry} with the given key and the value from this
	 * {@link Entry}.
	 * 
	 * @param key
	 *            The key to be used.
	 * @return The created {@link Entry}.
	 */
	public Entry<Key, Value> withKey(Key key) {
		return new Entry<Key, Value>(key, value);
	}

	/**
	 * Returns the value this {@link Entry} has been created with.
	 * 
	 * @return The value.
	 */
	public Value getValue() {
		return value;
	}

	/**
	 * Creates a new {@link Entry} with the given value and the key from this
	 * {@link Entry}.
	 * 
	 * @param value
	 *            The value to be used.
	 * @return The created {@link Entry}.
	 */
	public Entry<Key, Value> withValue(Value value) {
		return new Entry<Key, Value>(key, value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (object == null) {
			return false;
		} else if (getClass() != object.getClass()) {
			return false;
		}
		Entry<?, ?> other = (Entry<?, ?>) object;
		if (key == null && other.key != null || !key.equals(other.key)) {
			return false;
		} else if (value == null && other.value != null || !value.equals(other.value)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Entry [key=" + key + ", value=" + value + "]";
	}

}
