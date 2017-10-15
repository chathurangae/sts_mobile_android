package app.sts.com.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import app.sts.com.R;
import app.sts.com.api.ApiError;
import app.sts.com.api.AuthenticationService;
import app.sts.com.api.RetrofitProvider;
import app.sts.com.helpers.MarshMallowPermission;
import app.sts.com.helpers.PreferenceManager;
import app.sts.com.helpers.ValidationHelper;
import app.sts.com.model.AuthenticationResponse;
import app.sts.com.model.User;
import app.sts.com.ui.BaseActivity;
import app.sts.com.ui.ShellActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import io.reactivex.disposables.Disposable;

public class Login extends BaseActivity {

    private AuthenticationService authService;
    private Disposable subscription;
    @BindView(R.id.email_field)
    EditText emailField;
    @BindView(R.id.password_field)
    EditText passwordField;
    private PreferenceManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.authService = RetrofitProvider.createRetrofit(this).create(AuthenticationService.class);
        ButterKnife.bind(this);
        manager = new PreferenceManager(this);
        MarshMallowPermission permission = new MarshMallowPermission(this);
        permission.checkRuntimePermissions();
        initViews();
    }

    private void initViews() {
        if (null != new PreferenceManager(this).getUser()) {
            goToLandingPage();
        }
    }

    @OnEditorAction(R.id.password_field)
    public boolean onEditorAction(int actionId, KeyEvent event) {
        if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
                || (actionId == EditorInfo.IME_ACTION_DONE)) {
            userLogin();
            return true;
        }
        return false;
    }

    @OnClick(R.id.sign_in_button)
    public void login() {
        userLogin();
    }

    private void userLogin() {
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString();
        if (!ValidationHelper.isValidEmail(email)) {
            emailField.requestFocus();
            emailField.setError(getString(R.string.error_message_email));
        } else if (TextUtils.isEmpty(password)) {
            passwordField.requestFocus();
            passwordField.setError(getString(R.string.error_message_password));
        } else {
            this.showProgress();
            User user = new User(email, password);
            subscription = this.makeUIObservable(authService.authenticate(user))
                    .subscribe(
                            msg -> {
                                this.hideProgress();
                                onSuccess(msg);
                            },
                            error -> {
                                this.hideProgress();
                                if (error instanceof ApiError) {
                                    this.showAlert(error.getMessage());
                                } else {
                                    this.showAlert(error.getMessage());
                                }
                            });
        }
    }

    private void onSuccess(AuthenticationResponse response) {
        manager.putUser(response.getUser());
        goToLandingPage();
    }

    private void goToLandingPage() {
        launchActivity(ShellActivity.class);
        Login.this.overridePendingTransition(R.anim.animation, R.anim.animation2);
        this.finish();
    }

    @Override
    protected void onDestroy() {
        if (this.subscription != null) {
            this.subscription.dispose();
        }
        super.onDestroy();
    }
}
