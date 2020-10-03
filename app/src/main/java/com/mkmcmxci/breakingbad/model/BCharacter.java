package com.mkmcmxci.breakingbad.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class BCharacter {

    @PrimaryKey(autoGenerate = true)
    public int uuid;
    @ColumnInfo(name = "char_id")
    @SerializedName("char_id")
    private int char_id;
    @SerializedName("name")
    @ColumnInfo(name = "char_name")
    private String name;
    @SerializedName("birthday")
    @ColumnInfo(name = "char_birthday")
    private String birthday;
    @SerializedName("img")
    @ColumnInfo(name = "char_img")
    private String img;
    @SerializedName("status")
    @ColumnInfo(name = "char_status")
    private String status;
    @SerializedName("nickname")
    @ColumnInfo(name = "char_nickname")
    private String nickname;
    @SerializedName("portrayed")
    @ColumnInfo(name = "char_portrayed")
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
