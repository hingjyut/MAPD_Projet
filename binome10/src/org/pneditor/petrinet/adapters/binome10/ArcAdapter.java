package org.pneditor.petrinet.adapters.binome10;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.binome10.Arc;
import org.pneditor.petrinet.models.binome10.ArcOut;
import org.pneditor.petrinet.models.binome10.Cleaner;
import org.pneditor.petrinet.models.binome10.Zero;

import com.sun.glass.ui.TouchInputSupport;

public class ArcAdapter extends AbstractArc {
	
	
	private boolean isReset = false;
	private boolean isRegular = false;
	private boolean isInhibitory = false;
	
	Arc arc;
	
	public ArcAdapter(Arc arc) {
		this.arc = arc;
	}
	
	@Override
	public AbstractNode getSource() {
		
		// if it's an arcin, it's source is a transition, isPlace is false
		if (this.arc.isArcIn()) {
			return new AbstractNode(this.arc.getTransition().getName()) {
				@Override
				public boolean isPlace() {
						return false;
				}
			};
		}else {
			return new AbstractNode(this.arc.getPlace().getName()) {
				@Override
				public boolean isPlace() {
						return true;
				}
				};
		}
		
	}

	@Override
	public AbstractNode getDestination() {
		
		// if it's an arcin, it's destination is a place, isPlace is true
		if (this.arc.isArcIn()) {
			return new AbstractNode(this.arc.getPlace().getName()) {
				@Override
				public boolean isPlace() {
					return true;
				}
			};
		}else {
			return new AbstractNode(this.arc.getTransition().getName()) {
				@Override
				public boolean isPlace() {
						return false;
				}
				};
			}
	}

	@Override
	public boolean isReset() {
//		if (arc.getClass().equals(Cleaner.class)) {
//			return true;
//		}
//		return false;
		return isReset;
	}

	@Override
	public boolean isRegular() {
//		if (arc.getClass().equals(ArcOut.class)) {
//			return true;
//		}
//		return true;
		return isRegular;
	}

	@Override
	public boolean isInhibitory() {
//		if (arc.getClass().equals(Zero.class)) {
//			return true;
//		}
//		return false;
		return isInhibitory;
	}

	@Override
	public int getMultiplicity() throws ResetArcMultiplicityException {
		return 0;
	}

	@Override
	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		
		
	}

	public void setReset(boolean isReset) {
		this.isReset = isReset;
	}

	public void setRegular(boolean isRegular) {
		this.isRegular = isRegular;
	}

	public void setInhibitory(boolean isInhibitory) {
		this.isInhibitory = isInhibitory;
	}
	
	

}
