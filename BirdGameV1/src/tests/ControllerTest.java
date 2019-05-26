package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//import javax.jws.WebParam.Mode;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import gamePackage.Controller;
import gamePackage.GameState;

class ControllerTest {
	//Controller testController = new Controller();

	@Test
	public void testController() {
		Controller c = new Controller();
		c.init();
		
		c.getSideSwiperModel().getOsprey().setHealthCount(-1);
		c.updateSideSwiperModel();
		assertEquals(c.getState(), GameState.LOSE);
		
		c.getMigrationModel().getNorthernHarrier().setHealthCount(-1);
		c.updateMigrationModel();
		assertEquals(c.getState(), GameState.LOSE);
		
		c.getSideSwiperModel().setIsFirstFrame(true);
		c.getSideSwiperModel().setPicNumMap(9);
		c.updateSideSwiperModel();
		assertEquals(c.getState(), GameState.WHACKAMOLE);
		
		c.getMigrationModel().setFirstFrame(true);
		c.getMigrationModel().setPicNumMap(13);
		c.updateMigrationModel();
		assertEquals(c.getState(), GameState.WHACKAMOLE);
		
		
	}
//	@Test
//	public void testStart() {
//		assertEquals(true, testController.getControllerStart());
//	}
//	
//	@Test
//	public void testKeyTyped() {
//		assertEquals(true, testController.getKeyPressed());
//	}
//	
//	@Test
//	public void testKeyReleased() {
//		assertEquals(true, testController.getKeyReleased());
//	}
//	
//	@Test
//	public void testActionPerformed() {
//		assertEquals(true, testController.getActionPerformed());
//	}
//	
}
