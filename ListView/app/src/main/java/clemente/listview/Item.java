package clemente.listview;

import java.util.Random;

/**
 * Created by clemente on 11/11/16.
 */
public class Item {
    public int mID;
    public String mName;
    public int mLiter;

    public Item(int aId){
        mID = aId;
        mName = "Birra " + aId;
        mLiter = new Random().nextInt(10) + 1;
    }

}
