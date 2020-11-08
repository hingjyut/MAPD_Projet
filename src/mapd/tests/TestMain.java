package mapd.tests;
import mapd.arc.ArcIn;
import mapd.arc.ArcOut;
import mapd.arc.Zero;
import mapd.petriNet.PetriNet;
import mapd.petriNet.PetriNetSingleton;
import mapd.place.Place;
import mapd.transition.Transition;

public class TestMain {
	public static void main(String[] args) {
		
//		PetriNetSingleton petriNetSingleton = new PetriNetSingleton();
		
		PetriNet petriNet = new PetriNetSingleton().getSingleton();
		
		Place p1 = petriNet.createPlace(3);
		System.out.println(p1.toString());
		
		Place p2= petriNet.createPlace(0);
		System.out.println(p2.toString());
		
		Transition t1 = petriNet.createTransition();
		Transition t2 = petriNet.createTransition();
		
		System.out.println(t1.toString());
		System.out.println(t2.toString());
		
		ArcIn arcIn1 = petriNet.createArcIn(t1, p1);
		Zero arcIn = petriNet.createZero(p2, t1);
		System.out.println(arcIn.getValue());
		System.out.println(arcIn1.toString());	
	
	}

}