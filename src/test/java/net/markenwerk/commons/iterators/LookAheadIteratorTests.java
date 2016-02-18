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
 * JUnit test for {@link LookAheadIterator}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 */
public class LookAheadIteratorTests {

	/**
	 * Iterate over an empty iterator.
	 */
	@Test
	public void lookAhead_iterateEmpty() {

		Iterator<LookAhead<Object>> iterator = new LookAheadIterator<Object>(new EmptyIterator<Object>());

		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Iterate over an {@link Iterator} with one element.
	 */
	@Test
	public void object_iterateOne() {

		Object[] values = new Object[] { new Object() };
		Iterator<LookAhead<Object>> iterator = new LookAheadIterator<Object>(new ArrayIterator<Object>(values));

		Assert.assertTrue(iterator.hasNext());

		LookAhead<Object> first = iterator.next();
		Assert.assertSame(values[0], first.get());
		Assert.assertTrue(first.isLast());
		Assert.assertFalse(first.hasNext());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Iterate over an {@link Iterator} with two elements.
	 */
	@Test
	public void object_iterateTwo() {

		Object[] values = new Object[] { new Object(), new Object() };
		Iterator<LookAhead<Object>> iterator = new LookAheadIterator<Object>(new ArrayIterator<Object>(values));

		Assert.assertTrue(iterator.hasNext());

		LookAhead<Object> first = iterator.next();
		Assert.assertSame(values[0], first.get());
		Assert.assertFalse(first.isLast());
		Assert.assertTrue(first.hasNext());
		Assert.assertTrue(iterator.hasNext());

		LookAhead<Object> second = iterator.next();
		Assert.assertSame(values[1], second.get());
		Assert.assertTrue(second.isLast());
		Assert.assertFalse(second.hasNext());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Iterate over an {@link Iterator} with three elements.
	 */
	@Test
	public void object_iterateThree() {

		Object[] values = new Object[] { new Object(), new Object(), new Object() };
		Iterator<LookAhead<Object>> iterator = new LookAheadIterator<Object>(new ArrayIterator<Object>(values));

		Assert.assertTrue(iterator.hasNext());

		LookAhead<Object> first = iterator.next();
		Assert.assertSame(values[0], first.get());
		Assert.assertFalse(first.isLast());
		Assert.assertTrue(first.hasNext());
		Assert.assertTrue(iterator.hasNext());

		LookAhead<Object> second = iterator.next();
		Assert.assertSame(values[1], second.get());
		Assert.assertFalse(second.isLast());
		Assert.assertTrue(second.hasNext());
		Assert.assertTrue(iterator.hasNext());

		LookAhead<Object> third = iterator.next();
		Assert.assertSame(values[2], third.get());
		Assert.assertTrue(third.isLast());
		Assert.assertFalse(third.hasNext());
		Assert.assertFalse(iterator.hasNext());

	}

}
