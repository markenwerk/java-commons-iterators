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

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * A {@link NodeListIterator} is a {@link AbstractIndexedIterator} that iterates
 * over a given {@link NodeList}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 3.0.0
 */
public final class NodeListIterator extends AbstractIndexedIterator<Node> {

	private final NodeList nodeList;

	/**
	 * Creates a new {@link NodeListIterator}.
	 * 
	 * @param nodeList
	 *            The {@link NodeList} to iterate over.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link NodeList} is {@literal null}.
	 */
	public NodeListIterator(NodeList nodeList) throws IllegalArgumentException {
		super(0, length(nodeList));
		this.nodeList = nodeList;
	}

	private static int length(NodeList nodeList) {
		if (null == nodeList) {
			throw new IllegalArgumentException("The given node list is null");
		}
		return nodeList.getLength();
	}

	@Override
	public Node get(int index) {
		return nodeList.item(index);
	}

}
