package com.teraninja.constructioncity.model;

import okhttp3.MultipartBody;

public class ModelSendImage {
    MultipartBody.Part image;

    public MultipartBody.Part getImage() {
        return image;
    }

    public void setImage(MultipartBody.Part image) {
        this.image = image;
    }
}
