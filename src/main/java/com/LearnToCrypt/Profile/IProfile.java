package com.LearnToCrypt.Profile;

public interface IProfile {
    String getUserName();

    String getEmail();

    String getRole();

    void changePassword(String password);
}
