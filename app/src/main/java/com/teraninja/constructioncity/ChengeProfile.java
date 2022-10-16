package com.teraninja.constructioncity;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.teraninja.constructioncity.ui.main.ImagePickerHelper.getPathFromUri;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.squareup.picasso.Picasso;
import com.teraninja.constructioncity.databinding.FragmentChengeProfileBinding;
import com.teraninja.constructioncity.model.DataChangename;
import com.teraninja.constructioncity.model.DataChengeImage;
import com.teraninja.constructioncity.model.DataUpdatePhone;
import com.teraninja.constructioncity.model.ModelSendImage;
import com.teraninja.constructioncity.model.SendChangeName;
import com.teraninja.constructioncity.model.SendDataUpdatePassword;
import com.teraninja.constructioncity.model.SendUpdateNumberPhone;
import com.teraninja.constructioncity.ui.MoveViewModel;

import java.io.File;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class ChengeProfile extends Fragment {
    String Token;
    String name;
    String number;
    String image;
    SharedPreferences preferences;
    MoveViewModel model;
    String l;
    Uri uri;
    MultipartBody.Part part;
FragmentChengeProfileBinding binding;
    public ChengeProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_chenge_profile, container, false);
       model= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        preferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token", "no");
        name=preferences.getString("name","name");
        number=preferences.getString("number","number");
        image=preferences.getString("image","no");
        binding.name.setText(""+name);
        binding.number.setText(""+number);
        Picasso.get().load(image).into(binding.imageProfileEmail);
        binding.ChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDiloge(getString(R.string.changeyourname),"name");
            }
        });
        binding.ChangeNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDiloge(getString(R.string.changenumberphone),"number");
            }
        });
        binding.UpdatePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(ChengeProfile.this).crop().start(1);
            }
        });
        model.UpdateImage.observe(getViewLifecycleOwner(), new Observer<DataChengeImage>() {
            @Override
            public void onChanged(DataChengeImage dataChengeImage) {
                if (dataChengeImage.getStatus()==1){
                    Toast.makeText(getContext(), ""+dataChengeImage.getMessage(), Toast.LENGTH_SHORT).show();
                }else{

                }

            }
        });
        binding.ChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DilogeUpdatePassword();
            }
        });
        return binding.getRoot();
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                switch (requestCode) {
                    case 1:
                        uri=data.getData();
                      saveImage();
                        ModelSendImage sendImage = new ModelSendImage();
                        sendImage.setImage(part);
                        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                        builder.addPart(sendImage.getImage());
                        RequestBody requestBody = builder.build();
                        model.UpdateImage("Bearer"+Token,"application/json",l,requestBody);
                        break;
                }
               break;
            case RESULT_CANCELED:
                break;

        }
    }




    public void ShowDiloge(String text,String type){
        Dialog myDialog;
        TextView title;
        EditText editText;
        Button Save;
        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.itemchange);
        title=myDialog.findViewById(R.id.textTitel);
        title.setText(""+text);
        editText=myDialog.findViewById(R.id.value_name_or_number);
        Save=myDialog.findViewById(R.id.Save);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String AnyWay= editText.getText().toString().trim();
                if (AnyWay.isEmpty()){
                    editText.setError(getString(R.string.please_enter_your_name));
                }
                else{

                    if (type.equals("name")){
                        SendChangeName changeName = new SendChangeName();
                        changeName.setName(AnyWay);
                        model.ChangeName("Bearer"+Token,"application/json",l,changeName);
                        return;
                    }
                    if (type.equals("number")){

                        SendUpdateNumberPhone updateNumberPhone = new SendUpdateNumberPhone();
                        updateNumberPhone.setPhone(AnyWay);
                        model.UpdatenumberPhone("Bearer"+Token,"application/json",l,updateNumberPhone);
                        return;
                    }
                    else{

                    }
                }
            }
        });
model.ChangeName.observe(getViewLifecycleOwner(), new Observer<DataChangename>() {
    @Override
    public void onChanged(DataChangename dataChangename) {
        if (dataChangename.getStatus()==1){
            Toast.makeText(getContext(), ""+dataChangename.getMessage(), Toast.LENGTH_SHORT).show();
            myDialog.dismiss();
        }else {

        }

    }
});
model.updatePhone.observe(getViewLifecycleOwner(), new Observer<DataUpdatePhone>() {
            @Override
            public void onChanged(DataUpdatePhone dataUpdatePhone) {
                if (dataUpdatePhone.getStatus()==1){
                    Toast.makeText(getContext(), ""+dataUpdatePhone.getMessage(), Toast.LENGTH_SHORT).show();
                    myDialog.dismiss();
                }else {
                    Toast.makeText(getContext(), ""+dataUpdatePhone.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });

        Window window = myDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);





        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.create();
        myDialog.show();
}
    private void saveImage() {
        RequestBody fileReqBody = null;

        File file = new File(getPathFromUri(getContext(),uri));
        // Create a request body with file and image media type
        fileReqBody = RequestBody.create( file, MediaType.parse("image/*"));
        // Create MultipartBody.Part using file request-body,file name and part name
        part = MultipartBody.Part.createFormData("image", file.getName(), fileReqBody);
    }
    public void DilogeUpdatePassword(){
        Dialog myDialog;
        EditText old_password;
        EditText new_password;
        EditText confarn_password;
        Button button;
        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.item_update_password);
        old_password=myDialog.findViewById(R.id.old_password);
        new_password=myDialog.findViewById(R.id.new_password);
        confarn_password=myDialog.findViewById(R.id.confarm_password);
        button=myDialog.findViewById(R.id.Save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String old=old_password.getText().toString().trim();
                String newpass=new_password.getText().toString().trim();
                String comfarm=confarn_password.getText().toString().trim();
                if (old.isEmpty()){
                    old_password.setError(getString(R.string.oldpass));
                    return;
                }
                if (newpass.isEmpty()){
                    new_password.setError(getString(R.string.newpass));
                    return;
                }
                if (comfarm.isEmpty()){
                    confarn_password.setError(getString(R.string.confirm));
                    return;
                }
                else {
                    SendDataUpdatePassword updatePassword = new SendDataUpdatePassword();
                    updatePassword.setOld_password(old);
                    updatePassword.setPassword(newpass);
                    updatePassword.setPassword_confirmation(comfarm);
                    model.UpdatePassword("Bearer"+Token,"application/json",l,updatePassword);
                }


            }
        });
        model.UpdatePassword.observe(getViewLifecycleOwner(), new Observer<DataUpdatePhone>() {
            @Override
            public void onChanged(DataUpdatePhone dataUpdatePhone) {
                if (dataUpdatePhone.getStatus()==1){
                    Toast.makeText(getContext(), ""+dataUpdatePhone.getMessage(), Toast.LENGTH_SHORT).show();
                    myDialog.dismiss();
                }
            }
        });
        Window window = myDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);





        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.create();
        myDialog.show();
    }

}