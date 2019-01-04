package com.example.l.TripFlash.model;

import com.example.l.TripFlash.model.dto.User;

public interface OnLoginListener {
    void loginSuccess(User user);
    void loginFail();
}
