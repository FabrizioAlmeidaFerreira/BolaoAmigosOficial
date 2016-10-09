package com.example.fabrizio.bolaoamigosoficial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fabrizio.bolaoamigosoficial.users.UserSenhaCheck;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastreseActivity extends AppCompatActivity {

    EditText etLoginUsuario,etSenhaUsuario;
    TextView textView8,textView9,textView10;
    Button btnEnviaCadastro;
    ProgressBar mProgressBar;
    private String Login,Senha;

    protected boolean mbActive;
    protected static final int TIMER_RUNTIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrese);

        textView8 = (TextView) findViewById(R.id.textView8);
        textView9 = (TextView) findViewById(R.id.textView9);
        textView10 = (TextView) findViewById(R.id.textView10);

        etLoginUsuario = (EditText) findViewById(R.id.etLoginUsuario);
        etSenhaUsuario = (EditText) findViewById(R.id.etSenhaUsuario);

        btnEnviaCadastro = (Button) findViewById(R.id.btnEnviarCadastro);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.GONE);


        Retrofit retrofit = new Retrofit.Builder().baseUrl(cartolaService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        final cartolaService service = retrofit.create(cartolaService.class);

        final Intent intent = new Intent(CadastreseActivity.this, MainActivity.class);

        btnEnviaCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView8.setVisibility(View.GONE);
                textView9.setVisibility(View.GONE);
                textView10.setVisibility(View.GONE);

                Login = etLoginUsuario.getText().toString();
                etLoginUsuario.setVisibility(View.GONE);
                Senha = etSenhaUsuario.getText().toString();
                etSenhaUsuario.setVisibility(View.GONE);


                mProgressBar.setVisibility(View.VISIBLE);

                if( Login.length() > 2 && Senha.length() > 2 && !Login.equals("") && !Senha.equals("") ) {
                    Call<UserSenhaCheck> userSenhaCheckCall = service.cadastresse("enviado", Senha, Login);

                    userSenhaCheckCall.enqueue(new Callback<UserSenhaCheck>() {
                        @Override
                        public void onResponse(Call<UserSenhaCheck> call, Response<UserSenhaCheck> response) {

                            final UserSenhaCheck userSenhaCheck = response.body();

                            etLoginUsuario.setHint(userSenhaCheck.user);

                            final Thread Carregando = new Thread(){
                                @Override
                                public void run(){
                                    mbActive = true;
                                    try {
                                        int wited = 0;
                                        while (mbActive && (wited < TIMER_RUNTIME)){
                                            sleep(200);
                                            if (mbActive){
                                                wited += 200;
                                            }
                                        }

                                    }catch (Exception e){

                                    }finally {
                                        if (userSenhaCheck.user.equals("Success")) {
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                }
                            };
                            Carregando.start();



                        }

                        @Override
                        public void onFailure(Call<UserSenhaCheck> call, Throwable t) {
                            Log.e("TAG", " Erro: " + t.getMessage());

                        }
                    });
                }else {

                    if( Login.length() <= 2  )  Toast.makeText(CadastreseActivity.this, "Ops seu login deve conter no minimo 4 letras", Toast.LENGTH_LONG).show();
                    else if( Senha.length() <= 2)Toast.makeText(CadastreseActivity.this, "Ops sua senha deve conter no minimo 4 letras", Toast.LENGTH_LONG).show();
                    mProgressBar.setVisibility(View.GONE);
                    etLoginUsuario.setVisibility(View.VISIBLE);
                    etSenhaUsuario.setVisibility(View.VISIBLE);
                    textView8.setVisibility(View.VISIBLE);
                    textView9.setVisibility(View.VISIBLE);
                    textView10.setVisibility(View.VISIBLE);
                }
            }
        });

    }

}
