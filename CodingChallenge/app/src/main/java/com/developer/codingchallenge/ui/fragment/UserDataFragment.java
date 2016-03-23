package com.developer.codingchallenge.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.developer.codingchallenge.CodingChallengeApplication;
import com.developer.codingchallenge.R;
import com.developer.codingchallenge.bus.RxBus;
import com.developer.codingchallenge.ui.activity.MainActivity;

import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;


public class UserDataFragment extends Fragment {

    private TextView mDate;
    private TextView mTime;
    private TextView mName;
    private TextView mFood;
    private TextView mNumber;

    private CompositeSubscription subscriptions;
    private RxBus mRxBus;

    @Override
    public void onStart() {
        super.onStart();

        subscriptions = new CompositeSubscription();

        subscriptions
                .add(mRxBus.toObserverable()
                        .subscribe(new Action1<Object>() {
                            @Override
                            public void call(Object event) {
                                if (event instanceof UserOptionsFragment.loadUserInformation) {
                                    loudUserInformation();
                                }
                            }
                        }));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRxBus = ((MainActivity) getActivity()).getRxBusSingleton();

    }

    public static UserDataFragment newInstance() {
        UserDataFragment fragment = new UserDataFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_user, container, false);

        mDate = (TextView) view.findViewById(R.id.txt_date_userData);
        mTime = (TextView) view.findViewById(R.id.txt_time_userData);
        mName = (TextView) view.findViewById(R.id.txt_name_userData);
        mFood = (TextView) view.findViewById(R.id.txt_food_userData);
        mNumber = (TextView) view.findViewById(R.id.txt_number_userData);

        loudUserInformation();
        return view;
    }

    private void loudUserInformation() {

        String date = CodingChallengeApplication.getInstance().getCodingChallengePreference().getDate();
        if (date != "") {
            mDate.setText(date);
        }

        String time = CodingChallengeApplication.getInstance().getCodingChallengePreference().getTime();
        if (time != "") {
            mTime.setText(time);
        }

        String name = CodingChallengeApplication.getInstance().getCodingChallengePreference().getUserName();
        if (date != "") {
            mName.setText(name);
        }

        String food = CodingChallengeApplication.getInstance().getCodingChallengePreference().getFood();
        if (date != "") {
            mFood.setText(food);
        }

        String number = CodingChallengeApplication.getInstance().getCodingChallengePreference().getNumber();
        if (date != "") {
            mNumber.setText(number);
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        subscriptions.unsubscribe();
    }

}
