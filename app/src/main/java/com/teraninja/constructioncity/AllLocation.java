package com.teraninja.constructioncity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teraninja.constructioncity.DataClinte.OnClickLocation;
import com.teraninja.constructioncity.databinding.FragmentAllLocationBinding;
import com.teraninja.constructioncity.model.DataModelocation;
import com.teraninja.constructioncity.ui.MoveViewModel;
import com.teraninja.constructioncity.ui.main.AdapterAllLocation;


public class AllLocation extends Fragment implements OnClickLocation {
FragmentAllLocationBinding binding;
    MoveViewModel model;
    String Token;
    SharedPreferences preferences;

    public AllLocation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_all_location, container, false);
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token","no");
        model.allLocation("Bearer"+Token,"application/json");
        AdapterAllLocation allLocation = new AdapterAllLocation("all",this);
        binding.reclocation.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.reclocation.setAdapter(allLocation);
        model.allLocation.observe(getViewLifecycleOwner(), new Observer<DataModelocation>() {
            @Override
            public void onChanged(DataModelocation dataModelocation) {
                allLocation.getList(dataModelocation.getData());
            }
        });

       return binding.getRoot();
    }

    @Override
    public void getIdLocation(int id) {

    }
}