package application;

public class LocationInfo {

	private int LocNum;
	private String City;
	
	LocationInfo(int locNum,String city){
		this.LocNum=locNum;
		this.City=city;
	}
	public int getLocNum() {
		return LocNum;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	
}
