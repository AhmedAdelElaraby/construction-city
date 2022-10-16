package com.teraninja.constructioncity.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.constructioncity.DataClinte.OnClickIdServise;
import com.teraninja.constructioncity.DataClinte.OnClickOllStreem;
import com.teraninja.constructioncity.R;
import com.teraninja.constructioncity.databinding.ItemByCategoryBinding;
import com.teraninja.constructioncity.databinding.ItemsearchprodactedBinding;
import com.teraninja.constructioncity.model.SearchProdacted;
import com.teraninja.constructioncity.model.ServiseData;

import java.util.ArrayList;


public class AdapterSearchWithProdacted extends RecyclerView.Adapter<AdapterSearchWithProdacted.ViewHolder>{
    ArrayList<SearchProdacted> list = new ArrayList<>();
OnClickOllStreem onClickOllStreem;

    public AdapterSearchWithProdacted(OnClickOllStreem onClickOllStreem) {
        this.onClickOllStreem = onClickOllStreem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemsearchprodactedBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.itemsearchprodacted,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.recyclerBinding.namestreet.setText(list.get(position).getName_ar());
    AdapterProdactedSearshing searshing= new AdapterProdactedSearshing();
    holder.recyclerBinding.recCompany.setLayoutManager(new GridLayoutManager(holder.itemView.getContext(),2));
    holder.recyclerBinding.recCompany.setAdapter(searshing);
    searshing.getList(list.get(position).getProducts());
        holder.recyclerBinding.ShowOll.setOnClickListener(new View.OnClickListener() {
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

    public void getList(ArrayList<SearchProdacted> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemsearchprodactedBinding recyclerBinding;


        public ViewHolder(ItemsearchprodactedBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
