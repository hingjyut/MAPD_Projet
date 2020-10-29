package place;


public class Place {
	
	private int tokens;
	
	public Place(int tokens) {
		this.tokens = tokens;
	}
	
	public int getTokens() {
		return tokens;
	}

	public void setTokens(int tokens) {
		this.tokens = tokens;
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

	@Override
	public String toString() {
		return "Place [tokens=" + tokens + "]";
	}
	
	
}
