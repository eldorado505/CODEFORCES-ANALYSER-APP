package com.rajdeeptanwar5.codeforcesanalyser;

public class UserInfo {
    private String mLastName,mFirstName,mCountry,mCity,mHandle,mRank,mMaxRank,mOrganisation;
    private int mRating,mMaxRating,mContribution,mFriendCount;
    private long mLastOnlineTime,mRegistrationTime;

    public void setRating(int rating) {
        mRating=rating;
    }
    public void setMaxRating(int maxRating) {
        mMaxRating=maxRating;
    }
    public void setContribution(int contribution) {
        mContribution=contribution;
    }
    public void setFriendCount(int friendCount) {
        mFriendCount=friendCount;
    }
    public void setLastOnlineTime(long lastOnlineTime) {
        mLastOnlineTime=lastOnlineTime;
    }
    public void setRegistrationTime(long registrationTime) {
        mRegistrationTime=registrationTime;
    }
    public void setLastName(String lastName) {
        mLastName=lastName;
    }
    public void setFirstName(String firstName) {
        mFirstName=firstName;
    }
    public void setCountry(String country) {
        mCountry=country;
    }
    public void setCity(String city) {
        mCity=city;
    }
    public void setHandle(String handle) {
        mHandle=handle;
    }
    public void setRank(String rank) {
        mRank=rank;
    }
    public void setMaxRank(String maxRank) {
        mMaxRank=maxRank;
    }
    public void setOrganisation(String organisation) {
        mOrganisation=organisation;
    }

    public String getCountry() {
        return mCountry;
    }

    public String getLastName() {
        return mLastName;
    }
    public String getFirstName() {
        return mFirstName;
    }
    public String getCity() {
        return mCity;
    }
    public String getHandle() {
        return mHandle;
    }
    public String getRank() {
        return mRank;
    }
    public String getMaxRank() {
        return mMaxRank;
    }
    public String getOrganisation() {
        return mOrganisation;
    }
    public int getRating() {
        return mRating;
    }
    public int getMaxRating() {
        return mMaxRating;
    }
    public int getContribution() {
        return mContribution;
    }
    public int getFriendCount() {
        return mFriendCount;
    }
    public long getLastOnlineTime() {
        return mLastOnlineTime;
    }
    public long getRegistrationTime() {
        return mRegistrationTime;
    }

}
