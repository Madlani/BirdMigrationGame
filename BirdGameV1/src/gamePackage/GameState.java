package gamePackage;

public enum GameState {
	
	START("start"),
	SIDESWIPERTUTORIAL("sideswiperTutorial"),
	SIDESWIPER("sideswiper"),
	MIGRATION("migration"),
	WHACKAMOLE("whackamole"),
	WIN("win"),
	END("end");
	
	private String name = null;
	
	private GameState(String s) {
		this.name = s;
	}
	
	public String getName() {
		return this.name;
	}
	
}
