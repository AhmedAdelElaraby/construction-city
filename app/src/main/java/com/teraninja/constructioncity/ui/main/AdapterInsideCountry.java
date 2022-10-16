package com.teraninja.constructioncity.ui.main;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.constructioncity.DataClinte.OnClickOllStreem;
import com.teraninja.constructioncity.R;
import com.teraninja.constructioncity.databinding.ItemRecyclerInsideCountryBinding;
import com.teraninja.constructioncity.model.OllStreet;


import java.util.ArrayList;


public class AdapterInsideCountry extends RecyclerView.Adapter<AdapterInsideCountry.ViewHolder>{
    ArrayList<OllStreet> list = new ArrayList<>();
    OnClickOllStreem onClickOllStreem;

    public AdapterInsideCountry(OnClickOllStreem onClickOllStreem) {
        this.onClickOllStreem = onClickOllStreem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==0){

        }else {

        }
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemRecyclerInsideCountryBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_recycler_inside_country,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerBinding.nameAddrass.setText(list.get(position).getName());
        holder.recyclerBinding.l1.setBackgroundColor(Color.parseColor(list.get(position).getColor()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickOllStreem.getIdStreem(list.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position==2){
            return 0;

        }else {
            return 1;

        }
    }

    public void getList(ArrayList<OllStreet> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemRecyclerInsideCountryBinding recyclerBinding;


        public ViewHolder(ItemRecyclerInsideCountryBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
