package com.tintuc.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tintuc.entity.MenuEntity;
import com.tintuc.interfaces.AdapterListener;
import com.tintuc.tintuc24h.R;

import java.security.PublicKey;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter {
    private AdapterListener listener;
    private List<MenuEntity> menuEntities;

    public MenuAdapter(List<MenuEntity> menuEntityList, AdapterListener listener)
    {
        this.listener=listener;
        this.menuEntities=menuEntityList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View v = layoutInflater.inflate(R.layout.item_menu,null);
        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        final MenuViewHolder menuViewHolder =(MenuViewHolder) holder;
        final MenuEntity menuEntity = menuEntities.get(position);

        String title=menuEntity.getTitle();
        menuViewHolder.tvMenu.setText(title);

        if(menuEntity.isSelected())
        {
            menuViewHolder.rlItemMenu.setBackgroundResource(R.color.colorPrimary);
        }
        else
        {
            menuViewHolder.rlItemMenu.setBackgroundResource(android.R.color.white);
        }

        menuViewHolder.rlItemMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null)
                {
                    listener.onItemClickListener(menuEntity,position,menuViewHolder);
                }
            }
        });
    }

    private class MenuViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout rlItemMenu;
        TextView tvMenu;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            rlItemMenu=(RelativeLayout) itemView.findViewById(R.id.rl_item_menu);
            tvMenu=(TextView) itemView.findViewById(R.id.tv_menu);
        }
    }
    @Override
    public int getItemCount() {
        return menuEntities.size();
    }
}
