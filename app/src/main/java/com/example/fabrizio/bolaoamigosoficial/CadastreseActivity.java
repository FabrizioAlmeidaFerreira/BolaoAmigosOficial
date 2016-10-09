package com.example.fabrizio.bolaoamigosoficial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fabrizio.bolaoamigosoficial.users.UserSenhaCheck;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastreseActivity extends AppCompatActivity {

    EditText etLoginUsuario,etSenhaUsuario;
    Button btnEnviaCadastro;
    private String Login,Senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrese);

        etLoginUsuario = (EditText) findViewById(R.id.etLoginUsuario);
        etSenhaUsuario = (EditText) findViewById(R.id.etSenhaUsuario);

        btnEnviaCadastro = (Button) findViewById(R.id.btnEnviarCadastro);





        Retrofit retrofit = new Retrofit.Builder().baseUrl(cartolaService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        final cartolaService service = retrofit.create(cartolaService.class);

        final Intent intent = new Intent(CadastreseActivity.this, MainActivity.class);

        btnEnviaCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Login = etLoginUsuario.getText().toString();
                Senha = etSenhaUsuario.getText().toString();

                if( Login.length() > 2 && Senha.length() > 2 && !Login.equals("") && !Senha.equals("") ) {
                    Call<UserSenhaCheck> userSenhaCheckCall = service.cadastresse("enviado", Senha, Login);

                    userSenhaCheckCall.enqueue(new Callback<UserSenhaCheck>() {
                        @Override
                        public void onResponse(Call<UserSenhaCheck> call, Response<UserSenhaCheck> response) {

                            UserSenhaCheck userSenhaCheck = response.body();

                            etLoginUsuario.setHint(userSenhaCheck.user);

                            if (userSenhaCheck.user.equals("Success")) {
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserSenhaCheck> call, Throwable t) {
                            Log.e("TAG", " Erro: " + t.getMessage());

                        }
                    });
                }else {
                    if( Login.length() <= 2  )  Toast.makeText(CadastreseActivity.this, "Ops seu login deve conter no minimo 4 letras", Toast.LENGTH_LONG).show();
                    else if( Senha.length() <= 2)Toast.makeText(CadastreseActivity.this, "Ops sua senha deve conter no minimo 4 letras", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
