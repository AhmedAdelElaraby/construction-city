package com.teraninja.constructioncity.model;

public class ImageCompanies {
    public int id;
    public String imageable_type;
    public int imageable_id;
    public String path;
    public String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageable_type() {
        return imageable_type;
    }

    public void setImageable_type(String imageable_type) {
        this.imageable_type = imageable_type;
    }

    public int getImageable_id() {
        return imageable_id;
    }

    public void setImageable_id(int imageable_id) {
        this.imageable_id = imageable_id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
