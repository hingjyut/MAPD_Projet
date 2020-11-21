package org.pneditor.petrinet.models.binome10;

import java.util.ArrayList;

import org.pneditor.petrinet.models.binome10.Arc;

public class Place {
	
	/*
	 * this place's token, 1 by default
	 */
	private int tokens = 1;
	/*
	 * this place's id
	 */
	private int id;
	/*
	 * this place's name
	 */
	private String name;
	/*
	 * an arc list to store this place's relevant arcs
	 */
	private ArrayList<Arc> arcs;
	
	/**
	 * 
	 * create a place without specified tokens
	 */
	public Place(int id) {
		this.id = id;
		this.arcs = new ArrayList<>();
		this.name = "place: "+id;
	}
	
	/**
	 * create a place with a specified tokens
	 * @param id
	 * @param tokens
	 */
	public Place(int id, int tokens) {
		this.id = id;
		this.tokens = tokens;
		this.arcs = new ArrayList<>();
		this.name = "place: "+id;
	}

	/**
	 * users can add place's tokens
	 * @param num
	 */
	public void addTokens(int num) {
		this.setTokens(this.getTokens()+num);
	}
	
	/**
	 * users can sub place's tokens 
	 * @param num
	 */
	public void subTokens(int num) {
		// check if there is enough tokens to sub
		if (this.getTokens()<num) {
			// throw an error
			throw new ArithmeticException("There is not enough to do subtraction");
		}
		this.setTokens(this.getTokens()-num);
	}
	
	/**
	 * users can delete all tokens in this place
	 */
	public void deleteTokens() {
		this.setTokens(0);
	}
	
	/**
	 * add a relevant arc into this place
	 * @param arc
	 */
	public void addArc(Arc arc) {
		this.arcs.add(arc);
	}
	
	/**
	 * delete an arc in this place
	 * @param arc
	 */
	public void deletArc(Arc arc) {
		this.arcs.remove(arc);
	}
	
	public ArrayList<Arc> getArcs(){
		return this.arcs;
	}

	@Override
	public String toString() {
		return "Place [tokens=" + tokens + "]";
	}
	
	public Place getPlace() {
		return this;
	}
	
	public int getId() {
		return id;
	}

	public int getTokens() {
		return tokens;
	}

	public void setTokens(int tokens) {
		this.tokens = tokens;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	
}
