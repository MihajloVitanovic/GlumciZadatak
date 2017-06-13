package com.mvit.mihajlo.glumcizadatak.activities;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.mvit.mihajlo.glumcizadatak.R;
import com.mvit.mihajlo.glumcizadatak.db.DatabaseHelper;
import com.mvit.mihajlo.glumcizadatak.db.model.Actor;
import com.mvit.mihajlo.glumcizadatak.providers.ActorProvider;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by androiddevelopment on 13.6.17..
 */

public class DetailsActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;

    private int position = 2;

    OnItemSelectedListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if (savedInstanceState != null) {
            position = savedInstanceState.getInt("position", 0);
        }

        ImageView ivImage = (ImageView)findViewById(R.id.ivActorImage);
        InputStream is = null;
        try{
            is = getAssets().open(ActorProvider.getActorById(position).getImage());
            Drawable drawable = Drawable.createFromStream(is, null);
            ivImage.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        TextView tvFirstName = (TextView)findViewById(R.id.tvActorFirstName);
        tvFirstName.setText(ActorProvider.getActorById(position).getFirstName());

        TextView tvLastName = (TextView)findViewById(R.id.tvActorLastName);
        tvLastName.setText(ActorProvider.getActorById(position).getLastName());

        TextView tvSummary = (TextView)findViewById(R.id.tvActorSummary);
        tvSummary.setText(ActorProvider.getActorById(position).getSummary());

        TextView tvActorYear = (TextView)findViewById(R.id.tvActorYear);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = ActorProvider.getActorById(position).getYear();
        tvActorYear.setText(sdf.format(date));

        RatingBar rbRating = (RatingBar) findViewById(R.id.rbActorRating);
        rbRating.setRating(ActorProvider.getActorById(position).getRating());


        // Loads actors from array resource
        final List<String> actorMovies = ActorProvider.getActorMovies();

        // Creates an ArrayAdaptar from the array of String
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.list_item, actorMovies);
        ListView listView = (ListView)findViewById(R.id.lvActorMovies);



        // Assigns ArrayAdaptar to ListView
        listView.setAdapter(dataAdapter);

        // Updates DetailFragment
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.onItemSelected(position);
            }
        });


    }


    // onOptionsItemSelected method is called whenever an item in the Toolbar is selected.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create:
                try {
                    addItem();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.action_update:
                Toast.makeText(this, "Action " + getString(R.string.fragment_detal_action_update) + " executed.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_delete:
                Toast.makeText(this, "Action " + getString(R.string.fragment_detal_action_delete) + " executed.", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    //da bi dodali podatak u bazu, potrebno je da napravimo objekat klase
    //koji reprezentuje tabelu i popunimo podacima
    private void addItem() throws SQLException {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_layout);

        final EditText productName = (EditText) dialog.findViewById(R.id.product_name);
        final EditText productDescr = (EditText) dialog.findViewById(R.id.product_description);
        final EditText productYear = (EditText) dialog.findViewById(R.id.product_year);

        Button ok = (Button) dialog.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = productName.getText().toString();
                String desct = productDescr.getText().toString();
                String year = productYear.getText().toString();


                Actor product = new Actor();
                product.setmName(name);
                product.setGenre(desct);
                product.setYear(year);

                try {
                    getDatabaseHelper().getProductDao().create(product);
                    refresh();
                    Toast.makeText(DetailsActivity.this, "Movie inserted", Toast.LENGTH_SHORT).show();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        Button cancel = (Button) dialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    //Metoda koja komunicira sa bazom podataka
    public DatabaseHelper getDatabaseHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    // refresh() prikazuje novi sadrzaj.Povucemo nov sadrzaj iz baze i popunimo listu
    private void refresh() {
        ListView listview = (ListView) findViewById(R.id.actorsList);

        if (listview != null){
            ArrayAdapter<Actor> adapter = (ArrayAdapter<Actor>) listview.getAdapter();

            if(adapter!= null)
            {
                try {
                    adapter.clear();
                    List<Actor> list = getDatabaseHelper().getProductDao().queryForAll();

                    adapter.addAll(list);

                    adapter.notifyDataSetChanged();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // nakon rada sa bazo podataka potrebno je obavezno
        //osloboditi resurse!
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

    // Container activity must implement this interface
    public interface OnItemSelectedListener {

        public void onItemSelected(int position);
    }

}
