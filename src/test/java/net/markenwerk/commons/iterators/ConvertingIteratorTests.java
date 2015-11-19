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

import net.markenwerk.commons.interfaces.Converter;
import net.markenwerk.commons.interfaces.exceptions.ConverterException;

/**
 * JUnit test for {@link FilteringIterator}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public class ConvertingIteratorTests {

	private static final class Wrapper {

		private final Object wrapped;

		public Wrapper(Object wrapped) {
			this.wrapped = wrapped;
		}

	}

	private final Converter<Object, Wrapper> WRAPPING_CONVERTER = new Converter<Object, Wrapper>() {
		@Override
		public Wrapper convert(Object from) throws ConverterException {
			return null == from ? null : new Wrapper(from);
		}
	};

	/**
	 * Convert all values yielded by the underlying {@link Iterator} to their
	 * hash values.
	 */
	@Test
	public void converting_iterate() {

		Object[] values = new Object[] { new Object(), new Object() };
		Iterator<Wrapper> iterator = new ConvertingIterator<Object, Wrapper>(new ArrayIterator<Object>(values),
				WRAPPING_CONVERTER);

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[0], iterator.next().wrapped);
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[1], iterator.next().wrapped);
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Remove an object from the underlying {@link Iterator}.
	 */
	@Test
	public void converting_remove() {

		Object[] values = new Object[] { new Object() };
		Iterator<Wrapper> iterator = new ConvertingIterator<Object, Wrapper>(new ArrayIterator<Object>(values),
				WRAPPING_CONVERTER);

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[0], iterator.next().wrapped);
		Assert.assertFalse(iterator.hasNext());

		iterator.remove();

		Assert.assertNull(values[0]);

	}

}
