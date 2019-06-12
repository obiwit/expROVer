package ua.deti.exprover;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import ua.deti.exprover.utils.StringUtils;

public class NewROVActivity extends AppCompatActivity {

    private int PICK_IMAGE_REQUEST = 111;
    private Uri filePath;
    private ProgressDialog pd;
    private StorageReference imagesRef;
    private ImageButton imageIB;

    private static String TAG = NewROVActivity.class.getSimpleName();
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_rov);

        // firebase-related configs
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        imagesRef = storageRef.child("images");

        // ip address filter
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (end > start) {
                    String destTxt = dest.toString();
                    String resultingTxt = destTxt.substring(0, dstart) + source.subSequence(start, end) + destTxt.substring(dend);
                    if (!resultingTxt.matches ("^\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3})?)?)?)?)?)?")) {
                        return "";
                    } else {
                        String[] splits = resultingTxt.split("\\.");
                        for (int i=0; i<splits.length; i++) {
                            if (Integer.valueOf(splits[i]) > 255) {
                                return "";
                            }
                        }
                    }
                }
                return null;
            }
        };

        final EditText ipAddressET = findViewById(R.id.ipET);
        ipAddressET.setFilters(filters);

        final EditText nameET = findViewById(R.id.nameET);
        Button confirmBtn = findViewById(R.id.confirmBtn);
        
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/caviar_dreams.ttf");
        confirmBtn.setTypeface(font);
        nameET.setTypeface(font);
        ipAddressET.setTypeface(font);

        imageIB = findViewById(R.id.rovIB);

        imageIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // save name and ip
                String name = nameET.getText() + "";
                String ip = ipAddressET.getText() + "";
                Map<String, Object> rovData = new HashMap<>();
                rovData.put(name, ip);

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("users").document(user.getEmail())
                        .set(rovData, SetOptions.merge())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully written!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e(TAG, "Error writing document: " + e, e);
                                Toast.makeText(NewROVActivity.this, "Error updating database", Toast.LENGTH_SHORT).show();
                            }
                        });

                // save image
                if(filePath != null) {
                    StorageReference childRef = imagesRef.child(StringUtils.md5(name) + ".jpg");

                    //upload the image
                    try {
                        Bitmap originalImg = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                        ByteArrayOutputStream compressed = new ByteArrayOutputStream();
                        originalImg.compress(Bitmap.CompressFormat.JPEG, 65, compressed);

                        UploadTask uploadTask = childRef.putBytes(compressed.toByteArray());
                        originalImg.recycle();

                        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Log.d(TAG, "Image upload successfu!");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "Image upload failed -> " + e);
                            }
                        });
                    } catch (Exception e) {
                        Log.e(TAG, "Error uploading image: " + e, e);
                        Toast.makeText(NewROVActivity.this, "Error uploading image", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(NewROVActivity.this, "Select an image", Toast.LENGTH_SHORT).show();
                }

                Intent addIntent = new Intent(getApplicationContext(), MainScreenActivity.class);
                startActivity(addIntent);
                finish();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

            try {
                //getting image from gallery
                Bitmap bitmap = Bitmap.createScaledBitmap(
                        MediaStore.Images.Media.getBitmap(getContentResolver(), filePath),
                        120, 120, false);
                ByteArrayOutputStream compressed = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 40, compressed);

                //Setting image to ImageView
                imageIB.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
