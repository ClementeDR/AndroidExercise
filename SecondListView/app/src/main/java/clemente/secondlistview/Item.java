package clemente.secondlistview;

import java.util.Random;

/**
 * Created by clemente on 11/11/16.
 */
public class Item {
    public int mID;
    public String mName;
    public int mProgressValue;
    public int mRatingValue;

    public Item(int aValue){
        mID = aValue;
        mName = "Item " + mID;
        mProgressValue = new Random().nextInt(10);
        mRatingValue = new Random().nextInt(5);;
    }
}
