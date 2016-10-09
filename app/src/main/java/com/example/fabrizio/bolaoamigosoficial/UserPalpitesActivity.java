package com.example.fabrizio.bolaoamigosoficial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fabrizio.bolaoamigosoficial.adapters.CartolaCatalogAdapter;
import com.example.fabrizio.bolaoamigosoficial.adapters.PalpitesUsers;
import com.example.fabrizio.bolaoamigosoficial.adapters.PalpitesUsersAdapter;
import com.example.fabrizio.bolaoamigosoficial.models.CartolaCatalog;
import com.example.fabrizio.bolaoamigosoficial.models.UsuariosDados;
import com.example.fabrizio.bolaoamigosoficial.users.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserPalpitesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    TextView[] tvTimeANome = new  TextView[10];
    TextView[] tvTimeBNome = new  TextView[10];
    TextView[] tvAproveitamentoTimeA = new TextView[10];
    TextView[] tvAproveitamentoTimeB = new TextView[10];

    TextView[] tvPlacarOficialA = new TextView[10];
    TextView[] tvPlacarOficialB = new TextView[10];

    TextView[] tvJogoStatus = new TextView[10];
    TextView[] tvDataPartida = new TextView[10];
    TextView[] tvLocalPartida = new TextView[10];

    TextView[] etPalpiteUserA = new TextView[10];
    EditText[] etPalpiteUserB = new EditText[10];

    ImageView[] ivTimeA = new  ImageView[10];
    ImageView[] ivTimeB = new  ImageView[10];

    ImageView[] ivTimeAUser = new  ImageView[10];
    ImageView[] ivTimeBUser = new  ImageView[10];


    /* as imagens dos clubes para incluir no array associativo
    int id[] = {R.drawable._262,R.drawable._263,R.drawable._264,R.drawable._266,R.drawable._275,R.drawable._276,R.drawable._277,R.drawable._282,R.drawable._283,R.drawable._284
            ,R.drawable._285,R.drawable._287,R.drawable._292,R.drawable._293,R.drawable._294,R.drawable._303,R.drawable._315,R.drawable._316,R.drawable._327,R.drawable._344};

    // uso do map para criar tipo um array asociativo*/
    Map<String,Integer> imagens = new HashMap<String, Integer>();

    // TAG para uso do log de console
    private static final String TAG = "TAG";

    // ========variaveis para uso retrofit============
    //public RecyclerView recyclerViewCartolaCatalog;
    public RecyclerView recyclerViewPalpitesUsers;

    public PalpitesUsersAdapter palpitesUsersAdapter;
    public CartolaCatalogAdapter cartolaCatalogAdapter;

    public ArrayList<PalpitesUsers> arrayListPalpitesUsers;
    //public ArrayList<CartolaJogos> arrayListCartolaCatalog;
    // =========variaveis para uso retrofit==============

    public ImageButton ibWhatsaapShare;

    public TextView tvRodadaAtual;
    public String PalpitesUserA[] = new String[10];
    public String PalpitesUserB[] = new String[10];
    public String TimesWhatsappShare;
    public String SubtituloToolBar;
    public String NomeFromSharedPreferences;
    public String SenhaFromSharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_palpites);

        // inicializa todos editText imageView e textView em array
        InicializaTextViewEditTextImageView();

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);



        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // resgata os dados do usuario salvo e joga em variaveis strings
        SharedPreferences sPrefNomeUsuario = getSharedPreferences(Config.SHARED_PREF_NAME,MODE_PRIVATE);
        NomeFromSharedPreferences = sPrefNomeUsuario.getString("usuario",null);
        SenhaFromSharedPreferences = sPrefNomeUsuario.getString("senha",null);



        tvRodadaAtual = (TextView) findViewById(R.id.tvRodadaAtual);
        ibWhatsaapShare = (ImageButton) findViewById(R.id.btnWhatsappShare);

        tvRodadaAtual.setText(SenhaFromSharedPreferences);

        // =========variaveis recyclerview=========================

        arrayListPalpitesUsers = new ArrayList<PalpitesUsers>();
        recyclerViewPalpitesUsers = (RecyclerView) findViewById(R.id.rv_palpites);
        recyclerViewPalpitesUsers.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManagerPalpites = new LinearLayoutManager(this);
        linearLayoutManagerPalpites.setOrientation(linearLayoutManagerPalpites.HORIZONTAL);
        recyclerViewPalpitesUsers.setLayoutManager(linearLayoutManagerPalpites);
        palpitesUsersAdapter = new PalpitesUsersAdapter(this,arrayListPalpitesUsers);
        recyclerViewPalpitesUsers.setAdapter(palpitesUsersAdapter);


        // =========variaveis recyclerview=========================

        // ======variaveis recyclerview CartolaJogos==============
        /*arrayListCartolaCatalog = new ArrayList<CartolaJogos>();
        recyclerViewCartolaCatalog = (RecyclerView) findViewById(R.id.rv_partidas);
        recyclerViewCartolaCatalog.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewCartolaCatalog.setLayoutManager(linearLayoutManager);
        cartolaCatalogAdapter = new CartolaCatalogAdapter(this, arrayListCartolaCatalog);
        recyclerViewCartolaCatalog.addItemDecoration(new SimpleDividerItemDecoration(
                getApplicationContext()
        ));
        recyclerViewCartolaCatalog.setAdapter(cartolaCatalogAdapter);*/
        // ========variaveis recyclerview CartolaJogos===============




        imagens.put("Flamengo",R.drawable._262); imagens.put("Botafogo",R.drawable._263); imagens.put("Corinthians",R.drawable._264); imagens.put("Santa Cruz",R.drawable._344);
        imagens.put("Fluminense",R.drawable._266); imagens.put("Palmeiras",R.drawable._275); imagens.put("São Paulo",R.drawable._276); imagens.put("Santos",R.drawable._277);
        imagens.put("Atlético-MG",R.drawable._282); imagens.put("Cruzeiro",R.drawable._283); imagens.put("Grêmio",R.drawable._284); imagens.put("Internacional",R.drawable._285);
        imagens.put("Vitória",R.drawable._287); imagens.put("Sport",R.drawable._292); imagens.put("Atlético-PR",R.drawable._293); imagens.put("Coritiba",R.drawable._294);
        imagens.put("Ponte Preta",R.drawable._303); imagens.put("Chapecoense",R.drawable._315); imagens.put("Figueirense",R.drawable._316); imagens.put("América-MG",R.drawable._327);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(cartolaService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        cartolaService service = retrofit.create(cartolaService.class);


        Call<CartolaCatalog> requestCatalog = service.listPartidas();

        requestCatalog.enqueue(new Callback<CartolaCatalog>() {
            @Override
            public void onResponse(Call<CartolaCatalog> call, Response<CartolaCatalog> response) {
                if(!response.isSuccessful()){
                    Log.i(TAG,"Erro: "+response.code());
                }else {
                    CartolaCatalog catalogo = response.body();

                    // Escrevendo textos no toolbar rodada e nome do user
                    SubtituloToolBar = String.valueOf("Rodada atual : "+catalogo.Rodada);
                    toolbar.setTitle(String.format("%s","Olá : "+NomeFromSharedPreferences));
                    toolbar.setSubtitle(SubtituloToolBar);

                    TrabalhaResponseRetrofit(catalogo);


                }
                recyclerViewPalpitesUsers.setAdapter(palpitesUsersAdapter);
            }
            @Override
            public void onFailure(Call<CartolaCatalog> call, Throwable t) { Log.e(TAG, "Erro: "+t.getMessage());  }
        });



        ibWhatsaapShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    Intent whastsappshareIntent = new Intent(Intent.ACTION_SEND);
                    whastsappshareIntent.setType("text/plain");
                    whastsappshareIntent.setPackage("com.whatsapp");
                    whastsappshareIntent.putExtra(Intent.EXTRA_TEXT,TimesWhatsappShare);
                    startActivity(Intent.createChooser(whastsappshareIntent,"Compartilhar com:"));

                }catch (Exception e){
                    Toast.makeText(UserPalpitesActivity.this, "Enviar para whatsapp", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                finishAffinity();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.icluir_palpites) {
            Intent intent = new Intent(UserPalpitesActivity.this, PalpitesActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void InicializaTextViewEditTextImageView(){

        for(int k =0; k<10;k++){
            int resIdtvPlacarOficialA = getResources().getIdentifier("tvTimeAPlacar"+(k+1),"id",getPackageName());
            int resIdtvPlacarOficialB = getResources().getIdentifier("tvTimeBPlacar"+(k+1),"id",getPackageName());

            int resIdtvAproveitamentoTimeA = getResources().getIdentifier("tvAproveitamentoTimeA"+(k+1),"id",getPackageName());
            int resIdtvAproveitamentoTimeB = getResources().getIdentifier("tvAproveitamentoTimeB"+(k+1),"id",getPackageName());

            int resIdtvJogoStatus = getResources().getIdentifier("tvPalpitStaus"+(k+1),"id",getPackageName());
            int resIdtvDataPartida = getResources().getIdentifier("tvDataPartida"+(k+1),"id",getPackageName());
            int resIdtvLocalPartida = getResources().getIdentifier("tvLocalPartida"+(k+1),"id",getPackageName());

            int resIdetPalpiteUserA = getResources().getIdentifier("etPalpiteA"+(k+1),"id",getPackageName());
            int resIdetPalpiteUserB = getResources().getIdentifier("etPalpiteB"+(k+1),"id",getPackageName());

            int resIdivTimeA = getResources().getIdentifier("ivTimeA"+(k+1),"id",getPackageName());
            int resIdivTimeB = getResources().getIdentifier("ivTimeB"+(k+1),"id",getPackageName());

            int resIdivTimeAUser = getResources().getIdentifier("ivTimeAUser"+(k+1),"id",getPackageName());
            int resIdivTimeBUser = getResources().getIdentifier("ivTimeBUser"+(k+1),"id",getPackageName());

            int resIdtvTimeANome = getResources().getIdentifier("tvTimeANome"+(k+1),"id",getPackageName());
            int resIdtvTimeBNome = getResources().getIdentifier("tvTimeBNome"+(k+1),"id",getPackageName());

            tvTimeANome[k] = (TextView) findViewById(resIdtvTimeANome);
            tvTimeBNome[k] = (TextView) findViewById(resIdtvTimeBNome);

            tvAproveitamentoTimeA[k] = (TextView) findViewById(resIdtvAproveitamentoTimeA);
            tvAproveitamentoTimeB[k] = (TextView) findViewById(resIdtvAproveitamentoTimeB);

            tvPlacarOficialA[k] = (TextView) findViewById(resIdtvPlacarOficialA);
            tvPlacarOficialB[k] = (TextView) findViewById(resIdtvPlacarOficialB);

            tvJogoStatus[k] = (TextView) findViewById(resIdtvJogoStatus);
            tvDataPartida[k] = (TextView) findViewById(resIdtvDataPartida);
            tvLocalPartida[k] = (TextView) findViewById(resIdtvLocalPartida);

            etPalpiteUserA[k] = (EditText)findViewById(resIdetPalpiteUserA);
            etPalpiteUserB[k] = (EditText)findViewById(resIdetPalpiteUserB);

            ivTimeA[k] = (ImageView)findViewById(resIdivTimeA);
            ivTimeB[k] = (ImageView)findViewById(resIdivTimeB);

            ivTimeAUser[k] = (ImageView)findViewById(resIdivTimeAUser);
            ivTimeBUser[k] = (ImageView)findViewById(resIdivTimeBUser);
        }


    }

    public void TrabalhaResponseRetrofit(CartolaCatalog catalogo){

        int cont =0;
        for(UsuariosDados c: catalogo.UsuariosDados){
            arrayListPalpitesUsers.add(cont,new PalpitesUsers(c.Users.Nome+"\nPosição: "+c.Users.Classificacao+"\nPts : "+c.Users.Pontos
                    ,null,c.Users.PalpitesA[0],c.Users.PalpitesA[1],c.Users.PalpitesA[2],c.Users.PalpitesA[3],c.Users.PalpitesA[4],c.Users.PalpitesA[5],c.Users.PalpitesA[6]
                    ,c.Users.PalpitesA[7],c.Users.PalpitesA[8],c.Users.PalpitesA[9],c.Users.PalpitesB[0],c.Users.PalpitesB[1],c.Users.PalpitesB[2],c.Users.PalpitesB[3]
                    ,c.Users.PalpitesB[4],c.Users.PalpitesB[5],c.Users.PalpitesB[6],c.Users.PalpitesB[7],c.Users.PalpitesB[8],c.Users.PalpitesB[9]
                    ,imagens.get(catalogo.Jogos.get(0).Time.ClubeA),imagens.get(catalogo.Jogos.get(1).Time.ClubeA),imagens.get(catalogo.Jogos.get(2).Time.ClubeA)
                    ,imagens.get(catalogo.Jogos.get(3).Time.ClubeA),imagens.get(catalogo.Jogos.get(4).Time.ClubeA),imagens.get(catalogo.Jogos.get(5).Time.ClubeA)
                    ,imagens.get(catalogo.Jogos.get(6).Time.ClubeA),imagens.get(catalogo.Jogos.get(7).Time.ClubeA),imagens.get(catalogo.Jogos.get(8).Time.ClubeA)
                    ,imagens.get(catalogo.Jogos.get(9).Time.ClubeA),imagens.get(catalogo.Jogos.get(0).Time.ClubeB),imagens.get(catalogo.Jogos.get(1).Time.ClubeB)
                    ,imagens.get(catalogo.Jogos.get(2).Time.ClubeB),imagens.get(catalogo.Jogos.get(3).Time.ClubeB),imagens.get(catalogo.Jogos.get(4).Time.ClubeB)
                    ,imagens.get(catalogo.Jogos.get(5).Time.ClubeB),imagens.get(catalogo.Jogos.get(6).Time.ClubeB),imagens.get(catalogo.Jogos.get(7).Time.ClubeB)
                    ,imagens.get(catalogo.Jogos.get(8).Time.ClubeB),imagens.get(catalogo.Jogos.get(9).Time.ClubeB)));

            String UsuarioAtual = catalogo.UsuariosDados.get(cont).Users.Nome;
            //Log.i(TAG,"Nome: "+UsuarioAtual);

            if( UsuarioAtual.equals( NomeFromSharedPreferences ) ){
                PalpitesUserA[0] = c.Users.PalpitesA[0]; PalpitesUserA[1] = c.Users.PalpitesA[1];
                PalpitesUserA[2] = c.Users.PalpitesA[2]; PalpitesUserA[3] = c.Users.PalpitesA[3];
                PalpitesUserA[4] = c.Users.PalpitesA[4]; PalpitesUserA[5] = c.Users.PalpitesA[5];
                PalpitesUserA[6] = c.Users.PalpitesA[6]; PalpitesUserA[7] = c.Users.PalpitesA[7];
                PalpitesUserA[8] = c.Users.PalpitesA[8]; PalpitesUserA[9] = c.Users.PalpitesA[9];

                PalpitesUserB[0] = c.Users.PalpitesB[0]; PalpitesUserB[1] = c.Users.PalpitesB[1];
                PalpitesUserB[2] = c.Users.PalpitesB[2]; PalpitesUserB[3] = c.Users.PalpitesB[3];
                PalpitesUserB[4] = c.Users.PalpitesB[4]; PalpitesUserB[5] = c.Users.PalpitesB[5];
                PalpitesUserB[6] = c.Users.PalpitesB[6]; PalpitesUserB[7] = c.Users.PalpitesB[7];
                PalpitesUserB[8] = c.Users.PalpitesB[8]; PalpitesUserB[9] = c.Users.PalpitesB[9];
                //Log.i(TAG,"Palpites: "+PalpitesUserA[0]);

            }


            cont++;
        }

        // colocar nome e rodada no toolbar
        //tvRodadaAtual.setText(String.valueOf("Rodada Atual : "+catalogo.Rodada));




        StringBuilder stringShareWhatsapp = new StringBuilder();
        // coloca o nome do user primeiro na string whatsappshare
        stringShareWhatsapp.append(NomeFromSharedPreferences+"\n");




        for(int k=0;k<10;k++){
            // guarda as variaveis em strings para verificar se sao nulas
            String PlacarA = catalogo.Jogos.get(k).Time.PlacarA;
            String PlacarB = catalogo.Jogos.get(k).Time.PlacarB;

            // StringBuilder para pegar todos os jogos e formatar para enviar para whatsapp
            stringShareWhatsapp.append(catalogo.Jogos.get(k).Time.ClubeA+" "+PalpitesUserA[k]+" x "+PalpitesUserB[k]+" "+catalogo.Jogos.get(k).Time.ClubeB+"\n");

            if(PlacarA == null){PlacarA = "-";}else {PlacarA = catalogo.Jogos.get(k).Time.PlacarA;}
            if(PlacarB == null){PlacarB = "-";}else{PlacarB = catalogo.Jogos.get(k).Time.PlacarB;}

            tvTimeANome[k].setText(String.format("%s",catalogo.Jogos.get(k).Time.ClubeA+"\n"+catalogo.Jogos.get(k).Time.PosicaoA+"º"));
            //adiciona sombra no elemento
            tvTimeANome[k].setShadowLayer(14,3,3,Color.argb(255, 10, 50, 0));
            tvTimeBNome[k].setText(String.format("%s",catalogo.Jogos.get(k).Time.ClubeB+"\n"+catalogo.Jogos.get(k).Time.PosicaoB+"º"));
            //adiciona sombra no elemento
            tvTimeBNome[k].setShadowLayer(14,3,3,Color.argb(255, 10, 50, 0));

            tvPlacarOficialA[k].setText(PlacarA);
            tvPlacarOficialB[k].setText(PlacarB);

            // pega aproveitamento dos clubs
            String AproveitamentoA = "Aproveitamento \n";
            String AproveitamentoB = "Aproveitamento \n";


            for( int i =0;i < 5; i++ ) {
                AproveitamentoA += catalogo.Jogos.get(k).Time.AproveitamentoA[i]+" ";
                AproveitamentoB += catalogo.Jogos.get(k).Time.AproveitamentoB[i]+" ";
            }
            //insere o aproveitamento no text time a
            tvAproveitamentoTimeA[k].setText(AproveitamentoA);
            tvAproveitamentoTimeA[k].setShadowLayer(4,1,3,Color.argb(255, 10, 10, 0));

            tvAproveitamentoTimeB[k].setText(AproveitamentoA);
            tvAproveitamentoTimeB[k].setShadowLayer(4,1,3,Color.argb(255, 10, 10, 0));

            etPalpiteUserA[k].setHint(PalpitesUserA[k]);
            etPalpiteUserB[k].setHint(PalpitesUserB[k]);

            ivTimeA[k].setImageResource(imagens.get(catalogo.Jogos.get(k).Time.ClubeA));
            ivTimeB[k].setImageResource(imagens.get(catalogo.Jogos.get(k).Time.ClubeB));

            ivTimeAUser[k].setImageResource(imagens.get(catalogo.Jogos.get(k).Time.ClubeA));
            ivTimeBUser[k].setImageResource(imagens.get(catalogo.Jogos.get(k).Time.ClubeB));

            tvLocalPartida[k].setText(String.format("%s %s","Local da partida: ",catalogo.Jogos.get(k).Time.Local));
            tvDataPartida[k].setText(String.format("%s %s","Data da partida: ",catalogo.Jogos.get(k).Time.Data.replace("-","/")));

            if( PalpitesUserA[k].equals(catalogo.Jogos.get(k).Time.PlacarA) && PalpitesUserB[k].equals(catalogo.Jogos.get(k).Time.PlacarB)) {
                tvJogoStatus[k].setText(String.format("%s", "Acertou em cheio"));
                etPalpiteUserA[k].setBackgroundColor(Color.rgb(0,200,0));
                etPalpiteUserB[k].setBackgroundColor(Color.rgb(0,200,0));
                //tvJogoStatus[0].setBackgroundTintList(R.color.colorAccent);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    tvJogoStatus[k].setBackground(getResources().getDrawable(R.drawable.acertou));
                }
            }


                        /*arrayListCartolaCatalog.add(k,new CartolaJogos(catalogo.Jogos.get(k).Time.ClubeA,PlacarA,catalogo.Jogos.get(k).Time.ClubeB,PlacarB
                                ,imagens.get(catalogo.Jogos.get(k).Time.ClubeA),imagens.get(catalogo.Jogos.get(k).Time.ClubeB)
                                ,"Local da partida: "+catalogo.Jogos.get(k).Time.Local,"Data da partida: "+catalogo.Jogos.get(k).Time.Data.replace("-"," ")
                                ,PalpitesUserA[k],PalpitesUserB[k]));*/
        }



        TimesWhatsappShare = stringShareWhatsapp.toString();
        //Log.i(TAG,"Nome: "+String.valueOf(catalogo.UsuariosDados.get(0).Users.Nome));


        //recyclerViewCartolaCatalog.setAdapter(cartolaCatalogAdapter);
        //Log.i(TAG,"------"+catalogo.TimesA.get(1).Time);

    }

}
