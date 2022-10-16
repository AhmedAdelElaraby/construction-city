package com.teraninja.constructioncity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.teraninja.constructioncity.databinding.FragmentConfirmPhoneBinding;
import com.teraninja.constructioncity.model.DataRenendcode;
import com.teraninja.constructioncity.model.DataRqusteCode;
import com.teraninja.constructioncity.model.DataSendResendCode;
import com.teraninja.constructioncity.model.SendDataCode;
import com.teraninja.constructioncity.ui.MoveViewModel;

import java.util.Locale;


public class Confirm_Phone extends Fragment {
FragmentConfirmPhoneBinding binding;
String number;
String code;
MoveViewModel model;
String l;
    public Confirm_Phone() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_confirm__phone, container, false);
        model = ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        if (getArguments()!=null){
          Confirm_PhoneArgs confirm_phoneArgs = Confirm_PhoneArgs.fromBundle(getArguments());
          number=confirm_phoneArgs.getNumber();
            timersend();

        }
        binding.reternSendPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progress.setVisibility(View.VISIBLE);
                timersend();
                DataSendResendCode code = new DataSendResendCode();
                code.setPhone(number);
                model.Resendcode("application/json",l,code);
            }
        });
        model.resendcode.observe(getViewLifecycleOwner(), new Observer<DataRenendcode>() {
            @Override
            public void onChanged(DataRenendcode dataRenendcode) {
                binding.progress.setVisibility(View.GONE);

                if (dataRenendcode.getStatus()==1){

                    Toast.makeText(getContext(), ""+dataRenendcode.getMessage(), Toast.LENGTH_SHORT).show();
                    //code=String.valueOf(dataRenendcode.getMessage());

                }
            }
        });
        binding.txtPinEntry.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
            @Override
            public void onPinEntered(CharSequence str) {
                binding.progress.setVisibility(View.VISIBLE);

                code= String.valueOf(str);

                   SendDataCode codeoop = new SendDataCode();
                   codeoop.setPhone(number);
                   codeoop.setCode(code);
                    model.SendCode("application/json",l,codeoop);
                    // Toast.makeText(getContext(), "SUCCESS", Toast.LENGTH_SHORT).show();



                    // Toast.makeText(getContext(), "FAIL", Toast.LENGTH_SHORT).show();

            }
        });




model.SendCode.observe(getViewLifecycleOwner(), new Observer<DataRqusteCode>() {
    @Override
    public void onChanged(DataRqusteCode dataRqusteCode) {
        binding.progress.setVisibility(View.GONE);

        if (dataRqusteCode.getStatus()==1){
            Confirm_PhoneDirections.ActionConfirmPhoneToSignUp phoneDirections= Confirm_PhoneDirections.actionConfirmPhoneToSignUp();
            phoneDirections.setNumber(number);
            Navigation.findNavController(binding.getRoot()).navigate(phoneDirections);
            Toast.makeText(getContext(), ""+dataRqusteCode.getMessage(), Toast.LENGTH_SHORT).show();
            dataRqusteCode.setStatus(2);
        }else
        {
            binding.txtPinEntry.setText(null);
            binding.progress.setVisibility(View.GONE);

            Toast.makeText(getContext(), ""+dataRqusteCode.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
});
        return binding.getRoot();
    }
    public void  timersend(){

        new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                binding.reternSendPassword.setEnabled(false);
                binding.reternSendPassword.setText("remaining: " + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext

            }

            public void onFinish() {
                binding.reternSendPassword.setEnabled(true);
                binding.reternSendPassword.setText(""+getString(R.string.return_send_code));

            }

        }.start();

    }
}