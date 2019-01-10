package com.example.l.TripFlash.network;

import android.util.Log;

import com.example.l.TripFlash.model.AttractionModel;
import com.example.l.TripFlash.model.dto.CoinInfo;
import com.example.l.TripFlash.model.dto.CommentDTO;
import com.example.l.TripFlash.model.dto.FriendDTO;
import com.example.l.TripFlash.model.dto.PostDTO;
import com.example.l.TripFlash.model.dto.User;
import com.example.l.TripFlash.model.dto.UserDTO;
import com.github.mikephil.charting.data.CandleEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.web3j.crypto.Credentials;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.web3j.protocol.Web3j.build;

public class JSONParser {

    private static int length=150;

    public static User parseJsonToUser(JSONObject jsonObject) {
        User user = null;
        try {
            JSONObject userData = jsonObject.getJSONObject("data");
            user = new User(userData.getInt("userId"), userData.getString("userAccount"), userData.getString("userPassword"), userData.getString("userUsername"), userData.getString("userKey"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static String parseJsonToCityName(JSONObject jsonObject){
        String name=null;
        try{
            JSONArray districtsList=jsonObject.getJSONArray("districts");
            name=districtsList.getJSONObject(0).getString("name");
        }catch(Exception e){
            e.printStackTrace();
        }
        return name;
    }

    public static List<AttractionModel.AttractionSpot> parseJsonToAttractionList(JSONObject jsonObject){
        List<AttractionModel.AttractionSpot> attractionSpotList=new ArrayList<>();
        try{
            JSONArray poiList=jsonObject.optJSONArray("pois");
            for(int i=0;i<poiList.length();i++){
                JSONObject poi=poiList.getJSONObject(i);
                AttractionModel.AttractionSpot attractionSpot=new AttractionModel.AttractionSpot();
                attractionSpot.setId(poi.getString("id"));
                attractionSpot.setAddress(poi.getString("address"));
                attractionSpot.setLocation(poi.getString("location"));
                attractionSpot.setName(poi.getString("name"));
                attractionSpot.setType(poi.getString("type"));
                attractionSpotList.add(attractionSpot);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return attractionSpotList;
    }

    public static Long parseJsonToDistance(JSONObject jsonObject){
        Long distance=null;
        try{
            JSONObject route=jsonObject.getJSONObject("route");
            JSONArray paths=route.getJSONArray("paths");
            distance=Long.parseLong(paths.getJSONObject(0).getString("distance"));
        }catch(Exception e){
            e.printStackTrace();
        }
        return distance;
    }
}
