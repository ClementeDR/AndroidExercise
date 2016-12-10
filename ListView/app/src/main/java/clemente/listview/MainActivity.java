package clemente.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private CustomAdapter mAdapter;
    private ArrayList<Item> mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);
        initDB();

        mAdapter = new CustomAdapter(this, mDatabase);


        mListView.setAdapter(mAdapter);


    }

    private void initDB(){
        mDatabase = new ArrayList<Item>();
        for (int vIndex = 0; vIndex < 1000; vIndex++ ){
            Item vItem = new Item(vIndex);
            mDatabase.add(vItem);
        }
    }
}
