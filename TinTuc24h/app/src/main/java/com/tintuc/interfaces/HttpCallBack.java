package com.tintuc.interfaces;



import java.io.IOException;

import okhttp3.Request;

public interface HttpCallBack {
    void onSucess(String s);

    void onStart();

    void onFailure(Request request, IOException e);
}
