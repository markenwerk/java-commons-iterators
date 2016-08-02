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

import net.markenwerk.commons.datastructures.Box;
import net.markenwerk.commons.interfaces.Handler;

@SuppressWarnings("javadoc")
public class RemoveHandlerIteratorTests {

	private static final Handler<Object> EMPTY_HANDLER = new Handler<Object>() {
		@Override
		public void handle(Object object) {
		}
	};

	@Test(expected = IllegalArgumentException.class)
	public void create_nullIterator() {

		new RemoveHandlerIterator<Object>(null, EMPTY_HANDLER);

	}

	@Test(expected = IllegalArgumentException.class)
	public void create_nullRemoveHandler() {

		new RemoveHandlerIterator<Object>(new EmptyIterator<Object>(), null);

	}

	@Test
	public void iterate() {

		Object value = new Object();
		Iterator<Object> iterator = new RemoveHandlerIterator<Object>(new ObjectIterator<Object>(value), EMPTY_HANDLER);

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(value, iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	@Test
	public void remove() {

		final Box<Boolean> called = new Box<Boolean>();

		Handler<Object> handler = new Handler<Object>() {

			@Override
			public void handle(Object event) {
				called.setValue(true);
			}
		};

		Object value = new Object();
		Iterator<Object> iterator = new RemoveHandlerIterator<Object>(new ObjectIterator<Object>(value), handler);

		iterator.next();
		iterator.remove();

		Assert.assertTrue(called.getValue());

	}

}
