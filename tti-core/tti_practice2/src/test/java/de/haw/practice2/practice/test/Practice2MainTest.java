package de.haw.practice2.practice.test;

import java.io.FileNotFoundException;

import org.testng.annotations.Test;

import de.haw.practice2.practice.Practice2Main;

public class Practice2MainTest {
	
	@Test
	public void practiceMainTest() throws FileNotFoundException{
		Practice2Main pm = new Practice2Main();
		pm.createSchema();
	}

}
