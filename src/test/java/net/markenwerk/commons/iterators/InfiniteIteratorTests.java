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

import net.markenwerk.commons.exceptions.ProvisioningException;
import net.markenwerk.commons.interfaces.Provider;

@SuppressWarnings("javadoc")
public class InfiniteIteratorTests {

	private static final Object OBJECT = new Object();

	private static final Provider<Object> PROVIDER = new Provider<Object>() {

		@Override
		public Object provide() throws ProvisioningException {
			return OBJECT;
		}
	};

	@Test(expected = IllegalArgumentException.class)
	public void create_nullArray() {

		new ArrayIterator<Object>((Object[]) null);

	}

	@Test
	public void iterate() {

		Iterator<Object> iterator = new InfiniteIterator<Object>(PROVIDER);

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(OBJECT, iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(OBJECT, iterator.next());
		Assert.assertTrue(iterator.hasNext());

	}

	@Test(expected = UnsupportedOperationException.class)
	public void remove() {

		Iterator<Object> iterator = new InfiniteIterator<Object>(PROVIDER);

		iterator.remove();

	}

}
