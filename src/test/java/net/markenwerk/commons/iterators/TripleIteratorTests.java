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

import net.markenwerk.commons.datastructures.Triple;

@SuppressWarnings("javadoc")
public class TripleIteratorTests {

	@Test(expected = IllegalArgumentException.class)
	public void create_nullArray() {

		new TripleIterator<Object>(null);

	}

	@Test
	public void iterate() {

		Triple<Object, Object, Object> triple = new Triple<Object, Object, Object>(new Object(), new Object(),
				new Object());
		Iterator<Object> iterator = new TripleIterator<Object>(triple);

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(triple.getFirst(), iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(triple.getSecond(), iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(triple.getThird(), iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	@Test(expected = NoSuchElementException.class)
	public void iterate_noNext() {

		Triple<Object, Object, Object> triple = new Triple<Object, Object, Object>(new Object(), new Object(),
				new Object());
		Iterator<Object> iterator = new TripleIterator<Object>(triple);

		iterator.next();
		iterator.next();
		iterator.next();
		iterator.next();

	}

	@Test(expected = UnsupportedOperationException.class)
	public void remove() {

		Triple<Object, Object, Object> triple = new Triple<Object, Object, Object>(new Object(), new Object(),
				new Object());
		Iterator<Object> iterator = new TripleIterator<Object>(triple);

		iterator.remove();

	}

}
