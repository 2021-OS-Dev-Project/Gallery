package application.gallery;

public class ArtInfo {
	private int ArtNum;
	private String ArtName;
	private String Artist;
	private String StartPeriod;
	private String EndPeriod;
	private String Time;
	private String Price;
	private String Explanation;
	private String SRC;
	private int ExhibitionNum;

	ArtInfo(int ArtNum,String ArtName,String ArtistName,String StartPeriod,String EndPeriod,String Time,String Price,String SRC,int exhibitionNum){
		this.ArtNum=ArtNum;
		this.ArtName=ArtName;
		this.Artist=ArtistName;
		this.StartPeriod=StartPeriod;
		this.EndPeriod=EndPeriod;
		this.Time=Time;
		this.Price=Price;
		this.SRC=SRC;
		this.ExhibitionNum=exhibitionNum;
	}
	
	StringBuilder PrintArt() {
		StringBuilder msg=new StringBuilder();
		msg.append("Artist : "+Artist+"\nPeriod : "+StartPeriod+" ~ "+EndPeriod+"\nTime : "+ Time+"\nPrice : "+Price+" ");;
		return msg;
	}

	int getArtNum() {
		return ArtNum;
	}

	String getSrc() {
		return SRC;
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
	String getArtist() {
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
	
	public int getExhibitionNum() {
		return ExhibitionNum;
	}

}

