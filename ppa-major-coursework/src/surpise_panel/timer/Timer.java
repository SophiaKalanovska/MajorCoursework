package surpise_panel.timer;

public class Timer {
	
	private long currentTime;
	
	public Timer() {
		currentTime = System.currentTimeMillis();
	}
	
	public long getCurrentTime() {
		return currentTime;
	}
	
	public void setCurrentTime(long currentTime){
		this.currentTime = currentTime;
	}
	
	public void resetTimer(){
		currentTime = System.currentTimeMillis();
	}
	
	public boolean timerEvent(int timer){
		if(System.currentTimeMillis() - getCurrentTime() > timer){
			resetTimer();
			return true;
		}
		return false;
	}
	
	public boolean isTimerReady(int timer){
		if(System.currentTimeMillis() - getCurrentTime() > timer){
			return true;
		}
		return false;
	}

}
