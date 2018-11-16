package com.tintuc.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tintuc.entity.PostEntity;
import com.tintuc.interfaces.AdapterListener;
import com.tintuc.tintuc24h.R;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter {
    static final int TYPE_ITEM_NORMAL = 0;
    static final int TYPE_ITEM_LOAD_MORE = 1 ;

    private List<PostEntity> postEntities;
    private AdapterListener listener;

    public PostAdapter(List<PostEntity> postEntities,AdapterListener listener)
    {
        this.postEntities=postEntities;
        this.listener=listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        if(viewType == TYPE_ITEM_NORMAL)
        {
            View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_post,null);
            PostViewHolder postViewHolder =new PostViewHolder(v);
            return postViewHolder;
        }

            View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_load_more,null);
            LoadMoreViewHolder loadMoreViewHolder=new LoadMoreViewHolder(v);
            return loadMoreViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        if(holder instanceof PostViewHolder)
        {
            final PostEntity postEntity =postEntities.get(position);
            final PostViewHolder postViewHolder=(PostViewHolder) holder;
            postViewHolder.tvTitle.setText(postEntity.getTitle());
            postViewHolder.tvDesc.setText(postEntity.getDesc());

            Glide.with(postViewHolder.imgThumb.getContext()).load(postEntity.getThumb()).into(postViewHolder.imgThumb);

            postViewHolder.rlPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                    {
                        listener.onItemClickListener(postEntity,position,postViewHolder);
                    }
                }
            });
        }
        else
        {
            final LoadMoreViewHolder loadMoreViewHolder=(LoadMoreViewHolder) holder;
            loadMoreViewHolder.btnLoadMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                    {
                        listener.onItemClickListener(null,position,loadMoreViewHolder);
                    }
                }
            });

        }

    }

    private class PostViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout rlPost;
        ImageView imgThumb;
        TextView tvTitle;
        TextView tvDesc;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            imgThumb =(ImageView) itemView.findViewById(R.id.img_thumb);
            tvTitle=(TextView) itemView.findViewById(R.id.tv_title);
            tvDesc=(TextView) itemView.findViewById(R.id.tv_desc);
            rlPost=(RelativeLayout) itemView.findViewById(R.id.rl_post);
        }
    }

    private class LoadMoreViewHolder extends RecyclerView.ViewHolder
    {
        Button btnLoadMore;

        public LoadMoreViewHolder(@NonNull View itemView) {
            super(itemView);
            btnLoadMore=(Button) itemView.findViewById(R.id.btn_load_more);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position < postEntities.size())
        {
            return TYPE_ITEM_NORMAL;
        }
        return TYPE_ITEM_LOAD_MORE;
    }

    @Override
    public int getItemCount() {
        return postEntities.size()+1;
    }
}
