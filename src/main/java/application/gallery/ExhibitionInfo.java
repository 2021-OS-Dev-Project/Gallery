package application.gallery;
import application.Subscribe.ArtInfo;
import application.Subscribe.LocationInfo;

import java.util.ArrayList;

public class ExhibitionInfo {

    private int ExhibitionNum;
    private String ExhibitionName;
    private String siteURL;
    private LocationInfo location;
    private ArrayList<application.Subscribe.ArtInfo> Art;

    ExhibitionInfo(int ExhibitionNum, String ExhibitionName){
        this.ExhibitionNum=ExhibitionNum;
        this.ExhibitionName=ExhibitionName;
    }
    ExhibitionInfo(int ExhibitionNum, String ExhibitionName, String siteURL, LocationInfo loc){
        this.ExhibitionNum=ExhibitionNum;
        this.ExhibitionName=ExhibitionName;
        this.siteURL=siteURL;
        this.location=loc;
        Art=new ArrayList<application.Subscribe.ArtInfo>();
    }

    public String PrintExh() {
        return this.ExhibitionName+" : "+location.getCity();
    }

    public ExhibitionInfo(int parseInt, String name, application.Subscribe.ArtInfo tmp) {
        this.ExhibitionNum=ExhibitionNum;
        this.ExhibitionName=ExhibitionName;
        Art=new ArrayList<application.Subscribe.ArtInfo>();
        // TODO Auto-generated constructor stub
    }
    public int getNum() {
        return ExhibitionNum;
    }
    public String getExhibitionName() {
        return ExhibitionName;
    }

    public ArrayList<application.Subscribe.ArtInfo> getArt() {
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
