package com.example.l.TripFlash;

import android.app.Application;

import com.example.l.TripFlash.model.dto.User;

public class GlobalData extends Application {
    private User primaryUser;

    public User getPrimaryUser() {
        return primaryUser;
    }

    public void setPrimaryUser(User primaryUser) {
        this.primaryUser = primaryUser;
    }
}
