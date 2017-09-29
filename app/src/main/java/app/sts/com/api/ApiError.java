package app.sts.com.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiError extends Throwable {

    @SerializedName("error_code")
    @Expose
    private String errorCode;
    @SerializedName("message")
    @Expose
    private String errorMessage;
    @SerializedName("status_code")
    @Expose
    private int statusCode;

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}
