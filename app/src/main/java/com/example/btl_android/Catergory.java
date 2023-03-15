package com.example.btl_android;

public class Catergory {

    private int id;
    private String CatergoryName;
    private String imgBackground;
    private String imgIcon;

    public Catergory(int id, String catergoryName, String imgBackground, String imgIcon) {
        this.id = id;
        this.CatergoryName = catergoryName;
        this.imgBackground = imgBackground;
        this.imgIcon = imgIcon;
    }

    public int getId() {
        return id;
    }

    public String getCatergoryName() {
        return CatergoryName;
    }

    public String getImgBackground() {
        return imgBackground;
    }

    public String getImgIcon() {
        return imgIcon;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCatergoryName(String catergoryName) {
        CatergoryName = catergoryName;
    }

    public void setImgBackground(String imgBackground) {
        this.imgBackground = imgBackground;
    }

    public void setImgIcon(String imgIcon) {
        this.imgIcon = imgIcon;
    }

}
