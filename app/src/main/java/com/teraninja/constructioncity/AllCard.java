package com.teraninja.constructioncity;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.teraninja.constructioncity.DataClinte.OnClickShowInCard;
import com.teraninja.constructioncity.databinding.FragmentAllCardBinding;
import com.teraninja.constructioncity.model.DataModelAllCompanyInCard;
import com.teraninja.constructioncity.ui.MoveViewModel;
import com.teraninja.constructioncity.ui.main.AdapterAllCompanyInCard;


public class AllCard extends Fragment implements OnClickShowInCard {
FragmentAllCardBinding binding;
    MoveViewModel model;
    String Token;
    SharedPreferences preferences;

    public AllCard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_all_card, container, false);
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token","no");
        model.AllCompanyInCard("Bearer"+Token,"application/json");

        AdapterAllCompanyInCard inCard = new AdapterAllCompanyInCard(this);
        binding.rec.setLayoutManager( new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rec.setAdapter(inCard);
    model.AllCompanyInCard.observe(getViewLifecycleOwner(), new Observer<DataModelAllCompanyInCard>() {
        @Override
        public void onChanged(DataModelAllCompanyInCard dataModelAllCompanyInCard) {
            inCard.getList(dataModelAllCompanyInCard.getData());
        }
    });

        return binding.getRoot();
    }

    @Override
    public void getId(int companyId) {
        AllCardDirections.ActionAllCardToCart cardToCart=AllCardDirections.actionAllCardToCart();
        cardToCart.setCompanyId(String.valueOf(companyId));
        Navigation.findNavController(binding.getRoot()).navigate(cardToCart);
    }
}