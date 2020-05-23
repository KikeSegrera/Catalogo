package com.ejercicio.catalogo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ejercicio.catalogo.modelo.Producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONArray> {

    String url;
    RequestQueue queue;
    JsonArrayRequest request;
    ArrayList<Producto> productos;
    ListView lv;
    ProgressBar pbConexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productos = new ArrayList<Producto>();
        lv = findViewById(R.id.lv);
        pbConexion = findViewById(R.id.pbConexion);

        queue = Volley.newRequestQueue(this);
        url = getResources().getString(R.string.url_listado);
        request = new JsonArrayRequest(Request.Method.GET, url, null, this, this);
        queue.add(request);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        pbConexion.setVisibility(View.GONE);
        finish();
    }

    @Override
    public void onResponse(JSONArray response) {
        pbConexion.setVisibility(View.GONE);
        Log.d("RESPUESTA", response.toString());

        JSONObject jsonObject;
        String[][] datos = new String[response.length()][6];

        try{
            for(int i=0; i < response.length(); i++){
                jsonObject = response.getJSONObject(i);

                int id = jsonObject.getInt("id");
                datos[i][0] = String.valueOf(id);
                datos[i][1] = jsonObject.getString("name");
                datos[i][2] = jsonObject.getString("thumbnail_url");
                datos[i][3] = jsonObject.getString("price");
                datos[i][4] = jsonObject.getString("provider");
                datos[i][5] = jsonObject.getString("delivery");

                Producto producto = new Producto(id, datos[i][1], datos[i][2], datos[i][3], datos[i][4], datos[i][5]);

                productos.add(producto);
            }

            Adaptador adaptador = new Adaptador(this, datos);
            lv.setAdapter(adaptador);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Toast.makeText(MainActivity.this, String.valueOf(id), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    intent.putExtra("ID", id);
                    startActivity(intent);
                }
            });


        }catch (JSONException e){

        }
    }
}
