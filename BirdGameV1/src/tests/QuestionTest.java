package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gamePackage.BirdType;
import gamePackage.Question;

class QuestionTest {

	@Test
	void test() {
		Question ospreyQuestion = new Question(BirdType.OSPREY);
		Question northernHarrierQuestion = new Question(BirdType.NORTHERNHARRIER);
		
		ospreyQuestion.setCorrect(true);
		assertTrue(ospreyQuestion.isCorrect());
		
		northernHarrierQuestion.setCorrect(false);
		assertFalse(northernHarrierQuestion.isCorrect());
	}

}
