package clemente.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by clemente on 11/11/16.
 */
public class CustomAdapter extends BaseAdapter {
    private ArrayList<Item> mData;
    private Context mContext;

    public CustomAdapter(Context aContext, ArrayList aArrayList){
        mContext = aContext;
        mData = aArrayList;
    }

    class ViewHolder {
        TextView mName;
        TextView mLiter;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View vCell;
        if (view == null) {
            LayoutInflater vInflater = LayoutInflater.from(mContext);
            vCell = vInflater.inflate(R.layout.item_layout, null);

            ViewHolder vHolder = new ViewHolder();
            vHolder.mName =  (TextView) vCell.findViewById(R.id.txt_name);
            vHolder.mLiter = (TextView) vCell.findViewById(R.id.txt_value);

            vCell.setTag(vHolder);
        } else {
            vCell = view;
        }

        ViewHolder viewHolder = (ViewHolder) vCell.getTag();

        Item vItem = getItem(i);

        viewHolder.mName.setText(vItem.mName);
        viewHolder.mLiter.setText("" + vItem.mLiter);

        return vCell;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Item getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).mID;
    }

}
