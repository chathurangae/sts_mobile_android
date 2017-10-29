package app.sts.com.model;


import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("user_id")
    private int userId;

    @SerializedName("user_email")
    private String userEmail;

    @SerializedName("user_name")
    private String userName;

    @SerializedName("user_title_id")
    private int userTitleId;

    @SerializedName("user_address_id")
    private int userAddressId;

    @SerializedName("user_password")
    private String userPassword;

    public User(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
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

    public int getUserTitleId() {
        return userTitleId;
    }

    public void setUserTitleId(int userTitleId) {
        this.userTitleId = userTitleId;
    }

    public int getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(int userAddressId) {
        this.userAddressId = userAddressId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
