package gamePackage;

import java.io.Serializable;

public enum GameState implements Serializable {
	
	START("start"),
	TUTORIAL("tutorial"),
	SIDESWIPER("sideswiper"),
	MIGRATION("migration"),
	WHACKAMOLE("whackamole"),
	OSPREYWIN("ospreywin"),
	LOSE("lose"), 
	WIN("win");
	
	
	private String name = null;
	
	private GameState(String s) {
		this.name = s;
	}
	
	public String getName() {
		return this.name;
	}
	
}
