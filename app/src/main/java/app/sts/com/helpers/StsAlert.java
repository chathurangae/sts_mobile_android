package app.sts.com.helpers;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import app.sts.com.R;

public class StsAlert extends Dialog {

    private static final String DEFAULT_TITLE = "STS";
    private static final String DEFAULT_BUTTON_TEXT = "OK";

    private String title;
    private String message;
    private String buttonText;
    private OnClickListener onClickListener;

    public StsAlert(@NonNull Context context, String message) {
        this(context, DEFAULT_TITLE, message, DEFAULT_BUTTON_TEXT, Dialog::dismiss);
    }

    public StsAlert(@NonNull Context context, String message,
                    OnClickListener onClickListener) {
        this(context, DEFAULT_TITLE, message, DEFAULT_BUTTON_TEXT, onClickListener);
    }

    StsAlert(@NonNull Context context, String title, String message,
             String buttonText, OnClickListener onClickListener) {
        super(context);
        this.title = title;
        this.message = message;
        this.buttonText = buttonText;
        this.onClickListener = onClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
        Window window = this.getWindow();
        if (null != window) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        this.setContentView(R.layout.sts_alert);
        TextView titleView = this.findViewById(R.id.title);
        titleView.setText(this.title);
        TextView messageView = this.findViewById(R.id.message);
        messageView.setText(this.message);
        Button button = this.findViewById(R.id.ok_button);
        button.setText(this.buttonText);
        button.setOnClickListener(view -> this.onClickListener.onClick(this));
    }

    public interface OnClickListener {
        void onClick(StsAlert alert);
    }
}