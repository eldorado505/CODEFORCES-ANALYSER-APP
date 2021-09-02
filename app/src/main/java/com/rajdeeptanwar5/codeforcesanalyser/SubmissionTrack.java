package com.rajdeeptanwar5.codeforcesanalyser;

import android.content.Intent;
import android.util.Log;

import java.util.HashMap;
import java.util.HashSet;

public class SubmissionTrack {
    private final String LOG_TAG=SubmissionTrack.class.getName();
    private HashMap<String,Integer> mParticipationType=new HashMap<>();
    private HashMap<String,Integer> mQuesLevelContest=new HashMap<>();
    private HashMap<String,Integer> mQuesLevelPractice=new HashMap<>();
    private HashMap<String,Integer> mQuesRatingInContest=new HashMap<>();
    private  HashMap<String,Integer> mQuesRatingInPractice=new HashMap<>();
    private HashMap<String,Integer> mVerdictTypeInContest=new HashMap<>();
    private HashMap<String,Integer> mVerdictTypeInPractice=new HashMap<>();
    private HashMap<String,Integer> mTopicTagInContest=new HashMap<>();
    private HashMap<String,Integer> mTopicTagInPractice=new HashMap<>();


    public void incrementParticipationType(String participantType) {
        if(mParticipationType.containsKey(participantType)) {
            mParticipationType.put(participantType,mParticipationType.get(participantType)+ 1);
        }
        else mParticipationType.put(participantType,1);
    }

    public void incrementQuesLevelContest(String quesLevel) {
        if(mQuesLevelContest.containsKey(quesLevel)) {
            mQuesLevelContest.put(quesLevel,mQuesLevelContest.get(quesLevel)+1);
        }
        else mQuesLevelContest.put(quesLevel,1);
    }

    public void incrementQuesLevelPractice(String quesLevel) {
        if(mQuesLevelPractice.containsKey(quesLevel)) {
            mQuesLevelPractice.put(quesLevel,mQuesLevelPractice.get(quesLevel)+1);
        }
        else mQuesLevelPractice.put(quesLevel,1);
    }

    public void incrementQuesRatingContent(int quesRating) {
        String qRating = quesRating+"";
        if(mQuesRatingInContest.containsKey(qRating)){
            mQuesRatingInContest.put(qRating,mQuesRatingInContest.get(qRating)+1);
        }
        else {
            mQuesRatingInContest.put(qRating,1);
        }
    }

    public void incrementQuesRatingPractice(int quesRating) {
        String qRating = quesRating+"";
        if(mQuesRatingInPractice.containsKey(qRating)){
            mQuesRatingInPractice.put(qRating,mQuesRatingInPractice.get(qRating)+1);
        }
        else {
            mQuesRatingInPractice.put(qRating,1);
        }
    }

    public void incrementVerdictTypeContent(String verdictType) {
        int count = mVerdictTypeInContest.containsKey(verdictType) ? mVerdictTypeInContest.get(verdictType) : 0;
        mVerdictTypeInContest.put(verdictType, count + 1);
    }

    public void incrementVerdictTypePractice(String verdictType) {
        int count = mVerdictTypeInPractice.containsKey(verdictType) ? mVerdictTypeInPractice.get(verdictType) : 0;
        mVerdictTypeInPractice.put(verdictType, count + 1);
    }

    public void incrementTopicTagsContest(String topicTags) {
        if(mTopicTagInContest.containsKey(topicTags)) {
            mTopicTagInContest.put(topicTags,mTopicTagInContest.get(topicTags)+1);
        }
        else {
            mTopicTagInContest.put(topicTags,1);
        }
    }

    public void incrementTopicTagsPractice(String topicTags) {
        if(mTopicTagInPractice.containsKey(topicTags)) {
            mTopicTagInPractice.put(topicTags,mTopicTagInPractice.get(topicTags)+1);
        }
        else {
            mTopicTagInPractice.put(topicTags,1);
        }
    }

    public HashMap<String,Integer> getParticiapationType() {
        return mParticipationType;
    }
    public HashMap<String,Integer> getQuesLevelContest() {
        return mQuesLevelContest;
    }
    public HashMap<String,Integer> getQuesLevelPractice() {
        return mQuesLevelPractice;
    }
    public  HashMap<String,Integer> getQuesRatingContest() {
        return mQuesRatingInContest;
    }
    public  HashMap<String,Integer> getQuesRatingPractice() {
        return mQuesRatingInPractice;
    }
    public HashMap<String,Integer> getVerdictTypeContest() throws NullPointerException
    {
        try {
            return mVerdictTypeInContest;
        }
        catch (Exception e) {
            Log.e(LOG_TAG," lgg gye");

        }
        return new HashMap<String,Integer>();

    }
    public HashMap<String,Integer> getVerdictTypePractice() {
        return mVerdictTypeInPractice;
    }
    public HashMap<String,Integer> getTopicTagContest() {
        return mTopicTagInContest;
    }
    public HashMap<String,Integer> getTopicTagPractice() {
        return mTopicTagInPractice;
    }
}
