package com.example.xplorer.the_one.homeUpload;

public class post {
    private String postid;
    private String postImage;
    private String description;// post title
    private String publisher; // post title

    public post(String postid, String postImage, String description, String publisher) {
        this.postid = postid;
        this.postImage = postImage;
        this.description = description;
        this.publisher = publisher;
    }

    public post() {
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
