package com.developer.codingchallenge.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.developer.codingchallenge.CodingChallengeApplication;
import com.developer.codingchallenge.R;
import com.developer.codingchallenge.model.weather.CityWeather;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class WeatherFragment extends Fragment {
    private static final String CITY = "Salt Lake City, Utah";

    private TextView mPlace;
    private TextView mTempData;
    private TextView mGeo;
    private CityWeather mCityWeathers;
    private LinearLayout mContainer;
    private rx.Observer mObserverApi = new rx.Observer<CityWeather>() {
        @Override
        public void onCompleted() {
            setViewLayout();
        }

        @Override
        public void onError(Throwable e) {
            mContainer.setVisibility(View.VISIBLE);
        }

        @Override
        public void onNext(CityWeather cityWeathers) {
            mCityWeathers = cityWeathers;
        }
    };

    private void setViewLayout() {
        mPlace.setText(mCityWeathers.getPlaceString());
        mTempData.setText(mCityWeathers.getTempDataString());
        mGeo.setText(mCityWeathers.getGeoString());
    }

    public static WeatherFragment newInstance() {
        WeatherFragment fragment = new WeatherFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadWeather();
    }

    private void loadWeather() {
        Observable<CityWeather> cityWeatherObservable = apiCall();

        cityWeatherObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserverApi);
    }

    private Observable<CityWeather> apiCall() {
        return CodingChallengeApplication.getInstance().getCodingChallengeApi().getWeatherByCity(CITY, getString(R.string.app_id));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        mPlace = (TextView) view.findViewById(R.id.txt_place);
        mTempData = (TextView) view.findViewById(R.id.txt_tempData);
        mGeo = (TextView) view.findViewById(R.id.txt_geo);
        mContainer = (LinearLayout) view.findViewById(R.id.ll_noWeatherMessage);

        return view;
    }
}
