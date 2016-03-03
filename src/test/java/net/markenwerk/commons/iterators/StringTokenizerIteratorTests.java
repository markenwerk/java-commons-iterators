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
import java.util.StringTokenizer;

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link StringTokenizerIterator}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 */
public class StringTokenizerIteratorTests {

	/**
	 * Iterate over a {@code null} {@link StringTokenizer}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void iterateNullTokenizer() {

		new StringTokenizerIterator(null);

	}

	/**
	 * Iterate over an empty {@link StringTokenizer}.
	 */
	@Test
	public void iterateEmpty() {

		Iterator<String> iterator = new StringTokenizerIterator(new StringTokenizer(""));

		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Iterate over an {@link StringTokenizer} with one element.
	 */
	@Test
	public void iterateOne() {

		Iterator<String> iterator = new StringTokenizerIterator(new StringTokenizer("foo"));

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals("foo", iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Iterate over an {@link StringTokenizer} with two elements.
	 */
	@Test
	public void iterateTwo() {

		Iterator<String> iterator = new StringTokenizerIterator(new StringTokenizer("foo bar"));

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals("foo", iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals("bar", iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

}
