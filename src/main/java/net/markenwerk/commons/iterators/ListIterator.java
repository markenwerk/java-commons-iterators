package net.markenwerk.commons.iterators;

import java.util.List;
import java.util.NoSuchElementException;

public class ListIterator<Payload> implements Reiterator<Payload> {

	private final java.util.ListIterator<Payload> iterator;

	public ListIterator(List<Payload> list) throws IllegalArgumentException {
		if (null == list) {
			throw new IllegalArgumentException("The given list is null");
		}
		this.iterator = list.listIterator();
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Payload next() {
		return iterator.next();
	}

	@Override
	public boolean hasPrevious() {
		return iterator.hasPrevious();
	}

	@Override
	public Payload previous() throws NoSuchElementException {
		return iterator.previous();
	}

	@Override
	public void remove() throws UnsupportedOperationException, IllegalStateException {
		iterator.remove();
	}

}
