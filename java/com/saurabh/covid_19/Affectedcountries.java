package com.saurabh.covid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Affectedcountries extends AppCompatActivity {

    EditText search;
    SimpleArcLoader loader;
    ListView list;
    public static List<Country_detials> alist=new ArrayList<>();
    Country_detials cdetails;
    CustomAdapter cadapt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affectedcountries);

        search=findViewById(R.id.search_ed);
        loader=findViewById(R.id.loader);
        list=findViewById(R.id.list);

        getSupportActionBar().setTitle("Affected Countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fetch_data();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(),Country_data.class).putExtra("position",position));
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                cadapt.getFilter().filter(s);
                cadapt.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void fetch_data() {
        String url="https://corona.lmao.ninja/v2/countries/";
        loader.start();
        StringRequest request=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jarray = new JSONArray(response.toString());
                            for(int i=0;i<jarray.length();i++)
                            {
                                JSONObject jobj=jarray.getJSONObject(i);
                                String country=jobj.getString("country");
                                String cases=jobj.getString("cases");
                                String today_cases=jobj.getString("todayCases");
                                String deaths=jobj.getString("deaths");
                                String today_deaths=jobj.getString("todayDeaths");
                                String recovered=jobj.getString("recovered");
                                String active=jobj.getString("active");
                                String critical=jobj.getString("critical");
                                String tests=jobj.getString("tests");
                                String population=jobj.getString("population");
                                String continent=jobj.getString("continent");

                                JSONObject flag_obj=jobj.getJSONObject("countryInfo");
                                String flag=flag_obj.getString("flag");
                                //String flag="https://disease.sh/assets/img/flags/in.png";

                                cdetails=new Country_detials(flag,country,cases,today_cases,deaths,today_deaths,recovered,active,critical,tests,population,continent);
                                alist.add(cdetails);
                            }
                            cadapt=new CustomAdapter(Affectedcountries.this,alist);
                            list.setAdapter(cadapt);
                            loader.stop();
                            loader.setVisibility(View.GONE);
                            list.setVisibility(View.VISIBLE);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            loader.stop();
                            loader.setVisibility(View.GONE);
                            list.setVisibility(View.VISIBLE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue rqueue= Volley.newRequestQueue(this);
        rqueue.add(request);
    }
}
