package app.sts.com.api;

import app.sts.com.model.AuthenticationResponse;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthenticationService {

    @POST("authenticate")
    @FormUrlEncoded
    Observable<AuthenticationResponse> authenticate(@Field("username") String username
            , @Field("password") String password);
}
