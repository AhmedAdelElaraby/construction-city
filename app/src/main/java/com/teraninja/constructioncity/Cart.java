package com.teraninja.constructioncity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.HandlerThread;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.teraninja.constructioncity.DataClinte.OnClickLocation;
import com.teraninja.constructioncity.DataClinte.OnClickgetCounter;
import com.teraninja.constructioncity.databinding.FragmentCartBinding;
import com.teraninja.constructioncity.model.DataAddLocation;
import com.teraninja.constructioncity.model.DataChangename;
import com.teraninja.constructioncity.model.DataModelCard;
import com.teraninja.constructioncity.model.DataModelInCard;
import com.teraninja.constructioncity.model.DataModelOreder;
import com.teraninja.constructioncity.model.DataModelocation;
import com.teraninja.constructioncity.model.DataSendOrder;
import com.teraninja.constructioncity.model.DataSendaddLocation;
import com.teraninja.constructioncity.model.ModelDataAllCity;
import com.teraninja.constructioncity.model.SendChangeName;
import com.teraninja.constructioncity.model.SendDataForCard;
import com.teraninja.constructioncity.model.SendDataInCard;
import com.teraninja.constructioncity.model.SendDataOllStreet;
import com.teraninja.constructioncity.ui.MoveViewModel;
import com.teraninja.constructioncity.ui.main.AdapterAllLocation;
import com.teraninja.constructioncity.ui.main.AdapterSendOrders;

import java.util.ArrayList;
import java.util.Locale;


public class Cart extends Fragment implements OnClickgetCounter, OnClickLocation {
FragmentCartBinding binding;
    MoveViewModel model;
    String l;
   int ww=0;
  String CompanyId;
  String IdProdacted;
    String Token;
    Dialog myDialog;
    SharedPreferences preferences;
    ArrayList<String> listCityname= new ArrayList<>();
    ArrayList<Integer> listCityId= new ArrayList<>();
    int sendidcity;
   String AddressId;
   String phone;
    String loc="";
    String nameHome="";
    public Cart() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_cart, container, false);
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token","no");
        if (getArguments()!=null){
            CartArgs args=CartArgs.fromBundle(getArguments());
          CompanyId= args.getCompanyId();
          IdProdacted=args.getIdProdected();

            SendDataInCard inCard = new SendDataInCard();
            inCard.setCompany_id(CompanyId);
            Toast.makeText(getContext(), ""+CompanyId+""+IdProdacted, Toast.LENGTH_SHORT).show();
          model.InCard("Bearer"+Token,"application/json",inCard);
        }
        AdapterSendOrders orders = new AdapterSendOrders(this);
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rec.setAdapter(orders);
        model.InCArd.observe(getViewLifecycleOwner(), new Observer<DataModelInCard>() {
            @Override
            public void onChanged(DataModelInCard dataModelInCard) {

              orders.getList(dataModelInCard.getData().getProducts());
                Toast.makeText(getContext(), "" + dataModelInCard.getMessage(), Toast.LENGTH_SHORT).show();
                if (dataModelInCard.getData().getAddress()==null){
                    binding.clocation.setVisibility(View.GONE);
                    ww=0;
                    binding.SignUpSearhWork.setText(""+getString(R.string.Site));
                }
                else {
                    binding.clocation.setVisibility(View.VISIBLE);
                    ww=1;
                    binding.SignUpSearhWork.setText(getString(R.string.Confirmation));
                    AddressId=String.valueOf(dataModelInCard.getData().getAddress().getId());
                    binding.textlocation.setText("" + dataModelInCard.getData().getAddress().getLocation_type());
                    binding.textlocationchange.setText("" + dataModelInCard.getData().getAddress().getCity() + " - " + dataModelInCard.getData().getAddress().getStreet_name()
                            + " - " + dataModelInCard.getData().getAddress().getBuilding_type());



                }
                phone=dataModelInCard.getData().getPhone_user();
                binding.namepersone.setText("" + dataModelInCard.getData().getName_user());
                binding.numberphone.setText("" + dataModelInCard.getData().getPhone_user());
                binding.subtotalnumber.setText(""+dataModelInCard.getData().getSubtotal());
                binding.addedtaxnumber.setText(""+dataModelInCard.getData().getTax());
                binding.delavareynumber.setText(""+dataModelInCard.getData().getDeliveryValue());
                binding.theTotalNumber.setText(""+dataModelInCard.getData().getTotal());
            }
        });



binding.addnumberother.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});
binding.changeLocation.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ShowDiloge();
    }
});
binding.SignUpSearhWork.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (ww==0){
            ShowDilogeCreateLocation();
            Toast.makeText(getContext(), "location", Toast.LENGTH_SHORT).show();
        }else{
            DataSendOrder order = new DataSendOrder();
            order.setCompany_id(CompanyId);
            order.setUser_addresses_id(AddressId);
            order.setPhone(phone);
           model.CreateOrder("Bearer"+Token,"application/json",order);
            Toast.makeText(getContext(), "send", Toast.LENGTH_SHORT).show();
        }
    }
});
model.CreateOrder.observe(getViewLifecycleOwner(), new Observer<DataModelOreder>() {
    @Override
    public void onChanged(DataModelOreder dataModelOreder) {
        Toast.makeText(getContext(), ""+dataModelOreder.getMessage(), Toast.LENGTH_SHORT).show();

    }
});
model.ForCard.observe(getViewLifecycleOwner(), new Observer<DataModelCard>() {
    @Override
    public void onChanged(DataModelCard dataModelCard) {
        if (dataModelCard.getStatus()== 1){
            SendDataInCard inCard = new SendDataInCard();
            inCard.setCompany_id(CompanyId);
            model.InCard("Bearer"+Token,"application/json",inCard);
        }else {

        }
    }
});
model.RemoveProdacted.observe(getViewLifecycleOwner(), new Observer<DataModelCard>() {
    @Override
    public void onChanged(DataModelCard dataModelCard) {
        if (dataModelCard.getStatus()==1){
            SendDataInCard inCard = new SendDataInCard();
            inCard.setCompany_id(CompanyId);
            model.InCard("Bearer"+Token,"application/json",inCard);
        }
    }
});
        return binding.getRoot();
    }

    @Override
    public void setPlase(int id) {
        SendDataForCard forCard = new SendDataForCard();
        forCard.setProduct_id(String.valueOf(id));

        model.ForCard("Bearer"+Token,"application/json",forCard);

    }

    @Override
    public void setmines(int id) {
        SendDataForCard forCard = new SendDataForCard();
        forCard.setProduct_id(String.valueOf(id));

        model.RemoveProdacted("Bearer"+Token,"application/json",forCard);

    }

    public void ShowDiloge(){

       Button add;
       RecyclerView recyclerView;
        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.item_diloge_chose_location);
        add=myDialog.findViewById(R.id.SignUp_Searh_Work);
        recyclerView=myDialog.findViewById(R.id.recchoselocation);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                ShowDilogeCreateLocation();
            }
        });
        AdapterAllLocation allLocation = new AdapterAllLocation("diloge",this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(allLocation);
        model.allLocation("Bearer"+Token,"application/json");
        model.allLocation.observe(getViewLifecycleOwner(), new Observer<DataModelocation>() {
            @Override
            public void onChanged(DataModelocation dataModelocation) {
                allLocation.getList(dataModelocation.getData());
            }
        });

        Window window = myDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.create();
        myDialog.show();
    }

    @Override
    public void getIdLocation(int id) {
        myDialog.dismiss();
        SendDataInCard inCard = new SendDataInCard();
        inCard.setCompany_id(CompanyId);
        inCard.setUser_addresses_id(String.valueOf(id));
        model.InCard("Bearer"+Token,"application/json",inCard);
    }

    public void ShowDilogeCreateLocation(){
        Dialog CreatLocaton;
        Spinner Cityname;
        Button button;
        RadioButton b1;
        RadioButton b2;
        RadioButton b3;
        EditText namestreem;
        EditText addrass;
        EditText turn_number;
        EditText slit_number;
        EditText Add_information;
        RadioButton b4;
        RadioButton b5;

        CreatLocaton = new Dialog(getContext());
        CreatLocaton.setContentView(R.layout.item_create_location);
        Cityname=CreatLocaton.findViewById(R.id.spinner);
        button=CreatLocaton.findViewById(R.id.savelocation);
        b1=CreatLocaton.findViewById(R.id.radioButton1);
        b2=CreatLocaton.findViewById(R.id.radioButton2);
        b3=CreatLocaton.findViewById(R.id.radioButton3);
        namestreem=CreatLocaton.findViewById(R.id.namestreem);
        b4=CreatLocaton.findViewById(R.id.vela);
        b5=CreatLocaton.findViewById(R.id.omara);
        addrass=CreatLocaton.findViewById(R.id.addrass);
        turn_number=CreatLocaton.findViewById(R.id.turn_number);
        slit_number=CreatLocaton.findViewById(R.id.slit_number);
        Add_information=CreatLocaton.findViewById(R.id.Add_information);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t4 = namestreem.getText().toString();
                String t7 = addrass.getText().toString();
                String t8 = turn_number.getText().toString();
                String t9 = slit_number.getText().toString();
                String t10 = Add_information.getText().toString();
                if (sendidcity==0){
                    Toast.makeText(getContext(), ""+getString(R.string.chosecity), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (loc.isEmpty()){
                    b1.setError("");
                    b2.setError("");
                    b3.setError("");


                }
                if (nameHome.isEmpty()){
                    b4.setError("");
                    b5.setError("");


                }
                if (b1.isChecked()) {
                    loc = "work";

                }
                if (b2.isChecked()) {
                    loc ="home";

                }
                if (b3.isChecked()) {
                    loc = "others";

                } if (b4.isChecked()) {
                    nameHome = "villa";

                } if (b5.isChecked()) {
                    nameHome = "Building";

                }
                if (t4.isEmpty()){
                    namestreem.setError(getString(R.string.namestreet));
                    return;
                }  if (t7.isEmpty()){
                    addrass.setError(" ");
                    return;
                }  if (t8.isEmpty()){
                    turn_number.setError(" ");
                    return;
                }  if (t9.isEmpty()){
                    slit_number.setError(" ");
                    return;
                }if (t10.isEmpty()){
                    Add_information.setError(" ");
                    return;
                }
                else{
                    Toast.makeText(getContext(), ""+loc, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), ""+nameHome, Toast.LENGTH_SHORT).show();
                    DataSendaddLocation location = new DataSendaddLocation();
                    location.setBuilding_type(nameHome);
                    location.setLocation_type(loc);
                    location.setBuilding_number(t7);
                    location.setApartment_number(t9);
                    location.setTurn_number(t8);
                    location.setLocation_details(t10);
                    location.setStreet_name(t4);
                    location.setCity_id(String.valueOf(sendidcity));
                    model.AddLocation("Bearer"+Token,"application/json",location);
                }

            }
        });
        model.addLocation.observe(getViewLifecycleOwner(), new Observer<DataAddLocation>() {
            @Override
            public void onChanged(DataAddLocation dataAddLocation) {
                if (dataAddLocation.getStatus()==1){
                    Toast.makeText(getContext(), ""+dataAddLocation.getMessage(), Toast.LENGTH_SHORT).show();
                    SendDataInCard inCard = new SendDataInCard();
                    inCard.setCompany_id(CompanyId);
                    inCard.setUser_addresses_id(String.valueOf(dataAddLocation.getData().getId()));
                    model.InCard("Bearer"+Token,"application/json",inCard);
                    CreatLocaton.dismiss();
                }
            }
        });
        model.AllCity("application/json");
        model.city.observe(getViewLifecycleOwner(), new Observer<ModelDataAllCity>() {
            @Override
            public void onChanged(ModelDataAllCity modelDataAllCity) {
                listCityname.clear();
                listCityId.clear();
                listCityname.add(getString(R.string.chosecity));
                listCityId.add(0);
                for (int i=0;i<modelDataAllCity.getData().size();i++){
                    listCityname.add(modelDataAllCity.getData().get(i).getName_ar());
                    listCityId.add(modelDataAllCity.getData().get(i).getId());
                }
                ArrayAdapter<String> City = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,listCityname);
                Cityname.setAdapter(City);
            }
        });
        Cityname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sendidcity=listCityId.get(position);
                Toast.makeText(getContext(), ""+sendidcity, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Window window = CreatLocaton.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        CreatLocaton.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        CreatLocaton.create();
        CreatLocaton.show();
    }
}