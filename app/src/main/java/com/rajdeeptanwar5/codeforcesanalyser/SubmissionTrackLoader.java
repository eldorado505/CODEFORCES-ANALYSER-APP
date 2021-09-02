package com.rajdeeptanwar5.codeforcesanalyser;
import android.content.Context;
import android.util.Log;
import android.content.AsyncTaskLoader;
import android.widget.ListView;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SubmissionTrackLoader extends AsyncTaskLoader<SubmissionTrack> {
    private String mUsername;
    private String LOG_TAG=SubmissionTrackLoader.class.getName();
    private  String SUBMISSION_REQUEST_URL="https://codeforces.com/api/user.status?handle=";
    public SubmissionTrackLoader(Context context,String username) {
        super(context);
        mUsername=username;
        SUBMISSION_REQUEST_URL+=username+"&from=1&count=10000";
        Log.e(LOG_TAG,SUBMISSION_REQUEST_URL);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public SubmissionTrack loadInBackground() {
        SubmissionTrack submissionTrack=new SubmissionTrack();
        URL url=NetworkRequest.createUrl(SUBMISSION_REQUEST_URL);
        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = "";
        try {
            jsonResponse = NetworkRequest.makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG,"Error while making an request",e);
            return submissionTrack;
        }
        submissionTrack=extractUserInfo(jsonResponse);
        return submissionTrack;
    }

    SubmissionTrack extractUserInfo(String jsonResponse) {
        SubmissionTrack submissionTrack =new SubmissionTrack();
        if(jsonResponse==null|| jsonResponse.equals("")) return submissionTrack;
        try {
            JSONObject root = new JSONObject(jsonResponse);
            JSONArray result = root.getJSONArray("result");
            if (result.length() == 0) {
                return submissionTrack;
            }
//            int contest=0;
            for(int i=0;i<result.length();i++) {
                JSONObject submissionDetail=result.getJSONObject(i);
                JSONObject author=submissionDetail.getJSONObject("author");
                JSONObject problem=submissionDetail.getJSONObject("problem");
                JSONArray tags=problem.getJSONArray("tags");

                String participationType=author.getString("participantType");
                boolean inContest=false;
                if(participationType.equals("VIRTUAL") || participationType.equals("CONTESTANT")) inContest=true;
//                if(inContest) contest=contest+1;
                String verdict = submissionDetail.getString("verdict");
//                Log.e(LOG_TAG,verdict);
                String verdictType;
                switch (verdict) {
                    case "OK":
                        verdictType = "AC";
                        break;
                    case "WRONG_ANSWER":
                        verdictType = "WA";
                        break;
                    case "RUNTIME_ERROR":
                        verdictType = "RTE";
                        break;
                    case "TIME_LIMIT_EXCEEDED":
                        verdictType = "TLE";
                        break;
                    case "COMPILATION_ERROR":
                        verdictType = "CE";
                        break;
                    default:
                        verdictType = "Others";
                        break;
                }
//                Log.e(LOG_TAG,verdictType);
                if(inContest) {
                    submissionTrack.incrementParticipationType("CONTESTANT");
                    submissionTrack.incrementVerdictTypeContent(verdictType);
                    if(verdictType.equals("AC")) {
                        submissionTrack.incrementQuesLevelContest(problem.getString("index"));
                        if(problem.has("rating")) submissionTrack.incrementQuesRatingContent(problem.getInt("rating"));
                        for (int j = 0; j < tags.length(); j++) {
                            submissionTrack.incrementTopicTagsContest(tags.getString(j));
                        }
                    }
                }
                else {
                    submissionTrack.incrementParticipationType("PRACTICE");
                    submissionTrack.incrementVerdictTypePractice(verdictType);
                    if(verdictType.equals("AC")) {
                        submissionTrack.incrementQuesLevelPractice(problem.getString("index"));
                        if(problem.has("rating")) submissionTrack.incrementQuesRatingPractice(problem.getInt("rating"));
                        for (int j = 0; j < tags.length(); j++) {
                            submissionTrack.incrementTopicTagsPractice(tags.getString(j));
                        }
                    }
                }

            }
//            Log.e(LOG_TAG,""+contest);
//            HashMap<String,Integer> hm= submissionTrack.getTopicTagContest();
//            Iterator hmIterator = hm.entrySet().iterator();
//            while (hmIterator.hasNext()) {
//                Map.Entry mapElement = (Map.Entry)hmIterator.next();
//                int value = (int)mapElement.getValue();
//                Log.e(LOG_TAG,"key "+ mapElement.getKey() + ", value = " + value);
//            }

        }
        catch (Exception e) {
            Log.e(LOG_TAG, "Error while extracting the");
            return new SubmissionTrack();
        }
        return submissionTrack;
    }
}
