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

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IntefaceApi {
    @POST("api/login")
    Observable<DataLogin> getLogin(@Header("Accept") String Accept, @Header("Accept-Language") String Language, @Body SendDataLogin dataLogin);
    @POST("api/verify/code")
    Observable<DataRqusteCode> SendCode(@Header("Accept") String Accept, @Header("Accept-Language") String Language, @Body SendDataCode dataCode);
    @POST("api/resend/code")
    Observable<DataRenendcode> Resendcode(@Header("Accept") String Accept, @Header("Accept-Language") String Language, @Body DataSendResendCode dataCode);
    @POST("api/register")
    Observable<DataRegster> Register(@Header("Accept") String Accept, @Header("Accept-Language") String Language ,@Body SendDataRegister Register);
    @POST("api/all/street")
    Observable<DataOllStreet> OllStreet(@Header("Accept") String Accept, @Header("Accept-Language") String Language,@Body SendDataOllStreet ollStreet);
    @GET("api/street/market/{street_id}")
    Observable<DataCompaniesInStreet> CompaniesInStreet(@Header("Accept") String Accept, @Header("Accept-Language") String Accept_Language, @Path("street_id") int id);
    @GET("api/service/market/{company_id}")
    Observable<DataServise> ServiseInMarket(@Header("Accept") String Accept, @Header("Accept-Language") String Accept_Language, @Path("company_id") int company_id);
    @POST("api/brands/service")
    Observable<DataBrands> brands(@Header("Accept") String Accept, @Header("Accept-Language") String Accept_Language, @Body SendDataBrands brands);
    @POST("api/products/brand")
    Observable<DataProdacted> Prodacted(@Header("Accept") String Accept, @Header("Accept-Language") String Accept_Language, @Body SendDataProdacted brands);
    @GET("api/product/{product_id}")
    Observable<DataDetilseProdacted> DetilsePRodacted(@Header("Accept") String Accept, @Header("Accept-Language") String Accept_Language, @Path("product_id") int product_id);
    @POST("api/add/cart")
    Observable<DataModelCard> addtocard(@Header("Authorization") String Token,@Header("Accept") String Accept,@Body SendDataForCard forCard);
    @GET("api/categories/products")
    Observable<DataSearchToProdacted> SearchProdacted(@Header("Accept") String Accept,@Header("Accept-Language") String lang);
    @POST("api/logout")
    Observable<DataLogOut> LogOut(@Header("Authorization") String Token, @Header("Accept") String Accept);
    @POST("api/update/name")
    Observable<DataChangename> ChangeName(@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String lang,@Body SendChangeName changeName);
    @GET("api/current/orders")
    Observable<DataOrders> Orders(@Header("Authorization") String Token, @Header("Accept") String Accept);
    @GET("api/all/city")
    Observable<ModelDataAllCity> AllCity(@Header("Accept") String Accept);
    @GET("api/last/orders")
    Observable<DataOrders> lastOrder(@Header("Authorization") String Token, @Header("Accept") String Accept);
    @GET("api/all/address")
    Observable<DataModelocation> allLocation(@Header("Authorization") String Token, @Header("Accept") String Accept);
    @POST("api/product/company/cart")
    Observable<DataModelInCard> InCard(@Header("Authorization") String Token, @Header("Accept") String Accept,@Body SendDataInCard inCard);
    @GET("api/company/cart")
    Observable<DataModelAllCompanyInCard> AllCompanyInCard(@Header("Authorization") String Token, @Header("Accept") String Accept);
    @POST("api/rate/order")
    Observable<DataModelRateOrder> rateOrders(@Header("Authorization") String Token, @Header("Accept") String Accept, @Body DataSendRateOreders rateOreders);
    @POST("api/order/details")
    Observable<DataDetilseOrder> DetilseOrder(@Header("Authorization") String Token, @Header("Accept") String Accept, @Body DataSendRateOreders rateOreders);
    @GET("api/all/code")
    Observable<DataAllPremoCode> getAllcode(@Header("Authorization") String Token, @Header("Accept") String Accept);
    @POST("api/remove/cart")
    Observable<DataModelCard> removeptodacte(@Header("Authorization") String Token,@Header("Accept") String Accept,@Body SendDataForCard forCard);
    @POST("api/update/image")
    Observable<DataChengeImage> UpdatePhoto(@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String Accept_Language, @Body RequestBody sendImage);
    @POST("api/update/phone")
    Observable<DataUpdatePhone> UpdatePhone(@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String Accept_Language, @Body SendUpdateNumberPhone phone);
    @POST("api/update/password")
    Observable<DataUpdatePhone> UpdatePassword(@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String Accept_Language,@Body SendDataUpdatePassword updatePassword);
    @POST("api/create/order")
    Observable<DataModelOreder> CreateOrder(@Header("Authorization") String Token, @Header("Accept") String Accept, @Body DataSendOrder sendOrder);
    @POST("api/add/address")
    Observable<DataAddLocation> AddLocation(@Header("Authorization") String Token, @Header("Accept") String Accept, @Body DataSendaddLocation addLocation);
}
