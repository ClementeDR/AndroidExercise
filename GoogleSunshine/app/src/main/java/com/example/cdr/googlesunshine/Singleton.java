package com.example.cdr.googlesunshine;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by cdr on 12/12/16.
 */

public class Singleton {

    private static Singleton mIstance;
    private ArrayList<WeatherItem> mList;
    private Context mContext;
    private int mCount = 0;
    public URL mURL;
    public String mLink = "http://api.openweathermap.org/data/2.5/forecast?q=London,us&mode=json&appid=899d25f5d4fdcb5adad75eaefbbc449d";


    public interface IOGetData{
        void update();
        void download();
    }

    private IOGetData mListener = new IOGetData() {
        @Override
        public void update() {
        }

        @Override
        public void download() {
        }
    };

    public void setListener(IOGetData listener) {
        mListener = listener;
    }

    private Singleton(Context context){
        mContext = context;
        mList = new ArrayList<WeatherItem>();
        try {
            mURL = new URL(mLink);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static Singleton getInstance(Context context){

        if (mIstance == null)
            mIstance = new Singleton(context);

        return mIstance;
    }

    public void downloadList(){
        mList.clear();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.valueOf(mURL), new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                if (statusCode == 200){
                    JSONObject vJSONObject;
                    try {
                        vJSONObject = new JSONObject(new String(response));
                        JSONArray vJSONArray;
                        try {
                            if(vJSONObject.getString("cod").equals("200")){
                                vJSONArray = vJSONObject.getJSONArray("list");
                                if(vJSONArray.length() > 0){
                                    for (int i = 0; i < vJSONArray.length(); i++) {
                                        try {
                                            JSONObject vObject = vJSONArray.getJSONObject(i);
                                            WeatherItem vItem = new WeatherItem();
                                            vItem.setID(i);
                                            mCount++;
                                            //datetime
                                            vItem.setLongTime(vObject.getLong("dt"));
                                            //main->temp
                                            //  ->temp_min e temp_max
                                            //  ->temp_kf


                                            vItem.setTemperature(vObject.getJSONObject("main").getDouble("temp"));
                                            Log.d("TEMPERATURE", "" + vItem.getTemperature());

                                            //"dt_txt"
                                            vItem.setTime(vObject.getString("dt_txt"));

                                            if (vObject.getJSONArray("weather").length() != 0){
                                                vItem.setWeather("" + vObject.getJSONArray("weather").getJSONObject(0).getString("description"));
                                            } else {
                                                vItem.setWeather("Nothing to show");
                                            }

                                            mList.add(vItem);

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    if (mListener != null)
                                        mListener.download();
                                }

                            } else {
                                Toast toast = Toast.makeText(mContext, "Error", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                // called when response HTTP status is "200 OK"


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)

                Toast toast = Toast.makeText(mContext, new String(errorResponse), Toast.LENGTH_LONG);
                toast.show();
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }

    public ArrayList<WeatherItem> getList(){
        return mList;
    }


}
