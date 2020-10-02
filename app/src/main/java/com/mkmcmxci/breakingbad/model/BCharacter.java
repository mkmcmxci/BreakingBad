package com.mkmcmxci.breakingbad.model;

import com.google.gson.annotations.SerializedName;

public class BCharacter {

    @SerializedName("char_id")
    private int char_id;
    @SerializedName("name")
    private String name;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("img")
    private String img;
    @SerializedName("status")
    private String status;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("portrayed")
    private String portrayed;

    public BCharacter() {
    }

    public BCharacter(int char_id, String name, String birthday, String img, String status, String nickname, String portrayed) {
        this.char_id = char_id;
        this.name = name;
        this.birthday = birthday;
        this.img = img;
        this.status = status;
        this.nickname = nickname;
        this.portrayed = portrayed;
    }

    public int getChar_id() {
        return char_id;
    }

    public void setChar_id(int char_id) {
        this.char_id = char_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPortrayed() {
        return portrayed;
    }

    public void setPortrayed(String portrayed) {
        this.portrayed = portrayed;
    }
}
