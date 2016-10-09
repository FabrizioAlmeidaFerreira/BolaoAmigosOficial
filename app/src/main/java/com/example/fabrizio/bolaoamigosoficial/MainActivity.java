package com.example.fabrizio.bolaoamigosoficial;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fabrizio.bolaoamigosoficial.users.Config;
import com.example.fabrizio.bolaoamigosoficial.users.UserSenhaCheck;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public TextView tvUserCheck;
    public EditText etNomeUsuario;
    public EditText etSenhaUsuario;
    public Button btnLogin,btnCadastrese;

    // variaveis shared preferences para salvar nome e senha usuario
    private boolean loggedIn = false;
    private SharedPreferences.Editor editor;

    // TAG para uso do log de console
    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        // variaveis shared preferences para salvar nome e senha usuario
        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvUserCheck = (TextView) findViewById(R.id.tvUserCheck);
        etNomeUsuario = (EditText) findViewById(R.id.etNomeUsuario);
        etSenhaUsuario = (EditText) findViewById(R.id.etSenhaUsuario);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnCadastrese = (Button) findViewById(R.id.btnCadastrese);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( !String.valueOf(etNomeUsuario.getText()).equals("") && !String.valueOf(etSenhaUsuario.getText()).equals("")
                        && etNomeUsuario.getText().length() > 0 && etSenhaUsuario.length() > 0 ) {

                   // FazerLogin(String.valueOf(etNomeUsuario.getText()), String.valueOf(etSenhaUsuario.getText()));
                    // chama a funcao que faz login e salva no shared preferences a senha e nome do usuario
                    login();
                    tvUserCheck.setText(R.string.conectando);
                }else {
                    if( etNomeUsuario.getText().length() <= 0  )  Toast.makeText(MainActivity.this, "Ops seu login ou senha deve estar errado :(", Toast.LENGTH_LONG).show();
                    else if( etSenhaUsuario.length() <= 0 )Toast.makeText(MainActivity.this, "Ops seu login ou senha deve estar errado :(", Toast.LENGTH_LONG).show();
                }
            }
        });


        //Botao para cadastrarse se nao tem login
        btnCadastrese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCadastrese = new Intent(MainActivity.this, CadastreseActivity.class);
                startActivity(intentCadastrese);
                finish();
            }
        });



    }

    // onResume() quando resume o app verifica se o user tem senha e nome salvo no shared preferences
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);

        if(loggedIn){
            // SE ESTIVER LOGADO ENTÃO AO ENTRAR NA APLICAÇÃO VAI PARA TELA SEGUINTE
            Intent intent = new Intent(MainActivity.this, UserPalpitesActivity.class);
            startActivity(intent);
        }
    }


    private void login(){
        final String nome = etNomeUsuario.getText().toString().trim();
        final String senha = etSenhaUsuario.getText().toString().trim();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(cartolaService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        cartolaService service = retrofit.create(cartolaService.class);

        Call<UserSenhaCheck> userSenhaCheckCall = service.verificaUsuarioSenha("Setado",nome,senha);

        userSenhaCheckCall.enqueue(new Callback<UserSenhaCheck>() {
            @Override
            public void onResponse(Call<UserSenhaCheck> call, Response<UserSenhaCheck> response) {
                if(!response.isSuccessful()){
                    Log.i(TAG,"Erro: "+response.code());
                }else {
                    // insere os dados do user no shared preferences
                    editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                    editor.putString(Config.USUARIO_SHARED_PREF, nome);
                    editor.commit();

                    UserSenhaCheck userSenhaCheck = response.body();
                    tvUserCheck.setText(userSenhaCheck.user);
                    if(userSenhaCheck.user.equals("Success")) {
                        //LoginOk();
                        Intent intent = new Intent(MainActivity.this, UserPalpitesActivity.class);
                        //SE O LOGIN E SENHA FOR IGUAL AO QUE CONSTA NA TABELA DO BANCO DE DADOS ENTÃO VAI PARA OUTRA TELA
                        startActivity(intent);

                    }else {
                        Toast.makeText(MainActivity.this, "Ops seu login ou senha deve estar errado :(", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserSenhaCheck> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Ops! Parece que você esta sem conexão com a internet :( \n ou servidores fora do ar !!!", Toast.LENGTH_LONG).show();

            }
        });


    }


}
