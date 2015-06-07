package com.jondroid.jon.mostrarautoresjon;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.jondroid.jon.mostrarautoresjon.volley.VolleyController;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    /**
     * @author: Jon Martinez Acedo
     *
     * En esta clase principal se hace la llamada a la api en el método onCreate() de la actividad
     * a traves de una petición JSON. Para manipular el resultado JSON se usa la librería Volley.
     *
     * Los resultados son almacenados en una List de autores y a continuación mostrados en un ListView
     * customizada en la clase CustomListAdapter
     *
     *
     */

    //El manifest de la actividad principal la define como single top


    private String urlJsonObj = "http://api.hobbiespot.kubicode.com/api/authors";
    private static String TAG = MainActivity.class.getSimpleName();
    private Button btnMakeObjectRequest, btnMakeArrayRequest;
    private ListView ltwAutores;
    //tiempos de carga mostrados
    private ProgressDialog pDialog;
    private ArrayList<Autor> autores=new ArrayList<Autor>();
    private CustomListAdapter adapter;

   //String de visualizacion temp
    private String jsonResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        ltwAutores=(ListView)findViewById(R.id.listaAutores);
        adapter = new CustomListAdapter(this, autores);

        ltwAutores.setAdapter(adapter);
        ltwAutores.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // Toast.makeText(getBaseContext(), "" + autores.get(position).nombre, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, VistaDetalle.class);

                Autor a=autores.get(position);

                intent.putExtra("nomAutor",a.getNombre());
                intent.putExtra("apeAutor",a.getApellido());
                intent.putExtra("birthAutor",a.getFechaNac());
                intent.putExtra("averageAutor",a.getAverage_rating());
                //Envío el bitmap a vista en detalle. Se puede enviar también la url de nuevo, pero
                //al devolver la api una imagen aleatoria con cada llamada, prefiero que se mantenga
                //la misma con el bitmap.
                final NetworkImageView imageView = (NetworkImageView) view.findViewById(R.id.thumbnail);
                final BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                final Bitmap miBitmap = bitmapDrawable.getBitmap();
                intent.putExtra("fotoBM",miBitmap);


                intent.putExtra("urlAutor",a.getUrlPhoto());


                startActivity(intent);
            }
        });
        makeJsonObjectRequest();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    /**
     * Method to make json object request where json response starts wtih {
     * */
    private void makeJsonObjectRequest() {
        showpDialog();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
                urlJsonObj, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    //Se coje el objeto array json data
                    JSONArray array = response.getJSONArray("data");
                    jsonResponse="";
                    Autor au;
                    //Se sacan los diferentes JSON Objetcs dentro del array y se seleccionan los campos
                    for (int x = 0; x < array.length(); x++) {

                        JSONObject autor = (JSONObject) array.get(x);

                        String nombre = autor.getString("name");
                        String apellido = autor.getString("lastname");
                        int fechaNac = autor.getInt("birthdate");
                        int average_rating = autor.getInt("average_rating");
                        String urlPhoto=autor.getString("photo_url");


                        au =new Autor(nombre,apellido,fechaNac,average_rating,urlPhoto);
                        //Cargo el autor en la lista de autores a mostrar
                        autores.add(au);
                    }
                    Log.wtf("Tamaño Lista: ",""+autores.size());
                //Aqui cargaré la lista
                adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // quitamos el dialogo de progreso
                hidepDialog();
            }
        });

        // Se añade la peticion a la cola
        VolleyController.getInstance().addToRequestQueue(jsonObjReq);

    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
