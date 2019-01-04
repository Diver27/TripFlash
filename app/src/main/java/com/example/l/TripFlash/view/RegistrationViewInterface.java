package com.example.l.TripFlash.view;


import com.example.l.TripFlash.model.dto.User;

public interface RegistrationViewInterface {
    String getAccount();
    String getPassword();
    void registrationSuccess(User user);
    void registrationFail();
}
