package com.jondroid.jon.mostrarautoresjon;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.jondroid.jon.mostrarautoresjon.volley.VolleyController;


public class VistaDetalle extends AppCompatActivity {
   // ImageLoader imageLoader = VolleyController.getInstance().getImageLoader();
    private TextView txtNombre;
    private TextView txtApellido;
    private TextView txtCumplea;
    private TextView txtPromedio;
    private ImageView imageBM;

    String nombre;
    String apellido;
    int birthdate;
    int rate;
    String urlPhoto;
    Bitmap fotoBM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_detalle);
        Intent intent = getIntent();

        /**Instanciar**/
        txtNombre=(TextView)findViewById(R.id.txtNombre);
        txtApellido=(TextView)findViewById(R.id.txtApellido);
        txtCumplea=(TextView)findViewById(R.id.txtCumple);
        txtPromedio=(TextView)findViewById(R.id.txtPromedio);
        imageBM=(ImageView)findViewById(R.id.imgDetalle);




        /**Recogida de extras**/
        nombre=intent.getStringExtra("nomAutor");
        apellido=intent.getStringExtra("apeAutor");
        birthdate=intent.getIntExtra("birthAutor", 0);
        rate=intent.getIntExtra("averageAutor", 0);
        urlPhoto=intent.getStringExtra("urlAutor");
        fotoBM=intent.getParcelableExtra("fotoBM");


        /**Asignación de Variables**/
        txtNombre.setText(nombre);
        txtApellido.setText(apellido);
        imageBM.setImageBitmap(fotoBM);
        txtCumplea.setText(""+birthdate);
        txtPromedio.setText(""+rate);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vista_detalle, menu);
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
}
