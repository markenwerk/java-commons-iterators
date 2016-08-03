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

import net.markenwerk.commons.exceptions.ProvisioningException;
import net.markenwerk.commons.interfaces.Provider;

/**
 * An {@link InfiniteIterator} is an {@link AbstractProtectedIterator} that
 * infinitely iterates, using a {@link Provider}, tom provide further elements.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 3.0.0
 */
public final class InfiniteIterator<Payload> extends AbstractProtectedIterator<Payload> {

	private final Provider<? extends Payload> provider;

	/**
	 * Creates a new {@link InfiniteIterator}.
	 * 
	 * @param provider
	 *            The {@link Provider} to be used.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Provider} is {@literal null}.
	 */
	public InfiniteIterator(Provider<? extends Payload> provider) throws IllegalArgumentException {
		if (null == provider) {
			throw new IllegalArgumentException("The given provider is null");
		}
		this.provider = provider;
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	/**
	 * Returns an element provided by the {@link Provider}.
	 * 
	 * @throws ProvisioningException
	 *             If the {@link Provider} failed to provide an element.
	 */
	@Override
	public Payload next() throws ProvisioningException {
		return provider.provide();
	}

}
