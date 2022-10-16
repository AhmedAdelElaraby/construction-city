package com.teraninja.constructioncity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teraninja.constructioncity.databinding.FragmentLocationBinding;


public class Location extends Fragment {
FragmentLocationBinding binding;


    public Location() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_location, container, false);
//       binding.SignUpSearhWork.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               Navigation.findNavController(binding.getRoot()).navigate(R.id.action_location_to_orders);
//           }
//       });

      return binding.getRoot();
    }
}