package com.teraninja.constructioncity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teraninja.constructioncity.DataClinte.OnClickIdProdated;
import com.teraninja.constructioncity.databinding.FragmentByCategoryTwoBinding;
import com.teraninja.constructioncity.model.DataProdacted;
import com.teraninja.constructioncity.model.SendDataProdacted;
import com.teraninja.constructioncity.ui.MoveViewModel;
import com.teraninja.constructioncity.ui.main.AdapterByCategoryTwo;

import java.util.ArrayList;
import java.util.Locale;


public class ByCategory_two extends Fragment implements OnClickIdProdated {
FragmentByCategoryTwoBinding binding;
    String companyid;
    String brandsid;
MoveViewModel model;
String l;
    public ByCategory_two() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_by_category_two, container, false);
       model= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
       if (getArguments()!=null){
           ByCategory_twoArgs args =ByCategory_twoArgs.fromBundle(getArguments());
          companyid= args.getCompanyId();
          brandsid= args.getBrandsid();
          //

           //
           SendDataProdacted prodacted = new SendDataProdacted();
           prodacted.setCompany_id(companyid);
           prodacted.setBrand_id(brandsid);
           model.Prodacted("application/json",l,prodacted);
       }
        AdapterByCategoryTwo categoryTwo = new AdapterByCategoryTwo(this);
        binding.RecInsideCountry.setLayoutManager(new GridLayoutManager(getContext(),2));
        binding.RecInsideCountry.setAdapter(categoryTwo);

        binding.imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_byCategory_two_to_seting);
            }
        });


        model.Prodacted.observe(getViewLifecycleOwner(), new Observer<DataProdacted>() {
            @Override
            public void onChanged(DataProdacted dataProdacted) {
                categoryTwo.getList(dataProdacted.getData());
            }
        });
       return binding.getRoot();
    }

    @Override
    public void getIdProdacted(int ProdactedId) {
        ByCategory_twoDirections.ActionByCategoryTwoToShowDetilseCategory action=ByCategory_twoDirections.actionByCategoryTwoToShowDetilseCategory();
        action.setIdProdacted(ProdactedId);//ProdactedId
        action.setCompanyId(companyid);
        Navigation.findNavController(binding.getRoot()).navigate(action);

    }
}