package mapd.transition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import mapd.arc.Arc;
import mapd.arc.ArcIn;
import mapd.arc.ArcOut;
import mapd.arc.Cleaner;
import mapd.arc.Zero;

public class Transition {
	
	private int id;
	
	/**
	 * stores all arcIns in this transition
	 */
	private HashMap<Integer, Arc> arcIns;
	
	/**
	 * stores all arcOuts in this transition
	 */
	private HashMap<Integer, Arc> arcOuts;
	
	
	/**
	 * Instantiates a new transition.
	 *
	 * @param arcIns 	ArcIn list in this transition
	 * @param arcOuts   ArcOut list in this transition
	 * @param zeros     Zero list in this transition
	 * @param cleaners	Cleaner list in this transition
	 * 
	 */ 
	public Transition(int id) {
		this.id = id;
		this.arcIns = new HashMap<Integer, Arc>();
		this.arcOuts = new HashMap<Integer, Arc>();
	}
	
	/**
	 * add an arcIn into this transition's arcIns list
	 * @param arc
	 */
	public void addArcIns(Arc arc) {
		this.arcIns.put(arc.getId(), arc);
	}
	
	/**
	 * add an arcOut into this transition's arcOuts list
	 * @param arc
	 */
	public void addArcOuts(Arc arc) {
		this.arcOuts.put(arc.getId(), arc);
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
			for(Integer key: arcIns.keySet()) {
				Arc arc = arcIns.get(key);
				arc.getPlace().addTokens(arc.getValue());
			}
			for(Integer key: arcOuts.keySet()) {
				Arc arc = arcIns.get(key);
				arc.getPlace().subTokens(arc.getValue());
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
		// if all arc triggers are true, this transition can be fired
		for(Integer key: arcOuts.keySet()) {
			if (!arcOuts.get(key).trigger()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * delete arc in this transition
	 * @param arcId
	 */
	public void deleteArc(int arcId) {
		this.arcIns.remove(arcId);
		this.arcOuts.remove(arcId);
	}
	
	public HashMap<Integer, Arc> getArcIns(){
		return this.arcIns;
	}
	
	public HashMap<Integer, Arc> getArcOuts(){
		return this.arcOuts;
	}
	
	public int getId() {
		return id;
	}

	

}
