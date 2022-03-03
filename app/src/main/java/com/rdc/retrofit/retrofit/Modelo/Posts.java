package com.rdc.retrofit.retrofit.Modelo;

/*Este modelo va a tener todos los atributos que tiene un objeto Json,
  en este caso los mismos que tiene el archivo Json
  que está situado en está página: https://jsonplaceholder.typicode.com/posts*/
public class Posts {

    private int userId;
    private int id;
    private String title;
    private String body;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
