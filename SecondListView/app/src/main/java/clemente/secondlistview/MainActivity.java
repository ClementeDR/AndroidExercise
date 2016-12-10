package clemente.secondlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Item> mList;
    private ListView mListView;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listView);
        initDB();

        mAdapter = new MyAdapter(this, mList);

        mListView.setAdapter(mAdapter);
    }

    private void initDB(){
        mList = new ArrayList<Item>();
        for (int vIndex = 0; vIndex < 1000; vIndex++ ){
            Item vItem = new Item(vIndex);
            mList.add(vItem);
        }
    }
}
