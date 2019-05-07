package gamePackage;

public class Answer {

	private boolean correctness;
	private String answer;
	private int keyValue;
	
	public Answer(boolean correctness, String answer, int keyValue) {
		this.correctness = correctness;
		this.answer = answer;
		this.keyValue = keyValue;
	}
	
	public boolean getCorrectness() {
		return correctness;
	}

	public void setCorrectness(boolean correctness) {
		this.correctness = correctness;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(int keyValue) {
		this.keyValue = keyValue;
	}
	
	@Override
	public String toString() {
		return "Answer: " + this.getCorrectness();
	}
}
