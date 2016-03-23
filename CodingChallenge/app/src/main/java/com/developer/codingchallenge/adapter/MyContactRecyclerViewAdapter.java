package com.developer.codingchallenge.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.developer.codingchallenge.R;
import com.developer.codingchallenge.model.Contact;
import com.developer.codingchallenge.ui.fragment.ContactListFragment;

import java.util.List;

public class MyContactRecyclerViewAdapter extends RecyclerView.Adapter<MyContactRecyclerViewAdapter.ViewHolder> {

    private final List<Contact> mValues;
    private final ContactListFragment.OnListFragmentInteractionListener mListener;

    public MyContactRecyclerViewAdapter(List<Contact> items, ContactListFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_contact_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mItemNumber.setText(String.valueOf(position));
        holder.mTitle.setText(mValues.get(position).getTitle());
        holder.mContent.setText(mValues.get(position).getContent());

        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mItemNumber;
        public final TextView mTitle;
        public final TextView mContent;
        public final View mContainer;
        public Contact mItem;

        public ViewHolder(View view) {
            super(view);
            mContainer = (View) view.findViewById(R.id.ll_container);
            mItemNumber = (TextView) view.findViewById(R.id.txt_itemNumber);
            mTitle = (TextView) view.findViewById(R.id.txt_title);
            mContent = (TextView) view.findViewById(R.id.txt_content);
        }

    }
}
