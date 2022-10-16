package com.teraninja.constructioncity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.teraninja.constructioncity.DataClinte.OnClickOllStreem;
import com.teraninja.constructioncity.databinding.FragmentInsideCountryBinding;
import com.teraninja.constructioncity.model.DataOllStreet;
import com.teraninja.constructioncity.model.ModelDataAllCity;
import com.teraninja.constructioncity.model.SendDataOllStreet;
import com.teraninja.constructioncity.ui.MoveViewModel;
import com.teraninja.constructioncity.ui.main.AdapterInsideCountry;

import java.util.ArrayList;
import java.util.Locale;

public class InsideCountry extends Fragment implements OnClickOllStreem {
   FragmentInsideCountryBinding binding;
MoveViewModel model;
String l;
ArrayList<String> Cityname= new ArrayList<>();
ArrayList<Integer> CityId= new ArrayList<>();

    public InsideCountry() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_inside_country, container, false);
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        model.AllCity("application/json");
        SendDataOllStreet ollStreet = new SendDataOllStreet();
        model.OllStreet("application/json",l,ollStreet);
        AdapterInsideCountry country = new AdapterInsideCountry(this);
        binding.RecInsideCountry.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
       binding.RecInsideCountry.setAdapter(country);


       model.OllStreet.observe(getViewLifecycleOwner(), new Observer<DataOllStreet>() {
           @Override
           public void onChanged(DataOllStreet dataOllStreet) {
               country.getList(dataOllStreet.getData());
           }
       });
       binding.searchView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
           }
       });
        binding.imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_insideCountry_to_seting);
            }
        });
       model.city.observe(getViewLifecycleOwner(), new Observer<ModelDataAllCity>() {
           @Override
           public void onChanged(ModelDataAllCity modelDataAllCity) {
               Cityname.clear();
               CityId.clear();
               Cityname.add("Chose City");
               CityId.add(0);
               for (int i=0;i<modelDataAllCity.getData().size();i++){
                   Cityname.add(modelDataAllCity.getData().get(i).getName_ar());
                   CityId.add(modelDataAllCity.getData().get(i).getId());
               }
               ArrayAdapter<String> City = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,Cityname);
                binding.filter.setAdapter(City);

           }
       });
       binding.filter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               if (position==0){
                   // no send id to api if position equals 0
                   SendDataOllStreet ollStreet = new SendDataOllStreet();
                   model.OllStreet("application/json",l,ollStreet);
               }
               else{
                   //this is send id to api if position equals
                   SendDataOllStreet ollStreet = new SendDataOllStreet();
                   ollStreet.setCity_id(String.valueOf(CityId.get(position)));
                   model.OllStreet("application/json",l,ollStreet);
               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
        return binding.getRoot();
    }

    @Override
    public void getIdStreem(int id) {
        Toast.makeText(getContext(), ""+id, Toast.LENGTH_SHORT).show();
        InsideCountryDirections.ActionInsideCountryToByCompany country =InsideCountryDirections.actionInsideCountryToByCompany();
        country.setId(id);
        Navigation.findNavController(binding.getRoot()).navigate(country);

    }
}