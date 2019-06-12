package ua.deti.exprover;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private static final String TAG = SignUpActivity.class.getSimpleName();
    private static final int RC_SIGN_UP = 007;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.sign_up);

//        SpannableString s = new SpannableString("Sign Up");
//        s.setSpan(new TypefaceSpan(this, "caviar_dreams.ttf"), 0, s.length(),
//                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        // Update the action bar title with the TypefaceSpan instance
//        ActionBar actionBar = getActionBar();
//        if (actionBar != null) {
//            actionBar.setTitle(s);
//        }

        Button googleBtn = findViewById(R.id.googleBtn);
        Button emailBtn  = findViewById(R.id.emailBtn);
        Button signInBtn = findViewById(R.id.signInBtn);

        final EditText emailET = findViewById(R.id.emailET);
        final EditText passwordET = findViewById(R.id.passwordET);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/caviar_dreams.ttf");
        googleBtn.setTypeface(font);
        emailBtn.setTypeface(font);
        emailET.setTypeface(font);
        passwordET.setTypeface(font);

        Typeface bfont = Typeface.createFromAsset(getAssets(), "fonts/caviar_dreams_bold.ttf");
        signInBtn.setTypeface(bfont);

        // google sign in integration
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_UP);
            }
        });

        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String email = emailET.getText() + "";
                String password = passwordET.getText() + "";

                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");

                                // send email confirming sign-up
                                FirebaseUser user = mAuth.getCurrentUser();
                                user.sendEmailVerification()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d(TAG, "Email sent.");
                                                }
                                            }
                                        });

                                // set user's name = their email
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(user.getEmail().split("@")[0]).build();
                                user.updateProfile(profileUpdates);

                                // log in
                                Intent loggedInIntent = new Intent(getApplicationContext(), MainScreenActivity.class);
                                startActivity(loggedInIntent);
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
            // change to sign in page
            Intent signInIntent = new Intent(getApplicationContext(), SignInActivity.class);
            startActivity(signInIntent);
            finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_UP) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            // send email confirming sign-up
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            user.sendEmailVerification()
//                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//                                            if (task.isSuccessful()) {
//                                                Log.d(TAG, "Email sent.");
//                                            }
//                                        }
//                                    });


                            // log in
                            Intent loggedInIntent = new Intent(getApplicationContext(), MainScreenActivity.class);
                            startActivity(loggedInIntent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
