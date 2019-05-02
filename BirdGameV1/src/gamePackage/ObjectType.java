package gamePackage;

public enum ObjectType {
	PLANE("plane"),
	QUESTION_BOX("questionBox"),
	FOOD("food"),
	BLOCK("block");
	

	private String name = null;
	
	private ObjectType(String s){
		this.name = s;
	}
	public String getName() {
		return this.name;
	}
}
