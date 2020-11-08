package mapd.petriNet;

public class PetriNetSingleton {
	private static PetriNet singleInstance = null;
	
	public static PetriNet getSingleton() {
		if (singleInstance==null) {
			singleInstance = new PetriNet();
		}
		return singleInstance;
	}
}
