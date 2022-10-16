package com.teraninja.constructioncity.ui.main;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.constructioncity.DataClinte.OnClickIdServise;
import com.teraninja.constructioncity.R;
import com.teraninja.constructioncity.databinding.ItemByCategoryBinding;
import com.teraninja.constructioncity.databinding.ItemByCompanyBinding;
import com.teraninja.constructioncity.model.ServiseData;

import java.util.ArrayList;


public class AdapterByCategory extends RecyclerView.Adapter<AdapterByCategory.ViewHolder>{
    ArrayList<ServiseData> list = new ArrayList<>();
OnClickIdServise servise;

    public AdapterByCategory(OnClickIdServise servise) {
        this.servise = servise;
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
                servise.getId(list.get(position).getPivot().getCompany_id(),
                        list.get(position).getPivot().getService_id());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<ServiseData> list) {
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
