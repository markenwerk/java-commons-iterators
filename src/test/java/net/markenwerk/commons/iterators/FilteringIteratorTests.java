/*
 * Copyright (c) 2015, 2016 Torsten Krause, Markenwerk GmbH
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

import net.markenwerk.commons.interfaces.Predicate;

/**
 * JUnit test for {@link FilteringIterator}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 */
public class FilteringIteratorTests {

	private final Object UNSATISFYING_OBJECT = new Object();

	private final Predicate<Object> UNSATISFYING_OBJECT_PREDICATE = new Predicate<Object>() {
		@Override
		public boolean test(Object object) {
			return null == object || UNSATISFYING_OBJECT != object;
		}
	};

	/**
	 * Create with a {@code null} iterator.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void create_nullIterator() {

		new FilteringIterator<Object>(null, UNSATISFYING_OBJECT_PREDICATE);

	}

	/**
	 * Create with a {@code null} predicate.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void create_nullPredicate() {

		new FilteringIterator<Object>(new EmptyIterator<Object>(), null);

	}

	/**
	 * Filter out a unsatisfying value at the front of the underlying
	 * {@link Iterator}.
	 */
	@Test
	public void iterate_unsatisfyingAtFront() {

		Object[] values = new Object[] { UNSATISFYING_OBJECT, new Object() };
		Iterator<Object> iterator = new FilteringIterator<Object>(new ArrayIterator<Object>(values),
				UNSATISFYING_OBJECT_PREDICATE);

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[1], iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Filter out a unsatisfying value in the middle of the underlying
	 * {@link Iterator}.
	 */
	@Test
	public void iterate_unsatisfyingInMiddle() {

		Object[] values = new Object[] { new Object(), UNSATISFYING_OBJECT, new Object() };
		Iterator<Object> iterator = new FilteringIterator<Object>(new ArrayIterator<Object>(values),
				UNSATISFYING_OBJECT_PREDICATE);

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[0], iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[2], iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Filter out a unsatisfying value at the end of the underlying
	 * {@link Iterator}.
	 */
	@Test
	public void iterate_unsatisfyingAtEnd() {

		Object[] values = new Object[] { new Object(), UNSATISFYING_OBJECT };
		Iterator<Object> iterator = new FilteringIterator<Object>(new ArrayIterator<Object>(values),
				UNSATISFYING_OBJECT_PREDICATE);

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[0], iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Filter out a unsatisfying value in the middle of the underlying
	 * {@link Iterator}.
	 */
	@Test
	public void iterate_invertedPredicate() {

		Object[] values = new Object[] { new Object(), UNSATISFYING_OBJECT, new Object() };
		Iterator<Object> iterator = new FilteringIterator<Object>(new ArrayIterator<Object>(values),
				UNSATISFYING_OBJECT_PREDICATE, true);

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(UNSATISFYING_OBJECT, iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Try to iterate with no next element.
	 */
	@Test(expected = NoSuchElementException.class)
	public void iterate_noNext() {

		Iterator<Object> iterator = new FilteringIterator<Object>(new EmptyIterator<Object>(),
				UNSATISFYING_OBJECT_PREDICATE);

		iterator.next();

	}

	/**
	 * Remove an object from the underlying {@link Iterator}.
	 */
	@Test
	public void remove() {

		Object replacement = new Object();
		Object[] values = new Object[] { new Object() };
		Iterator<Object> iterator = new FilteringIterator<Object>(new ArrayIterator<Object>(values, replacement),
				UNSATISFYING_OBJECT_PREDICATE);

		iterator.next();
		iterator.remove();

		Assert.assertSame(replacement, values[0]);

	}

	/**
	 * Try to remove a value before call to {@link Iterator#next()}.
	 */
	@Test(expected = IllegalStateException.class)
	public void remove_beforeNext() {

		Iterator<Object> iterator = new FilteringIterator<Object>(new EmptyIterator<Object>(),
				UNSATISFYING_OBJECT_PREDICATE);

		iterator.remove();

	}

}
