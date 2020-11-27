package org.pneditor.petrinet.adapters.binome10;

import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.models.binome10.Transition;

public class TransistionAdapter extends AbstractTransition{
	Transition t;
	
	public TransistionAdapter(String label) {
		super(label);
	}
	
	public TransistionAdapter(Transition t) {
		super(t.getName());
		this.t = t;
	}
}