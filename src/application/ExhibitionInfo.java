package application;
import java.util.ArrayList;
public class ExhibitionInfo {

	private int ExhibitionNum;
	private String ExhibitionName;
	private String siteURL;
	private LocationInfo location;
	private ArrayList<ArtInfo> Art;
	
	ExhibitionInfo(int ExhibitionNum,String ExhibitionName,String siteURL,LocationInfo loc){
		this.ExhibitionNum=ExhibitionNum;
		this.ExhibitionName=ExhibitionName;
		this.siteURL=siteURL;
		this.location=loc;
		Art=new ArrayList<ArtInfo>();
	}

	public String getExhibitionName() {
		return ExhibitionName;
	}

	public ArrayList<ArtInfo> getArt() {
		return Art;
	}

	public void setArt(ArtInfo art) {
		Art.add(art);
	}

	public void setExhibitionName(String exhibitionName) {
		ExhibitionName = exhibitionName;
	}

	public String getSiteURL() {
		return siteURL;
	}

	public void setSiteURL(String siteURL) {
		this.siteURL = siteURL;
	}

	public LocationInfo getLocation() {
		return location;
	}

	public void setLocation(LocationInfo location) {
		this.location = location;
	}

}