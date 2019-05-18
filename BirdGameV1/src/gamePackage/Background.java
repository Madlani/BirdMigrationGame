package gamePackage;

public enum Background {
	LAND("land"),
	BEACH("beach"),
	OCEAN("ocean");

	
	private String state = null;
	
	private Background(String state) {
		this.state = state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getState() {
		return this.state;
	}
}
