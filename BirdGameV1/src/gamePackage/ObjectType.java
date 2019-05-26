package gamePackage;

import java.io.Serializable;

public enum ObjectType implements Serializable {
	//Osprey
	PLANE("plane"),
	FISH("fish"),
	OSPREY("osprey"),
	CLOUD_QUESTION_BOX("cloudQuestionBox"),
	THUNDER_CLOUD("thunderCloud"),
	FOX("fox"),
	
	//NorthernHarrier
	NORTHERNHARRIER("northernHarrier"),
	TREE("tree"),
	OWL("owl"),
	MOUSE("mouse"),
	BUSH_QUESTION_BOX("bushQuestionBox");
	
	private String name = null;
	
	private ObjectType(String s){
		this.name = s;
	}
	public String getName() {
		return this.name;
	}
}
