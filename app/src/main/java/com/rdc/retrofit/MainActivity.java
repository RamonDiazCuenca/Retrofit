package com.rdc.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.rdc.retrofit.databinding.ActivityMainBinding;
import com.rdc.retrofit.retrofit.Interfaz.JsonPlaceHolderApi;
import com.rdc.retrofit.retrofit.Modelo.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getPosts();

    }

    private void getPosts(){
        // Empezamos a traer los datos

        // Creamos instancia de Retrofit,
        // En la baseUrl le pones la url NO completa,
        // porque por medio de la interfaz, sabe donde buscar los datos
        // NO OLVIDAR poner la última (/) porque sino no alcanza los datos
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Llamamos a la interfaz
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Posts>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Posts>>() {

            // Vamos a manejar la respuesta que nos da el servidor con toda la información de la página con el json
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {

                if(!response.isSuccessful()){ // Sí el código de respuesta no es exitoso
                    binding.tvjson.setText("Código: " +response.code());
                    return;// retorna, pregunta de vuelta
                }

                // Traer los datos y mostrarlos en el TextView
                List<Posts> postsList = response.body();// Aqui tenemos la respuesta del servidor, que son los datos

                for(Posts posts: postsList){
                    String contenido = "";
                    contenido += "userId:"+posts.getUserId()+"\n";
                    contenido += "id:"+posts.getId()+"\n";
                    contenido += "title:"+posts.getTitle()+"\n";
                    contenido += "body:"+posts.getBody()+"\n\n";
                    binding.tvjson.append(contenido);
                }

            }
            // Manejar un tipo de error sí no llego a traer bien la información
            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

                binding.tvjson.setText(t.getMessage());
            }
        });
    }
}