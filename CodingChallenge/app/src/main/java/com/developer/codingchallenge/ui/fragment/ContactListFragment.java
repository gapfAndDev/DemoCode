package com.developer.codingchallenge.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.developer.codingchallenge.R;
import com.developer.codingchallenge.adapter.MyContactRecyclerViewAdapter;
import com.developer.codingchallenge.dummy.ContactList;
import com.developer.codingchallenge.model.Contact;


public class ContactListFragment extends Fragment {

    private int mColumnCount = 2;
    private OnListFragmentInteractionListener mListener = new OnListFragmentInteractionListener() {
        @Override
        public void onListFragmentInteraction(Contact item) {
            Toast.makeText(getActivity().getApplicationContext(), String.format("Id %s, %s", item.getItemNumber(), item.getTitle()), Toast.LENGTH_SHORT).show();
        }
    };

    public static ContactListFragment newInstance() {
        ContactListFragment fragment = new ContactListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyContactRecyclerViewAdapter(ContactList.getInstance().getItems(), mListener));
        }
        return view;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Contact item);
    }

}
