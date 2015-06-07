package com.jondroid.jon.mostrarautoresjon;

/**
 * Created by Jon on 06/06/2015.
 */
import java.nio.charset.Charset;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.jondroid.jon.mostrarautoresjon.volley.VolleyController;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Autor> autoresList;
    ImageLoader imageLoader = VolleyController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<Autor> movieItems) {
        this.activity = activity;
        this.autoresList = movieItems;
    }

    @Override
    public int getCount() {
        return autoresList.size();
    }

    @Override
    public Object getItem(int location) {
        return autoresList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = VolleyController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView rating = (TextView) convertView.findViewById(R.id.rating);
        TextView genre = (TextView) convertView.findViewById(R.id.genre);
        TextView year = (TextView) convertView.findViewById(R.id.releaseYear);

        // getting movie data for the row
        Autor m = autoresList.get(position);


        // thumbnail image
        thumbNail.setImageUrl(m.getUrlPhoto(), imageLoader);

        // title
        title.setText(m.getNombre());
      //  rating.setText(""+m.getAverage_rating());
        //genre.setText(""+m.getUrlPhoto());
       ;
        rating.setText("Birth Date: "+String.valueOf(m.getFechaNac()));

        return convertView;
    }

}