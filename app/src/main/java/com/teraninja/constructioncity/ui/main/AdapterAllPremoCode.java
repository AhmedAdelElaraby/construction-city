package com.teraninja.constructioncity.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.teraninja.constructioncity.R;
import com.teraninja.constructioncity.databinding.ItemAllPremoCodeBinding;
import com.teraninja.constructioncity.databinding.ItemAllcompanyIncardBinding;
import com.teraninja.constructioncity.model.AllPremocod;
import com.teraninja.constructioncity.model.ModelAllCard;

import java.util.ArrayList;


public class AdapterAllPremoCode extends RecyclerView.Adapter<AdapterAllPremoCode.ViewHolder>{
    ArrayList<AllPremocod> list = new ArrayList<>();




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemAllPremoCodeBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_all_premo_code,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.recyclerBinding.text.setText(""+list.get(position).getCode()
    +""+list.get(position).getDiscount()+""+list.get(position).getCompany().get(0).getName());
    Picasso.get().load(list.get(position).getCompany().get(0).getImages().get(0).getPath()).into(holder.recyclerBinding.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<AllPremocod> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemAllPremoCodeBinding recyclerBinding;


        public ViewHolder( ItemAllPremoCodeBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
