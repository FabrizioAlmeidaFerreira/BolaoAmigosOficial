package com.example.fabrizio.bolaoamigosoficial.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fabrizio.bolaoamigosoficial.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Fabrizio on 02/10/2016.
 */

public class PalpitesUsersAdapter extends RecyclerView.Adapter<PalpitesUsersAdapter.PalpitesUserViewHolder> {

    Context context;
    ArrayList<PalpitesUsers> itens;

    public PalpitesUsersAdapter(Context context, ArrayList<PalpitesUsers> itens){

        this.context = context;
        this.itens = itens;
    }


    @Override
    public PalpitesUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.mostra_palpites_user_adapter,parent,false);
        PalpitesUserViewHolder viewHolder = new PalpitesUserViewHolder(context,view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PalpitesUserViewHolder viewHolder, int position) {

        PalpitesUsers palpitesUsers = itens.get(position);
        viewHolder.tvUserNome.setText(palpitesUsers.getUserNome());
        viewHolder.tvPalpiteTimaA1.setText(palpitesUsers.getPalpiteA1());  viewHolder.tvPalpiteTimaA2.setText(palpitesUsers.getPalpiteA2());  viewHolder.tvPalpiteTimaA3.setText(palpitesUsers.getPalpiteA3());
        viewHolder.tvPalpiteTimaA4.setText(palpitesUsers.getPalpiteA4());  viewHolder.tvPalpiteTimaA5.setText(palpitesUsers.getPalpiteA5());  viewHolder.tvPalpiteTimaA6.setText(palpitesUsers.getPalpiteA6());
        viewHolder.tvPalpiteTimaA7.setText(palpitesUsers.getPalpiteA7());  viewHolder.tvPalpiteTimaA8.setText(palpitesUsers.getPalpiteA8());  viewHolder.tvPalpiteTimaA9.setText(palpitesUsers.getPalpiteA9());
        viewHolder.tvPalpiteTimaA10.setText(palpitesUsers.getPalpiteA10());

        viewHolder.tvPalpiteTimaB1.setText(palpitesUsers.getPalpiteB1());  viewHolder.tvPalpiteTimaB2.setText(palpitesUsers.getPalpiteB2());  viewHolder.tvPalpiteTimaB3.setText(palpitesUsers.getPalpiteB3());
        viewHolder.tvPalpiteTimaB4.setText(palpitesUsers.getPalpiteB4());  viewHolder.tvPalpiteTimaB5.setText(palpitesUsers.getPalpiteB5());  viewHolder.tvPalpiteTimaB6.setText(palpitesUsers.getPalpiteB6());
        viewHolder.tvPalpiteTimaB7.setText(palpitesUsers.getPalpiteB7());  viewHolder.tvPalpiteTimaB8.setText(palpitesUsers.getPalpiteB8());  viewHolder.tvPalpiteTimaB9.setText(palpitesUsers.getPalpiteB9());
        viewHolder.tvPalpiteTimaB10.setText(palpitesUsers.getPalpiteB10());


        try {
            Picasso.with(context).load(palpitesUsers.getTimeAImagen1()).into(viewHolder.ivPalpiteTimeA1);  Picasso.with(context).load(palpitesUsers.getTimeAImagen2()).into(viewHolder.ivPalpiteTimeA2);
            Picasso.with(context).load(palpitesUsers.getTimeAImagen3()).into(viewHolder.ivPalpiteTimeA3);  Picasso.with(context).load(palpitesUsers.getTimeAImagen4()).into(viewHolder.ivPalpiteTimeA4);
            Picasso.with(context).load(palpitesUsers.getTimeAImagen5()).into(viewHolder.ivPalpiteTimeA5);  Picasso.with(context).load(palpitesUsers.getTimeAImagen6()).into(viewHolder.ivPalpiteTimeA6);
            Picasso.with(context).load(palpitesUsers.getTimeAImagen7()).into(viewHolder.ivPalpiteTimeA7);  Picasso.with(context).load(palpitesUsers.getTimeAImagen8()).into(viewHolder.ivPalpiteTimeA8);
            Picasso.with(context).load(palpitesUsers.getTimeAImagen9()).into(viewHolder.ivPalpiteTimeA9);  Picasso.with(context).load(palpitesUsers.getTimeAImagen10()).into(viewHolder.ivPalpiteTimeA10);

            Picasso.with(context).load(palpitesUsers.getTimeBImagen1()).into(viewHolder.ivPalpiteTimeB1);  Picasso.with(context).load(palpitesUsers.getTimeBImagen2()).into(viewHolder.ivPalpiteTimeB2);
            Picasso.with(context).load(palpitesUsers.getTimeBImagen3()).into(viewHolder.ivPalpiteTimeB3);  Picasso.with(context).load(palpitesUsers.getTimeBImagen4()).into(viewHolder.ivPalpiteTimeB4);
            Picasso.with(context).load(palpitesUsers.getTimeBImagen5()).into(viewHolder.ivPalpiteTimeB5);  Picasso.with(context).load(palpitesUsers.getTimeBImagen6()).into(viewHolder.ivPalpiteTimeB6);
            Picasso.with(context).load(palpitesUsers.getTimeBImagen7()).into(viewHolder.ivPalpiteTimeB7);  Picasso.with(context).load(palpitesUsers.getTimeBImagen8()).into(viewHolder.ivPalpiteTimeB8);
            Picasso.with(context).load(palpitesUsers.getTimeBImagen9()).into(viewHolder.ivPalpiteTimeB9);  Picasso.with(context).load(palpitesUsers.getTimeBImagen10()).into(viewHolder.ivPalpiteTimeB10);

        }catch (Exception e){

        }

    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

    public class PalpitesUserViewHolder extends RecyclerView.ViewHolder {

        private Context context;
        TextView tvUserNome,tvPalpiteTimaA1,tvPalpiteTimaA2,tvPalpiteTimaA3,tvPalpiteTimaA4,tvPalpiteTimaA5,tvPalpiteTimaA6,tvPalpiteTimaA7,tvPalpiteTimaA8,tvPalpiteTimaA9,tvPalpiteTimaA10;
        TextView tvPalpiteTimaB1,tvPalpiteTimaB2,tvPalpiteTimaB3,tvPalpiteTimaB4,tvPalpiteTimaB5,tvPalpiteTimaB6,tvPalpiteTimaB7,tvPalpiteTimaB8,tvPalpiteTimaB9,tvPalpiteTimaB10;
        ImageView ivUserFoto,ivPalpiteTimeA1,ivPalpiteTimeA2,ivPalpiteTimeA3,ivPalpiteTimeA4,ivPalpiteTimeA5,ivPalpiteTimeA6,ivPalpiteTimeA7,ivPalpiteTimeA8,ivPalpiteTimeA9,ivPalpiteTimeA10
                ,ivPalpiteTimeB1,ivPalpiteTimeB2,ivPalpiteTimeB3,ivPalpiteTimeB4,ivPalpiteTimeB5,ivPalpiteTimeB6,ivPalpiteTimeB7,ivPalpiteTimeB8,ivPalpiteTimeB9,ivPalpiteTimeB10;


        public PalpitesUserViewHolder(Context context,View itemView) {
            super(itemView);

            tvUserNome = (TextView) itemView.findViewById(R.id.tvUserNome);
            tvPalpiteTimaA1 = (TextView) itemView.findViewById(R.id.tvPalpiteTimaA1);  tvPalpiteTimaA2 = (TextView) itemView.findViewById(R.id.tvPalpiteTimaA2);
            tvPalpiteTimaA3 = (TextView) itemView.findViewById(R.id.tvPalpiteTimaA3);  tvPalpiteTimaA4 = (TextView) itemView.findViewById(R.id.tvPalpiteTimaA4);
            tvPalpiteTimaA5 = (TextView) itemView.findViewById(R.id.tvPalpiteTimaA5);  tvPalpiteTimaA6 = (TextView) itemView.findViewById(R.id.tvPalpiteTimaA6);
            tvPalpiteTimaA7 = (TextView) itemView.findViewById(R.id.tvPalpiteTimaA7);  tvPalpiteTimaA8 = (TextView) itemView.findViewById(R.id.tvPalpiteTimaA8);
            tvPalpiteTimaA9 = (TextView) itemView.findViewById(R.id.tvPalpiteTimaA9);  tvPalpiteTimaA10 = (TextView) itemView.findViewById(R.id.tvPalpiteTimaA10);

            tvPalpiteTimaB1 = (TextView) itemView.findViewById(R.id.tvPalpiteTimaB1);  tvPalpiteTimaB2 = (TextView) itemView.findViewById(R.id.tvPalpiteTimaB2);
            tvPalpiteTimaB3 = (TextView) itemView.findViewById(R.id.tvPalpiteTimaB3);  tvPalpiteTimaB4 = (TextView) itemView.findViewById(R.id.tvPalpiteTimaB4);
            tvPalpiteTimaB5 = (TextView) itemView.findViewById(R.id.tvPalpiteTimaB5);  tvPalpiteTimaB6 = (TextView) itemView.findViewById(R.id.tvPalpiteTimaB6);
            tvPalpiteTimaB7 = (TextView) itemView.findViewById(R.id.tvPalpiteTimaB7);  tvPalpiteTimaB8 = (TextView) itemView.findViewById(R.id.tvPalpiteTimaB8);
            tvPalpiteTimaB9 = (TextView) itemView.findViewById(R.id.tvPalpiteTimaB9);  tvPalpiteTimaB10 = (TextView) itemView.findViewById(R.id.tvPalpiteTimaB10);

            ivUserFoto = (ImageView) itemView.findViewById(R.id.ivUsuario);

            ivPalpiteTimeA1 = (ImageView) itemView.findViewById(R.id.ivPalpiteTimeA1);  ivPalpiteTimeA2 = (ImageView) itemView.findViewById(R.id.ivPalpiteTimeA2);
            ivPalpiteTimeA3 = (ImageView) itemView.findViewById(R.id.ivPalpiteTimeA3);  ivPalpiteTimeA4 = (ImageView) itemView.findViewById(R.id.ivPalpiteTimeA4);
            ivPalpiteTimeA5 = (ImageView) itemView.findViewById(R.id.ivPalpiteTimeA5);  ivPalpiteTimeA6 = (ImageView) itemView.findViewById(R.id.ivPalpiteTimeA6);
            ivPalpiteTimeA7 = (ImageView) itemView.findViewById(R.id.ivPalpiteTimeA7);  ivPalpiteTimeA8 = (ImageView) itemView.findViewById(R.id.ivPalpiteTimeA8);
            ivPalpiteTimeA9 = (ImageView) itemView.findViewById(R.id.ivPalpiteTimeA9);  ivPalpiteTimeA10 = (ImageView) itemView.findViewById(R.id.ivPalpiteTimeA10);

            ivPalpiteTimeB1 = (ImageView) itemView.findViewById(R.id.ivPalpiteTimeB1);  ivPalpiteTimeB2 = (ImageView) itemView.findViewById(R.id.ivPalpiteTimeB2);
            ivPalpiteTimeB3 = (ImageView) itemView.findViewById(R.id.ivPalpiteTimeB3);  ivPalpiteTimeB4 = (ImageView) itemView.findViewById(R.id.ivPalpiteTimeB4);
            ivPalpiteTimeB5 = (ImageView) itemView.findViewById(R.id.ivPalpiteTimeB5);  ivPalpiteTimeB6 = (ImageView) itemView.findViewById(R.id.ivPalpiteTimeB6);
            ivPalpiteTimeB7 = (ImageView) itemView.findViewById(R.id.ivPalpiteTimeB7);  ivPalpiteTimeB8 = (ImageView) itemView.findViewById(R.id.ivPalpiteTimeB8);
            ivPalpiteTimeB9 = (ImageView) itemView.findViewById(R.id.ivPalpiteTimeB9);  ivPalpiteTimeB10 = (ImageView) itemView.findViewById(R.id.ivPalpiteTimeB10);



        }

    }

}
