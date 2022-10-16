package com.teraninja.constructioncity.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.constructioncity.DataClinte.OnClickIdServise;
import com.teraninja.constructioncity.DataClinte.OnClickLocation;
import com.teraninja.constructioncity.R;
import com.teraninja.constructioncity.databinding.ItemAllLocationBinding;
import com.teraninja.constructioncity.databinding.ItemByCategoryBinding;
import com.teraninja.constructioncity.model.LocationModel;
import com.teraninja.constructioncity.model.ServiseData;

import java.util.ArrayList;


public class AdapterAllLocation extends RecyclerView.Adapter<AdapterAllLocation.ViewHolder>{
    ArrayList<LocationModel> list = new ArrayList<>();
String caseshow;
OnClickLocation onClickLocation;


    public AdapterAllLocation(String caseshow, OnClickLocation onClickLocation) {
        this.caseshow = caseshow;
        this.onClickLocation = onClickLocation;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemAllLocationBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_all_location,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.recyclerBinding.caseorders.setText(""+list.get(position).getLocation_type());
    holder.recyclerBinding.addrass.setText(""+list.get(position).getCity()+" - "+list.get(position)
            .getStreet_name()+" : "+list.get(position).getLocation_details());
        switch (caseshow){
            case"all":
                holder.recyclerBinding.chenge.setEnabled(true);
                holder.itemView.setEnabled(false);
                break;
            case "diloge":
                holder.recyclerBinding.chenge.setText("");
                holder.recyclerBinding.chenge.setEnabled(false);

                break;
        }
        holder.recyclerBinding.chenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLocation.getIdLocation(list.get(position).getId());
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLocation.getIdLocation(list.get(position).getId());

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<LocationModel> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemAllLocationBinding recyclerBinding;


        public ViewHolder(ItemAllLocationBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
