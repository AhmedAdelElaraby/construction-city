package com.teraninja.constructioncity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.teraninja.constructioncity.databinding.FragmentPasswordBinding;
import com.teraninja.constructioncity.model.DataLogin;
import com.teraninja.constructioncity.model.SendDataLogin;
import com.teraninja.constructioncity.ui.MoveViewModel;

import java.util.Locale;


public class Password extends Fragment {
FragmentPasswordBinding binding;
 String number;
MoveViewModel model;
    SharedPreferences preferences;
    String l;
    public Password() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_password, container, false);
       model= ViewModelProviders.of(this).get(MoveViewModel.class);
        preferences= getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);

        l= Locale.getDefault().getLanguage();
       if (getArguments()!=null){
           PasswordArgs args = PasswordArgs.fromBundle(getArguments());
           number=args.getPhone();

       }
       binding.SignUpSearhWork.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               binding.progress.setVisibility(View.VISIBLE);
               binding.SignUpSearhWork.setVisibility(View.GONE);
               String pasword=binding.numberphone.getText().toString().trim();
               if (pasword.isEmpty()){
                   binding.progress.setVisibility(View.GONE);
                   binding.SignUpSearhWork.setVisibility(View.VISIBLE);
                   binding.numberphone.setError("Please Enter Password");
                   return;
               }else{
                   SendDataLogin login = new SendDataLogin();
                   login.setPhone(number);
                   login.setPassword(pasword);
                   model.login("application/json",l,login);

               }

           }
       });
       model.login.observe(getViewLifecycleOwner(), new Observer<DataLogin>() {
           @Override
           public void onChanged(DataLogin dataLogin) {
               if (dataLogin.getStatus()==0){
                   binding.progress.setVisibility(View.GONE);
                   binding.SignUpSearhWork.setVisibility(View.VISIBLE);
                   Toast.makeText(getContext(), ""+dataLogin.getMessage(), Toast.LENGTH_SHORT).show();
                   return;
               }if (dataLogin.getStatus()==3){
                   Toast.makeText(getContext(), ""+dataLogin.getMessage(), Toast.LENGTH_SHORT).show();
                    savedata("token",dataLogin.getData().getToken());
                    savedata("name",dataLogin.getData().getUser().getName());
                    savedata("number",""+dataLogin.getData().getUser().getPhone());
                    savedata("image",dataLogin.getData().getUser().getImage());
                    savedata("case","login");
                   Navigation.findNavController(binding.getRoot()).navigate(R.id.action_password2_to_home2);
                   binding.progress.setVisibility(View.GONE);
                   binding.SignUpSearhWork.setVisibility(View.VISIBLE);
                   dataLogin.setStatus(5);
               }
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