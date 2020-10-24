package transition;

import java.util.ArrayList;
import java.util.Iterator;

import Arc.ArcIn;
import Arc.ArcOut;
import Arc.Cleaner;
import Arc.Zero;

/**
 * @author hingjyutcheung
 *
 */
public class Transition {
	
	private ArrayList<ArcIn> arcIns;
	private ArrayList<ArcOut> arcOuts;
	private ArrayList<Zero> zeros;
	private ArrayList<Cleaner> cleaners;
	private boolean fire;
	
	/**
	 * Instantiates a new transition.
	 *
	 * @param arcIns 	ArcIn list in this transition
	 * @param arcOuts   ArcOut list in this transition
	 * @param zeros     Zero list in this transition
	 * @param cleaners	Cleaner list in this transition
	 * 
	 */ 
	public Transition() {
		// TODO Auto-generated constructor stub
		this.arcIns = new ArrayList<ArcIn>();
		this.arcOuts = new ArrayList<ArcOut>();
		this.zeros = new ArrayList<Zero>();
		this.cleaners = new ArrayList<Cleaner>();
	}
	
	/**
	 * Fires all arcs in this transition
	 * 
	 * @return if this transition is fired: true; if not: false
	 */
	public boolean nextStep() {
		// if all arcs are good, we start to fire this transition
		if (checkArcs()) {
			// No need to verify arcIn, we can add tokens from transition to Place as we want
			for(ArcIn arcIn: arcIns) {
				arcIn.getPlace().addTokens(arcIn.getValue());
			}
			// sub tokens from place
			for(ArcOut arcOut : arcOuts) {
				arcOut.getPlace().subTokens(arcOut.getValue());
			}
			// delete all tokens in place
			for(Cleaner cleaner : cleaners) {
				cleaner.getPlace().deleteTokens();
			}
			return true;
		}
		return false;
		
	}
	
	/**
	 * Checks if all arcs are okay be to fired
	 * 
	 * @return this transition can be fired: true; else false
	 */
	public boolean checkArcs() {
		// if arc's value > place's tokens, this transition can't be fired
		for(ArcOut arcOut : arcOuts) {
			if (!arcOut.draw()) {
				this.setFire(false);
			}
		}
		// if place's tokens is less than 1, this transition can't be fired
		for(Cleaner cleaner : cleaners) {
			if (!cleaner.draw()) {
				this.setFire(false);
			}
		}
		// if place's value is not equal to 0, this transition can't be fired
		for (Zero zero : zeros) {
			if (!zero.draw()) {
				this.setFire(false);
			}
		}
		return this.getFire();
	}
	
	public void addArcIn(ArcIn arcIn) {
		this.arcIns.add(arcIn);
	}
	
	public void addArcOut(ArcOut arcOut) {
		this.arcOuts.add(arcOut);
	}
	
	public void addZero(Zero zero) {
		this.zeros.add(zero);
	}
	
	public void addCleaner(Cleaner cleaner) {
		this.cleaners.add(cleaner);
	}
	
	public ArrayList<ArcIn> getArcIns() {
		return arcIns;
	}

	public ArrayList<ArcOut> getArcOuts() {
		return arcOuts;
	}

	public ArrayList<Cleaner> getCleaners() {
		return cleaners;
	}
	
	public ArrayList<Zero> getZeros() {
		return zeros;
	}
	
	public boolean getFire() {
		return this.fire;
	}
	
	public void setFire(boolean fire) {
		this.fire = fire;
	}

	

}
