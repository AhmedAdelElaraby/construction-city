package com.teraninja.constructioncity;

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

import com.teraninja.constructioncity.databinding.FragmentSignUpBinding;
import com.teraninja.constructioncity.model.DataRegster;
import com.teraninja.constructioncity.model.SendDataRegister;
import com.teraninja.constructioncity.ui.MoveViewModel;

import java.util.Locale;


public class SignUp extends Fragment {
FragmentSignUpBinding binding;
String number;
MoveViewModel model;
String l;
    public SignUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_sign_up, container, false);
       model= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        if (getArguments()!=null){
           SignUpArgs signUpArgs=SignUpArgs.fromBundle(getArguments());
           number=signUpArgs.getNumber();
       }
       binding.SignUpSearhWork.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               binding.progress.setVisibility(View.VISIBLE);
               binding.SignUpSearhWork.setVisibility(View.GONE);
               String name=binding.name.getText().toString().trim();
               String password=binding.password.getText().toString().trim();
               String confirmPassword=binding.confirmPassword.getText().toString().trim();
               if (name.isEmpty()){
                   binding.name.setError(getString(R.string.please_enter_your_name));
                   binding.progress.setVisibility(View.GONE);
                   binding.SignUpSearhWork.setVisibility(View.VISIBLE);
                   return;
               }
               if (password.isEmpty()){
                   binding.password.setError(getString(R.string.please_enter_password));
                   binding.progress.setVisibility(View.GONE);
                   binding.SignUpSearhWork.setVisibility(View.VISIBLE);
                   return;
               }
               if (confirmPassword.isEmpty()){
                   binding.confirmPassword.setError(getString(R.string.confirm_password));
                   binding.progress.setVisibility(View.GONE);
                   binding.SignUpSearhWork.setVisibility(View.VISIBLE);
                   return;
               }
               if (!password.equals(confirmPassword)){
                   binding.password.setError(getString(R.string.confirmation));
                   binding.confirmPassword.setError(getString(R.string.confirmation));
                   binding.progress.setVisibility(View.GONE);
                   binding.SignUpSearhWork.setVisibility(View.VISIBLE);
                   return;
               }
               else{
                   SendDataRegister register = new SendDataRegister();
                   register.setName(name);
                   register.setPassword(password);
                   register.setPassword_confirmation(confirmPassword);
                   register.setPhone(number);

                model.Register("application/json",l,register);
               }
           }
       });
    model.regster.observe(getViewLifecycleOwner(), new Observer<DataRegster>() {
        @Override
        public void onChanged(DataRegster dataRegster) {
            if (dataRegster.getStatus()==1){
                binding.progress.setVisibility(View.GONE);
                Toast.makeText(getContext(), ""+dataRegster.getMessage(), Toast.LENGTH_SHORT).show();

                binding.SignUpSearhWork.setVisibility(View.VISIBLE);
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_signUp_to_login);
                dataRegster.setStatus(3);

            }else if (dataRegster.getStatus()==0){
                binding.progress.setVisibility(View.GONE);
                binding.SignUpSearhWork.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), ""+dataRegster.getMessage(), Toast.LENGTH_SHORT).show();

            }
            else {

            }
        }
    });










       return binding.getRoot();
    }
}