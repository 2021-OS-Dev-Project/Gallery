package application;

public class ArtInfo {
	private int ArtNum;
	private String ArtName;
	private String Artist;
	private String StartPeriod;
	private String EndPeriod;
	private String Time;
	private String Price;
	private String ImageSRC;
	private String Explanation;
	private int ExhibitionNum;

	ArtInfo(int ArtNum,String ArtName,String ArtistName,String StartPeriod,String EndPeriod,String Time,String Price,int exhibitionNum){
		this.ArtNum=ArtNum;
		this.ArtName=ArtName;
		this.Artist=ArtistName;
		this.StartPeriod=StartPeriod;
		this.EndPeriod=EndPeriod;
		this.Time=Time;
		this.Price=Price;
		this.ExhibitionNum=exhibitionNum;
	}
	
	String PrintArt() {
		
		for(ExhibitionInfo tmp:Object.exhibition)
			if(tmp.getNum()==ExhibitionNum)
				return "작품 : "+ArtName+" "+Artist+" "+StartPeriod+" ~ "+EndPeriod+" "+ Time+" "+Price+" "+ tmp.PrintExh();
		return "작품 : "+ArtName+" "+Artist+" "+StartPeriod+" ~ "+EndPeriod+" "+ Time+" "+Price;
	}

	int getArtNum() {
		return ArtNum;
	}
	void setArtName(String Art) {
		this.ArtName=ArtName;
	}
	void setArtist(String Artist) {
		this.Artist=Artist;
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

