package welcome;

import java.util.Observable;


public class Model extends Observable {
	
	public Model() {
			
		
		
		}
	
	
	public void inform() {
		
		System.out.println(hasChanged());
		
		setChanged();
		
		System.out.println(hasChanged());
		
		notifyObservers("Hello");
		
		System.out.println("received from controller");
		
	}
	
	

}
