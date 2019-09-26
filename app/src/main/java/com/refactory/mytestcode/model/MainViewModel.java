package com.refactory.mytestcode.model;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.refactory.mytestcode.api.ApiClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

public class MainViewModel extends ViewModel {
    private final ArrayList<Listusers> listItemUsers = new ArrayList<>();
    private MutableLiveData<ArrayList<Listusers>> listMutableLiveData = new MutableLiveData<>();

    public void getData(){
        GetEndpointUser getEndpointUser = ApiClient.getClient().create(GetEndpointUser.class);
        Call<ResponseBody> call = getEndpointUser.getUsers();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    JSONArray jsonArray = new JSONArray(result);

                    for (int i = 0; i< jsonArray.length(); i++){
                        JSONObject users = jsonArray.getJSONObject(i);
                        Listusers listusers = new Listusers(users);
                        listItemUsers.add(listusers);
                    }
                    listMutableLiveData.postValue(listItemUsers);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("XXX", "onFailure: "+t.getMessage());
            }
        });

    }
    public LiveData<ArrayList<Listusers>> getAllData(){
        return listMutableLiveData;
    }
    interface GetEndpointUser{
        @GET("users")
        Call<ResponseBody> getUsers ();
    }

}
