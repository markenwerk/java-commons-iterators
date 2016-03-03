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
 * JUnit test for {@link InfixedIterator}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 */
public class InfixedIteratorTests {

	private static final Object INFIX = new Object();

	/**
	 * Iterate over a {@code null} iterator.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void create_nullIterator() {

		new InfixedIterator<Object>(null, INFIX);

	}

	/**
	 * Iterate over an empty iterator.
	 */
	@Test
	public void iterate_noElements() {

		Iterator<Object> iterator = new InfixedIterator<Object>(new EmptyIterator<Object>(), INFIX);

		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Iterate over an {@link Iterator} with one element.
	 */
	@Test
	public void iterate_oneElement() {

		Object[] values = new Object[] { new Object() };
		Iterator<Object> iterator = new InfixedIterator<Object>(new ArrayIterator<Object>(values), INFIX);

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[0], iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Iterate over an {@link Iterator} with two elements.
	 */
	@Test
	public void iterate_twoElements() {

		Object[] values = new Object[] { new Object(), new Object() };
		Iterator<Object> iterator = new InfixedIterator<Object>(new ArrayIterator<Object>(values), INFIX);

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[0], iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(INFIX, iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[1], iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Iterate over an {@link Iterator} with three elements.
	 */
	@Test
	public void iterate_threeElements() {

		Object[] values = new Object[] { new Object(), new Object(), new Object() };
		Iterator<Object> iterator = new InfixedIterator<Object>(new ArrayIterator<Object>(values), INFIX);

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[0], iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(INFIX, iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[1], iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(INFIX, iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[2], iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Try to iterate with no next element.
	 */
	@Test(expected = NoSuchElementException.class)
	public void iterate_noNext() {

		Iterator<Object> iterator = new InfixedIterator<Object>(new EmptyIterator<Object>(), INFIX);

		iterator.next();

	}

	/**
	 * Remove an object from the underlying {@link Iterator}.
	 */
	@Test
	public void remove() {

		Object replacement = new Object();
		Object[] values = new Object[] { new Object() };
		Iterator<Object> iterator = new InfixedIterator<Object>(new ArrayIterator<Object>(values, replacement), INFIX);

		iterator.next();
		iterator.remove();

		Assert.assertSame(replacement, values[0]);

	}

	/**
	 * Try to remove a value before call to {@link Iterator#next()}.
	 */
	@Test(expected = IllegalStateException.class)
	public void remove_beforeNext() {

		Iterator<Object> iterator = new InfixedIterator<Object>(new EmptyIterator<Object>(), INFIX);

		iterator.remove();

	}

}
