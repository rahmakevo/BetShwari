package co.ke.siundu254.betshwari.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import co.ke.siundu254.betshwari.model.Regular;
import co.ke.siundu254.betshwari.ui.VIPDetailActivity;

/**
 * Created by rahmak on 8/22/18
 */

public class VIPRecyclerAdapter extends RecyclerView.Adapter<VIPRecyclerAdapter.VIPHolder> {
    List<Regular> list;
    Context context;

    public VIPRecyclerAdapter(List<Regular> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public VIPRecyclerAdapter.VIPHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(co.ke.siundu254.betshwari.R.layout.single_regular_layout, parent, false);
        return new VIPRecyclerAdapter.VIPHolder(view);
    }

    @Override
    public void onBindViewHolder(VIPRecyclerAdapter.VIPHolder holder, int position) {
        Regular regular = list.get(position);
        holder.mTitle.setText(regular.getTitle());
        holder.mStatus.setText(regular.getStatus());
        holder.mDate.setText(regular.getDate());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final String mTipRef = database.getReference().child("tips").child("regular").push().getKey();
        final String mTips = regular.tips;
        final String mTitleReg = regular.title;
        final String mStatusReg = regular.status;

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), VIPDetailActivity.class);
                intent.putExtra("pushId", mTipRef);
                intent.putExtra("pushTitle", mTitleReg);
                intent.putExtra("pushStatus", mStatusReg);
                intent.putExtra("pushTips", mTips);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        int arr = 0;
        try {
            if (list.size() == 0) {
                arr = 0;
            } else {
                arr = list.size();
            }
        } catch (Exception ignore) {

        }
        return arr;
    }

    class VIPHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView mTitle, mStatus, mDate;
        public VIPHolder(View itemView) {
            super(itemView);
            this.mView = itemView;

            this.mTitle = mView.findViewById(co.ke.siundu254.betshwari.R.id.single_regular_title);
            this.mStatus = mView.findViewById(co.ke.siundu254.betshwari.R.id.single_regular_status);
            this.mDate = mView.findViewById(co.ke.siundu254.betshwari.R.id.single_regular_date);
        }
    }
}
