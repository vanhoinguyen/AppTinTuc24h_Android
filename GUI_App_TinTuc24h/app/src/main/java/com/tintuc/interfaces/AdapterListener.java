package com.tintuc.interfaces;

import android.support.v7.widget.RecyclerView;
// Dung de Luu tat ca Cac EVENTS cua Adapter
public interface AdapterListener {
    public void onItemClickListener(Object o, int pos, RecyclerView.ViewHolder holder);
}
