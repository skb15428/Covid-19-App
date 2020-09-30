package com.saurabh.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView cases,active,recovered,tcases,tdeaths,todeaths,acount,critical;
    ScrollView sview;
    SimpleArcLoader sload;
    PieChart pchart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cases=findViewById(R.id.cases_txt);
        recovered=findViewById(R.id.recovered_txt);
        active=findViewById(R.id.active_txt);
        tcases=findViewById(R.id.today_cases_txt);
        tdeaths=findViewById(R.id.today_death_txt);
        todeaths=findViewById(R.id.total_death_txt);
        critical=findViewById(R.id.critical_txt);
        acount=findViewById(R.id.affected_county_txt);

        sview=findViewById(R.id.stat);
        sload=findViewById(R.id.loader);
        pchart=findViewById(R.id.piechart);

        fetch_data();
    }

    private void fetch_data() {
        String url="https://corona.lmao.ninja/v2/all/";
        sload.start();

        StringRequest request=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj=new JSONObject(response.toString());
                            cases.setText(jobj.getString("cases"));
                            recovered.setText(jobj.getString("recovered"));
                            active.setText(jobj.getString("active"));
                            critical.setText(jobj.getString("critical"));
                            tcases.setText(jobj.getString("todayCases"));
                            tdeaths.setText(jobj.getString("todayDeaths"));
                            todeaths.setText(jobj.getString("deaths"));
                            acount.setText(jobj.getString("affectedCountries"));

                            pchart.addPieSlice(new PieModel("Cases",Integer.parseInt(cases.getText().toString()), Color.parseColor("#d92027")));
                            pchart.addPieSlice(new PieModel("Recovered",Integer.parseInt(recovered.getText().toString()), Color.parseColor("#a8df65")));
                            pchart.addPieSlice(new PieModel("Deaths",Integer.parseInt(todeaths.getText().toString()), Color.parseColor("#862a5c")));
                            pchart.addPieSlice(new PieModel("Active",Integer.parseInt(active.getText().toString()), Color.parseColor("#f4a548")));

                            pchart.startAnimation();

                            sload.stop();
                            sload.setVisibility(View.GONE);
                            sview.setVisibility(View.VISIBLE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            sload.stop();
                            sload.setVisibility(View.GONE);
                            sview.setVisibility(View.VISIBLE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                sload.stop();
                sload.setVisibility(View.GONE);
                sview.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue rqueue= Volley.newRequestQueue(this);
        rqueue.add(request);
    }

    public void trackfun(View view) {
        startActivity(new Intent(getApplicationContext(),Affectedcountries.class));
    }
}
