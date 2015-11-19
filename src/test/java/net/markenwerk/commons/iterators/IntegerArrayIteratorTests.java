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
 * JUnit test for {@link IntegerArrayIterator}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public class IntegerArrayIteratorTests {

	/**
	 * Iterate over a {@code int[]}.
	 */
	@Test
	public void intArray_iterate() {

		int[] values = new int[] { 1, 2 };
		Iterator<Integer> iterator = new IntegerArrayIterator(values);

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(new Integer(values[0]), iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(new Integer(values[1]), iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}
	
	
	/**
	 * Iterate over a {@code null} array.
	 */
	@Test
	public void array_iterateNullArray() {

		Iterator<Integer> iterator = new IntegerArrayIterator(null);

		Assert.assertFalse(iterator.hasNext());

	}
	
	/**
	 * Remove a value in a {@code int[]}.
	 */
	@Test
	public void intArray_remove() {

		int[] values = new int[] { 1 };
		Iterator<Integer> iterator = new IntegerArrayIterator(values);

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(new Integer(values[0]), iterator.next());
		Assert.assertFalse(iterator.hasNext());

		iterator.remove();

		Assert.assertEquals(0, values[0]);

	}

}
