package welcome;

import java.util.Observable;

public class Model extends Observable {
	
	public Model() {
			
			
		
		}
	
	
	public void inform() {
		
		System.out.println("va");
		setChanged();
		notifyObservers("Test");
		
	}
	
	public void test() {
		
		System.out.println("test");
		
		setChanged();
		notifyObservers("Test");
		
	}
	
	

}
