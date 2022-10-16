package com.teraninja.constructioncity.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.teraninja.constructioncity.DataClinte.OnClickIdProdated;
import com.teraninja.constructioncity.R;
import com.teraninja.constructioncity.databinding.ItemByCategoryBinding;
import com.teraninja.constructioncity.databinding.ItemByCategoryTwoBinding;
import com.teraninja.constructioncity.model.Prodacted;

import java.util.ArrayList;


public class AdapterByCategoryTwo extends RecyclerView.Adapter<AdapterByCategoryTwo.ViewHolder>{
    ArrayList<Prodacted> list = new ArrayList<>();
    OnClickIdProdated idProdated;

    public AdapterByCategoryTwo(OnClickIdProdated idProdated) {
        this.idProdated = idProdated;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemByCategoryTwoBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_by_category_two,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.recyclerBinding.nameProdected.setText(list.get(position).getName());
        holder.recyclerBinding.pric.setText(" "+list.get(position).getPrice());
        Picasso.get().load(list.get(position).getImage().get(0).getPath()).fit().into(holder.recyclerBinding.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idProdated.getIdProdacted(list.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<Prodacted> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemByCategoryTwoBinding recyclerBinding;


        public ViewHolder(ItemByCategoryTwoBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
