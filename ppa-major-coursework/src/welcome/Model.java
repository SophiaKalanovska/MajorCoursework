package welcome;

import java.util.Observable;


public class Model extends Observable {
	
	private boolean flag;
	
	public Model() {
		
		flag = false;
			
		}
	
	
	public void inform() {
		
		System.out.println("inform's been called ");

		setChanged();
		notifyObservers("Grab Data");

	}
	
	public void desinform() {
		
		System.out.println("desinform's been called ");
		
		setChanged();
		notifyObservers("Out of bounds");
		
	}
	
	public void left() {
		
		setChanged();
		notifyObservers("Left");
		
	}
	
public void right() {
		
		setChanged();
		notifyObservers("Right");
		
	}
	
    
    public boolean getFlag() {
    	
    	return flag;
    	
    }
	
	

}
