package com.tintuc.api;

import android.content.Context;

import com.tintuc.api.base.BaseOkHttp;
import com.tintuc.interfaces.HttpCallBack;
import com.tintuc.util.Define;
import com.tintuc.util.LogUtil;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class NewsApi {
    public static void apiEx(Context ctx, HttpCallBack httpCallBack)
    {
        BaseOkHttp baseOkHttp =new BaseOkHttp.Builder()
                .setHttpCallBack(httpCallBack)
                .setContext(ctx)
                .setWantShowDialog(true)
                .setWantDialogCancelabel(true)
                .setTitle(".....")
                .setMessage("Loading ... ")
                .build();





        OkHttpClient okHttpClient=BaseOkHttp.getOkHttpClient();

        Request request = new Request.Builder()
                .url(Define.API_EXAMPLE)
                .build();

        okHttpClient.newCall(request).enqueue(baseOkHttp);

    }

    public static void getListPost(Context ctx,int cateloryId, int limit, int offset,  HttpCallBack httpCallBack)
    {
        BaseOkHttp baseOkHttp =new BaseOkHttp.Builder()
                .setHttpCallBack(httpCallBack)
                .setContext(ctx)
                .setWantShowDialog(true)
                .setWantDialogCancelabel(true)
                .setTitle(".....")
                .setMessage("Loading ... ")
                .build();

        OkHttpClient okHttpClient=BaseOkHttp.getOkHttpClient();
        //?catelory_id=2&limit=3&offset=0

        String url = Define.API_GET_LIST_POST + "?catelory_id="+ cateloryId + "&limit=" + limit + "&offset=" + offset ;

        LogUtil.d("getListPost",url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(baseOkHttp);

    }

    public static void getPostDetail(Context ctx,int postId, HttpCallBack httpCallBack)
    {
        BaseOkHttp baseOkHttp =new BaseOkHttp.Builder()
                .setHttpCallBack(httpCallBack)
                .setContext(ctx)
                .setWantShowDialog(true)
                .setWantDialogCancelabel(true)
                .setTitle(".....")
                .setMessage("Loading ... ")
                .build();

        OkHttpClient okHttpClient=BaseOkHttp.getOkHttpClient();

        //?post_id=10
        String url=Define.API_GET_POST_DETAIL + "?post_id=" + postId;

        Request request = new Request.Builder()
                .url(url)
                .build();


        okHttpClient.newCall(request).enqueue(baseOkHttp);

    }
}
