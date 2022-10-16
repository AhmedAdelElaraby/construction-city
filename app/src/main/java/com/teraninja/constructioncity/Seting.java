package com.teraninja.constructioncity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.akexorcist.localizationactivity.core.LocalizationDelegate;
import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.akexorcist.localizationactivity.ui.LocalizationApplication;
import com.teraninja.constructioncity.databinding.FragmentSetingBinding;
import com.teraninja.constructioncity.model.DataLogOut;
import com.teraninja.constructioncity.ui.MoveViewModel;

import java.util.Locale;


public class Seting extends Fragment {
FragmentSetingBinding binding;
MoveViewModel model;
    String Token;
    String casehome;
    SharedPreferences preferences;
    public Seting() {
        // Required empty public constructor
    }


   
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_seting, container, false);
        model = ViewModelProviders.of(this).get(MoveViewModel.class);
        preferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token", "no");
        casehome = preferences.getString("case","no");

        binding.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();

            }
        });
        binding.SaveAddrees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (casehome.equals("skip")){
                    Toast.makeText(getContext(),""+getString(R.string.caselogin), Toast.LENGTH_SHORT).show();
                }else {
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_seting_to_allLocation);
                }
            }
        });
        binding.cOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (casehome.equals("skip")){
                    Toast.makeText(getContext(),""+getString(R.string.caselogin), Toast.LENGTH_SHORT).show();
                }else {
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_seting_to_chengeProfile);
                }
            }
        });
        binding.cFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (casehome.equals("skip")){
                    Toast.makeText(getContext(),""+getString(R.string.caselogin), Toast.LENGTH_SHORT).show();
                }else {
                    model.LogOut("Bearer" + Token, "application/json");
                }
            }
        });
        model.logOut.observe(getViewLifecycleOwner(), new Observer<DataLogOut>() {
            @Override
            public void onChanged(DataLogOut dataLogOut) {
                if (dataLogOut.getStatus() == 1) {
                    Toast.makeText(getContext(), "" + dataLogOut.getMessage(), Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_seting_to_splash_One);
                }
            }
        });

        binding.changeTheLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Locale.getDefault().getLanguage().equals("en")){

                    LocalizationDelegate delegate = new LocalizationDelegate(getActivity());
                    delegate.setLanguage(getContext(),"ar");

                }else {
                    LocalizationDelegate delegate = new LocalizationDelegate(getActivity());
                    delegate.setLanguage(getContext(),"en");

                }

            }
        });
        binding.Orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (casehome.equals("skip")){
                    Toast.makeText(getContext(),""+getString(R.string.caselogin), Toast.LENGTH_SHORT).show();
                }else {
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_seting_to_orders);
                }
            }
        });

        return binding.getRoot();
    }
}