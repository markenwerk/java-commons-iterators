package net.markenwerk.commons.iterators;

final class IteratorLookAhead<Payload> implements LookAhead<Payload> {

	private final Payload current;

	private final Payload next;

	private final boolean nextExists;

	IteratorLookAhead() {
		this(null, null, false);
	}

	private IteratorLookAhead(Payload current, Payload next, boolean nextExists) {
		this.current = current;
		this.next = next;
		this.nextExists = nextExists;
	}

	protected IteratorLookAhead<Payload> shift(Payload next, boolean nextExists) {
		return new IteratorLookAhead<Payload>(this.next, next, nextExists);
	}

	@Override
	public Payload get() {
		return current;
	}

	@Override
	public Payload getNext() {
		return next;
	}

	@Override
	public boolean hasNext() {
		return nextExists;
	}

	@Override
	public boolean isLast() {
		return !nextExists;
	}

	@Override
	public String toString() {
		return "IteratorLookAhead [current=" + current + ", next=" + next + ", hasNext=" + nextExists + "]";
	}

}
