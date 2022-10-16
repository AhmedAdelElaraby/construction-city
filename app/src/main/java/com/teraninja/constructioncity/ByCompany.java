package com.teraninja.constructioncity;

import android.graphics.drawable.Drawable;
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
import android.widget.Toast;

import com.teraninja.constructioncity.DataClinte.OnClickCompaniesOllStreet;
import com.teraninja.constructioncity.databinding.FragmentByCompanyBinding;
import com.teraninja.constructioncity.model.DataCompaniesInStreet;
import com.teraninja.constructioncity.ui.MoveViewModel;
import com.teraninja.constructioncity.ui.main.AdapterByCompany;

import java.util.ArrayList;
import java.util.Locale;


public class ByCompany extends Fragment implements OnClickCompaniesOllStreet {
FragmentByCompanyBinding binding;
MoveViewModel model;
int idstreet;
String l;
    public ByCompany() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_by_company, container, false);
       model= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
       if (getArguments()!=null){
           ByCompanyArgs args =ByCompanyArgs.fromBundle(getArguments());
          idstreet= args.getId();
          model.getcompanies("application/json",l,idstreet);
       }
        AdapterByCompany company = new AdapterByCompany(this);
        binding.RecInsideCountry.setLayoutManager(new GridLayoutManager(getContext(),2));
        binding.RecInsideCountry.setAdapter(company);



        binding.searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_byCompany_to_byCategory);
            }
        });

    model.companies.observe(getViewLifecycleOwner(), new Observer<DataCompaniesInStreet>() {
        @Override
        public void onChanged(DataCompaniesInStreet dataCompaniesInStreet) {
                company.getList(dataCompaniesInStreet.getData());

        }
    });
        binding.imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_byCompany_to_seting);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void getId(int id) {
        Toast.makeText(getContext(), ""+id, Toast.LENGTH_SHORT).show();
        ByCompanyDirections.ActionByCompanyToByCategory action = ByCompanyDirections.actionByCompanyToByCategory();
        action.setId(id);
        Navigation.findNavController(binding.getRoot()).navigate(action);

    }
}