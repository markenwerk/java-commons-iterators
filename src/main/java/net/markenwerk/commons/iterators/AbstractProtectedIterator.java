package net.markenwerk.commons.iterators;

/**
 * An {@link AbstractProtectedIterator} is a base implementation for
 * {@link ProtectedIterator} that guarantees that every call to
 * {@linkplain AbstractProtectedIterator#remove()} throws an
 * {@link UnsupportedOperationException}.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 3.0.1
 */
public abstract class AbstractProtectedIterator<Payload> implements ProtectedIterator<Payload> {

	/**
	 * Creates a new {@link AbstractProtectedIterator}.
	 */
	protected AbstractProtectedIterator() {
	}

	@Override
	public final void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Cannot remove from a protected iterator");
	}

}
