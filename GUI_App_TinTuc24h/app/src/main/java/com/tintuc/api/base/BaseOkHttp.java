package com.tintuc.api.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;

import com.tintuc.interfaces.HttpCallBack;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public abstract class BaseOkHttp implements Callback {
    private static OkHttpClient okHttpClient=null;

    public static OkHttpClient getOkHttpClient(){
        if(okHttpClient == null)
        {
            okHttpClient =new OkHttpClient
                    .Builder()
                    .readTimeout(30,TimeUnit.SECONDS)
                    .writeTimeout(30,TimeUnit.SECONDS)
                    .connectTimeout(30,TimeUnit.SECONDS)
                    .addInterceptor((Interceptor) new LoggingInterceptor())
                    .build();

        }
        return okHttpClient;
    }

    private boolean wantDialogCancelabel =true;
    private boolean wantShowDialog = true;
    private Context context;
    private ProgressDialog progress;
    private String title ="";
    private String message="Message...";
    private HttpCallBack httpCallBack;

    protected BaseOkHttp()
    {

    }

    public void start()
    {
        if(this.context != null)
        {
            progress = new ProgressDialog(this.context);
            progress.setCancelable(wantDialogCancelabel);
            progress.setMessage(message);

            if(null != title && TextUtils.isEmpty(title))
            {
                progress.setTitle(title);
            }
        }

        if(progress !=null && wantShowDialog)
        {
            progress.show();
        }

        if(httpCallBack != null)
        {
            httpCallBack.onStart();
        }
    }

    @Override
    public void onFailure(Request request, IOException e) {
        dissmisDialog();
        if(httpCallBack != null)
        {
            httpCallBack.onFailure(request,e);
        }
    }

    @Override
    public void onResponse(Response response) throws IOException {
        dissmisDialog();

        if(response.isSuccessful())
        {
            if(httpCallBack != null)
            {
                String s=response.body().string();
                httpCallBack.onSucess(s);
            }
        }
        else {
            onFailure(null,null);
        }
    }

    private void dissmisDialog()
    {
        if(progress != null && progress.isShowing())
        {
            try {
                if(context instanceof Activity)
                {
                    if(((Activity) context).isFinishing()){
                        return;
                    }
                }
                progress.dismiss();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }


    public boolean isWantDialogCancelabel() {
        return wantDialogCancelabel;
    }

    public void setWantDialogCancelabel(boolean wantDialogCancelabel) {
        this.wantDialogCancelabel = wantDialogCancelabel;
    }

    public boolean isWantShowDialog() {
        return wantShowDialog;
    }

    public void setHttpCallBack(HttpCallBack httpCallBack) {
        this.httpCallBack = httpCallBack;
    }

    public void setWantShowDialog(boolean wantShowDialog) {
        this.wantShowDialog = wantShowDialog;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ProgressDialog getProgress() {
        return progress;
    }

    public void setProgress(ProgressDialog progress) {
        this.progress = progress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class Builder {
        private BaseOkHttp baseOkHttp =new BaseOkHttp() {};

        public Builder setWantShowDialog (boolean wantShowDialog)
        {
            baseOkHttp.wantShowDialog =wantShowDialog;
            return this;
        }

        public Builder setWantDialogCancelabel (boolean wantDialogCancelabel)
        {
            baseOkHttp.wantDialogCancelabel =wantDialogCancelabel;
            return this;
        }
        public Builder setHttpCallBack(HttpCallBack httpCallBack)
        {
            this.baseOkHttp.httpCallBack=httpCallBack;
            return this;
        }

        public Builder setContext(Context ctx)
        {
            baseOkHttp.context=ctx;
            return Builder.this;
        }

        public Builder setTitle(String title)
        {
            baseOkHttp.title=title;
            return Builder.this;
        }

        public Builder setMessage(String message)
        {
            baseOkHttp.message=message;
            return Builder.this;
        }

        public BaseOkHttp build()
        {
            baseOkHttp.start();
            return baseOkHttp;
        }


    }
}
