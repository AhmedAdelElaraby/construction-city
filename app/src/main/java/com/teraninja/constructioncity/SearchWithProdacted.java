package com.teraninja.constructioncity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teraninja.constructioncity.DataClinte.OnClickOllStreem;
import com.teraninja.constructioncity.databinding.FragmentSearchWithProdactedBinding;
import com.teraninja.constructioncity.model.DataSearchToProdacted;
import com.teraninja.constructioncity.ui.MoveViewModel;
import com.teraninja.constructioncity.ui.main.AdapterSearchWithProdacted;

import java.util.Locale;


public class SearchWithProdacted extends Fragment implements OnClickOllStreem {
FragmentSearchWithProdactedBinding binding;
MoveViewModel model;
String l;
    public SearchWithProdacted() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_search_with_prodacted, container, false);
       model= ViewModelProviders.of(this).get(MoveViewModel.class);
       l=Locale.getDefault().getLanguage();
       model.SearchProdacted("application/json",l);
        AdapterSearchWithProdacted search = new AdapterSearchWithProdacted(this);
        binding.RecInsideCountry.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.RecInsideCountry.setAdapter(search);
       model.search.observe(getViewLifecycleOwner(), new Observer<DataSearchToProdacted>() {
           @Override
           public void onChanged(DataSearchToProdacted dataSearchToProdacted) {
               search.getList(dataSearchToProdacted.getData());
           }
       });
        binding.imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_byCategory_two_to_seting);
            }
        });

       return binding.getRoot();
    }

    @Override
    public void getIdStreem(int id) {

    }
}