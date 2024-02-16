package com.example.pizzaria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pizzaria.api.CEPService;
import com.example.pizzaria.model.CEP;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private EditText tetResulado,tetResulado1,tetResulado2,tetResulado3,tetResulado4;
    private Button btnRecuoerarCEP;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tetResulado = findViewById(R.id.tetResulado);
        tetResulado1 = findViewById(R.id.tetResulado1);
        tetResulado2 = findViewById(R.id.tetResulado2);
        tetResulado3 = findViewById(R.id.tetResulado3);
        tetResulado4 = findViewById(R.id.tetResulado4);

        btnRecuoerarCEP = findViewById(R.id.btnRecuoerarCEP);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        btnRecuoerarCEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarCEPRetrofit();

            }
        });


    }
    private void recuperarCEPRetrofit(){

        CEPService cepService = retrofit.create(CEPService.class);
        Call<CEP> call = cepService.recuperarCEP();

        call.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                if(response.isSuccessful()){
                    CEP cep = response.body();
                    tetResulado.setText(cep.getLogradouro());
                    tetResulado1.setText(cep.getBairro());
                    tetResulado2.setText(cep.getLocalidade());
                    tetResulado3.setText(cep.getUf());
                    tetResulado4.setText(cep.getCep());
                }
            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {

            }
        });
    }
}