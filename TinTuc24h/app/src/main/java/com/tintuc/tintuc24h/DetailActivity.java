    package com.tintuc.tintuc24h;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tintuc.api.NewsApi;
import com.tintuc.entity.PostEntity;
import com.tintuc.interfaces.HttpCallBack;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Request;

    public class DetailActivity extends AppCompatActivity {

    private ImageView imgBack;
    private WebView webView;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //Tao bien hung du lieu tu intent
        Bundle bundle=getIntent().getExtras();
        final PostEntity postEntity= (PostEntity) bundle.getSerializable("post");


        imgBack=(ImageView) findViewById(R.id.img_back);
        webView=(WebView)findViewById(R.id.wb_content);
        tvTitle=(TextView)findViewById(R.id.tv_title);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvTitle.setText(postEntity.getTitle());

        NewsApi.getPostDetail(this, postEntity.getId(), new HttpCallBack() {
            @Override
            public void onSucess(final String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            JSONObject jsonObject=new JSONObject(s);
                            PostEntity postEntity1=new PostEntity(jsonObject);
                            String data="<html><head><title></title><style>*{max-width: 100%;}</style></head><body>"+postEntity1.getContent()+"</body></html>";
                            webView.loadData(data,"text/html; charset=utf-8","utf-8");
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                    }
                });
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFailure(Request request, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DetailActivity.this,"Kết nối mạng có vấn đề! Yêu cầu kiểm tra lại! ",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}
