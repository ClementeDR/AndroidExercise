package clemente.secondlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by clemente on 11/11/16.
 */
public class MyAdapter extends BaseAdapter{

    private ArrayList<Item> mArrayList;
    private Context mContext;


    public MyAdapter(Context aContext, ArrayList<Item> aItemArrayList) {
        mContext = aContext;
        mArrayList = aItemArrayList;
    }

    class ViewHolder{
        TextView mName;
        ProgressBar mProgressBar;
        RatingBar mRatingBar;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vView;
        if (view == null){
            LayoutInflater vInflater = LayoutInflater.from(mContext);
            vView = vInflater.inflate(R.layout.item_layout, null);


            ViewHolder vHolder = new ViewHolder();
            vHolder.mName = (TextView) vView.findViewById(R.id.txtName);
            vHolder.mProgressBar = (ProgressBar) vView.findViewById(R.id.progressBar);
            vHolder.mRatingBar = (RatingBar) vView.findViewById(R.id.ratingBar);

            vView.setTag(vHolder);
        } else {
            vView = view;
        }

        Item vItem = getItem(i);

        ViewHolder viewHolder = (ViewHolder) vView.getTag();

        viewHolder.mName.setText(vItem.mName);
        viewHolder.mProgressBar.setProgress(vItem.mProgressValue * 10);
        viewHolder.mRatingBar.setRating((float)(vItem.mRatingValue));


        return vView;
    }


    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Item getItem(int i) {
        return mArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).mID;
    }


}
