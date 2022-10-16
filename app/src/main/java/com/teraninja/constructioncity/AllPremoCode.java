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

import com.teraninja.constructioncity.databinding.FragmentAllPremoCodeBinding;
import com.teraninja.constructioncity.model.DataAllPremoCode;
import com.teraninja.constructioncity.ui.MoveViewModel;
import com.teraninja.constructioncity.ui.main.AdapterAllPremoCode;


public class AllPremoCode extends Fragment {
FragmentAllPremoCodeBinding binding;
MoveViewModel model;
    String Token;
    SharedPreferences preferences;


    public AllPremoCode() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_all_premo_code, container, false);
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token","no");
        model.Allcode("Bearer"+Token,"application/json");
        AdapterAllPremoCode premoCode = new AdapterAllPremoCode();
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rec.setAdapter(premoCode);

    model.premoCode.observe(getViewLifecycleOwner(), new Observer<DataAllPremoCode>() {
        @Override
        public void onChanged(DataAllPremoCode dataAllPremoCode) {
            premoCode.getList(dataAllPremoCode.getData());
        }
    });
       return binding.getRoot();
    }
}