package ua.deti.exprover;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ua.deti.exprover.models.NavItem;
import ua.deti.exprover.models.ROV;
import ua.deti.exprover.utils.DrawerListAdapter;
import ua.deti.exprover.utils.PreferencesFragment;
import ua.deti.exprover.utils.ROVAdapter;
import ua.deti.exprover.utils.StringUtils;

public class MainScreenActivity extends AppCompatActivity {

    // rov list
    private List<ROV> rovList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ROVAdapter mAdapter;
    // firebase
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseStorage storage;
    private StorageReference storageRef;

    // side menu
    private static String TAG = MainScreenActivity.class.getSimpleName();
    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        // More info: http://codetheory.in/difference-between-setdisplayhomeasupenabled-sethomebuttonenabled-and-setdisplayshowhomeenabled/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // history
        Button historyBtn = findViewById(R.id.historyBtn);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/caviar_dreams.ttf");
        historyBtn.setTypeface(font);

        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
//                Intent historyIntent = new Intent(getApplicationContext(), MjpegViewer.class);
//                startActivity(historyIntent);

                Intent historyIntent = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(historyIntent);
            }
        });

        /*******************************************************************************************
         * ROV list
         ******************************************************************************************/
        recyclerView = findViewById(R.id.recycler_view);
        updateROVListView();

        // firebase-related configs
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        int genericROV = getResources().
                getIdentifier("@drawable/icons8_colourful_sub_100",
                        "drawable",
                        "ua.deti.exprover");
        rovList.add(new ROV("10.42.0.1", "Pro 4", genericROV));
        updateROVListView();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(user.getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot result = task.getResult();
                            Map<String, Object> rovMap = result.getData();

                            if (rovMap.size() > 0) {
                                for (final Map.Entry<String, Object> entry : rovMap.entrySet()) {

                                    final String rovName = entry.getKey();
                                    StorageReference imageRef = storageRef.child("images/" + StringUtils.md5(rovName) + ".jpg");

                                    Log.d(TAG, "images/" + StringUtils.md5(rovName) + ".jpg");

                                    try {
                                        final File localFile = File.createTempFile("images", "jpg");
                                        imageRef.getFile(localFile)
                                            .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                                @Override
                                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                                    Log.d(TAG, "got image from firebase storage");
                                                    rovList.add(new ROV((String) entry.getValue(), rovName, localFile));
                                                    updateROVListView();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception exception) {
                                                    Log.d(TAG, "failed to get image from firebase storage");
                                                    int genericROV = getResources().
                                                            getIdentifier("@drawable/icons8_colourful_sub_100",
                                                                    "drawable",
                                                                    "ua.deti.exprover");
                                                    rovList.add(new ROV((String) entry.getValue(), rovName, genericROV));
                                                    updateROVListView();
                                                }
                                            });
                                    } catch (Exception e) {
                                        Log.e(TAG, "Error: " + e, task.getException());
                                        Toast.makeText(MainScreenActivity.this,
                                                "Error retrieving ROV image", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }

                            Log.d(TAG, "Setting up mAdapter & recyclerView; length: " + rovList.size());
                            for (ROV r : rovList) {
                                Log.d(TAG, r.toString());
                            }

                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });



        /*******************************************************************************************
         * side menu
         ******************************************************************************************/
        mNavItems.add(new NavItem("Google Drive", "Sync with Google Drive", R.drawable.google_drive));
        mNavItems.add(new NavItem("Pin Lock", "Configure a pin lock for extra security", R.drawable.icons8_lock_100));
//        mNavItems.add(new NavItem("About", "Get to know about us", R.drawable.launch_ic));

        // DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu();
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Log.d(TAG, "onDrawerClosed: " + getTitle());

                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // Populate the Navigtion Drawer with options
        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        mDrawerList = (ListView) findViewById(R.id.navList);
        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        mDrawerList.setAdapter(adapter);

        // Drawer Item click listeners
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position);
            }
        });

        // listeners for confirm buttons and out of 'modal' clicks
        Button confirmDrive = findViewById(R.id.drive_confirm);
        final Button confirmPin = findViewById(R.id.pin_lock_confirm);
        confirmPin.setTypeface(font);
        confirmDrive.setTypeface(font);

        final View blackout = findViewById(R.id.blackout);
        final RelativeLayout pin_lockRL = findViewById(R.id.pin_lock);
        final RelativeLayout google_driveRL = findViewById(R.id.google_drive);

        confirmDrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // close settings 'window' and hide the blackout
                google_driveRL.setVisibility(View.GONE);
                blackout.setVisibility(View.GONE);
            }
        });
        confirmPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // close settings 'window' and hide the blackout
                if (confirmPin.getCurrentTextColor() == getResources().getColor(R.color.white)) {
                    pin_lockRL.setVisibility(View.GONE);
                    blackout.setVisibility(View.GONE);
                }
            }
        });
        blackout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent ev) {
                // close open 'windows' and hide the blackout if click is outside the windows
                if (google_driveRL.getVisibility() != View.GONE) {
                    if ((ev.getX() > google_driveRL.getRight() || ev.getX() < google_driveRL.getLeft())
                                || (ev.getY() < google_driveRL.getTop() || ev.getY() > google_driveRL.getBottom())) {
                        google_driveRL.setVisibility(View.GONE);
                        blackout.setVisibility(View.GONE);

                    }
                } else {
                    if ((ev.getX() > pin_lockRL.getRight() || ev.getX() < pin_lockRL.getLeft())
                            || (ev.getY() < pin_lockRL.getTop() || ev.getY() > pin_lockRL.getBottom())) {
                        pin_lockRL.setVisibility(View.GONE);
                        blackout.setVisibility(View.GONE);

                    }
                }
                return true;
            }
        });

        // pin code input (i might be ashamed, ok? :/)
        final Button digit0Btn = findViewById(R.id.num_0);
        final Button digit1Btn = findViewById(R.id.num_1);
        final Button digit2Btn = findViewById(R.id.num_2);
        final Button digit3Btn = findViewById(R.id.num_3);
        final Button digit4Btn = findViewById(R.id.num_4);
        final Button digit5Btn = findViewById(R.id.num_5);
        final Button digit6Btn = findViewById(R.id.num_6);
        final Button digit7Btn = findViewById(R.id.num_7);
        final Button digit8Btn = findViewById(R.id.num_8);
        final Button digit9Btn = findViewById(R.id.num_9);
        final Button digitBckBtn = findViewById(R.id.num_bck);
        final EditText digit1ET = findViewById(R.id.digit_1);
        final EditText digit2ET = findViewById(R.id.digit_2);
        final EditText digit3ET = findViewById(R.id.digit_3);
        final EditText digit4ET = findViewById(R.id.digit_4);
        final EditText digit5ET = findViewById(R.id.digit_5);
        final EditText digit6ET = findViewById(R.id.digit_6);

        digit1ET.setFocusable(false);
        digit2ET.setFocusable(false);
        digit3ET.setFocusable(false);
        digit4ET.setFocusable(false);
        digit5ET.setFocusable(false);
        digit6ET.setFocusable(false);

        digit0Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (digit0Btn.getCurrentTextColor() == getResources().getColor(R.color.dark_grey_blue)) {
                    if (digit1ET.getText() + "" == "") {
                        digit1ET.setText("0");
                    } else if (digit2ET.getText() + "" == "") {
                        digit2ET.setText("0");
                    } else if (digit3ET.getText() + "" == "") {
                        digit3ET.setText("0");
                    } else if (digit4ET.getText() + "" == "") {
                        digit4ET.setText("0");
                    } else if (digit5ET.getText() + "" == "") {
                        digit5ET.setText("0");
                    } else if (digit6ET.getText() + "" == "") {
                        digit6ET.setText("0");
                        confirmPin.setBackgroundColor(getResources().getColor(R.color.colorPrimaryXDark));
                        confirmPin.setTextColor(getResources().getColor(R.color.white));
                    }
                }
            }
        });
        digit1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (digit1Btn.getCurrentTextColor() == getResources().getColor(R.color.dark_grey_blue)) {
                    if (digit1ET.getText() + "" == "") {
                        digit1ET.setText("1");
                    } else if (digit2ET.getText() + "" == "") {
                        digit2ET.setText("1");
                    } else if (digit3ET.getText() + "" == "") {
                        digit3ET.setText("1");
                    } else if (digit4ET.getText() + "" == "") {
                        digit4ET.setText("1");
                    } else if (digit5ET.getText() + "" == "") {
                        digit5ET.setText("1");
                    } else if (digit6ET.getText() + "" == "") {
                        digit6ET.setText("1");
                        confirmPin.setBackgroundColor(getResources().getColor(R.color.colorPrimaryXDark));
                        confirmPin.setTextColor(getResources().getColor(R.color.white));
                    }
                }
            }
        });
        digit2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (digit2Btn.getCurrentTextColor() == getResources().getColor(R.color.dark_grey_blue)) {
                    if (digit1ET.getText() + "" == "") {
                        digit1ET.setText("2");
                    } else if (digit2ET.getText() + "" == "") {
                        digit2ET.setText("2");
                    } else if (digit3ET.getText() + "" == "") {
                        digit3ET.setText("2");
                    } else if (digit4ET.getText() + "" == "") {
                        digit4ET.setText("2");
                    } else if (digit5ET.getText() + "" == "") {
                        digit5ET.setText("2");
                    } else if (digit6ET.getText() + "" == "") {
                        digit6ET.setText("2");
                        confirmPin.setBackgroundColor(getResources().getColor(R.color.colorPrimaryXDark));
                        confirmPin.setTextColor(getResources().getColor(R.color.white));
                    }
                }
            }
        });
        digit3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (digit3Btn.getCurrentTextColor() == getResources().getColor(R.color.dark_grey_blue)) {
                    if (digit1ET.getText() + "" == "") {
                        digit1ET.setText("3");
                    } else if (digit2ET.getText() + "" == "") {
                        digit2ET.setText("3");
                    } else if (digit3ET.getText() + "" == "") {
                        digit3ET.setText("3");
                    } else if (digit4ET.getText() + "" == "") {
                        digit4ET.setText("3");
                    } else if (digit5ET.getText() + "" == "") {
                        digit5ET.setText("3");
                    } else if (digit6ET.getText() + "" == "") {
                        digit6ET.setText("3");
                        confirmPin.setBackgroundColor(getResources().getColor(R.color.colorPrimaryXDark));
                        confirmPin.setTextColor(getResources().getColor(R.color.white));
                    }
                }
            }
        });
        digit4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (digit4Btn.getCurrentTextColor() == getResources().getColor(R.color.dark_grey_blue)) {
                    if (digit1ET.getText() + "" == "") {
                        digit1ET.setText("4");
                    } else if (digit2ET.getText() + "" == "") {
                        digit2ET.setText("4");
                    } else if (digit3ET.getText() + "" == "") {
                        digit3ET.setText("4");
                    } else if (digit4ET.getText() + "" == "") {
                        digit4ET.setText("4");
                    } else if (digit5ET.getText() + "" == "") {
                        digit5ET.setText("4");
                    } else if (digit6ET.getText() + "" == "") {
                        digit6ET.setText("4");
                        confirmPin.setBackgroundColor(getResources().getColor(R.color.colorPrimaryXDark));
                        confirmPin.setTextColor(getResources().getColor(R.color.white));
                    }
                }
            }
        });
        digit5Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (digit5Btn.getCurrentTextColor() == getResources().getColor(R.color.dark_grey_blue)) {
                    if (digit1ET.getText() + "" == "") {
                        digit1ET.setText("5");
                    } else if (digit2ET.getText() + "" == "") {
                        digit2ET.setText("5");
                    } else if (digit3ET.getText() + "" == "") {
                        digit3ET.setText("5");
                    } else if (digit4ET.getText() + "" == "") {
                        digit4ET.setText("5");
                    } else if (digit5ET.getText() + "" == "") {
                        digit5ET.setText("5");
                    } else if (digit6ET.getText() + "" == "") {
                        digit6ET.setText("5");
                        confirmPin.setBackgroundColor(getResources().getColor(R.color.colorPrimaryXDark));
                        confirmPin.setTextColor(getResources().getColor(R.color.white));
                    }
                }
            }
        });
        digit6Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (digit6Btn.getCurrentTextColor() == getResources().getColor(R.color.dark_grey_blue)) {
                    if (digit1ET.getText() + "" == "") {
                       digit1ET.setText("6");
                    } else if (digit2ET.getText() + "" == "") {
                        digit2ET.setText("6");
                    } else if (digit3ET.getText() + "" == "") {
                        digit3ET.setText("6");
                    } else if (digit4ET.getText() + "" == "") {
                        digit4ET.setText("6");
                    } else if (digit5ET.getText() + "" == "") {
                        digit5ET.setText("6");
                    } else if (digit6ET.getText() + "" == "") {
                        digit6ET.setText("6");
                        confirmPin.setBackgroundColor(getResources().getColor(R.color.colorPrimaryXDark));
                        confirmPin.setTextColor(getResources().getColor(R.color.white));
                    }
                }
            }
        });
        digit7Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (digit7Btn.getCurrentTextColor() == getResources().getColor(R.color.dark_grey_blue)) {
                    if (digit1ET.getText() + "" == "") {
                        digit1ET.setText("7");
                    } else if (digit2ET.getText() + "" == "") {
                        digit2ET.setText("7");
                    } else if (digit3ET.getText() + "" == "") {
                        digit3ET.setText("7");
                    } else if (digit4ET.getText() + "" == "") {
                        digit4ET.setText("7");
                    } else if (digit5ET.getText() + "" == "") {
                        digit5ET.setText("7");
                    } else if (digit6ET.getText() + "" == "") {
                        digit6ET.setText("7");
                        confirmPin.setBackgroundColor(getResources().getColor(R.color.colorPrimaryXDark));
                        confirmPin.setTextColor(getResources().getColor(R.color.white));
                    }
                }
            }
        });
        digit8Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (digit8Btn.getCurrentTextColor() == getResources().getColor(R.color.dark_grey_blue)) {
                    if (digit1ET.getText() + "" == "") {
                        digit1ET.setText("8");
                    } else if (digit2ET.getText() + "" == "") {
                        digit2ET.setText("8");
                    } else if (digit3ET.getText() + "" == "") {
                        digit3ET.setText("8");
                    } else if (digit4ET.getText() + "" == "") {
                        digit4ET.setText("8");
                    } else if (digit5ET.getText() + "" == "") {
                        digit5ET.setText("8");
                    } else if (digit6ET.getText() + "" == "") {
                        digit6ET.setText("8");
                        confirmPin.setBackgroundColor(getResources().getColor(R.color.colorPrimaryXDark));
                        confirmPin.setTextColor(getResources().getColor(R.color.white));
                    }
                }
            }
        });
        digit9Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (digit9Btn.getCurrentTextColor() == getResources().getColor(R.color.dark_grey_blue)) {
                    if (digit1ET.getText() + "" == "") {
                        digit1ET.setText("9");
                    } else if (digit2ET.getText() + "" == "") {
                        digit2ET.setText("9");
                    } else if (digit3ET.getText() + "" == "") {
                        digit3ET.setText("9");
                    } else if (digit4ET.getText() + "" == "") {
                        digit4ET.setText("9");
                    } else if (digit5ET.getText() + "" == "") {
                        digit5ET.setText("9");
                    } else if (digit6ET.getText() + "" == "") {
                        digit6ET.setText("9");
                        confirmPin.setBackgroundColor(getResources().getColor(R.color.colorPrimaryXDark));
                        confirmPin.setTextColor(getResources().getColor(R.color.white));
                    }
                }
            }
        });
        digitBckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (digitBckBtn.getCurrentTextColor() == getResources().getColor(R.color.dark_grey_blue)) {
                    if (!(digit6ET.getText() + "").equals("")) {
                        digit6ET.setText("");
                        confirmPin.setBackgroundColor(getResources().getColor(R.color.light_grey));
                        confirmPin.setTextColor(getResources().getColor(R.color.light_grey_blue));
                    } else if (!(digit5ET.getText() + "").equals("")) {
                        digit5ET.setText("");
                    } else if (!(digit4ET.getText() + "").equals("")) {
                        digit4ET.setText("");
                    } else if (!(digit3ET.getText() + "").equals("")) {
                        digit3ET.setText("");
                    } else if (!(digit2ET.getText() + "").equals("")) {
                        digit2ET.setText("");
                    } else if (!(digit1ET.getText() + "").equals("")) {
                        digit1ET.setText("");
                    }
                }
            }
        });

        // listener for the switches to enable text editing
        final EditText drive_path_ET = findViewById(R.id.drive_pathET);
        final EditText drive_email_ET = findViewById(R.id.drive_accountET);
        drive_path_ET.setInputType(InputType.TYPE_NULL);
        drive_email_ET.setInputType(InputType.TYPE_NULL);

        Switch drive_switch = findViewById(R.id.enable_google_drive_switch);
        drive_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    drive_path_ET.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    drive_email_ET.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                    drive_path_ET.setTextColor(getResources().getColor(R.color.dark_grey_blue));
                    drive_email_ET.setTextColor(getResources().getColor(R.color.dark_grey_blue));
                } else {
                    drive_path_ET.setInputType(InputType.TYPE_NULL);
                    drive_email_ET.setInputType(InputType.TYPE_NULL);
                    drive_path_ET.setTextColor(getResources().getColor(R.color.light_grey_blue));
                    drive_email_ET.setTextColor(getResources().getColor(R.color.light_grey_blue));
                }
            }
        });

        Switch pin_switch = findViewById(R.id.enable_pin_lock_switch);
        pin_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (digit6ET.getText().equals("")) {
                        confirmPin.setBackgroundColor(getResources().getColor(R.color.colorPrimaryXDark));
                        confirmPin.setTextColor(getResources().getColor(R.color.white));
                    } else {
                        confirmPin.setBackgroundColor(getResources().getColor(R.color.light_grey));
                        confirmPin.setTextColor(getResources().getColor(R.color.light_grey_blue));
                    }

                    digit0Btn.setBackgroundColor(getResources().getColor(R.color.xlight_grey_blue));
                    digit0Btn.setTextColor(getResources().getColor(R.color.dark_grey_blue));
                    digit1Btn.setBackgroundColor(getResources().getColor(R.color.xlight_grey_blue));
                    digit1Btn.setTextColor(getResources().getColor(R.color.dark_grey_blue));
                    digit2Btn.setBackgroundColor(getResources().getColor(R.color.xlight_grey_blue));
                    digit2Btn.setTextColor(getResources().getColor(R.color.dark_grey_blue));
                    digit3Btn.setBackgroundColor(getResources().getColor(R.color.xlight_grey_blue));
                    digit3Btn.setTextColor(getResources().getColor(R.color.dark_grey_blue));
                    digit4Btn.setBackgroundColor(getResources().getColor(R.color.xlight_grey_blue));
                    digit4Btn.setTextColor(getResources().getColor(R.color.dark_grey_blue));
                    digit5Btn.setBackgroundColor(getResources().getColor(R.color.xlight_grey_blue));
                    digit5Btn.setTextColor(getResources().getColor(R.color.dark_grey_blue));
                    digit6Btn.setBackgroundColor(getResources().getColor(R.color.xlight_grey_blue));
                    digit6Btn.setTextColor(getResources().getColor(R.color.dark_grey_blue));
                    digit7Btn.setBackgroundColor(getResources().getColor(R.color.xlight_grey_blue));
                    digit7Btn.setTextColor(getResources().getColor(R.color.dark_grey_blue));
                    digit8Btn.setBackgroundColor(getResources().getColor(R.color.xlight_grey_blue));
                    digit8Btn.setTextColor(getResources().getColor(R.color.dark_grey_blue));
                    digit9Btn.setBackgroundColor(getResources().getColor(R.color.xlight_grey_blue));
                    digit9Btn.setTextColor(getResources().getColor(R.color.dark_grey_blue));
                    digitBckBtn.setBackgroundColor(getResources().getColor(R.color.xlight_grey_blue));
                    digitBckBtn.setTextColor(getResources().getColor(R.color.dark_grey_blue));
                } else {
                    confirmPin.setBackgroundColor(getResources().getColor(R.color.colorPrimaryXDark));
                    confirmPin.setTextColor(getResources().getColor(R.color.white));

                    digit0Btn.setBackgroundColor(getResources().getColor(R.color.light_grey));
                    digit0Btn.setTextColor(getResources().getColor(R.color.light_grey_blue));
                    digit1Btn.setBackgroundColor(getResources().getColor(R.color.light_grey));
                    digit1Btn.setTextColor(getResources().getColor(R.color.light_grey_blue));
                    digit2Btn.setBackgroundColor(getResources().getColor(R.color.light_grey));
                    digit2Btn.setTextColor(getResources().getColor(R.color.light_grey_blue));
                    digit3Btn.setBackgroundColor(getResources().getColor(R.color.light_grey));
                    digit3Btn.setTextColor(getResources().getColor(R.color.light_grey_blue));
                    digit4Btn.setBackgroundColor(getResources().getColor(R.color.light_grey));
                    digit4Btn.setTextColor(getResources().getColor(R.color.light_grey_blue));
                    digit5Btn.setBackgroundColor(getResources().getColor(R.color.light_grey));
                    digit5Btn.setTextColor(getResources().getColor(R.color.light_grey_blue));
                    digit6Btn.setBackgroundColor(getResources().getColor(R.color.light_grey));
                    digit6Btn.setTextColor(getResources().getColor(R.color.light_grey_blue));
                    digit7Btn.setBackgroundColor(getResources().getColor(R.color.light_grey));
                    digit7Btn.setTextColor(getResources().getColor(R.color.light_grey_blue));
                    digit8Btn.setBackgroundColor(getResources().getColor(R.color.light_grey));
                    digit8Btn.setTextColor(getResources().getColor(R.color.light_grey_blue));
                    digit9Btn.setBackgroundColor(getResources().getColor(R.color.light_grey));
                    digit9Btn.setTextColor(getResources().getColor(R.color.light_grey_blue));
                    digitBckBtn.setBackgroundColor(getResources().getColor(R.color.light_grey));
                    digitBckBtn.setTextColor(getResources().getColor(R.color.light_grey_blue));
                }
            }
        });

        // listener for keyboard popping up (google drive modal)
        final RelativeLayout rootView = findViewById(R.id.root_view);
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int heightDiff = rootView.getRootView().getHeight() - rootView.getHeight();

                if (heightDiff > 100) {
                    Log.e("MyActivity", "keyboard opened");
                } else {
                    Log.e("MyActivity", "keyboard closed");
                }
            }
        });
    }

    private void updateROVListView() {
        mAdapter = new ROVAdapter(MainScreenActivity.this, rovList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                recyclerView.setLayoutManager(mLayoutManager);
                                recyclerView.setItemAnimator(new DefaultItemAnimator());
                                recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        View blackout = findViewById(R.id.blackout);
        if (blackout.getVisibility() != View.GONE) {
            RelativeLayout google_driveRL = findViewById(R.id.google_drive);
            RelativeLayout pin_lockRL = findViewById(R.id.pin_lock);

            google_driveRL.setVisibility(View.GONE);
            pin_lockRL.setVisibility(View.GONE);
            blackout.setVisibility(View.GONE);
        } else {
            // log user out and end activity (app)
            FirebaseAuth.getInstance().signOut();
            finish();
        }

    }

    /*
     * Called when a particular item from the navigation drawer
     * is selected.
     * */
    private void selectItemFromDrawer(int position) {
        Fragment fragment = new PreferencesFragment();

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mainContent, fragment)
                .commit();

        mDrawerList.setItemChecked(position, true);

        if (mNavItems.get(position).getTitle() == "Google Drive") {
            View blackout = findViewById(R.id.blackout);
            RelativeLayout google_driveRL = findViewById(R.id.google_drive);

            blackout.setVisibility(View.VISIBLE);
            google_driveRL.setVisibility(View.VISIBLE);
        } else if (mNavItems.get(position).getTitle() == "Pin Lock") {
            View blackout = findViewById(R.id.blackout);
            RelativeLayout pin_lockRL = findViewById(R.id.pin_lock);

            blackout.setVisibility(View.VISIBLE);
            pin_lockRL.setVisibility(View.VISIBLE);
        }
        //setTitle(mNavItems.get(position).getTitle());

        // Close the drawer
        mDrawerLayout.closeDrawer(mDrawerPane);
    }

    /* back arrow on which you can tap to open/close the drawer */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle
        // If it returns true, then it has handled
        // the nav drawer indicator touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    /* change to the 3 bar indicator icon when the drawer is closed */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check for signed in user
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            TextView username = findViewById(R.id.user_name);
            username.setText(user.getDisplayName());
        } else {
            // No user is signed in, change to sign in page
            Intent signInIntent = new Intent(getApplicationContext(), SignInActivity.class);
            startActivity(signInIntent);
            finish();
        }

    }

}


