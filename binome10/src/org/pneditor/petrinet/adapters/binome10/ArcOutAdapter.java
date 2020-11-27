package org.pneditor.petrinet.adapters.binome10;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.binome10.Arc;
import org.pneditor.petrinet.models.binome10.ArcOut;
import org.pneditor.petrinet.models.binome10.Cleaner;
import org.pneditor.petrinet.models.binome10.Zero;

public class ArcOutAdapter extends AbstractArc {
	
	Arc arc;
	
	public ArcOutAdapter(Arc arc) {
		this.arc = arc;
	}
	
	@Override
	public AbstractNode getSource() {
		
		return new AbstractNode(this.arc.getPlace().getName()) {
			@Override
			public boolean isPlace() {
				return true;
			}
		};
	}

	@Override
	public AbstractNode getDestination() {
		
		return new AbstractNode(this.arc.getTransition().getName()) {
			@Override
			public boolean isPlace() {
				return false;
			}
		};
	}

	@Override
	public boolean isReset() {
		if (arc.getClass().equals(Cleaner.class)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isRegular() {
		if (arc.getClass().equals(ArcOut.class)) {
			return true;
		}
		return true;
	}

	@Override
	public boolean isInhibitory() {
		if (arc.getClass().equals(Zero.class)) {
			return true;
		}
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
