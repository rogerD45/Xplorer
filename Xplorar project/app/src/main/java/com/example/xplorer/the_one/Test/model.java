package com.example.xplorer.the_one.Test;

public class model
{

    String imageName;
//    String imageLatitude;
//    String imagelongitude;
    String imageURL;

    public model() {
    }

    public model(String imageName, String imageURL) {
        this.imageName = imageName;
//        this.imageLatitude = imageLatitude; //, String imageLatitude, String imagelongitude
//        this.imagelongitude = imagelongitude;
        this.imageURL = imageURL;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
