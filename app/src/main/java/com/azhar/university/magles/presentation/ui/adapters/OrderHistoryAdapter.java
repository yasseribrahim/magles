package com.azhar.university.magles.presentation.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhar.university.magles.R;
import com.azhar.university.magles.domain.models.OrderHistory;
import com.azhar.university.magles.presentation.ui.utils.DatesUtils;
import com.azhar.university.magles.presentation.ui.utils.UIUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder> {
    private final List<OrderHistory> histories;

    public OrderHistoryAdapter(List<OrderHistory> histories) {
        this.histories = histories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.history = histories.get(position);
        int statusColor = UIUtils.getStatusColor(holder.history.getStatus().getId());
        holder.note.setText(holder.history.getNote());
        holder.date.setText(DatesUtils.formatDateOnly(holder.history.getCreationDate()));
        holder.createdBy.setText(holder.history.getCreatedBy().getName());
        holder.status.setText(holder.history.getStatus().getStatus());
        holder.status.setTextColor(statusColor);
        holder.indicator.setBackgroundColor(statusColor);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.indicator)
        ImageView indicator;
        @BindView(R.id.note)
        TextView note;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.status)
        TextView status;
        @BindView(R.id.created_by)
        TextView createdBy;

        View view;
        OrderHistory history;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }
    }
}
