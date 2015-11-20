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

import net.markenwerk.commons.interfaces.Predicate;

/**
 * A {@link FilteringIterator} is an {@link Iterator} that can be wrapped around
 * a given {@link Iterator} and filters out values according to a given
 * {@link Predicate}.
 * 
 * <p>
 * Calling {@link FilteringIterator#next()} will never return a value that
 * doesn't satisfy the given {@link Predicate} and calling
 * {@link FilteringIterator#hasNext()} will never return {@literal true}, unless
 * a value that satisfies the given {@link Predicate} is available.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public class FilteringIterator<Payload> implements Iterator<Payload> {

	private final Iterator<Payload> iterator;

	private final Predicate<Payload> predicate;

	private final boolean satisfyingTestResult;

	private boolean nextPrepared;

	private boolean nextDetected;

	private Payload next;

	/**
	 * Creates a new {@link FilteringIterator} from the given {@link Iterator}
	 * and the given {@link Predicate}.
	 * 
	 * @param iterator
	 *            The {@link Iterator}, around which the new
	 *            {@link NullFreeIterator} will be wrapped.
	 * @param predicate
	 *            The {@link Predicate} to {@link Predicate#test(Object) test}
	 *            every value yielded by the given {@link Iterator} with.
	 */
	public FilteringIterator(Iterator<Payload> iterator, Predicate<Payload> predicate) {
		this(iterator, predicate, false);
	}

	/**
	 * Creates a new {@link FilteringIterator} from the given {@link Iterator}
	 * and the given {@link Predicate}.
	 * 
	 * @param iterator
	 *            The {@link Iterator}, around which the new
	 *            {@link NullFreeIterator} will be wrapped.
	 * @param predicate
	 *            The {@link Predicate} to {@link Predicate#test(Object) test}
	 *            every value yielded by the given {@link Iterator} with.
	 * @param invertPredicate
	 *            Whether to invert the test result and yielt values that don't
	 *            satisfy the given {@link Predicate}.
	 */
	public FilteringIterator(Iterator<Payload> iterator, Predicate<Payload> predicate, boolean invertPredicate) {
		this.iterator = iterator;
		this.predicate = predicate;
		satisfyingTestResult = !invertPredicate;
	}

	@Override
	public boolean hasNext() {
		prepareNext();
		return nextDetected;
	}

	@Override
	public Payload next() {
		prepareNext();
		nextPrepared = false;
		return next;
	}

	@Override
	public void remove() {
		iterator.remove();
	}

	private void prepareNext() {
		if (!nextPrepared) {
			next = null;
			nextDetected = false;
			while (!nextDetected && iterator.hasNext()) {
				Payload nextPayload = iterator.next();
				if (satisfyingTestResult == predicate.test(nextPayload)) {
					next = nextPayload;
					nextDetected = true;
				}
			}
			nextPrepared = true;
		}
	}

}
