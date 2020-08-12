package com.example.dangdinhtien_ps10163_asm.Callback;


public interface VolleyCallback<T>{
    void onReponse(T reponse);
    void onError(String err);
}
