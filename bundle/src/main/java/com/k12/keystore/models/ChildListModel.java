package com.k12.keystore.models;

/**
 * Created by Saurabh on 4/26/2016.
 */
public class ChildListModel {
     String path;
    String title;

    public ChildListModel(String path, String title) {
        this.path = path;
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
