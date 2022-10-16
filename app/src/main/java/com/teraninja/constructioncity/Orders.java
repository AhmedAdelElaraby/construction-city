package com.teraninja.constructioncity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.teraninja.constructioncity.DataClinte.OnClickOrders;
import com.teraninja.constructioncity.databinding.FragmentOrdersBinding;
import com.teraninja.constructioncity.model.DataModelRateOrder;
import com.teraninja.constructioncity.model.DataOrders;
import com.teraninja.constructioncity.model.DataSendRateOreders;
import com.teraninja.constructioncity.ui.MoveViewModel;
import com.teraninja.constructioncity.ui.main.AdapterOrders;

import java.util.ArrayList;


public class Orders extends Fragment implements OnClickOrders {
   FragmentOrdersBinding binding;
MoveViewModel model;
    String Token;
    SharedPreferences preferences;
    public Orders() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_orders, container, false);
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token","no");
        model.Orders("Bearer"+Token,"application/json");

        AdapterOrders orders = new AdapterOrders(this);
       orders.setStuste("current");
        binding.recorders.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.recorders.setAdapter(orders);
        binding.currentOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.currentOrders.setBackground(getResources().getDrawable(R.drawable.style_buttonsignup));
                model.Orders("Bearer"+Token,"application/json");
                binding.previousOrders.setBackground(null);
                orders.setStuste("current");
                binding.previousOrders.setTextColor(getResources().getColor(R.color.textcolor));
                binding.currentOrders.setTextColor(getResources().getColor(R.color.white));
            }
        });
       binding.previousOrders.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               orders.setStuste("last");

               model.LastOrders("Bearer"+Token,"application/json");
               binding.currentOrders.setBackground(null);
               binding.currentOrders.setTextColor(getResources().getColor(R.color.textcolor));
               binding.previousOrders.setBackground(getResources().getDrawable(R.drawable.style_buttonsignup));
               binding.previousOrders.setTextColor(getResources().getColor(R.color.white));

           }
       });

        model.Ordres.observe(getViewLifecycleOwner(), new Observer<DataOrders>() {
            @Override
            public void onChanged(DataOrders dataOrders) {
                orders.getList(dataOrders.getData());

            }
        });
model.lastOrdres.observe(getViewLifecycleOwner(), new Observer<DataOrders>() {
    @Override
    public void onChanged(DataOrders dataOrders) {
        orders.getList(dataOrders.getData());
    }
});


        return binding.getRoot();
    }

    @Override
    public void openDiloge(int id) {
        Diloge(id);
    }

    @Override
    public void Intentcurrent(int id) {
        Toast.makeText(getContext(), "current", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void Intentlast(int id) {
    OrdersDirections.ActionOrdersToLastDetildeOrder detildeOrder=OrdersDirections.actionOrdersToLastDetildeOrder();
    detildeOrder.setId(String.valueOf(id));
        Navigation.findNavController(binding.getRoot()).navigate(detildeOrder);
    }
    public void Diloge(int id){
        Dialog myDialog;
        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.item_diloge_rot_orders);
        RatingBar simpleRatingBar = myDialog.findViewById(R.id.ratingBar);
        TextView Save=myDialog.findViewById(R.id.save);
        int numberstars = simpleRatingBar.getNumStars();
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSendRateOreders rateOreders = new DataSendRateOreders();
                rateOreders.setRate(String.valueOf(numberstars));
                rateOreders.setOrder_id(String.valueOf(id));
                model.RateOrders("Bearer"+Token,"application/json",rateOreders);
                model.rateorder.observe(getViewLifecycleOwner(), new Observer<DataModelRateOrder>() {
                    @Override
                    public void onChanged(DataModelRateOrder dataModelRateOrder) {
                        if (dataModelRateOrder.getStatus()== 1){
                            Toast.makeText(getContext(), ""+dataModelRateOrder.getMessage(), Toast.LENGTH_SHORT).show();
                            myDialog.dismiss();
                        }
                        else {
                            Toast.makeText(getContext(), ""+dataModelRateOrder.getMessage(), Toast.LENGTH_SHORT).show();

                            myDialog.dismiss();
                        }
                    }
                });

            }
        });

        Window window = myDialog.getWindow();

        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.create();
        myDialog.show();
    }
}