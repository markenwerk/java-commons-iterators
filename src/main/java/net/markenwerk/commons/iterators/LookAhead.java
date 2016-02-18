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

/**
 * A {@link LookAhead} is a simple container that holds the
 * {@link LookAhead#get() current} and the {@link LookAhead#getNext() next}
 * payload object of an {@link LookAheadIterator}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.1.6
 *
 * @param <Payload>
 *           The payload type.
 */
public interface LookAhead<Payload> {

	/**
	 * Returns the current payload object of an {@link LookAheadIterator}.
	 * 
	 * @return The current payload object. May be {@literal null}.
	 */
	public Payload get();

	/**
	 * Returns the next payload object of an {@link LookAheadIterator}. The
	 * returned value is not meaningful, if this {@link LookAhead} has no
	 * {@literal LookAhead#hashCode() next} payload object.
	 * 
	 * @return The next payload object. May be {@literal null}.
	 */

	public Payload getNext();

	/**
	 * Returns whether this {@link LookAhead} has a next payload object.
	 * 
	 * @return Whether this {@link LookAhead} has a next payload object.
	 */
	public boolean hasNext();

	/**
	 * Returns whether this {@link LookAhead} has a no next payload object.
	 * 
	 * @return Whether this {@link LookAhead} has a no next payload object.
	 */
	public boolean isLast();

}
