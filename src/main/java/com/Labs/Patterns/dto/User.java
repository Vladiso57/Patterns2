package com.Labs.Patterns.dto;

public class User {
    private int idUser;
    private String nickname;
    private String password;

    public User(){}
    public User(String nickname, String password,int idUser) {
        this.nickname = nickname;
        this.password = password;
        this.idUser=idUser;
    }

    public User(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
