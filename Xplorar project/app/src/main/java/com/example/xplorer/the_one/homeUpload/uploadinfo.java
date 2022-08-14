package com.example.xplorer.the_one.homeUpload;

public class uploadinfo {

        String imageName;
        String imageLatitude;
        String imageLongitude;
        String imageURL;


//    uploadinfo() {
//
//    }
//
//    public uploadinfo(String imageName, String imageLatitude, String imageLongitude, String imageURL,String imageid) {
//        this.imageName = imageName;
//        this.imageLatitude = imageLatitude;
//        this.imageLongitude = imageLongitude;
//        this.imageURL = imageURL;
//        this.imageid = imageid;
//    }
//
//
//    public String getImageName() {
//        return imageName;
//    }
//
//    public String getImageLatitude() {
//        return imageLatitude;
//    }
//
//    public String getImageLongitude() {
//        return imageLongitude;
//    }
//
//    public String getImageURL() {
//        return imageURL;
//    }
//
//    public void setImageName(String imageName) {
//        this.imageName = imageName;
//    }
//
//    public void setImageLatitude(String imageLatitude) {
//        this.imageLatitude = imageLatitude;
//    }
//
//    public void setImageLongitude(String imageLongitude) {
//        this.imageLongitude = imageLongitude;
//    }
//
//    public void setImageURL(String imageURL) {
//        this.imageURL = imageURL;
//    }
//
//    public String getImageid() {
//        return imageid;
//    }
//
//    public void setImageid(String imageid) {
//        this.imageid = imageid;
//    }

    uploadinfo() {
    }

    public uploadinfo(String imageName, String imageLatitude, String imageLongitude, String imageURL) {
        this.imageName = imageName;
        this.imageLatitude = imageLatitude;
        this.imageLongitude = imageLongitude;
        this.imageURL = imageURL;

    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageLatitude() {
        return imageLatitude;
    }

    public void setImageLatitude(String imageLatitude) {
        this.imageLatitude = imageLatitude;
    }

    public String getImageLongitude() {
        return imageLongitude;
    }

    public void setImageLongitude(String imageLongitude) {
        this.imageLongitude = imageLongitude;
    }
//
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }


}
