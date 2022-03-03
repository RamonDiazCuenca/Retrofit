package com.rdc.retrofit.retrofit.Interfaz;

/* A la interfaz le vamos a indicar,
que método vamos a usar para hacer una acción expecifica en esa página,
en este caso, consumir datos*/

import com.rdc.retrofit.retrofit.Modelo.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    //Método encargado de obtener toda la información de la página
    /* Está lista va a ser de tipo Posts, porque la lista necesita traer información que contenga
    este tipo de datos userId, id, title....*/

    // https://jsonplaceholder.typicode.com/posts
    @GET("posts")
    Call<List<Posts>> getPosts();
}
