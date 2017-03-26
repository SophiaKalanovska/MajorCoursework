package welcome;

import java.util.Observable;


public class Model extends Observable {
	
	private boolean flag;
	
	public Model() {
		
		flag = false;
			
		}
	
	
	public void inform() {
		
		//WelcomePanel welcome = new WelcomePanel();
		//welcome.update(this, "Test");
		
		
		
		System.out.println("inform's been called ");
		
	//	setChanged();	
	//	notifyObservers("Test");
			
			setChanged();
			notifyObservers("Test 2");

		

		//return flag;
		
	}
	
	public void dataGrabbing() {
		
		System.out.println("from ta mere");
		setChanged();
		notifyObservers("Grab Data");
		
	}
	
	
	
    public void infromTo() {
		
    	System.out.println("infromTo's been called");
		setChanged();	
		notifyObservers("cbTo changed");

		
	}
    
    public boolean getFlag() {
    	
    	return flag;
    	
    }
	
	

}
