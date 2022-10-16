package com.teraninja.constructioncity;

import android.animation.TimeInterpolator;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.guilhe.views.CircularProgressView;
import com.teraninja.constructioncity.databinding.FragmentLoginBinding;
import com.teraninja.constructioncity.model.DataLogin;
import com.teraninja.constructioncity.model.SendDataLogin;
import com.teraninja.constructioncity.ui.MoveViewModel;

import java.util.Locale;
import java.util.Objects;


public class Login extends Fragment {
FragmentLoginBinding binding;
MoveViewModel model;
String l;
    String number;
    public Login() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_login, container, false);
       model= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        binding.SignUpSearhWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progress.setVisibility(View.VISIBLE);
                binding.SignUpSearhWork.setVisibility(View.GONE);
                number=binding.numberphone.getText().toString().trim();
                if (number.isEmpty()){
                    binding.progress.setVisibility(View.GONE);
                    binding.SignUpSearhWork.setVisibility(View.VISIBLE);
                    binding.numberphone.setError("Please Enter your number Phone");
                    return;
                }else {
                    SendDataLogin dataLogin= new SendDataLogin();
                    dataLogin.setPhone(number);
                    model.login("application/json",l,dataLogin);
                }
             //   Navigation.findNavController(binding.getRoot()).navigate(R.id.action_login_to_confirm_Phone);
            }
        });
        model.login.observe(getViewLifecycleOwner(), new Observer<DataLogin>() {
            @Override
            public void onChanged(DataLogin dataLogin) {


                if (dataLogin.getStatus()==0){
                    binding.progress.setVisibility(View.GONE);
                    binding.SignUpSearhWork.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), ""+dataLogin.getMessage(), Toast.LENGTH_SHORT).show();
                    LoginDirections.ActionLoginToPassword2 toPassword2= LoginDirections.actionLoginToPassword2();
                    toPassword2.setPhone(number);
                    Navigation.findNavController(binding.getRoot()).navigate(toPassword2);
                    dataLogin.setStatus(3);
                    //GoTo Enter Password
                    return;
                }
                if (dataLogin.getStatus()==1){
                    binding.progress.setVisibility(View.GONE);
                    binding.SignUpSearhWork.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), ""+dataLogin.getMessage(), Toast.LENGTH_SHORT).show();
                   // Toast.makeText(getContext(), ""+dataLogin.getData().getOriginal().getMessage(), Toast.LENGTH_SHORT).show();
                    LoginDirections.ActionLoginToConfirmPhone toConfirmPhone =LoginDirections.actionLoginToConfirmPhone();
                    toConfirmPhone.setNumber(number);
                 // toConfirmPhone.setCode(String.valueOf(dataLogin.getData().getOriginal().getMessage()));
                    Navigation.findNavController(binding.getRoot()).navigate(toConfirmPhone);
                    dataLogin.setStatus(3);

                    return;

                }if (dataLogin.getStatus()==2){
                    binding.progress.setVisibility(View.GONE);
                    binding.SignUpSearhWork.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(),""+dataLogin.getMessage(), Toast.LENGTH_SHORT).show();
                    LoginDirections.ActionLoginToSignUp toSignUp = LoginDirections.actionLoginToSignUp();
                    toSignUp.setNumber(number);
                    Navigation.findNavController(binding.getRoot()).navigate(toSignUp);
                    dataLogin.setStatus(3);

                    return;

                }else{
                    binding.progress.setVisibility(View.GONE);
                    binding.SignUpSearhWork.setVisibility(View.VISIBLE);
                    //Toast.makeText(getContext(), ""+dataLogin.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
        return binding.getRoot();
    }

}