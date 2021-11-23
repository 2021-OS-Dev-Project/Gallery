package application.model;

import application.gallery.MyListener;

public class Exhibitions {
    private String cover;
    private String name;
    private String explanation;
    private MyListener myListener;

    public String getCover() {
        return cover;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void getCover(String img) {
        this.cover = img;
    }
}
