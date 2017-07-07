package com.example.skiline11.pierwszaaplikacja.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.skiline11.pierwszaaplikacja.R;
import com.example.skiline11.pierwszaaplikacja.model.Person;
import com.example.skiline11.pierwszaaplikacja.model.Run;

import java.util.List;

/**
 * Created by skiline11 on 19.01.17.
 */

public class ListOfRunAdapter extends BaseAdapter {

    private Context mContext;
    private List<Run> mRunList;

    public ListOfRunAdapter(Context mContext, List<Run> mRunList) {
        this.mContext = mContext;
        this.mRunList = mRunList;
    }

    @Override
    public int getCount() {
        return mRunList.size();
    }

    @Override
    public Object getItem(int position) {
        return mRunList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mRunList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.run_view, null);
        TextView pos = (TextView) v.findViewById(R.id.run_msc);
        TextView name = (TextView) v.findViewById(R.id.run_name);
        TextView date = (TextView) v.findViewById(R.id.run_date);
        TextView result = (TextView) v.findViewById(R.id.run_result);

        pos.setText(String.valueOf(position + 1));
        name.setText(String.valueOf(mRunList.get(position).getName()));
        date.setText(String.valueOf(mRunList.get(position).getDate()));
        result.setText(String.valueOf(mRunList.get(position).getResult()));
        return v;
    }
}
