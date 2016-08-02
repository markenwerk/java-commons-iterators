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

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("javadoc")
public class NullSaveIteratorTests {

	@Test
	public void iterate_nonNullIterator() {

		Object[] values = new Object[] { new Object(), new Object() };
		Iterator<Object> iterator = new NullSaveIterator<Object>(new ArrayIterator<Object>(values));

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[0], iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[1], iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	@Test
	public void iterate_nonNullIterable() {

		final Object[] values = new Object[] { new Object(), new Object() };
		Iterator<Object> iterator = new NullSaveIterator<Object>(new Iterable<Object>() {
			@Override
			public Iterator<Object> iterator() {
				return new ArrayIterator<Object>(values);
			}
		});

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[0], iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[1], iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	@Test
	public void iterate_nullIterator() {

		Iterator<Object> iterator = new NullSaveIterator<Object>((Iterator<Object>) null);

		Assert.assertFalse(iterator.hasNext());

	}

	@Test
	public void iterate_nNullIterable() {

		Iterator<Object> iterator = new NullSaveIterator<Object>((Iterable<Object>) null);

		Assert.assertFalse(iterator.hasNext());

	}

	@Test
	public void remove() {

		RemoveTestIterator testIterator = new RemoveTestIterator();
		Iterator<Object> iterator = new NullSaveIterator<Object>(testIterator);

		iterator.next();
		iterator.remove();

		Assert.assertTrue(testIterator.removed());

	}

}
