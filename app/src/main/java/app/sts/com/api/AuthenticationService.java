package app.sts.com.api;

import app.sts.com.model.AuthenticationResponse;
import app.sts.com.model.User;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthenticationService {

    @POST("user/login")
    Observable<AuthenticationResponse> authenticate(@Body User userAuth);
}
