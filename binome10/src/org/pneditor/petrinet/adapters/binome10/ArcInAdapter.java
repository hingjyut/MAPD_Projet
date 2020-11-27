package org.pneditor.petrinet.adapters.binome10;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.binome10.Arc;

public class ArcInAdapter extends AbstractArc{
	
	Arc arc;
	
	public ArcInAdapter(Arc arc) {
		this.arc = arc;
	}

	@Override
	public AbstractNode getSource() {

		return new AbstractNode(this.arc.getTransition().getName()) {
			@Override
			public boolean isPlace() {
				return false;
			}
		};
		
	}

	@Override
	public AbstractNode getDestination() {
		
		return new AbstractNode(this.arc.getPlace().getName()) {
			@Override
			public boolean isPlace() {
				return true;
			}
		};
	}

	// reset is cleaner in our case
	@Override
	public boolean isReset() {
		
		return false;
	}

	@Override
	public boolean isRegular() {
		
		return true;
	}

	// inhibitory is zero in our case
	@Override
	public boolean isInhibitory() {
		
		return false;
	}

	@Override
	public int getMultiplicity() throws ResetArcMultiplicityException {
		
		return 0;
	}

	@Override
	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		
	}

}
