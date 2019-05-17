package gamePackage;

public enum ObjectType {
	PLANE("plane"),
	QUESTION_BOX("questionBox"),
	FOOD("food"),
	BLOCK("block"),
	BIRD("bird"),
	CLOUD_QUESTION_BOX("cloudQuestionBox"),
	THUNDER_CLOUD("thunderCloud"),
	MOUSE("mouse"),
	FOX("fox");

	private String name = null;
	
	private ObjectType(String s){
		this.name = s;
	}
	public String getName() {
		return this.name;
	}
}
