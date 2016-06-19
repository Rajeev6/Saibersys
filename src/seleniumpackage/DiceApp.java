package seleniumpackage;

import java.awt.AWTException;

public class DiceApp {

	public static void main(String args[]) throws InterruptedException, AWTException 
	{
		DiceLogin dl=new DiceLogin();
		dl.dataExtract();
		
		dl.quit();
	}

}
