package com.example.fabrizio.bolaoamigosoficial.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fabrizio.bolaoamigosoficial.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Fabrizio on 29/09/2016.
 */

public class CartolaCatalogAdapter extends RecyclerView.Adapter<CartolaCatalogAdapter.CartolaCatalogViewHolder>  {

    Context context;
    ArrayList<CartolaJogos> Itens;

    private int PlacarAOficial,PalpiteOficialA;
    private int PlacarBOficial,PalpiteOficialB;

    private String[] mDataset;

    public CartolaCatalogAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    public CartolaCatalogAdapter(Context context, ArrayList<CartolaJogos> itens){

        this.context = context;
        this.Itens = itens;
    }




    @Override
    public CartolaCatalogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.jogos_adapter, parent, false);
        CartolaCatalogViewHolder viewHolder = new CartolaCatalogViewHolder(context,view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CartolaCatalogViewHolder viewHolder, int position) {

        CartolaJogos cartolaJogos = Itens.get(position);

        viewHolder.tvTimeANome.setText(cartolaJogos.getTimeANome());
        viewHolder.tvTimeAPlacar.setText(cartolaJogos.getTimeAPlacar());

        viewHolder.tvTimeBNome.setText(cartolaJogos.getTimeBNome());
        viewHolder.tvTimeBPlacar.setText(cartolaJogos.getTimeBPlacar());

        viewHolder.tvDataPartida.setText(cartolaJogos.getDataPartida());
        viewHolder.tvLocalPartida.setText(cartolaJogos.getLocalPartida());

        //viewHolder.myCustomEditTextListener.updatePosition(holder.getAdapterPosition());
        viewHolder.etPalpiteA.setTag( position );
        viewHolder.etPalpiteA.setHint(cartolaJogos.getPalpiteAUser());

        viewHolder.etPalpiteB.setTag( position );
        viewHolder.etPalpiteB.setHint(cartolaJogos.getPalpiteBUser());


        if( cartolaJogos.getTimeAPlacar().equals(cartolaJogos.getPalpiteAUser()) && cartolaJogos.getTimeBPlacar().equals(cartolaJogos.getPalpiteBUser()) ){
            viewHolder.tvPalpitesStatus.setText("Acertou em cheio");
            viewHolder.tvPalpitesStatus.setBackgroundColor(Color.GREEN);
        }else if(PlacarAOficial > PlacarBOficial && PalpiteOficialA > PalpiteOficialB){
            viewHolder.tvPalpitesStatus.setText("Acertou Parcialmente");
        }


        try {
            Picasso.with(context).load(cartolaJogos.getTimeAImagen()).into(viewHolder.ivTimaAImagen);
            //
            Picasso.with(context).load(cartolaJogos.getTimeBImagen()).into(viewHolder.ivTimeBImagen);

            Picasso.with(context).load(cartolaJogos.getTimeAImagen()).into(viewHolder.ivTimeAUser);
            Picasso.with(context).load(cartolaJogos.getTimeBImagen()).into(viewHolder.ivTimeBUser);

        }catch (Exception e){

        }
       // viewHolder.ivTimaAImagen.setImageResource(cartolaJogos.getTimeAImagen());


    }
    @Override
    public int getItemCount() {
        return Itens.size();
    }


    public class CartolaCatalogViewHolder extends RecyclerView.ViewHolder{

        private Context context;
        public TextView tvTimeANome;
        public TextView tvTimeBNome;
        public TextView tvTimeAPlacar;
        public TextView tvTimeBPlacar;
        public TextView tvDataPartida;
        public TextView tvLocalPartida;
        public TextView tvPalpitesStatus;

        public EditText etPalpiteA;
        public EditText etPalpiteB;


        public ImageView ivTimaAImagen;
        public ImageView ivTimeBImagen;
        public ImageView ivTimeAUser;
        public ImageView ivTimeBUser;


        public CartolaCatalogViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            tvTimeANome = (TextView) itemView.findViewById(R.id.tvTimeANome);
            tvTimeAPlacar = (TextView) itemView.findViewById(R.id.tvTimeAPlacar);
            tvTimeBNome = (TextView) itemView.findViewById(R.id.tvTimeBNome);
            tvTimeBPlacar = (TextView) itemView.findViewById(R.id.tvTimeBPlacar);
            tvDataPartida = (TextView) itemView.findViewById(R.id.tvDataPartida);
            tvLocalPartida = (TextView) itemView.findViewById(R.id.tvLocalPartida);
            tvPalpitesStatus = (TextView) itemView.findViewById(R.id.textView3);
            ivTimaAImagen = (ImageView) itemView.findViewById(R.id.ivTimeA);
            ivTimeBImagen = (ImageView) itemView.findViewById(R.id.ivTimeB);

            etPalpiteA = (EditText) itemView.findViewById(R.id.etPalpiteA);
            MyTextWatcher textWatcherA = new MyTextWatcher(etPalpiteA);
            etPalpiteA.addTextChangedListener(textWatcherA);

            etPalpiteB = (EditText) itemView.findViewById(R.id.etPalpiteB);
            MyTextWatcher textWatcherB = new MyTextWatcher(etPalpiteB);
            etPalpiteB.addTextChangedListener(textWatcherB);

            ivTimeAUser = (ImageView) itemView.findViewById(R.id.ivTimeAUser);
            ivTimeBUser = (ImageView) itemView.findViewById(R.id.ivTimeBUser);
        }


    }
    public class MyTextWatcher implements TextWatcher {
        private EditText editText;

        public MyTextWatcher(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int position = (int) editText.getTag();
            // Do whatever you want with position
            //editText.setText("2");
            //editText.setHint(s);
            //editText.append(s);


        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.i("TAG",""+s);

        }
    }

}

