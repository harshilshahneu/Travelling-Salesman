package edu.northeastern.info6205.tspsolver.jgrapht.graph;

import java.io.Serializable;

class IntrusiveEdge implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;

	Object source;

	Object target;

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// shouldn't happen as we are Cloneable
			throw new InternalError();
		}
	}
}
