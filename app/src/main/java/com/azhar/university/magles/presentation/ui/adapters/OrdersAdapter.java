package com.azhar.university.magles.presentation.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhar.university.magles.R;
import com.azhar.university.magles.domain.models.Order;
import com.azhar.university.magles.presentation.ui.utils.DatesUtils;
import com.azhar.university.magles.presentation.ui.utils.UIUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {
    private final List<Order> orders;

    public OrdersAdapter(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.order = orders.get(position);
        int statusColor = UIUtils.getStatusColor(holder.order.getCurrentStatus().getId());
        holder.title.setText(holder.order.getTitle());
        holder.date.setText(DatesUtils.formatDateOnly(holder.order.getDate()));
        holder.createdBy.setText(holder.order.getCreatedBy().getName());
        holder.status.setText(holder.order.getCurrentStatus().getStatus());
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
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.indicator)
        ImageView indicator;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.status)
        TextView status;
        @BindView(R.id.created_by)
        TextView createdBy;

        View view;
        Order order;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }
    }
}
