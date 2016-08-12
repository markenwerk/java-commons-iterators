package net.markenwerk.commons.iterators;

/**
 * An {@link AbstractProtectedReiterator} is a base implementation for
 * {@link ProtectedReiterator} that guarantees that every call to
 * {@linkplain AbstractProtectedReiterator#remove()} throws an
 * {@link UnsupportedOperationException}.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 3.1.1
 */
public abstract class AbstractProtectedReiterator<Payload> extends AbstractProtectedIterator<Payload> implements
		ProtectedReiterator<Payload> {

	/**
	 * Creates a new {@link AbstractProtectedReiterator}.
	 */
	protected AbstractProtectedReiterator() {
	}

}
