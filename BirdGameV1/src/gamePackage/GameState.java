package gamePackage;

public enum GameState {
	
	START("start"),
	SIDESWIPERTUTORIAL("sideswiperTutorial"),
	SIDESWIPER("sideswiper"),
	MIGRATION("migration"),
	WHACKAMOLE("whackamole"),
	OSPREYWIN("ospreywin"),
	END("end"), 
	MIGRATIONTUTORIAL("northernHarrier");
	
	
	private String name = null;
	
	private GameState(String s) {
		this.name = s;
	}
	
	public String getName() {
		return this.name;
	}
	
}
