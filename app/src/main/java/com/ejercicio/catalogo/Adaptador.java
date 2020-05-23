package com.ejercicio.catalogo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Adaptador extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context contexto;
    String[][] datos;

    public Adaptador(Context contexto, String[][] datos) {
        this.contexto = contexto;
        this.datos = datos;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return datos.length;
    }

    @Override
    public Object getItem(int position) {
        return datos[position];
    }

    @Override
    public long getItemId(int position) {
        return Long.valueOf(datos[position][0]);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.list_element, null);
        TextView tvName = vista.findViewById(R.id.tvName);
        TextView tvPrice = vista.findViewById(R.id.tvPrice);
        TextView tvProvider = vista.findViewById(R.id.tvProvider);
        TextView tvDelivery = vista.findViewById(R.id.tvDelivery);
        ImageView ivPreview = vista.findViewById(R.id.ivPreview);

        tvName.setText(datos[position][1]);
        tvPrice.setText(contexto.getResources().getString(R.string.price,datos[position][3]));
        tvProvider.setText(contexto.getResources().getString(R.string.provider,datos[position][4]));
        tvDelivery.setText(contexto.getResources().getString(R.string.delivery,datos[position][5]));

        Picasso.with(contexto)
                .load(datos[position][2])
                .into(ivPreview);

        return vista;
    }
}