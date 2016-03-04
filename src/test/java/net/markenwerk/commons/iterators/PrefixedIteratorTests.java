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
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link PrefixedIterator}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 */
public class PrefixedIteratorTests {

	private static final Object PREFIX = new Object();

	/**
	 * Iterate with a {@code null} iterator.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void create_nullIterator() {

		new PrefixedIterator<Object>(null, PREFIX);

	}

	/**
	 * Iterate with a {@code null} prefixes.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void create_nullPrefixes() {

		new PrefixedIterator<Object>(new EmptyIterator<Object>(), (Object[]) null);

	}

	/**
	 * Iterate over an empty iterator.
	 */
	@Test
	public void iterate_noElements() {

		Iterator<Object> iterator = new PrefixedIterator<Object>(new EmptyIterator<Object>(), PREFIX);

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(PREFIX, iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Iterate over an {@link Iterator} with one element.
	 */
	@Test
	public void iterate_oneElement() {

		Object[] values = new Object[] { new Object() };
		Iterator<Object> iterator = new PrefixedIterator<Object>(new ArrayIterator<Object>(values), PREFIX);

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(PREFIX, iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[0], iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Iterate over an {@link Iterator} with no prefixes.
	 */
	@Test
	public void iterate_noPrefixes() {

		Object[] values = new Object[] { new Object() };
		Iterator<Object> iterator = new PrefixedIterator<Object>(new ArrayIterator<Object>(values));

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[0], iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Iterate over an {@link Iterator} with two prefixes.
	 */
	@Test
	public void iterate_twoPrefixes() {

		Object additionalPrefix = new Object();
		Object[] values = new Object[] { new Object() };
		Iterator<Object> iterator = new PrefixedIterator<Object>(new ArrayIterator<Object>(values), PREFIX,
				additionalPrefix);

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(PREFIX, iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(additionalPrefix, iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[0], iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Try to iterate with no next element.
	 */
	@Test(expected = NoSuchElementException.class)
	public void iterate_noNext() {

		Iterator<Object> iterator = new PrefixedIterator<Object>(new EmptyIterator<Object>());

		iterator.next();

	}

	/**
	 * Remove an object from the underlying {@link Iterator}.
	 */
	@Test
	public void remove() {

		RemoveTestIterator testIterator = new RemoveTestIterator();
		Iterator<Object> iterator = new PrefixedIterator<Object>(testIterator, PREFIX);

		iterator.next();
		iterator.next();
		iterator.remove();

		Assert.assertTrue(testIterator.removed());

	}

	/**
	 * Try to remove a value before call to {@link Iterator#next()}.
	 */
	@Test(expected = IllegalStateException.class)
	public void remove_beforeNext() {

		Iterator<Object> iterator = new PrefixedIterator<Object>(new EmptyIterator<Object>(), PREFIX);

		iterator.remove();

	}

}
