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

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link LongArrayIterator}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 */
public class LongArrayIteratorTests {

	/**
	 * Iterate over a {@code long[]}.
	 */
	@Test
	public void longArray_iterate() {

		long[] values = new long[] { 1, 2 };
		Iterator<Long> iterator = new LongArrayIterator(values);

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Long.valueOf(values[0]), iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Long.valueOf(values[1]), iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Iterate over a {@code null} array.
	 */
	@Test
	public void longArray_iterateNullArray() {

		Iterator<Long> iterator = new LongArrayIterator(null);

		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Remove a value in a {@code long[]}.
	 */
	@Test
	public void longArray_removeWithFallback() {

		long replacement = 0;
		long[] values = new long[] { 1 };
		Iterator<Long> iterator = new LongArrayIterator(values, replacement);

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Long.valueOf(values[0]), iterator.next());
		Assert.assertFalse(iterator.hasNext());

		iterator.remove();

		Assert.assertEquals(replacement, values[0]);

	}

	/**
	 * Remove a value in a {@code long[]}.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void longArray_removeWithoutFallback() {

		long[] values = new long[] { 1 };
		Iterator<Long> iterator = new LongArrayIterator(values);

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Long.valueOf(values[0]), iterator.next());
		Assert.assertFalse(iterator.hasNext());

		iterator.remove();

	}

}
