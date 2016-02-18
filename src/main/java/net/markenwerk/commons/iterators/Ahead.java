package net.markenwerk.commons.iterators;

public interface Ahead<Payload> {

	public Payload get();

	public Payload getNext();

	public boolean hasNext();

}
