
public class Place {
	private int tockens;
	public Place(int tokens) {
		this.tockens = tokens;
	}
	
	public int getTockens() {
		return tockens;
	}

	public void setTockens(int tockens) {
		this.tockens = tockens;
	}

	public void addTokens(int num) {
		this.setTockens(this.getTockens()+num);
	}
	
	public void subTokens(int num) throws Exception {
		// check if there is enough tokens to sub
		if (this.getTockens()<num) {
			// throw an error
			throw new Exception("There is not enough to do subtraction");
		}
		this.setTockens(this.getTockens()-num);
	}
	
}
