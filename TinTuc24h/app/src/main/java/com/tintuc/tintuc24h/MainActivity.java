package com.tintuc.tintuc24h;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tintuc.adapter.MenuAdapter;
import com.tintuc.adapter.PostAdapter;
import com.tintuc.api.NewsApi;
import com.tintuc.entity.MenuEntity;
import com.tintuc.entity.PostEntity;
import com.tintuc.interfaces.AdapterListener;
import com.tintuc.interfaces.HttpCallBack;
import com.tintuc.util.LogUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

    private ImageView imgMenu;
    private RecyclerView rvMenu;
    private RecyclerView rvPost;


    private MenuAdapter menuAdapter;

    private List<MenuEntity> menuEntities=new ArrayList<>();

    private Context context=this;

    private List<PostEntity> postEntities=new ArrayList<>();

    private PostAdapter postAdapter;

    private int cateloryId=1;
    private TextView tvTitle;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MenuEntity menuThoiSu = new MenuEntity();
        menuThoiSu.setId(1);
        menuThoiSu.setTitle("Thời sự");
        menuThoiSu.setSelected(true);

        MenuEntity menuTheThao = new MenuEntity();
        menuTheThao.setId(2);
        menuTheThao.setTitle("Thể thao");

        MenuEntity menuKinhTe = new MenuEntity();
        menuKinhTe.setId(3);
        menuKinhTe.setTitle("Kinh tế");

        MenuEntity menuChinhTri = new MenuEntity();
        menuChinhTri.setId(4);
        menuChinhTri.setTitle("Chính trị");

        menuEntities.add(menuThoiSu);
        menuEntities.add(menuTheThao);
        menuEntities.add(menuKinhTe);
        menuEntities.add(menuChinhTri);

        imgMenu = (ImageView) findViewById(R.id.img_menu);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(menuThoiSu.getTitle());

        swipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                postEntities.clear();
                postAdapter.notifyDataSetChanged();
                getListPost();
            }
        });



        final SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen._20sdp);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen._150sdp);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.layout_menu);

        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.toggle();
            }
        });

        menuAdapter = new MenuAdapter(menuEntities, new AdapterListener() {
            @Override
            public void onItemClickListener(Object o, int pos, RecyclerView.ViewHolder holder) {


                //reset
                for (int i = 0; i < menuEntities.size(); ++i) {
                    MenuEntity entity = menuEntities.get(i);
                    entity.setSelected(false);
                }

                MenuEntity menuEntity = (MenuEntity) o;
                cateloryId = menuEntity.getId();

                tvTitle.setText(menuEntity.getTitle());
                menuEntity.setSelected(true);
                menuAdapter.notifyDataSetChanged();


                menu.toggle();

                postEntities.clear();
                postAdapter.notifyDataSetChanged();
                getListPost();

            }
        });

        rvMenu = (RecyclerView) menu.findViewById(R.id.rv_menu);

        rvMenu.setLayoutManager(new LinearLayoutManager(this));
        rvMenu.setItemAnimator(new DefaultItemAnimator());
        rvMenu.setAdapter(menuAdapter);


        rvPost = (RecyclerView) findViewById(R.id.rv_post);
        rvPost.setLayoutManager(new LinearLayoutManager(this));
        rvPost.setItemAnimator(new DefaultItemAnimator());

        postAdapter = new PostAdapter(postEntities, new AdapterListener() {
            @Override
            public void onItemClickListener(Object o, int pos, RecyclerView.ViewHolder holder) {
                if(pos < postEntities.size())
                {
                    //click len bai post
                    PostEntity postEntity=(PostEntity) o;
                    Intent intent = new Intent(context,DetailActivity.class);
                    intent.putExtra("post",postEntity);
                    startActivity(intent);
                }
                else
                {
                    //click len nut loadmore
                    getListPost();
                }
            }
        });

        rvPost.setAdapter(postAdapter);

        getListPost();
    }

    private void getListPost()
    {
        NewsApi.getListPost(context, cateloryId, 3, postEntities.size(), new HttpCallBack() {
            @Override
            public void onSucess(final String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        try {
                            JSONArray jsonArray = new JSONArray(s);
                            for(int i=0;i<jsonArray.length();++i)
                            {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                PostEntity postEntity=new PostEntity(jsonObject);
                                postEntities.add(postEntity);
                                LogUtil.d("postEntity",postEntity.toString());
                            }
                            postAdapter.notifyDataSetChanged();
                        } catch (Exception e) {
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
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(context,"Kết nối mạng có vấn đề! Yêu cầu kiểm tra lại! ",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
    }


