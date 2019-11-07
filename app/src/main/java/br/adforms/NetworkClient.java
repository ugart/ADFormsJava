package br.adforms;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

class NetworkClient {

//    private final static String BASE_URL = "http://192.168.203.113:3000/"; //local
    private final static String BASE_URL = "https://adforms.herokuapp.com/"; //heroku

    public interface WebServiceAPI {

        @POST("/api/usuarios")
        Call<Usuario> postCadastro(@Body Usuario usuario);

    }

    static WebServiceAPI getNetworkClient() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient
                        .addNetworkInterceptor(new StethoInterceptor())
                        .build())
                .build();

        return retrofit.create(WebServiceAPI.class);
    }

}