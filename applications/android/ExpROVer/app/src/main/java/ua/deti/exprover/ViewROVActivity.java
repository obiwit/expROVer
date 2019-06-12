package ua.deti.exprover;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewROVActivity extends AppCompatActivity {

    private String rovIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_rov);

        String rovName;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                rovName= null;
                rovIP= "10.42.0.1";
            } else {
                rovName= extras.getString("ua.deti.exprover.ROV_NAME");
                rovIP=extras.getString("ua.deti.exprover.ROV_IP");
            }
        } else {
            rovName= (String) savedInstanceState.getSerializable("ua.deti.exprover.ROV_NAME");
            rovIP= (String) savedInstanceState.getSerializable("ua.deti.exprover.ROV_IP");
        }

        TextView rov_name_tv = findViewById(R.id.rov_name);
        rov_name_tv.setText(rovName);


        // add event listeners
        RelativeLayout new_expedition_rl = findViewById(R.id.new_expedition);
        RelativeLayout view_history_rl = findViewById(R.id.view_history);
        RelativeLayout configure_rl = findViewById(R.id.configure);

        new_expedition_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final android.view.View v) {
                Intent newExpIntent = new Intent(getApplicationContext(), CockpitActivity.class);
                newExpIntent.putExtra("ua.deti.exprover.ROV_NAME",
                        ((TextView)findViewById(R.id.rov_name)).getText());
                newExpIntent.putExtra("ua.deti.exprover.ROV_IP", rovIP);
                startActivity(newExpIntent);
            }
        });

        view_history_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final android.view.View v) {
                Intent viewHistIntent = new Intent(getApplicationContext(), HistoryActivity.class);
                viewHistIntent.putExtra("ua.deti.exprover.ROV_NAME",
                        ((TextView)findViewById(R.id.rov_name)).getText());
                startActivity(viewHistIntent);
            }
        });

        configure_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final android.view.View v) {
                Intent configureIntent = new Intent(getApplicationContext(), ConfigureROVActivity.class);
                configureIntent.putExtra("ua.deti.exprover.ROV_NAME",
                        ((TextView)findViewById(R.id.rov_name)).getText());
                startActivity(configureIntent);
                finish();
            }
        });
    }
}
