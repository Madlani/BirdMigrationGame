package gamePackage;

public enum BirdType {
	OSPREY("osprey"), NORTHERNHARRIER("northernHarrier");

	private String state = null;

	private BirdType(String state) {
		this.state = state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return this.state;
	}
}
