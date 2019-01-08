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

    public static List<AttractionModel.AttractionSpot> parseJsonToAttractionList(JSONObject jsonObject){
        List<AttractionModel.AttractionSpot> attractionSpotList=new ArrayList<>();
        //TODO
        return attractionSpotList;
    }

}
