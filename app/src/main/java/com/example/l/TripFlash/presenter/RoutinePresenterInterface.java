package com.example.l.TripFlash.presenter;

import android.content.Context;

import java.util.Map;

public interface RoutinePresenterInterface {
    void LoadRoutineList(Context context);
    void addRoutine(Context context,Map<String,Object> map);
    void deleteRoutine(Context context,String location);
}
