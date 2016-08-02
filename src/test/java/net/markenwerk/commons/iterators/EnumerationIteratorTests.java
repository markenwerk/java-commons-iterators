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

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("javadoc")
public class EnumerationIteratorTests {

	@Test(expected = IllegalArgumentException.class)
	public void create_nullEnumeration() {

		new EnumerationIterator<Object>(null);

	}

	@Test
	public void iterate_emptyEnumeration() {

		Enumeration<Object> enumeration = new Vector<Object>(Arrays.asList()).elements();
		Iterator<Object> iterator = new EnumerationIterator<Object>(enumeration);

		Assert.assertFalse(iterator.hasNext());

	}

	@Test
	public void iterate_nonEmptyEnumeration() {

		Object value = new Object();
		Enumeration<Object> enumeration = new Vector<Object>(Arrays.asList(value)).elements();
		Iterator<Object> iterator = new EnumerationIterator<Object>(enumeration);

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(value, iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	@Test(expected = NoSuchElementException.class)
	public void iterate_noNext() {

		Enumeration<Object> enumeration = new Vector<Object>(Arrays.asList()).elements();
		Iterator<Object> iterator = new EnumerationIterator<Object>(enumeration);

		iterator.next();

	}

	@Test(expected = UnsupportedOperationException.class)
	public void remove() {

		Enumeration<Object> enumeration = new Vector<Object>(Arrays.asList()).elements();
		Iterator<Object> iterator = new EnumerationIterator<Object>(enumeration);

		iterator.remove();

	}

}
