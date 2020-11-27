package org.pneditor.petrinet.adapters.binome10;

import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.models.binome10.Place;

public class PlaceAdapter extends AbstractPlace{

	Place place;
	public PlaceAdapter(String label) {
		super(label);
	}
	
	public PlaceAdapter(Place p) {
		super(p.getName());
		this.place = p;
	}

	@Override
	public void addToken() {
		this.place.addTokens(1);
		
	}

	@Override
	public void removeToken() {
		this.place.subTokens(1);
	}

	@Override
	public int getTokens() {
		return this.place.getTokens();
	}

	@Override
	public void setTokens(int tokens) {
		this.place.setTokens(tokens);
		
	}
	

}
