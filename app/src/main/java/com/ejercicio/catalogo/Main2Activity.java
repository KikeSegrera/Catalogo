package com.ejercicio.catalogo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<String> {

    TextView tvName, tvDesc;
    ImageView ivPreview;
    ProgressBar pbConexion;

    String url;
    RequestQueue queue;
    StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle bundle = getIntent().getExtras();

        final long id = bundle.getLong("ID");

        tvName = findViewById(R.id.tvName);
        tvDesc = findViewById(R.id.tvDesc);
        tvDesc.setMovementMethod(new ScrollingMovementMethod());
        ivPreview = findViewById(R.id.ivPreview);
        pbConexion = findViewById(R.id.pbConexion);

        queue = Volley.newRequestQueue(this);
        url = getResources().getString(R.string.url_desc) + id;

        request = new StringRequest(Request.Method.POST, url, this, this){
            protected Map<String,String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(id));
                return params;
            }
        };

        queue.add(request);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        pbConexion.setVisibility(View.GONE);
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onResponse(String response) {
        pbConexion.setVisibility(View.GONE);

        try {
            JSONObject jsonObject = new JSONObject(response);

            tvName.setText(new String(jsonObject.getString("name").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
            tvDesc.setText(new String(jsonObject.getString("desc").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));

            Picasso.with(this)
                    .load(jsonObject.getString("imag_url"))
                    .into(ivPreview);

        }catch(JSONException e){

        }
    }
}
