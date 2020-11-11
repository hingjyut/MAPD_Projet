package mapd.tests;
import mapd.petriNet.PetriNet;
import mapd.petriNet.PetriNetSingleton;

public class TestMain {
	public static void main(String[] args) {
		
		PetriNet petriNet = PetriNetSingleton.getSingleton();
		
		int p1 = petriNet.createPlace(3);
		System.out.println(petriNet.getPlaces().get(p1).toString());
		
		int p2= petriNet.createPlace(0);
		System.out.println(petriNet.getPlaces().get(p2).toString());
		
		int t1 = petriNet.createTransition();
		int t2 = petriNet.createTransition();
		
		int arcIn1 = petriNet.createArcIn(t1, p1);
		int arcIn2 = petriNet.createZero(p2, t2);
		
		System.out.println(petriNet.getArcs().get(arcIn1).toString());
		System.out.println(petriNet.getArcs().get(arcIn2).toString());
		System.out.println(petriNet.getArcs());
		petriNet.deleteArc(arcIn1);
		System.out.println("after delete arc");
		System.out.println(petriNet.getArcs().toString());
	
	}

}
