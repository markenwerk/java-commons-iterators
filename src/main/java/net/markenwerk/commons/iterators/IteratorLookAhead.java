package net.markenwerk.commons.iterators;

final class IteratorLookAhead<Payload> implements Ahead<Payload> {

	private Payload current;

	private Payload next;

	private boolean nextExists;

	protected IteratorLookAhead() {
	}

	protected void shift(Payload next, boolean nextExists) {
		current = this.next;
		this.next = next;
		this.nextExists = nextExists;
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
	public String toString() {
		return "IteratorLookAhead [current=" + current + ", next=" + next + ", hasNext=" + nextExists + "]";
	}

}
