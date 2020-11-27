package org.pneditor.petrinet.models.binome10;

public class TestMain {
	public static void main(String[] args) {
		
		PetriNet petriNet = PetriNetSingleton.getSingleton();
		
		// create a place with 3 tokens
		int p1 = petriNet.createPlace(3);
		
		// create a place with 0 tokens
		int p2= petriNet.createPlace(0);
		
		// create a place with default tokens
		int p3= petriNet.createPlace();
		
		// create 2 transitions
		int t1 = petriNet.createTransition();
		int t2 = petriNet.createTransition();
		
		// create a regular arc and an inhibitory arc
		int arc1 = petriNet.createArcIn(t1, p1);
		int arc2 = petriNet.createZero(p2, t2);
		int arc3 = petriNet.createCleaner(p3, t2);
		int arc4 = petriNet.createArcOut(p1, t2);
		
		/*
		 * p2-->t2
		 * p3-->t2
		 * t1-->p1-->t2
		 */
		
		petriNet.deleteArc(arc1);
	 	petriNet.deleteArc(arc2);
	 	petriNet.deleteArc(arc3);
	 	petriNet.deleteArc(arc4);
	 	petriNet.deletePlace(p1);
	 	petriNet.deleteTransition(t1);
		
		/**
		 * 
		 * 
		 * 1. Delete functions don't work well for instance cause deleteArc doesn't work
		 * 
		 	petriNet.deleteArc(arc1);
		 	petriNet.deleteArc(arc2);
		 	petriNet.deleteArc(arc3);
		 	petriNet.deleteArc(arc4);
		 	petriNet.deletePlace(p1);
		 	petriNet.deleteTransition(t1);
		 * 
		 * 
		 * 
		 * 2. Some exceptions
		 	petriNet.getPlaces().get(p2).subTokens(1); 	// can't do sub in a place with 0 token
		 	petriNet.createZero(p3, t1);					// can't create an arc zero with a place has more than 0 token
		 * 
		 */
		
		
		
		
	
	}

}