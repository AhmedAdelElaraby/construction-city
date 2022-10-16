package com.teraninja.constructioncity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teraninja.constructioncity.DataClinte.OnClickId;
import com.teraninja.constructioncity.databinding.FragmentByCategoryBinding;
import com.teraninja.constructioncity.databinding.FragmentByCategoryOneBinding;
import com.teraninja.constructioncity.model.DataBrands;
import com.teraninja.constructioncity.model.SendDataBrands;
import com.teraninja.constructioncity.ui.MoveViewModel;
import com.teraninja.constructioncity.ui.main.AdapterByCategory;
import com.teraninja.constructioncity.ui.main.AdapterByCategoryOne;

import java.util.ArrayList;
import java.util.Locale;


public class ByCategory_one extends Fragment implements OnClickId {
FragmentByCategoryOneBinding binding;
String companyid;
String servrsid;
String l;
MoveViewModel model;
    public ByCategory_one() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      binding= DataBindingUtil.inflate(inflater,R.layout.fragment_by_category_one, container, false);
      model= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();

        if (getArguments()!=null){
          ByCategory_oneArgs args = ByCategory_oneArgs.fromBundle(getArguments());
          companyid= args.getCompanyId();
          servrsid=args.getServiseId();
          SendDataBrands brands = new SendDataBrands();
          brands.setCompany_id(companyid);
          brands.setService_id(servrsid);
          model.Brands("application/json",l,brands);

      }
        AdapterByCategoryOne byCategory = new AdapterByCategoryOne(this);
        binding.RecInsideCountry.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.RecInsideCountry.setAdapter(byCategory);

        model.Brands.observe(getViewLifecycleOwner(), new Observer<DataBrands>() {
            @Override
            public void onChanged(DataBrands dataBrands) {
                byCategory.getList(dataBrands.getData());
            }
        });


        binding.imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_byCategory_one_to_seting);
            }
        });


binding.searchView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
    }
});



      return binding.getRoot();
    }

    @Override
    public void getId(int brandsid) {
        ByCategory_oneDirections.ActionByCategoryOneToByCategoryTwo action=ByCategory_oneDirections.actionByCategoryOneToByCategoryTwo();
        action.setBrandsid(String.valueOf(brandsid));
        action.setCompanyId(companyid);
        Navigation.findNavController(binding.getRoot()).navigate(action);

    }
}