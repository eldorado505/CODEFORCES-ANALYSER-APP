package com.rajdeeptanwar5.codeforcesanalyser;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.anychart.chart.common.dataentry.ValueDataEntry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ProfileActivity extends AppCompatActivity {
    private SubmissionTrack mSubmissionTrack=null;
    public static final String LOG_TAG=ProfileActivity.class.getName();
    private final ProfileActivity context=this;
    public static final int LOADER_SUBMISSION = 0 , LOADER_USERINFO = 1 , LOADER_RATING_CHANGE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent=getIntent();

        String username=intent.getStringExtra("key");
        getLoaderManager().initLoader(LOADER_USERINFO,null,new LoaderManager.LoaderCallbacks<UserInfo>() {

            @Override
            public Loader<UserInfo> onCreateLoader(int id, Bundle args) {
               return new UserInfoLoader(context,username);
            }

            @Override
            public void onLoadFinished(Loader<UserInfo> loader, UserInfo data) {
                if(data==null) return;
                updateInfoUI(data);
            }

            @Override
            public void onLoaderReset(Loader<UserInfo> loader) {

            }
        });
        getLoaderManager().initLoader(LOADER_SUBMISSION, null, new LoaderManager.LoaderCallbacks<SubmissionTrack>() {
            @Override
            public Loader<SubmissionTrack> onCreateLoader(int id, Bundle args) {
                return new SubmissionTrackLoader(context,username);
            }

            @Override
            public void onLoadFinished(Loader<SubmissionTrack> loader, SubmissionTrack data) {
                if(data==null) return;
                mSubmissionTrack=data;
            }

            @Override
            public void onLoaderReset(Loader<SubmissionTrack> loader) {
            }
        });
        TextView verdictAnalyserView=findViewById(R.id.verdict_analyser);
        verdictAnalyserView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(context,AnalyserActivity.class);
                intent1.putExtra("AnalyserType","Verdict Analyser");
                intent1.putExtra("username",username);
                HashMap<String,Integer> verdictTypeInContest,verdictTypeInPractice;
                try {
                     verdictTypeInContest=mSubmissionTrack.getVerdictTypeContest();
                     verdictTypeInPractice=mSubmissionTrack.getVerdictTypePractice();

                }
                catch (Exception e) {
                    Log.e(LOG_TAG,"aa gya catch me");
                    return;
                }
                intent1.putExtra("Hashmap Contest",verdictTypeInContest);
                intent1.putExtra("Hashmap Practice",verdictTypeInPractice);
                startActivity(intent1);
            }
        });
        TextView quesLevelAnalyserView=findViewById(R.id.ques_level_analyser);
        quesLevelAnalyserView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(context,AnalyserActivity.class);
                intent1.putExtra("AnalyserType","Question Level Analyser");
                HashMap<String,Integer> quesLevelInContest=mSubmissionTrack.getQuesLevelContest();
                HashMap<String,Integer> quesLevelInPractice=mSubmissionTrack.getQuesLevelPractice();
                intent1.putExtra("Hashmap Contest",quesLevelInContest);
                intent1.putExtra("username",username);
                intent1.putExtra("Hashmap Practice",quesLevelInPractice);
                startActivity(intent1);
            }
        });
        TextView quesRatingAnalyserView=findViewById(R.id.ques_rating_analyser);
        quesRatingAnalyserView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(context,AnalyserActivity.class);
                intent1.putExtra("AnalyserType","Rating Analyser");
                HashMap<String,Integer> quesRatingInContest=mSubmissionTrack.getQuesRatingContest();
                HashMap<String,Integer> quesRatingInPractice=mSubmissionTrack.getQuesRatingPractice();
                intent1.putExtra("Hashmap Contest",quesRatingInContest);
                intent1.putExtra("username",username);
                intent1.putExtra("Hashmap Practice",quesRatingInPractice);
                startActivity(intent1);
            }
        });
        TextView topicAnalyserView=findViewById(R.id.topics_analyser);
        topicAnalyserView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(context,AnalyserActivity.class);
                intent1.putExtra("AnalyserType","Topic Analyser");
                HashMap<String,Integer> topicInContest=mSubmissionTrack.getTopicTagContest();
                HashMap<String,Integer> topicInPractice=mSubmissionTrack.getTopicTagPractice();
                intent1.putExtra("Hashmap Contest",topicInContest);
                intent1.putExtra("username",username);
                intent1.putExtra("Hashmap Practice", topicInPractice);
                startActivity(intent1);
            }
        });
    }


    private void updateInfoUI(UserInfo userInfo) {
        TextView usernameView= findViewById(R.id.handle);
        TextView nameView= findViewById(R.id.name);
        TextView ratingView= findViewById(R.id.rating);
        TextView friendsAndContributionView= findViewById(R.id.friends_and_contribution);
        TextView lastOnlineView=  findViewById(R.id.last_online);
        TextView organisationView= findViewById(R.id.organisation);

        try
        { // Set handle
            if (userInfo.getHandle()==null) {
                LinearLayout handleLayout= findViewById(R.id.handle_layout);
                handleLayout.setVisibility(View.GONE);
            } else {
                usernameView.setText(userInfo.getHandle());
            }

            //Set name
            if (userInfo.getFirstName()==null || userInfo.getLastName()==null) {
                LinearLayout nameLayout= findViewById(R.id.name_layout);
                nameLayout.setVisibility(View.GONE);
            } else {
                nameView.setText(userInfo.getFirstName() + " " + userInfo.getLastName());
            }

            //Set ratings
            String rating = "[ " + userInfo.getRating() + " - " + userInfo.getRank() + " ] (" + userInfo.getMaxRating() + " - " + userInfo.getMaxRank() + " )";
            ratingView.setText(rating);

            //Set Friends and Contribution
            if(userInfo.getContribution()==0&&userInfo.getFriendCount()==0) {
                LinearLayout friendsAndContributionLayout= findViewById(R.id.friends_and_contribution_Layout);
                friendsAndContributionLayout.setVisibility(View.GONE);
            }
            else {
                String friendsAndContribution = userInfo.getFriendCount() + " & " + userInfo.getContribution();
                friendsAndContributionView.setText(friendsAndContribution);
            }

            //Set last online
            String lastOnline = formatTime(userInfo.getLastOnlineTime()) + " ( " + formatDate(userInfo.getLastOnlineTime()) + " )";
            lastOnlineView.setText(lastOnline);

            //Set organisation
            if(userInfo.getOrganisation()==null) {
                LinearLayout organisationLayout= findViewById(R.id.organisation_layout);
                organisationLayout.setVisibility(View.GONE);
            }
            else {
                organisationView.setText(userInfo.getOrganisation());
            }
        }
        catch (Exception e) {
            Log.e(LOG_TAG,"lag gaye");
        }
    }



    String formatDate(long time) {
        Date dateObject = new Date(time);
        SimpleDateFormat dateFormatter=new SimpleDateFormat("MMM DD, yyyy");
        return dateFormatter.format(dateObject);
    }



    String formatTime(long time) {
        Date dateObject = new Date(time);
        DateFormat timeFormatter = new SimpleDateFormat("h:mm a");
        return  timeFormatter.format(dateObject);
    }
}