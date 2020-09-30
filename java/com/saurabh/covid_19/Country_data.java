package com.saurabh.covid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class Country_data extends AppCompatActivity {

    int position;
    TextView Country,Total_cases,Total_deaths,recovered,recovered_per,active,today_cases,today_deaths,tests,critical,population,continent;
    ScrollView sview;
    SimpleArcLoader sload;
    PieChart pchart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_data);

        Intent intent =getIntent();
        position=intent.getIntExtra("position",0);

        Country=findViewById(R.id.heading);
        Total_cases=findViewById(R.id.tot_cases_txt);
        Total_deaths=findViewById(R.id.tot_death_txt);
        recovered=findViewById(R.id.recovered_txt);
        recovered_per=findViewById(R.id.recovery_per_txt);
        active=findViewById(R.id.active_txt);
        today_cases=findViewById(R.id.today_cases_txt);
        today_deaths=findViewById(R.id.today_death_txt);
        tests=findViewById(R.id.tests_txt);
        critical=findViewById(R.id.critical_txt);
        population=findViewById(R.id.population_txt);
        continent=findViewById(R.id.continent_txt);

        sview=findViewById(R.id.stat);
        sload=findViewById(R.id.loader);
        pchart=findViewById(R.id.piechart);

        Country.setText(Affectedcountries.alist.get(position).getCountry());
        Total_cases.setText(Affectedcountries.alist.get(position).getCases());
        Total_deaths.setText(Affectedcountries.alist.get(position).getDeaths());
        recovered.setText(Affectedcountries.alist.get(position).getRecovered());
        Float percentage=(Integer.parseInt(recovered.getText().toString())*100.0f)/Integer.parseInt(Total_cases.getText().toString());
        recovered_per.setText(String.valueOf(percentage));
        active.setText(Affectedcountries.alist.get(position).getActive());
        today_cases.setText(Affectedcountries.alist.get(position).getToday_cases());
        today_deaths.setText(Affectedcountries.alist.get(position).getToday_deaths());
        tests.setText(Affectedcountries.alist.get(position).getTests());
        critical.setText(Affectedcountries.alist.get(position).getCritical());
        population.setText(Affectedcountries.alist.get(position).getPopulation());
        continent.setText(Affectedcountries.alist.get(position).getContinent());

        pchart.addPieSlice(new PieModel("Cases",Integer.parseInt(Total_cases.getText().toString()), Color.parseColor("#d92027")));
        pchart.addPieSlice(new PieModel("Recovered",Integer.parseInt(recovered.getText().toString()), Color.parseColor("#a8df65")));
        pchart.addPieSlice(new PieModel("Deaths",Integer.parseInt(Total_deaths.getText().toString()), Color.parseColor("#862a5c")));
        pchart.addPieSlice(new PieModel("Active",Integer.parseInt(active.getText().toString()), Color.parseColor("#f4a548")));

        pchart.startAnimation();

        sload.stop();
        sload.setVisibility(View.GONE);
        sview.setVisibility(View.VISIBLE);


        getSupportActionBar().setTitle("Details of Country");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
