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

import com.teraninja.constructioncity.DataClinte.OnClickIdServise;
import com.teraninja.constructioncity.databinding.FragmentByCategoryBinding;
import com.teraninja.constructioncity.model.DataServise;
import com.teraninja.constructioncity.ui.MoveViewModel;
import com.teraninja.constructioncity.ui.main.AdapterByCategory;

import java.util.ArrayList;
import java.util.Locale;


public class ByCategory extends Fragment implements OnClickIdServise {
FragmentByCategoryBinding binding;
int id ;
 MoveViewModel model;
 String l;
    public ByCategory() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_by_category, container, false);
       model= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();

        if (getArguments()!=null){
        ByCategoryArgs args = ByCategoryArgs.fromBundle(getArguments());
       id= args.getId();
       model.ServiseInMarket("application/json",l,id);
    }
        AdapterByCategory byCategory = new AdapterByCategory(this);
        binding.RecInsideCountry.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.RecInsideCountry.setAdapter(byCategory);

        binding.searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_byCategory_to_byCategory_one);
            }
        });
        model.serviseInMarket.observe(getViewLifecycleOwner(), new Observer<DataServise>() {
            @Override
            public void onChanged(DataServise dataServise) {
                byCategory.getList(dataServise.getData());
            }
        });
        binding.imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_byCategory_to_seting);
            }
        });
       return binding.getRoot();
    }

    @Override
    public void getId(int company_id, int servies_id) {
        ByCategoryDirections.ActionByCategoryToByCategoryOne action = ByCategoryDirections.actionByCategoryToByCategoryOne();
        action.setCompanyId(String.valueOf(company_id));
        action.setServiseId(String.valueOf(servies_id));
        Navigation.findNavController(binding.getRoot()).navigate(action);



    }
}