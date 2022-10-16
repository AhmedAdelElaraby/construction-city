package com.teraninja.constructioncity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.teraninja.constructioncity.databinding.FragmentLastDetildeOrderBinding;
import com.teraninja.constructioncity.model.DataDetilseOrder;
import com.teraninja.constructioncity.model.DataSendRateOreders;
import com.teraninja.constructioncity.ui.MoveViewModel;


public class lastDetildeOrder extends Fragment {
FragmentLastDetildeOrderBinding binding;
   MoveViewModel model;
    String Token;
    SharedPreferences preferences;
   String idorder;
    public lastDetildeOrder() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_last_detilde_order, container, false);
     model= ViewModelProviders.of(this).get(MoveViewModel.class);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token","no");
        if (getArguments()!=null) {
            lastDetildeOrderArgs args = lastDetildeOrderArgs.fromBundle(getArguments());
            idorder = args.getId();
            DataSendRateOreders oreders = new DataSendRateOreders();
            oreders.setOrder_id(idorder);
            model.DetilseOrder("Bearer" + Token, "application/json", oreders);
        }
        model.dataDetilseOrder.observe(getViewLifecycleOwner(), new Observer<DataDetilseOrder>() {
            @Override
            public void onChanged(DataDetilseOrder dataDetilseOrder) {
                Toast.makeText(getContext(), ""+dataDetilseOrder.getMessage(), Toast.LENGTH_SHORT).show();
                binding.time.setText(""+dataDetilseOrder.getData().getDate_start_order());
                binding.data.setText(""+dataDetilseOrder.getData().getStart_order());
                binding.textstutes.setText(""+dataDetilseOrder.getData().getOrder().getStatus());
                binding.price.setText(""+dataDetilseOrder.getData().getOrder().getTotal());

                Picasso.get().load(dataDetilseOrder.getData().getProducts().get(0)
                        .getProduct().get(0).getImage().get(0).getPath()).into(binding.imageprodact);
                binding.pricepro.setText(""+dataDetilseOrder.getData().getProducts().get(0).getProduct().get(0).getPrice());
                binding.nmaeprodact.setText(""+dataDetilseOrder.getData().getProducts().get(0).getProduct().get(0).getName());
                binding.textlocation.setText(""+dataDetilseOrder.getData().getAddress().getLocation_type());
                binding.textlocationchange.setText(""+dataDetilseOrder.getData().getAddress().getStreet_name()+"\t"+
                        dataDetilseOrder.getData().getAddress().getBuilding_number()+"\t"+dataDetilseOrder.
                        getData().getAddress().getBuilding_type());
            }
        });
      return binding.getRoot();
    }
}