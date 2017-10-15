package app.sts.com.model;


import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    private int userId;

    @SerializedName("user_email")
    private String userEmail;

    @SerializedName("user_name")
    private String userName;

    @SerializedName("user_designation")
    private String userDesignation;

    @SerializedName("user_address")
    private String userAddress;

    private String password;

    public User(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserDesignation() {
        return userDesignation;
    }

    public void setUserDesignation(String userDesignation) {
        this.userDesignation = userDesignation;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
