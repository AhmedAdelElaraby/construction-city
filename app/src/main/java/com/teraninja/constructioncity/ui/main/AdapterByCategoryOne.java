package com.teraninja.constructioncity.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.constructioncity.DataClinte.OnClickId;
import com.teraninja.constructioncity.R;
import com.teraninja.constructioncity.databinding.ItemByCategoryBinding;
import com.teraninja.constructioncity.model.Brands;

import java.util.ArrayList;


public class AdapterByCategoryOne extends RecyclerView.Adapter<AdapterByCategoryOne.ViewHolder>{
    ArrayList<Brands> list = new ArrayList<>();
    OnClickId onClickId;

    public AdapterByCategoryOne(OnClickId onClickId) {
        this.onClickId = onClickId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemByCategoryBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_by_category,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerBinding.text.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickId.getId(list.get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<Brands> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemByCategoryBinding recyclerBinding;


        public ViewHolder(ItemByCategoryBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
