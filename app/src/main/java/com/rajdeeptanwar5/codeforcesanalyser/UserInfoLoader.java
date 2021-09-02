package com.rajdeeptanwar5.codeforcesanalyser;
import android.content.Context;
import android.util.Log;
import android.content.AsyncTaskLoader;
import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;


public class UserInfoLoader extends AsyncTaskLoader<UserInfo> {
    private  String USERINFO_REQUEST_URL="https://codeforces.com/api/user.info?handles=";
    public static String LOG_TAG=UserInfoLoader.class.getName();
    private String mUserName;
    public UserInfoLoader(Context context, String userName) {
        super(context);
        mUserName=userName;
        USERINFO_REQUEST_URL+=mUserName;
//        Log.e(LOG_TAG,USERINFO_REQUEST_URL);
    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }
    @Nullable
    @Override
    public UserInfo loadInBackground() {
        UserInfo userInfo= null;

        URL url=NetworkRequest.createUrl(USERINFO_REQUEST_URL);
        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = "";
        try {
            assert url != null;
            jsonResponse = NetworkRequest.makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG,"Error while making an request",e);
            return userInfo;
        }
        userInfo=extractUserInfo(jsonResponse);

        return userInfo;
    }

    private UserInfo extractUserInfo(String jsonResponse) {
        UserInfo userInfo = new UserInfo();
        if(jsonResponse==null|| jsonResponse.equals("")) return userInfo;
        try {
            JSONObject root = new JSONObject(jsonResponse);
            JSONArray result=root.getJSONArray("result");
            if(result.length()==0) {
                return userInfo;
            }

             JSONObject userDetail=result.getJSONObject(0);

            if(userDetail.has("lastName")) userInfo.setLastName(userDetail.getString("lastName"));
            if(userDetail.has("country")) userInfo.setCountry(userDetail.getString("country"));
            if(userDetail.has("city")) userInfo.setCity(userDetail.getString("city"));
            if(userDetail.has("rating")) userInfo.setRating(userDetail.getInt("rating"));
            if(userDetail.has("handle")) userInfo.setHandle(userDetail.getString("handle"));
            if(userDetail.has("firstName")) userInfo.setFirstName(userDetail.getString("firstName"));
            if(userDetail.has("organization")) userInfo.setOrganisation(userDetail.getString("organization"));
            if(userDetail.has("rank")) userInfo.setRank(userDetail.getString("rank"));
            if(userDetail.has("maxRank")) userInfo.setMaxRank(userDetail.getString("maxRank"));
            if(userDetail.has("maxRating")) userInfo.setMaxRating(userDetail.getInt("maxRating"));
            if(userDetail.has("contribution")) userInfo.setContribution(userDetail.getInt("contribution"));
            if(userDetail.has("friendOfCount")) userInfo.setFriendCount(userDetail.getInt("friendOfCount"));
            if(userDetail.has("lastOnlineTimeSeconds")) userInfo.setLastOnlineTime(userDetail.getLong("lastOnlineTimeSeconds"));
            if(userDetail.has("registrationTimeSeconds")) userInfo.setRegistrationTime(userDetail.getLong("registrationTimeSeconds"));
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error while extracting");
            return new UserInfo();
        }
        return userInfo;
    }
}
