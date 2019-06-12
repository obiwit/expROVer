package ua.deti.exprover;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConfigureROVActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configure_rov);

        // get stored settings
        String rovName;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                rovName= null;
            } else {
                rovName= extras.getString("ua.deti.exprover.ROV_NAME");
            }
        } else {
            rovName= (String) savedInstanceState.getSerializable("ua.deti.exprover.ROV_NAME");
        }

        TextView name_et = findViewById(R.id.nameET);
        name_et.setText(rovName);


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

        EditText ipAddressET = findViewById(R.id.ipET);
        ipAddressET.setFilters(filters);

        EditText nameET = findViewById(R.id.nameET);
        Button confirmBtn = findViewById(R.id.confirmBtn);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/caviar_dreams.ttf");
        confirmBtn.setTypeface(font);
        nameET.setTypeface(font);
        ipAddressET.setTypeface(font);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
            Intent backIntent = new Intent(getApplicationContext(), ViewROVActivity.class);
            backIntent.putExtra("ua.deti.exprover.ROV_NAME",
                    ((EditText)findViewById(R.id.nameET)).getText() + "");
            startActivity(backIntent);
            finish();
            }
        });
    }
}
