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
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link FilteringIterator}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public class CombinedIteratorTests {

	/**
	 * Iterate over no iterators.
	 */
	@Test
	@SuppressWarnings("unchecked")
	public void combined_noIterators() {

		Iterator<Object> iterator = new CombinedIterator<Object>();

		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Iterate over one iterator.
	 */
	@Test
	@SuppressWarnings("unchecked")
	public void combined_oneIterator() {

		Object[] values = new Object[] { new Object(), new Object() };
		Iterator<Object> iterator = new CombinedIterator<Object>(new ArrayIterator<Object>(values));

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[0], iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[1], iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Iterate over multiple iterator.
	 */
	@Test
	@SuppressWarnings("unchecked")
	public void combined_multipleIterators() {

		Object[] values = new Object[] { new Object(), new Object() };
		Iterator<Object> iterator = new CombinedIterator<Object>(new ArrayIterator<Object>(values),
				new ArrayIterator<Object>(values));

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[0], iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[1], iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[0], iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[1], iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Iterate over multiple iterator.
	 */
	@Test
	public void combined_iteratorsIterator() {

		Object[] values = new Object[] { new Object(), new Object() };

		List<Iterator<Object>> iteratorsList = new LinkedList<Iterator<Object>>();
		iteratorsList.add(new ArrayIterator<Object>(values));
		iteratorsList.add(new ArrayIterator<Object>(values));
		Iterator<Object> iterator = new CombinedIterator<Object>(iteratorsList.iterator());

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[0], iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[1], iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[0], iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[1], iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Remove an object from the underlying {@link Iterator}.
	 */
	@Test
	@SuppressWarnings("unchecked")
	public void combined_remove() {

		Object[] values = new Object[] { new Object() };
		Iterator<Object> iterator = new CombinedIterator<Object>(new ArrayIterator<Object>(values));

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[0], iterator.next());
		Assert.assertFalse(iterator.hasNext());

		iterator.remove();

		Assert.assertNull(values[0]);

	}

}
