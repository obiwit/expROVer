package ua.deti.exprover;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ua.deti.exprover.models.History;
import ua.deti.exprover.utils.HistoryAdapter;

public class HistoryActivity extends AppCompatActivity {
    private List<History> historyList = new ArrayList<>();
    private RecyclerView recyclerView;
    private HistoryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        recyclerView = (RecyclerView) findViewById(R.id.history_recycler_view);

        mAdapter = new HistoryAdapter(this, historyList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        initHistoryData();

        // this crashes the app :'D
//        getActionBar().setHomeButtonEnabled(true);
//        getActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void initHistoryData() {
        History history = new History("Viagem a Atlantida", Calendar.getInstance(), 68,
                5, "Gloria");
        historyList.add(history);

        history = new History("Tanque de pesca", Calendar.getInstance(), 4000,
                33, "Barra");
        historyList.add(history);

        history = new History("Pesca Submarina", Calendar.getInstance(), 15748,
                2, "Algarve");
        historyList.add(history);

        history = new History("Classificaçao Dr. Luisa", Calendar.getInstance(), 123456,
                7, "Porto");
        historyList.add(history);


        mAdapter.notifyDataSetChanged();
    }
}
