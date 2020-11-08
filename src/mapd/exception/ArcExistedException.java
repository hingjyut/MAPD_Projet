package mapd.exception;

public class ArcExistedException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int placeId;
	private int transitionnId;
	
	public ArcExistedException() {
		
	}
	
	public ArcExistedException(int placeId, int transitionId) {
		this.placeId = placeId;
		this.transitionnId = transitionId;
	}
	
	@Override
	public String toString() {
		return String.format("This arc between transition : %1 and place : %2 has already existed", this.transitionnId, this.placeId);
	}

}
