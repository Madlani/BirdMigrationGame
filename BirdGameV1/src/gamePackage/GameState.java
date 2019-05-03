package gamePackage;

public enum GameState {
	
	START("start"),
	SIDESWIPER("sideswiper"),
	MIGRATION("migration"),
	WHACKAMOLE("whackamole"),
	END("end");
	
	private String name = null;
	
	private GameState(String s) {
		this.name = s;
	}
	
	public String getName() {
		return this.name;
	}
	
}
