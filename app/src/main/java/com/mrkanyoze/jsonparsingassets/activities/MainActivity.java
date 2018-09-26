package com.mrkanyoze.jsonparsingassets.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.mrkanyoze.jsonparsingassets.R;
import com.mrkanyoze.jsonparsingassets.adapters.CountriesAdapter;
import com.mrkanyoze.jsonparsingassets.models.Country;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CountriesAdapter countriesAdapter;
    private ArrayList<Country> countries;
    private EditText mFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Initializing the ArrayList
        countries = new ArrayList<>();

        recyclerView = findViewById(R.id.card_recycler_view);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mFilter = findViewById(R.id.etFilter);
        mFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                final String query = s.toString().toLowerCase().trim();
                final ArrayList<Country> filteredList = new ArrayList<>();

                for (int i = 0; i < countries.size(); i++) {

                    final String text = countries.get(i).getCountry().toLowerCase();
                    if (text.contains(query)) {
                        filteredList.add(countries.get(i));
                    }
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                countriesAdapter = new CountriesAdapter(filteredList);
                recyclerView.setAdapter(countriesAdapter);
                countriesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        getCoutries();
    }



    private void getCoutries(){

        try {
            JSONArray jsonArray = new JSONArray(readJSONFromAsset());
            // Parsing json
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Country country = new Country();
                country.setCountry_id(obj.getString("country_id"));
                country.setCountry(obj.getString("country"));
                country.setTelephone_code(obj.getString("telephone_code"));

                countries.add(country);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);


        countriesAdapter = new CountriesAdapter(countries);
        recyclerView.setAdapter(countriesAdapter);
    }

    public String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("countries.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
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
}
