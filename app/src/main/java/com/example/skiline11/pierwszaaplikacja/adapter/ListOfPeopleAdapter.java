package com.example.skiline11.pierwszaaplikacja.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.skiline11.pierwszaaplikacja.R;
import com.example.skiline11.pierwszaaplikacja.model.Person;

import java.util.List;

/**
 * Created by skiline11 on 18.01.17.
 */

public class ListOfPeopleAdapter extends BaseAdapter {

    private Context mContext;
    private List<Person> mPeopleList;

    public ListOfPeopleAdapter(Context mContext, List<Person> mPeopleList) {
        this.mContext = mContext;
        this.mPeopleList = mPeopleList;
    }

    @Override
    public int getCount() {
        return mPeopleList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPeopleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(mPeopleList.get(position).getId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.friend_view, null);
        TextView name = (TextView) v.findViewById(R.id.friendName);
        TextView surname = (TextView) v.findViewById(R.id.friendSurname);
        TextView pesel = (TextView) v.findViewById(R.id.friendPesel);
        name.setText(String.valueOf(mPeopleList.get(position).getName()));
        surname.setText(String.valueOf(mPeopleList.get(position).getSurname()));
        pesel.setText(String.valueOf(mPeopleList.get(position).getId()));
        return v;
    }
}
