package com.example.listviewusuarios;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListaUsuarioActivity  extends Activity
{

    static RequestQueue queue;
    static ListView lista;
    static AdaptadorUsuario adaptador;

    @Override
    protected  void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuario);
                queue= Volley.newRequestQueue(this);
       // obtenerDatosVolley();

        //*************************************
        String url = "http://192.168.0.25:8080/api/usuarios";
        final ArrayList<Usuario> model = new ArrayList<>();
        //TextView tvGet=(TextView)findViewById(R.id.tvGet);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {

                    JSONObject objeto = null;
                    try {
                        Usuario user;
                        Gson gson = new Gson();

                        objeto = response.getJSONObject(i);
                        user = gson.fromJson(objeto.toString(), Usuario.class);
                        model.add(user);
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(),
                                "mensaje de error"+e,Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
                adaptador = new AdaptadorUsuario(getApplicationContext(), model);

                lista = (ListView) findViewById(R.id.lvlista);
                lista.setAdapter(adaptador);

                lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l)
                    {
                        try{
                            Usuario usuario = (Usuario) adaptador.getItem(posicion);
                            Log.e("persona: " , usuario.getCi() + " - " + usuario.getNombre());
                            Toast.makeText(getBaseContext(),"Tu nombre es: " + usuario.getNombre(), Toast.LENGTH_SHORT).show();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        "mensaje de error"+error,Toast.LENGTH_SHORT).show();
            }
        }
        );
        queue.add(request);
    }
}
