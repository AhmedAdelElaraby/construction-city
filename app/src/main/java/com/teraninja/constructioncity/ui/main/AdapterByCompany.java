package com.teraninja.constructioncity.ui.main;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.teraninja.constructioncity.DataClinte.OnClickCompaniesOllStreet;
import com.teraninja.constructioncity.R;
import com.teraninja.constructioncity.databinding.ItemByCompanyBinding;
import com.teraninja.constructioncity.databinding.ItemRecyclerInsideCountryBinding;
import com.teraninja.constructioncity.model.DataCompanies;
import com.teraninja.constructioncity.model.ImageCompanies;

import java.util.ArrayList;


public class AdapterByCompany extends RecyclerView.Adapter<AdapterByCompany.ViewHolder>{
    ArrayList<DataCompanies> list = new ArrayList<>();
    OnClickCompaniesOllStreet onClickCompaniesOllStreet;

    public AdapterByCompany(OnClickCompaniesOllStreet onClickCompaniesOllStreet) {
        this.onClickCompaniesOllStreet = onClickCompaniesOllStreet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemByCompanyBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_by_company,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(list.get(position).getImages().get(0).getPath()).fit().into(holder.recyclerBinding.imageitem);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCompaniesOllStreet.getId(list.get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<DataCompanies> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemByCompanyBinding recyclerBinding;


        public ViewHolder(ItemByCompanyBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
