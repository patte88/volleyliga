package com.pocketpalsson.volleyball.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MatchModel {


    public enum Type {
        PAST,
        LIVE,
        FUTURE
    }

    public String championshipName; //Name of the Championship
    public String seasonDescription; //Name of the Season
    public int championshipLegNumber; //Round Number
    public String championshipLegName; //Round Name
    public String legType; //Type of the Round - i.e. Final Four, Main Phase, etc

    public int setsWonByHome; //Set Won by the Home Team
    public int setsWonByGuest; //Set Won by the Guest Team


    public String referee1Name; //Name of the 1st Referee
    public String referee2Name; //Name of the 2nd Referee
    public int spectators; //Number of spectators
    public double receipts; //Match Receipts


    //Cleaned up parameters
    public int championshipMatchID; //ID of the Match in Data Project's DB
    public int federationMatchNumber = 110236; //Identifies in unique way the match in Organization's DB
    public String federationMatchID; //Identifies in unique way the match in Organization's DB
    public String stadium; //Stadium Name
    public String stadiumCity; //Stadium City
    public Calendar matchDateTime; //Date and Time of the Match
    public int matchId;
    public TeamModel teamHome = new TeamModel("Test 1"), teamGuest = new TeamModel("Test 2");
    public HashMap<Integer, SetInfoModel> setResults = new HashMap<>();
    public MatchStatisticsModel statistics = new MatchStatisticsModel();
    public boolean goldenSetPlayed;

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy-HH:mm", Locale.getDefault());

    public List<SetInfoModel> getSetList() {
        List<SetInfoModel> result = new ArrayList<>();
        List<Integer> keys = Arrays.asList(1, 2, 3, 4, 5, 6);
        for (Integer key : keys) {
            if(setResults.containsKey(key)){
                result.add(setResults.get(key));
            }
        }
        return result;
    }

    public SetInfoModel getSetInfo(int number) {
        if (setResults.containsKey(number)) {
            return setResults.get(number);
        }
        return null;
    }

    public static String getDateString(Calendar date) {
        return dateFormat.format(date.getTime());
    }

    public void setSetInfo(SetInfoModel set, int number) {
        setResults.put(number, set);
    }

    public void computeTotalPoints() {
//        statistics.totalPoints = new StatisticModel("Total points");
//        for (SetInfoModel setInfo : setResults.values()) {
//            statistics.totalPoints.addToHomeStat(setInfo.scoreHome);
//            statistics.totalPoints.addToGuestStat(setInfo.scoreGuest);
//        }
    }

    public String getTitle() {
        return teamHome.shortName + " " + setsWonByHome + " - " + setsWonByGuest + " " + teamGuest.shortName;
    }

    public boolean isInFuture() {
        return System.currentTimeMillis() < matchDateTime.getTimeInMillis();
    }

    public Type getType() {
        if(isInFuture()){
            return Type.FUTURE;
        }
        if(setsWonByGuest == 3 || setsWonByHome == 3){
            return Type.PAST;
        }
        return Type.LIVE;
    }

    public static class MatchComparator implements Comparator<MatchModel> {
        public int compare(MatchModel entry1, MatchModel entry2) {
            return entry1.matchDateTime.compareTo(entry2.matchDateTime);
        }
    }
}
