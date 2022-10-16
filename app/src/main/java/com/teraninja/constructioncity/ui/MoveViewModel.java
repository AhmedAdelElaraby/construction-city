package com.teraninja.constructioncity.ui;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.teraninja.constructioncity.DataClinte.RetrofitAPI;
import com.teraninja.constructioncity.model.DataAddLocation;
import com.teraninja.constructioncity.model.DataAllPremoCode;
import com.teraninja.constructioncity.model.DataBrands;
import com.teraninja.constructioncity.model.DataChangename;
import com.teraninja.constructioncity.model.DataChengeImage;
import com.teraninja.constructioncity.model.DataCompaniesInStreet;
import com.teraninja.constructioncity.model.DataDetilseOrder;
import com.teraninja.constructioncity.model.DataDetilseProdacted;
import com.teraninja.constructioncity.model.DataLogOut;
import com.teraninja.constructioncity.model.DataLogin;
import com.teraninja.constructioncity.model.DataModelAllCompanyInCard;
import com.teraninja.constructioncity.model.DataModelCard;
import com.teraninja.constructioncity.model.DataModelInCard;
import com.teraninja.constructioncity.model.DataModelOreder;
import com.teraninja.constructioncity.model.DataModelRateOrder;
import com.teraninja.constructioncity.model.DataModelocation;
import com.teraninja.constructioncity.model.DataOllStreet;
import com.teraninja.constructioncity.model.DataOrders;
import com.teraninja.constructioncity.model.DataProdacted;
import com.teraninja.constructioncity.model.DataRegster;
import com.teraninja.constructioncity.model.DataRenendcode;
import com.teraninja.constructioncity.model.DataRqusteCode;
import com.teraninja.constructioncity.model.DataSearchToProdacted;
import com.teraninja.constructioncity.model.DataSendOrder;
import com.teraninja.constructioncity.model.DataSendRateOreders;
import com.teraninja.constructioncity.model.DataSendResendCode;
import com.teraninja.constructioncity.model.DataSendaddLocation;
import com.teraninja.constructioncity.model.DataServise;
import com.teraninja.constructioncity.model.DataUpdatePhone;
import com.teraninja.constructioncity.model.ModelDataAllCity;
import com.teraninja.constructioncity.model.ModelSendImage;
import com.teraninja.constructioncity.model.SendChangeName;
import com.teraninja.constructioncity.model.SendDataBrands;
import com.teraninja.constructioncity.model.SendDataCode;
import com.teraninja.constructioncity.model.SendDataForCard;
import com.teraninja.constructioncity.model.SendDataInCard;
import com.teraninja.constructioncity.model.SendDataLogin;
import com.teraninja.constructioncity.model.SendDataOllStreet;
import com.teraninja.constructioncity.model.SendDataProdacted;
import com.teraninja.constructioncity.model.SendDataRegister;
import com.teraninja.constructioncity.model.SendDataUpdatePassword;
import com.teraninja.constructioncity.model.SendUpdateNumberPhone;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class MoveViewModel extends ViewModel {

    public MutableLiveData<DataLogin> login= new MutableLiveData();
    public MutableLiveData<DataRqusteCode> SendCode= new MutableLiveData();
    public MutableLiveData<DataRenendcode> resendcode= new MutableLiveData();
    public MutableLiveData<DataRegster> regster= new MutableLiveData();
    public MutableLiveData<DataOllStreet> OllStreet= new MutableLiveData();
    public MutableLiveData<DataCompaniesInStreet> companies = new MutableLiveData<>();
    public MutableLiveData<DataServise> serviseInMarket= new MutableLiveData<>();
    public MutableLiveData<DataBrands> Brands = new MutableLiveData<>();
    public MutableLiveData<DataProdacted> Prodacted = new MutableLiveData<>();
    public MutableLiveData<DataDetilseProdacted> detilseProdacted = new MutableLiveData<>();
    public MutableLiveData<DataModelCard> ForCard = new MutableLiveData<>();
    public MutableLiveData<DataSearchToProdacted> search= new MutableLiveData<>();
    public MutableLiveData<DataLogOut> logOut= new MutableLiveData<>();
    public MutableLiveData<DataChangename> ChangeName= new MutableLiveData<>();
    public MutableLiveData<DataOrders> Ordres= new MutableLiveData<>();
    public MutableLiveData<DataOrders> lastOrdres= new MutableLiveData<>();
    public MutableLiveData<DataModelocation> allLocation= new MutableLiveData<>();
    public MutableLiveData<ModelDataAllCity> city = new MutableLiveData<>();
    public MutableLiveData<DataModelInCard> InCArd= new MutableLiveData<>();
    public MutableLiveData<DataModelAllCompanyInCard> AllCompanyInCard = new MutableLiveData<>();
    public MutableLiveData<DataModelRateOrder> rateorder = new MutableLiveData<>();
    public MutableLiveData<DataDetilseOrder> dataDetilseOrder = new MutableLiveData<>();
    public MutableLiveData<DataAllPremoCode> premoCode = new MutableLiveData<>();
    public MutableLiveData<DataModelCard> RemoveProdacted = new MutableLiveData<>();
    public MutableLiveData<DataChengeImage> UpdateImage = new MutableLiveData<>();
    public MutableLiveData<DataUpdatePhone> updatePhone = new MutableLiveData<>();
    public MutableLiveData<DataUpdatePhone> UpdatePassword = new MutableLiveData<>();
    public MutableLiveData<DataModelOreder> CreateOrder= new MutableLiveData<>();
    public MutableLiveData<DataAddLocation> addLocation = new MutableLiveData<>();
    public void login(String Accept, String lang, SendDataLogin dataLogin){
        Observable observable = RetrofitAPI.getInstans().getLogin(Accept,lang,dataLogin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                login.setValue((DataLogin) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataLogin data = new DataLogin(object.getInt("status"), object.getString("message"));
                        login.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void SendCode(String Accept, String lang, SendDataCode dataCode){
        Observable observable = RetrofitAPI.getInstans().SendCode(Accept,lang,dataCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                SendCode.setValue((DataRqusteCode) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataRqusteCode data = new DataRqusteCode(object.getInt("status"), object.getString("message"));
                        SendCode.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void Resendcode(String Accept, String lang, DataSendResendCode dataCode){
        Observable observable = RetrofitAPI.getInstans().Resendcode(Accept,lang,dataCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                resendcode.setValue((DataRenendcode) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataRenendcode data = new DataRenendcode(object.getInt("status"), object.getInt("message"));
                        resendcode.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void Register(String Accept, String lang, SendDataRegister dataRegister){
        Observable observable = RetrofitAPI.getInstans().Register(Accept,lang,dataRegister)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                regster.setValue((DataRegster) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataRegster data = new DataRegster(object.getInt("status"), object.getString("message"));
                        regster.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void OllStreet(String Accept, String lang, SendDataOllStreet ollStreet){
        Observable observable = RetrofitAPI.getInstans().OllStreet(Accept,lang,ollStreet)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                OllStreet.setValue((DataOllStreet) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataOllStreet data = new DataOllStreet(object.getInt("status"), object.getString("message"));
                        OllStreet.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void getcompanies(String Accept, String lang,int id){

        Observable observable = RetrofitAPI.getInstans().CompaniesInStreet(Accept,lang,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                companies.setValue((DataCompaniesInStreet) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataCompaniesInStreet data = new DataCompaniesInStreet(object.getInt("status"), object.getString("message"));
                        companies.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void ServiseInMarket(String Accept, String lang,int id){

        Observable observable = RetrofitAPI.getInstans().ServiseInMarket(Accept,lang,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                serviseInMarket.setValue((DataServise) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataServise data = new DataServise(object.getInt("status"), object.getString("message"));
                        serviseInMarket.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void Brands(String Accept, String lang, SendDataBrands brands){

        Observable observable = RetrofitAPI.getInstans().Brands(Accept,lang,brands)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                Brands.setValue((DataBrands) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataBrands data = new DataBrands(object.getInt("status"), object.getString("message"));
                        Brands.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void Prodacted(String Accept, String lang,SendDataProdacted prodacted){

        Observable observable = RetrofitAPI.getInstans().Prodacted(Accept,lang,prodacted)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                Prodacted.setValue((DataProdacted) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataProdacted data = new DataProdacted(object.getInt("status"), object.getString("message"));
                        Prodacted.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void DetilseProdacted(String Accept, String lang,int Idprodacted){

        Observable observable = RetrofitAPI.getInstans().DetilseProdacted(Accept,lang,Idprodacted)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                detilseProdacted.setValue((DataDetilseProdacted) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataDetilseProdacted data = new DataDetilseProdacted(object.getInt("status"), object.getString("message"));
                        detilseProdacted.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void ForCard(String Token, String Accept, SendDataForCard forCard){

        Observable observable = RetrofitAPI.getInstans().ForCard(Token,Accept,forCard)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                ForCard.setValue((DataModelCard) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataModelCard data = new DataModelCard(object.getInt("status"), object.getString("message"));
                        ForCard.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void SearchProdacted(String Accept, String lang){

        Observable observable = RetrofitAPI.getInstans().SearchProdacted(Accept,lang)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                search.setValue((DataSearchToProdacted) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataSearchToProdacted data = new DataSearchToProdacted(object.getInt("status"), object.getString("message"));
                        search.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void LogOut(String Token, String Accept){
        Observable observable = RetrofitAPI.getInstans().LogOut(Token,Accept)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                logOut.setValue((DataLogOut) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataLogOut data = new DataLogOut(object.getInt("status"), object.getString("message"));
                        logOut.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void ChangeName(String Token, String Accept, String lang, SendChangeName changeName){
        Observable observable = RetrofitAPI.getInstans().ChangeName1(Token,Accept,lang,changeName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                ChangeName.setValue((DataChangename) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataChangename data = new DataChangename(object.getInt("status"), object.getString("message"));
                        ChangeName.setValue(data);
                        Log.i("ahmed", ""+data.getMessage());
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void Orders(String Token, String Accept){
        Observable observable = RetrofitAPI.getInstans().Orders(Token,Accept)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                Ordres.setValue((DataOrders) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataOrders data = new DataOrders(object.getInt("status"), object.getString("message"));
                        Ordres.setValue(data);
                        Log.i("ahmed", ""+data.getMessage());
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void AllCity( String Accept){
        Observable observable = RetrofitAPI.getInstans().AllCity(Accept)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                city.setValue((ModelDataAllCity) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        ModelDataAllCity data = new ModelDataAllCity(object.getInt("status"));
                        city.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void LastOrders(String Token, String Accept){
        Observable observable = RetrofitAPI.getInstans().lastOrder(Token,Accept)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                lastOrdres.setValue((DataOrders) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataOrders data = new DataOrders(object.getInt("status"), object.getString("message"));
                        lastOrdres.setValue(data);
                        Log.i("ahmed", ""+data.getMessage());
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void allLocation(String Token, String Accept){
        Observable observable = RetrofitAPI.getInstans().allLocation(Token,Accept)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                allLocation.setValue((DataModelocation) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataModelocation data = new DataModelocation(object.getInt("status"));
                        allLocation.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void InCard(String Token, String Accept, SendDataInCard inCard){
        Observable observable = RetrofitAPI.getInstans().InCard(Token,Accept,inCard)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                InCArd.setValue((DataModelInCard) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataModelInCard data = new DataModelInCard(object.getInt("status"),object.getString("message"));
                        InCArd.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void AllCompanyInCard(String Token, String Accept){
        Observable observable = RetrofitAPI.getInstans().AllCompanyInCard(Token,Accept)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(@NonNull Object o) {

                AllCompanyInCard.setValue((DataModelAllCompanyInCard) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed123", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataModelAllCompanyInCard data = new DataModelAllCompanyInCard(object.getInt("status"),object.getString("message"));
                        AllCompanyInCard.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void RateOrders(String Token, String Accept, DataSendRateOreders rateOreders){
        Observable observable = RetrofitAPI.getInstans().RateOreders(Token,Accept,rateOreders)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                rateorder.setValue((DataModelRateOrder) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataModelRateOrder data = new DataModelRateOrder(object.getInt("status"),object.getString("message"));
                        rateorder.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void DetilseOrder(String Token, String Accept, DataSendRateOreders rateOreders){
        Observable observable = RetrofitAPI.getInstans().DetildeOrder(Token,Accept,rateOreders)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                dataDetilseOrder.setValue((DataDetilseOrder) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataDetilseOrder data = new DataDetilseOrder(object.getInt("status"),object.getString("message"));
                        dataDetilseOrder.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void Allcode(String Token, String Accept){
        Observable observable = RetrofitAPI.getInstans().Allcode(Token,Accept)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                premoCode.setValue((DataAllPremoCode) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataAllPremoCode data = new DataAllPremoCode();
                        premoCode.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void RemoveProdacted(String Token, String Accept, SendDataForCard forCard){

        Observable observable = RetrofitAPI.getInstans().removeprodact(Token,Accept,forCard)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                RemoveProdacted.setValue((DataModelCard) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataModelCard data = new DataModelCard(object.getInt("status"), object.getString("message"));
                        RemoveProdacted.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void UpdateImage(String Token, String Accept,String lang, RequestBody sendImage){

        Observable observable = RetrofitAPI.getInstans().UpdateImage(Token,Accept,lang,sendImage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                UpdateImage.setValue((DataChengeImage) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataChengeImage data = new DataChengeImage(object.getInt("status"), object.getString("message"));
                        UpdateImage.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void UpdatenumberPhone(String Token, String Accept,String lang, SendUpdateNumberPhone phone){

        Observable observable = RetrofitAPI.getInstans().UpdatePhone(Token,Accept,lang,phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                updatePhone.setValue((DataUpdatePhone) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataUpdatePhone data = new DataUpdatePhone(object.getInt("status"), object.getString("message"));
                        updatePhone.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void UpdatePassword(String Token, String Accept,String lang, SendDataUpdatePassword updatePassword){

        Observable observable = RetrofitAPI.getInstans().UpdatePassword(Token,Accept,lang,updatePassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                UpdatePassword.setValue((DataUpdatePhone) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataUpdatePhone data = new DataUpdatePhone(object.getInt("status"), object.getString("message"));
                        UpdatePassword.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void CreateOrder(String Token, String Accept , DataSendOrder sendOrder){

        Observable observable = RetrofitAPI.getInstans().CreateOrder(Token,Accept,sendOrder)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                CreateOrder.setValue((DataModelOreder) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataModelOreder data = new DataModelOreder(object.getInt("status"), object.getString("message"));
                        CreateOrder.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void AddLocation(String Token, String Accept , DataSendaddLocation sendaddLocation){

        Observable observable = RetrofitAPI.getInstans().ADDLocation(Token,Accept,sendaddLocation)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                addLocation.setValue((DataAddLocation) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataAddLocation data = new DataAddLocation(object.getInt("status"), object.getString("message"));
                        addLocation.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }

}
