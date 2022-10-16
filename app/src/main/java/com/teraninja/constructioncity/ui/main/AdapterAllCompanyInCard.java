package com.teraninja.constructioncity.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.teraninja.constructioncity.DataClinte.OnClickIdServise;
import com.teraninja.constructioncity.DataClinte.OnClickShowInCard;
import com.teraninja.constructioncity.R;
import com.teraninja.constructioncity.databinding.ItemAllcompanyIncardBinding;
import com.teraninja.constructioncity.databinding.ItemByCategoryBinding;
import com.teraninja.constructioncity.model.ModelAllCard;
import com.teraninja.constructioncity.model.ServiseData;

import java.util.ArrayList;


public class AdapterAllCompanyInCard extends RecyclerView.Adapter<AdapterAllCompanyInCard.ViewHolder>{
    ArrayList<ModelAllCard> list = new ArrayList<>();
    OnClickShowInCard onClickShowInCard;

    public AdapterAllCompanyInCard(OnClickShowInCard onClickShowInCard) {
        this.onClickShowInCard = onClickShowInCard;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemAllcompanyIncardBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_allcompany_incard,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerBinding.nameCompany.setText(list.get(position).getName());
        Picasso.get().load(list.get(position).getImages().get(0).getPath()).fit().into(holder.recyclerBinding.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickShowInCard.getId(list.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<ModelAllCard> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemAllcompanyIncardBinding recyclerBinding;


        public ViewHolder(ItemAllcompanyIncardBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
