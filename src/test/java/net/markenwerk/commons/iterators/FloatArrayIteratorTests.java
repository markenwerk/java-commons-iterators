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
 * JUnit test for {@link FloatArrayIterator}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 */
public class FloatArrayIteratorTests {

	/**
	 * Iterate over a {@code float[]}.
	 */
	@Test
	public void iterate() {

		float[] values = new float[] { 1, 2 };
		Iterator<Float> iterator = new FloatArrayIterator(values);

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Float.valueOf(values[0]), iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Float.valueOf(values[1]), iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Iterate over a {@code null} array.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void iterateNullArray() {

		new FloatArrayIterator(null);

	}

	/**
	 * Remove a value in a {@code float[]}.
	 */
	@Test
	public void removeWithFallback() {

		float replacement = 0;
		float[] values = new float[] { 1 };
		Iterator<Float> iterator = new FloatArrayIterator(values, replacement);

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Float.valueOf(values[0]), iterator.next());
		Assert.assertFalse(iterator.hasNext());

		iterator.remove();

		Assert.assertEquals(replacement, values[0], 0);

	}

	/**
	 * Remove a value in a {@code float[]}.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void removeWithoutFallback() {

		float[] values = new float[] { 1 };
		Iterator<Float> iterator = new FloatArrayIterator(values);

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Float.valueOf(values[0]), iterator.next());
		Assert.assertFalse(iterator.hasNext());

		iterator.remove();

	}

}
