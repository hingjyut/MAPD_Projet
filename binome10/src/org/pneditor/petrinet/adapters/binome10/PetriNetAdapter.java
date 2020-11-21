package org.pneditor.petrinet.adapters.binome10;

import static org.hamcrest.CoreMatchers.instanceOf;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.PetriNetInterface;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.UnimplementedCaseException;
import org.pneditor.petrinet.models.binome10.Place;
import org.pneditor.petrinet.models.binome10.Arc;
import org.pneditor.petrinet.models.binome10.ArcIn;
import org.pneditor.petrinet.models.binome10.Cleaner;
import org.pneditor.petrinet.models.binome10.PetriNet;
import org.pneditor.petrinet.models.binome10.Transition;
import org.pneditor.petrinet.models.binome10.Zero;;

public class PetriNetAdapter extends PetriNetInterface {

	PetriNet petriNet =new PetriNet();
	@Override
	public AbstractPlace addPlace() {
		int placeId = petriNet.createPlace();
		Place place = petriNet.getPlaces().get(placeId);
		return new PlaceAdapter(place);
		
	}

	@Override
	public AbstractTransition addTransition() {
		int transitionId = petriNet.createTransition();
		Transition transition = petriNet.getTransitions().get(transitionId); 
		return new TransistionAdapter(transition.getName());
	}

	@Override
	public AbstractArc addRegularArc(AbstractNode source, AbstractNode destination) throws UnimplementedCaseException {
		// transition to place, it's arcIn in our case
		if (!source.isPlace() && destination.isPlace()) {
			int arcInId = petriNet.createArcIn(source.getId(), destination.getId());
			Arc arcIn = petriNet.getArcs().get(arcInId);
			return new ArcInAdapter(arcIn);
		}else if (source.isPlace() && !destination.isPlace()) {
			int arcOutId = petriNet.createArcOut(source.getId(), destination.getId());
			Arc arcOut = petriNet.getArcs().get(arcOutId);
			return new ArcOutAdapter(arcOut);
		}
		return null;
	}

	@Override
	public AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		int arcId = petriNet.createZero(place.getId(), transition.getId());
		Zero zero = (Zero) petriNet.getArcs().get(arcId);
		return new ArcOutAdapter(zero);
	}

	@Override
	public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		int arcId = petriNet.createZero(place.getId(), transition.getId());
		Cleaner cleaner = (Cleaner) petriNet.getArcs().get(arcId);
		return new ArcOutAdapter(cleaner);
	}

	@Override
	public void removePlace(AbstractPlace place) {
		petriNet.deletePlace(place.getId());
	}

	@Override
	public void removeTransition(AbstractTransition transition) {
		petriNet.deleteTransition(transition.getId());
	}

	@Override
	public void removeArc(AbstractArc arc) {
		int sourceId = arc.getSource().getId();
		int destId = arc.getDestination().getId();
		if (arc.isSourceAPlace()) {
			petriNet.deleteArc(sourceId, destId);
		}else {
			petriNet.deleteArc(destId, sourceId);
		}
		
	}

	@Override
	public boolean isEnabled(AbstractTransition transition) throws ResetArcMultiplicityException {
		int transitionId = transition.getId();
		Transition t = petriNet.getTransitions().get(transitionId);
		return t.checkArcs();
	}

	@Override
	public void fire(AbstractTransition transition) throws ResetArcMultiplicityException {
		int transitionId = transition.getId();
		Transition t = petriNet.getTransitions().get(transitionId);
		t.nextStep();
	}
	

}
