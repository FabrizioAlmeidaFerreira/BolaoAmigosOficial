package com.example.fabrizio.bolaoamigosoficial;

import com.example.fabrizio.bolaoamigosoficial.models.CartolaCatalog;
import com.example.fabrizio.bolaoamigosoficial.users.UserSenhaCheck;
import com.example.fabrizio.bolaoamigosoficial.users.UsersCatalogo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Fabrizio on 28/09/2016.
 */

public interface cartolaService {

    public static final String BASE_URL = "{Sua API}";

    @GET("api_cartola_refeito.php")
    Call<CartolaCatalog> listPartidas();

    /*@GET("api-users")
    Call<UsersCatalogo> listaDadosUser(@Query("nome") String nome, @Query("rodada") String rodada);*/

    @GET("api_verifica_login_user.php")
    Call<UserSenhaCheck> verificaUsuarioSenha(@Query("verifica") String cadastresse,@Query("usuario") String usuario, @Query("senha") String senha);

    @GET("api_verifica_login_user.php")
    Call<UserSenhaCheck> cadastresse(@Query("cadastrarse") String cadastresse, @Query("senha") String senha , @Query("nome") String nome);



}
