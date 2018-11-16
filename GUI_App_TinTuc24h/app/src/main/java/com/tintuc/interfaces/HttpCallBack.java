package com.tintuc.interfaces;



import java.io.IOException;

import okhttp3.Request;
// Luu Events cua HttpCallBack
public interface HttpCallBack {
    void onSucess(String s);

    void onStart();

    void onFailure(Request request, IOException e);
}
