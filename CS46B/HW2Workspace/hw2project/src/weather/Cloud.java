package weather;

public class Cloud {

	private float bottom;
	private float top;
	
	Cloud (float b, float t){
		
		bottom = b;
		top = t;
	}
	
	public float getHeight() {
		
		return top - bottom;
	
	}
	
	public String rain() {
		
		return "It is raining";
	}
}
