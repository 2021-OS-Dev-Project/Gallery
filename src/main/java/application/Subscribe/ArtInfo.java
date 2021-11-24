package application.Subscribe;

public class ArtInfo {
	private int ArtNum;
	private String museumName;
	private String ArtName;
	private String Artist;
	private String StartPeriod;
	private String EndPeriod;
	private String Time;
	private String Price;
	private String Explanation;
	private int ExhibitionNum;

	ArtInfo(int ArtNum, String museumName,String ArtName,String ArtistName,String StartPeriod,String EndPeriod,String Time,String Price){
		this.ArtNum=ArtNum;
		this.museumName = museumName;
		this.ArtName=ArtName;
		this.Artist=ArtistName;
		this.StartPeriod=StartPeriod;
		this.EndPeriod=EndPeriod;
		this.Time=Time;
		this.Price=Price;
	}

	int getArtNum() {
		return ArtNum;
	}

	String getMuseumName(){
		return museumName;
	}
	
	String getArtName() {
		return ArtName;
	}
	void setArtName(String Art) {
		this.ArtName=ArtName;
	}
	void setArtist(String Artist) {
		this.Artist=Artist;
	}

	String getArtist(){
		return Artist;
	}

	public String getStartPeriod() {
		return StartPeriod;
	}

	public void setStartPeriod(String startPeriod) {
		StartPeriod = startPeriod;
	}

	public String getEndPeriod() {
		return EndPeriod;
	}

	public void setEndPeriod(String endPeriod) {
		EndPeriod = endPeriod;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}
}