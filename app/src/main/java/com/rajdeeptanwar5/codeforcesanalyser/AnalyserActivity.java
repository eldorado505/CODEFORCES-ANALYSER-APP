package com.rajdeeptanwar5.codeforcesanalyser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalyserActivity extends AppCompatActivity {
    private boolean contest=true;
    private String username;
    private Pie pieContest,piePractice;
    private final String LOG_TAG=AnalyserActivity.class.getName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyser);
        Intent intent=getIntent();
        username=intent.getStringExtra("username");
        String analyserType=intent.getStringExtra("AnalyserType");
         HashMap<String,Integer> hashMapContest = (HashMap<String, Integer>) intent.getSerializableExtra("Hashmap Contest");
         HashMap<String,Integer>hashMapPractice=(HashMap<String, Integer>) intent.getSerializableExtra("Hashmap Practice");


        TextView heading=findViewById(R.id.heading);
        TextView subHeading=findViewById(R.id.sub_heading);
        subHeading.setText(username+" In Official / Unofficial Contest");
        heading.setText(analyserType);

        // Create chart
        pieContest = AnyChart.pie();
        piePractice=AnyChart.pie();
        List<DataEntry> dataContest = new ArrayList<>();
        List<DataEntry> dataPractice = new ArrayList<>();
//        Log.e(LOG_TAG,analyserType+" "+hashMapContest.size()+" "+hashMapPractice.size());
        for (Map.Entry<String, Integer> mapElement : hashMapContest.entrySet()) {
            String key = mapElement.getKey();
            int value = mapElement.getValue();
            dataContest.add(new ValueDataEntry(key,value));
            Log.e(LOG_TAG,"key = "+key+" , value = "+value);
        }
        for (Map.Entry<String, Integer> mapElement : hashMapPractice.entrySet()) {
            String key = mapElement.getKey();
            int value = mapElement.getValue();
            dataPractice.add(new ValueDataEntry(key,value));
            Log.e(LOG_TAG,"key = "+key+" , value = "+value);
        }

//      Log.e(LOG_TAG,""+analyserType);
//        data.add(new ValueDataEntry("John", 10000));
//        data.add(new ValueDataEntry("Jake", 12000));
//        data.add(new ValueDataEntry("Peter", 18000));
//        data.add(new ValueDataEntry("rajdeep",15000));

        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        piePractice.data(dataPractice);
        pieContest.data(dataContest);
        anyChartView.setChart(pieContest);
    }
    public void onSwitchClick(View view) {
        contest=!contest;
        TextView switchView=findViewById(R.id.switch_);
        TextView subHeading=findViewById(R.id.sub_heading);
        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        if(contest) {
            subHeading.setText(username+" In Official / Unofficial Contest");
            switchView.setText("Switch to practice mode");
            anyChartView.setChart(pieContest);
        }
        else {
            subHeading.setText(username+" In Practice");
            switchView.setText("Switch to contest mode");
            anyChartView.setChart(AnyChart.pie());
        }
    }
}