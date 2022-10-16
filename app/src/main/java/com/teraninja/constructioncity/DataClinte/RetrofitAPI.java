package com.teraninja.constructioncity.DataClinte;

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
import com.teraninja.constructioncity.ui.main.AdapterByCategory;


import io.reactivex.Observable;
import okhttp3.OkHttpClient;

import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitAPI {
    IntefaceApi interFaceAPI;
    private static final String BASE_URL="https://city.teraninjadev.com/";
    public static RetrofitAPI instans;
    public RetrofitAPI() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        interFaceAPI=retrofit.create(IntefaceApi.class);
    }
    public static RetrofitAPI getInstans() {
        if (null==instans){
            instans=new RetrofitAPI();}
        return instans;}
    public Observable<DataLogin> getLogin(String Accept,String lang,SendDataLogin dataLogin){
        return interFaceAPI.getLogin(Accept,lang,dataLogin);}
    public Observable<DataRqusteCode> SendCode(String Accept, String lang, SendDataCode dataCode){
        return interFaceAPI.SendCode(Accept,lang,dataCode);}
    public Observable<DataRenendcode> Resendcode(String Accept, String lang, DataSendResendCode dataCode){
        return interFaceAPI.Resendcode(Accept,lang,dataCode);}
    public Observable<DataRegster> Register(String Accept, String lang, SendDataRegister dataRegister){
        return interFaceAPI.Register(Accept,lang,dataRegister);}
    public Observable<DataOllStreet> OllStreet(String Accept, String lang, SendDataOllStreet ollStreet){
        return interFaceAPI.OllStreet(Accept,lang,ollStreet);}
    public Observable<DataCompaniesInStreet> CompaniesInStreet(String Accept, String lang,int id){
        return interFaceAPI.CompaniesInStreet(Accept,lang,id);}
    public Observable<DataServise> ServiseInMarket(String Accept, String lang, int id){
        return interFaceAPI.ServiseInMarket(Accept,lang,id);}
    public Observable<DataBrands> Brands(String Accept, String lang, SendDataBrands brands){
        return interFaceAPI.brands(Accept,lang,brands);}
    public Observable<DataProdacted> Prodacted(String Accept, String lang, SendDataProdacted brandsProdacted){
        return interFaceAPI.Prodacted(Accept,lang,brandsProdacted);}
    public Observable<DataDetilseProdacted> DetilseProdacted(String Accept, String lang, int idProdacted){
        return interFaceAPI.DetilsePRodacted(Accept,lang,idProdacted);}
    public Observable<DataModelCard> ForCard(String Token, String Accept, SendDataForCard forCard){
        return interFaceAPI.addtocard(Token,Accept,forCard);}
    public Observable<DataSearchToProdacted> SearchProdacted(String Accept, String lang){
        return interFaceAPI.SearchProdacted(Accept,lang);}
    public Observable<DataLogOut> LogOut(String Token, String Accept){
        return interFaceAPI.LogOut(Token,Accept);}
    public Observable<DataChangename> ChangeName1(String Token, String Accept, String lang, SendChangeName changeName1){
        return interFaceAPI.ChangeName(Token,Accept,lang,changeName1);}
    public Observable<DataOrders> Orders(String Token, String Accept){
        return interFaceAPI.Orders(Token,Accept);}
    public Observable<ModelDataAllCity> AllCity(String Accept){
        return interFaceAPI.AllCity(Accept);}
    public Observable<DataOrders> lastOrder(String Token, String Accept){
        return interFaceAPI.lastOrder(Token,Accept);}
    public Observable<DataModelocation> allLocation(String Token, String Accept){
        return interFaceAPI.allLocation(Token,Accept);}
    public Observable<DataModelInCard> InCard(String Token, String Accept, SendDataInCard inCard){
        return interFaceAPI.InCard(Token,Accept,inCard);}
    public Observable<DataModelAllCompanyInCard> AllCompanyInCard(String Token, String Accept){
        return interFaceAPI.AllCompanyInCard(Token,Accept);}
    public Observable<DataModelRateOrder> RateOreders(String Token, String Accept, DataSendRateOreders rateOreders){
        return interFaceAPI.rateOrders(Token,Accept,rateOreders);}
    public Observable<DataDetilseOrder> DetildeOrder(String Token, String Accept, DataSendRateOreders rateOreders){
        return interFaceAPI.DetilseOrder(Token,Accept,rateOreders);}
    public Observable<DataAllPremoCode> Allcode(String Token, String Accept){
        return interFaceAPI.getAllcode(Token,Accept);}
    public Observable<DataModelCard> removeprodact(String Token, String Accept, SendDataForCard forCard) {
        return interFaceAPI.removeptodacte(Token,Accept,forCard);}
    public Observable<DataChengeImage> UpdateImage(String Token, String Accept,String lang, RequestBody sendImage) {
        return interFaceAPI.UpdatePhoto(Token,Accept,lang,sendImage);}
    public Observable<DataUpdatePhone> UpdatePhone(String Token, String Accept,String lang, SendUpdateNumberPhone numberPhone){
        return interFaceAPI.UpdatePhone(Token,Accept,lang,numberPhone);}
    public Observable<DataUpdatePhone> UpdatePassword(String Token, String Accept,String lang, SendDataUpdatePassword updatePassword){
        return interFaceAPI.UpdatePassword(Token,Accept,lang,updatePassword);}
    public Observable<DataModelOreder> CreateOrder(String Token, String Accept, DataSendOrder sendOrder){
        return interFaceAPI.CreateOrder(Token,Accept,sendOrder);}
    public Observable<DataAddLocation> ADDLocation(String Token, String Accept, DataSendaddLocation sendaddLocation){
        return interFaceAPI.AddLocation(Token,Accept,sendaddLocation);}
}


