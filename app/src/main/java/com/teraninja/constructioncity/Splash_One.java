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

import com.teraninja.constructioncity.databinding.FragmentSplashOneBinding;


public class Splash_One extends Fragment {

   FragmentSplashOneBinding binding;
    SharedPreferences preferences;
    public Splash_One() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_splash__one, container, false);
        preferences= getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);

        binding.SignUpSearhWork.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Navigation.findNavController(binding.getRoot()).navigate(R.id.action_splash_One_to_login);
           }
       });
        binding.Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedata("case","skip");
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_splash_One_to_home2);

            }
        });

       return binding.getRoot();
    }
    public  void savedata(String key,String val){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,val);
        editor.commit();

    }
}