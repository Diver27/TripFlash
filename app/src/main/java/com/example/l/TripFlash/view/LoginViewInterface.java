package com.example.l.TripFlash.view;


import com.example.l.TripFlash.model.dto.User;

public interface LoginViewInterface {
    String getAccount();
    String getPassword();
    void clearUserId();
    void clearPassword();
    void enterMainActivity(User user);
    void showFail();
}
