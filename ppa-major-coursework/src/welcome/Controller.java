package welcome;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import gui.View;


public class Controller implements ItemListener {
	
	private Model model = new Model();	
	//private View view;
	private boolean flag;
	
	private String fromBound; 
	private String toBound;
	int counter = 0;
	
	public Controller(Model model) {
		
		this.model = model;
		
		//this.view  = view;
		
		flag = false;
		
	}
	@Override
	public void itemStateChanged(ItemEvent event) {
		
	//	int from;
	//	int to;

		if (event.getStateChange() == ItemEvent.SELECTED) {
			
			if (flag == false) {
				
		        String item = (String)event.getItem();

		    //    System.out.println("from: " + view.getJcbFrom());
		          
		  //      fromBound =  view.getJcbFrom();
		        
		        counter++;
		        flag = true;
		        //  model.inform();

			} else {
				
		        String item = (String)event.getItem();
		          
		    //    System.out.println("tooo: " + view.getJcbTo());
		          
		      //  toBound =  view.getJcbTo();
		        counter++;  
		        model.inform();
				
			}
			
			//if (counter == 2) model.dataGrabbing();
			
		}
			
		/*	System.out.println("flage false");
			
	         // Object item = event.getItem();
	          String item = (String)event.getItem();
	          
	          System.out.println("Counter 0");
	          
	          //System.out.println(item);
	          
	    //      from = Integer.parseInt(item);
	          System.out.println("from: " + view.getJcbFrom());
	          
	          fromBound =  view.getJcbFrom();
	          
	        //  model.inform();

	          // do something with object
	       } else
		
		if (event.getStateChange() == ItemEvent.SELECTED  && flag == true) {
			
			System.out.println("flage true");
			
	          String item = (String)event.getItem();

	          System.out.println("Counter 1");
	          
	          System.out.println("tooo: " + view.getJcbTo());
	          
	          toBound =  view.getJcbTo();
	          
	          model.inform();

	       } */
		
		System.out.println("wesh fin");
		
		
	}  
	
/*	public String getFromBound() {
		
		return fromBound;
		
	}
	
	public String getToBound() {
		
		return toBound;
		
	}*/


}
