package com.teraninja.constructioncity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.teraninja.constructioncity.databinding.FragmentHomeBinding;


public class Home extends Fragment {
FragmentHomeBinding binding;
String image;
    SharedPreferences preferences;
    String casehome;
    public Home() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        image = preferences.getString("image","no");
        casehome = preferences.getString("case","no");
        if (casehome.equals("skip")){

        }else {
            Picasso.get().load(image).into(binding.imageProfile);
        }

       binding.imageProfile.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Navigation.findNavController(binding.getRoot()).navigate(R.id.action_home2_to_seting);
           }
       });
       binding.l2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Navigation.findNavController(binding.getRoot()).navigate(R.id.action_home2_to_insideCountry);
           }
       });
       binding.order.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (casehome.equals("skip")){
                   Toast.makeText(getContext(),""+getString(R.string.caselogin), Toast.LENGTH_SHORT).show();
               }else {
                   Navigation.findNavController(binding.getRoot()).navigate(R.id.action_home2_to_allCard);
               }
           }
       });
    binding.l3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_home2_to_searchWithProdacted);
        }
    });


    binding.l4.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (casehome.equals("skip")){
                Toast.makeText(getContext(),""+getString(R.string.caselogin), Toast.LENGTH_SHORT).show();
            }else {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_home2_to_allPremoCode);

            }
        }
    });
       return  binding.getRoot();

    }
}