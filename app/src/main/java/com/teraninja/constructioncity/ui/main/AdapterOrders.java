package com.teraninja.constructioncity.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.constructioncity.DataClinte.OnClickOrders;
import com.teraninja.constructioncity.R;
import com.teraninja.constructioncity.databinding.ItemByCategoryBinding;
import com.teraninja.constructioncity.databinding.ItemordersBinding;
import com.teraninja.constructioncity.model.OrdersModel;

import java.util.ArrayList;


public class AdapterOrders extends RecyclerView.Adapter<AdapterOrders.ViewHolder>{
    ArrayList<OrdersModel> list = new ArrayList<>();
    OnClickOrders orders;
String stusteonclic;

    public void setStuste(String stuste) {
        this.stusteonclic = stuste;
    }

    public AdapterOrders(OnClickOrders orders) {
        this.orders = orders;
    }

    public ArrayList<OrdersModel> getList() {
        return list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemordersBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.itemorders,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerBinding.view.setVisibility(View.GONE);
        holder.recyclerBinding.butoon.setVisibility(View.GONE);
        holder.recyclerBinding.DayandData.setText(list.get(position).getCreated_at());
        holder.recyclerBinding.caseorders.setText(""+list.get(position).getStatus());
//        holder.recyclerBinding.ordertime.setText(""+list.get(position));
        holder.recyclerBinding.priceorder.setText(""+list.get(position).getTotal());
        String stutse=list.get(position).getStatus();
        if (stusteonclic.equals("last")){
            switch (stutse) {
                case "done":
                holder.recyclerBinding.view.setVisibility(View.VISIBLE);
                holder.recyclerBinding.butoon.setVisibility(View.VISIBLE);
                break;
                case"تم الاستلام":
                holder.recyclerBinding.view.setVisibility(View.VISIBLE);
                holder.recyclerBinding.butoon.setVisibility(View.VISIBLE);
                break;
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (stusteonclic.equals("last")){
                orders.Intentlast(list.get(position).getId());
            }else {
                orders.Intentcurrent(list.get(position).getId());
            }
            }
        });
        holder.recyclerBinding.butoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orders.openDiloge(list.get(position).getId());
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<OrdersModel> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemordersBinding recyclerBinding;


        public ViewHolder(ItemordersBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
