package ua.deti.exprover;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import io.github.controlwear.virtual.joystick.android.JoystickView;

public class CockpitActivity extends AppCompatActivity  {

    private Typeface font;
    private ImageView imgHeading;
    private TextView tvHeading;
    private float currentDegree = 0f;

    private String rovName, rovIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get extras
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                rovName= "";
                rovIP= "10.42.0.1";
            } else {
                rovName= extras.getString("ua.deti.exprover.ROV_NAME");
                rovIP=extras.getString("ua.deti.exprover.ROV_IP");
            }
        } else {
            rovName= (String) savedInstanceState.getSerializable("ua.deti.exprover.ROV_NAME");
            rovIP= (String) savedInstanceState.getSerializable("ua.deti.exprover.ROV_IP");
        }

        // force landscape
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Hide the status (and action) bar.
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        // set layout
        setContentView(R.layout.cockpit);

        // initialize heading
//        updateHeading(81.2f);


        // change vertical joystick color
        SeekBar depth_seekBar = findViewById(R.id.tempVerticalJoystickSeekBar);
        depth_seekBar.getProgressDrawable().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_IN);
        depth_seekBar.getThumb().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_IN);
        depth_seekBar.setProgressDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        depth_seekBar.setProgress(50);
        //depth_seekBar.setThumb(new BitmapDrawable(BitmapFactory.decodeResource(getResources(), R.drawable.depth_thumb)));

        // joystick event listener
        JoystickView joystick = findViewById(R.id.joystickView);
        joystick.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
//                updateHeading(angle);
            }
        });
        // vertical controller event listeners


        // light intensity seekbar event listener
        findViewById(R.id.lightsVerticalSeekBar);

        // set up video streaming
        WebView streamview = findViewById(R.id.camera_feed);
        streamview.loadUrl("http://10.42.0.167:8000/cockpit.html?ip=10.42.0.1");//+rovIP);
        WebSettings webSettings = streamview.getSettings();
        webSettings.setAppCacheEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);



        /*******************************************************************************************
         * pop-ups logic
         ******************************************************************************************/
        imgHeading = findViewById(R.id.current_heading_img);
        tvHeading = findViewById(R.id.current_heading);

        ImageButton exitExitPopUp = findViewById(R.id.exit_exitBtn);
        LinearLayout endExpeditionBtn = findViewById(R.id.backBtn);

        Button confirmExit = findViewById(R.id.exit_confirmBtn);

        font = Typeface.createFromAsset(getAssets(), "fonts/caviar_dreams.ttf");

        final View blackout = findViewById(R.id.blackout);
        final RelativeLayout exitPopUp = findViewById(R.id.exit_popup);

        confirmExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // end activity
                finish();
            }
        });
        exitExitPopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // close exit 'window' and hide the blackout
                exitPopUp.setVisibility(View.GONE);
                blackout.setVisibility(View.GONE);
            }
        });
        blackout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent ev) {
                return true;
            }
        });
        endExpeditionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent endExpIntent = new Intent(getApplicationContext(), CockpitActivity.class);
                endExpIntent.putExtra("ua.deti.exprover.ROV_NAME",
                        ((TextView)findViewById(R.id.rov_name)).getText());
                endExpIntent.putExtra("ua.deti.exprover.ROV_IP", rovIP);
                startActivity(endExpIntent);
                finish();
            }
        });

        // lights' pull-up
        LinearLayout lightsPullUpTrigger = findViewById(R.id.lights_popup_hint);
        final LinearLayout lightsPullUp = findViewById(R.id.lights_popup_layout);
        final ImageView lightsPullUpIcon = findViewById(R.id.lights_popup_action_icon);
        lightsPullUpTrigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // toogle visibility
                if (lightsPullUp.getVisibility() == View.INVISIBLE) {
                    lightsPullUp.setVisibility(View.VISIBLE);
                    lightsPullUpIcon.setImageResource(R.drawable.icons8_down_100);
                } else {
                    lightsPullUp.setVisibility(View.INVISIBLE);
                    lightsPullUpIcon.setImageResource(R.drawable.icons8_up_100);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        View blackout = findViewById(R.id.blackout);

        if (blackout.getVisibility() != View.GONE) {
            blackout.setVisibility(View.GONE);
        } else {
            // query the user on whether they want to end expedition
            blackout.setVisibility(View.VISIBLE);
            RelativeLayout exitPopUp = findViewById(R.id.exit_popup);
            exitPopUp.setVisibility(View.VISIBLE);
        }
    }

//    private void updateHeading(float angle) {
//        float degree = Math.round(angle);
//        tvHeading.setText(String.format("%.1f", degree) + "°");
//
//        // create a rotation animation (reverse turn degree degrees)
//        RotateAnimation ra = new RotateAnimation(
//                currentDegree,
//                -degree,
//                Animation.RELATIVE_TO_SELF, 0.5f,
//                Animation.RELATIVE_TO_SELF,
//                0.5f);
//
//        // set the animation after the end of the reservation status
//        ra.setFillAfter(true);
//        ra.setDuration(210);
//
//        // Start the animation
//        imgHeading.startAnimation(ra);
//        currentDegree = -degree;
//    }
}