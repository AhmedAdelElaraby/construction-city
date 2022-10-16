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
import android.widget.ImageView;
import android.widget.Toast;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.teraninja.constructioncity.databinding.FragmentShowDetilseCategoryBinding;
import com.teraninja.constructioncity.model.DataDetilseProdacted;
import com.teraninja.constructioncity.model.DataModelCard;
import com.teraninja.constructioncity.model.SendDataForCard;
import com.teraninja.constructioncity.ui.MoveViewModel;

import java.util.ArrayList;
import java.util.Locale;


public class ShowDetilseCategory extends Fragment {
  FragmentShowDetilseCategoryBinding binding;
  int Id;
  MoveViewModel model;
  String l;
  int cuont=1;
  String Token;
    String casehome;
  String CompanyId;
    SharedPreferences preferences;
  ArrayList<SlideModel> list = new ArrayList<>();

    public ShowDetilseCategory() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_show_detilse_category, container, false);
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token","no");
        casehome = preferences.getString("case","no");

        if (getArguments()!=null){
            ShowDetilseCategoryArgs args=ShowDetilseCategoryArgs.fromBundle(getArguments());
           Id= args.getIdProdacted();
            CompanyId= args.getCompanyId();
            Toast.makeText(getContext(), ""+Id+""+CompanyId, Toast.LENGTH_SHORT).show();
           model.DetilseProdacted("application/json",l,Id);
        }
        model.detilseProdacted.observe(getViewLifecycleOwner(), new Observer<DataDetilseProdacted>() {
            @Override
            public void onChanged(DataDetilseProdacted dataDetilseProdacted) {

                binding.cuont.setText(""+cuont);
                binding.name.setText(""+dataDetilseProdacted.getData().getName());
                binding.price.setText(""+dataDetilseProdacted.getData().getPrice());
                binding.caseprodacte.setText(""+dataDetilseProdacted.getData().getStatus());
                binding.detls.setText(""+dataDetilseProdacted.getData().getDescription());
                list.clear();
                for (int i=0;i<dataDetilseProdacted.getData().getImage().size();i++){
                    list.add(new SlideModel(dataDetilseProdacted.getData().getImage().get(i).getPath()," ", ScaleTypes.FIT));

                }
                binding.imageslider.setImageList(list, ScaleTypes.FIT) ;// for all images

                // binding.
            }
        });
        binding.plase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cuont++;
                binding.cuont.setText(""+cuont);
            }
        });
        binding.mines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cuont==1){

                }else {
                    cuont--;
                    binding.cuont.setText("" + cuont);
                }
            }
        });
        binding.SignUpSearhWork.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if (casehome.equals("skip")){
                  Toast.makeText(getContext(),""+getString(R.string.caselogin), Toast.LENGTH_SHORT).show();
              }else {
                  Toast.makeText(getContext(), ""+cuont+"company"+CompanyId+" prodactedId"+Id, Toast.LENGTH_SHORT).show();
                  SendDataForCard forCard = new SendDataForCard();
                  forCard.setProduct_id(String.valueOf(Id));
                  forCard.setQty(String.valueOf(cuont));
                  model.ForCard("Bearer"+Token,"application/json",forCard);
              }


          }
        });
        binding.imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_showDetilseCategory_to_seting);
            }
        });
        model.ForCard.observe(getViewLifecycleOwner(), new Observer<DataModelCard>() {
            @Override
            public void onChanged(DataModelCard dataModelCard) {
               // Toast.makeText(getContext(), "Qty"+dataModelCard.getData().get(0).getQty(), Toast.LENGTH_SHORT).show();
                if (dataModelCard.getStatus()==1){
                    ShowDetilseCategoryDirections.ActionShowDetilseCategoryToCart toCart=ShowDetilseCategoryDirections.actionShowDetilseCategoryToCart();
                    toCart.setCompanyId(CompanyId);
                    toCart.setIdProdected(String.valueOf(Id));
                    Navigation.findNavController(binding.getRoot()).navigate(toCart);
                    dataModelCard.setStatus(3);
                }
                Toast.makeText(getContext(), ""+dataModelCard.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();
    }
}