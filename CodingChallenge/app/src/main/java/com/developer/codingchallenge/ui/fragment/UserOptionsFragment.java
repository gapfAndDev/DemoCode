package com.developer.codingchallenge.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.codetroopers.betterpickers.datepicker.DatePickerBuilder;
import com.codetroopers.betterpickers.datepicker.DatePickerDialogFragment;

import com.codetroopers.betterpickers.numberpicker.NumberPickerBuilder;
import com.codetroopers.betterpickers.numberpicker.NumberPickerDialogFragment;
import com.codetroopers.betterpickers.timepicker.TimePickerBuilder;
import com.codetroopers.betterpickers.timepicker.TimePickerDialogFragment;
import com.developer.codingchallenge.CodingChallengeApplication;
import com.developer.codingchallenge.R;
import com.developer.codingchallenge.bus.RxBus;
import com.developer.codingchallenge.ui.activity.MainActivity;

import java.math.BigDecimal;
import java.math.BigInteger;

public class UserOptionsFragment extends Fragment implements DatePickerDialogFragment.DatePickerDialogHandler, TimePickerDialogFragment.TimePickerDialogHandler, NumberPickerDialogFragment.NumberPickerDialogHandlerV2 {

    private TextView mDate;
    private TextView mTime;
    private TextView mNumber;
    private EditText mName;

    private RxBus mRxBus;
    private RadioGroup mFood;

    public static UserOptionsFragment newInstance() {
        UserOptionsFragment fragment = new UserOptionsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRxBus = ((MainActivity) getActivity()).getRxBusSingleton();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_options, container, false);

        mDate = (TextView) view.findViewById(R.id.txt_date);
        mTime = (TextView) view.findViewById(R.id.txt_time);
        mNumber = (TextView) view.findViewById(R.id.txt_number);
        mName = (EditText) view.findViewById(R.id.edt_name);
        mFood = (RadioGroup) view.findViewById(R.id.rdg_food);

        mFood.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                CodingChallengeApplication.getInstance().getCodingChallengePreference().setIdFood(checkedId);
                switch (checkedId){
                    case R.id.rdb_pizza:
                        CodingChallengeApplication.getInstance().getCodingChallengePreference().setFood(getString(R.string.pizza));
                        break;
                    case R.id.rdb_chicken:
                        CodingChallengeApplication.getInstance().getCodingChallengePreference().setFood(getString(R.string.chicken));
                        break;
                    case R.id.rdb_hamburger:
                        CodingChallengeApplication.getInstance().getCodingChallengePreference().setFood(getString(R.string.hamburger));
                        break;
                    case R.id.rdb_spaghetti:
                        CodingChallengeApplication.getInstance().getCodingChallengePreference().setFood(getString(R.string.spaghetti));
                        break;
                    case R.id.rdb_vegi:
                        CodingChallengeApplication.getInstance().getCodingChallengePreference().setFood(getString(R.string.vegi));

                        break;

                }
            }
        });

        Button btnDate = (Button) view.findViewById(R.id.btn_date);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerBuilder dpb = new DatePickerBuilder()
                        .setFragmentManager(getFragmentManager())
                        .addDatePickerDialogHandler(UserOptionsFragment.this)
                        .setStyleResId(R.style.BetterPickersDialogFragment);
                dpb.show();
            }
        });

        Button btnTime = (Button) view.findViewById(R.id.btn_time);
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerBuilder tpb = new TimePickerBuilder()
                        .setFragmentManager(getFragmentManager())
                        .addTimePickerDialogHandler(UserOptionsFragment.this)
                        .setStyleResId(R.style.BetterPickersDialogFragment);
                tpb.show();
            }
        });

        Button btnNumber = (Button) view.findViewById(R.id.btn_number);
        btnNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumberPickerBuilder npb = new NumberPickerBuilder()
                        .setFragmentManager(getFragmentManager())
                        .addNumberPickerDialogHandler(UserOptionsFragment.this)
                        .setStyleResId(R.style.BetterPickersDialogFragment);
                npb.show();
            }
        });

        Button btnSave = (Button) view.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mName.getText().length() > 0) {
                    CodingChallengeApplication.getInstance().getCodingChallengePreference().setName(mName.getText().toString());
                }

                if (mRxBus.hasObservers()) {
                    mRxBus.send(new loadUserInformation());
                }
            }
        });

        userInformation();
        return view;
    }

    private void userInformation() {

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
        if (food != "") {


            int id = CodingChallengeApplication.getInstance().getCodingChallengePreference().getIdFood();
           if (id != 999999)
            mFood.check(id);
        }

        String number = CodingChallengeApplication.getInstance().getCodingChallengePreference().getNumber();
        if (date != "") {
            mNumber.setText(number);
        }
    }

    @Override
    public void onDialogDateSet(int reference, int year, int monthOfYear, int dayOfMonth) {
        mDate.setText(getString(R.string.date_picker_result_value, year, monthOfYear, dayOfMonth));
        CodingChallengeApplication.getInstance().getCodingChallengePreference().setDate(getString(R.string.date_picker_result_value, year, monthOfYear, dayOfMonth));
    }

    @Override
    public void onDialogTimeSet(int reference, int hourOfDay, int minute) {
        mTime.setText(getString(R.string.time_picker_result_value, hourOfDay, minute));
        CodingChallengeApplication.getInstance().getCodingChallengePreference().setTime(getString(R.string.time_picker_result_value, hourOfDay, minute));
    }

    @Override
    public void onDialogNumberSet(int reference, BigInteger number, double decimal, boolean isNegative, BigDecimal fullNumber) {
        mNumber.setText(getString(R.string.number_picker_result_value, fullNumber));
        CodingChallengeApplication.getInstance().getCodingChallengePreference().setNumber(getString(R.string.number_picker_result_value, fullNumber));
    }

    public static class loadUserInformation {
        //RxBus use this.
    }

}
