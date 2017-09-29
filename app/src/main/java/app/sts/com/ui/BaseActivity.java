package app.sts.com.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import app.sts.com.api.RetrofitProvider;
import app.sts.com.helpers.ProgressDialog;
import app.sts.com.helpers.StsAlert;
import io.reactivex.Observable;

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        this.progressDialog = new ProgressDialog(this);
    }

    public <T> Observable<T> makeUIObservable(Observable<T> observable) {
        return RetrofitProvider.configureObservable(this, observable);
    }

    public void launchActivity(Class<? extends Activity> activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    public void showAlert(String message) {
        StsAlert alert = new StsAlert(this, message);
        alert.show();
    }

    public void showProgress() {
        if (!progressDialog.isShowing()) {
            this.progressDialog.show();
        }
    }

    public void hideProgress() {
        if (progressDialog.isShowing()) {
            this.progressDialog.dismiss();
        }
    }
}
