package mapd.exception;

public class NoThisTypeArcException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String arcType;
	
	public NoThisTypeArcException() {
		
	}
	
	public NoThisTypeArcException (String arcType) {
		super();
		this.arcType = arcType;
	}
	
	@Override
	public String toString() {
		return String.format("This type's arc %1 doesn't exist", this.arcType);
	}

}
