package com.example.pizzaria.api;

import com.example.pizzaria.model.CEP;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CEPService {
    @GET("03589001/json/")
    Call<CEP> recuperarCEP();
}
