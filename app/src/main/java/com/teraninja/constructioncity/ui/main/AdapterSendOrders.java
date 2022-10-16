package com.teraninja.constructioncity.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.teraninja.constructioncity.DataClinte.OnClickgetCounter;
import com.teraninja.constructioncity.R;
import com.teraninja.constructioncity.databinding.ItemAllcompanyIncardBinding;
import com.teraninja.constructioncity.databinding.ItemSendOrdersBinding;
import com.teraninja.constructioncity.model.ModelAllCard;
import com.teraninja.constructioncity.model.ProductModelInCard;

import java.util.ArrayList;


public class AdapterSendOrders extends RecyclerView.Adapter<AdapterSendOrders.ViewHolder>{
    ArrayList<ProductModelInCard> list = new ArrayList<>();
int cuonter;

OnClickgetCounter counter;

    public AdapterSendOrders(OnClickgetCounter counter) {
        this.counter = counter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemSendOrdersBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_send_orders,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        cuonter=list.get(position).getPivot().getQty();

        holder.recyclerBinding.name.setText(list.get(position).getName());
        holder.recyclerBinding.price.setText(""+list.get(position).getPrice());
        Picasso.get().load(list.get(position).getImages().get(0).getPath()).fit().into(holder.recyclerBinding.image);
        holder.recyclerBinding.cuont.setText(""+list.get(position).getPivot().getQty());
        holder.recyclerBinding.plase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



//                holder.recyclerBinding.cuont.setText(""+cuonter);
            counter.setPlase(list.get(position).getId());

            }
        });
        holder.recyclerBinding.mines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

          //  holder.recyclerBinding.cuont.setText(""+cuonter);

                counter.setmines(list.get(position).getId());

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<ProductModelInCard> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemSendOrdersBinding recyclerBinding;


        public ViewHolder(ItemSendOrdersBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
