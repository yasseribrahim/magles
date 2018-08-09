package com.azhar.university.magles.presentation.ui.activities;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.azhar.university.magles.R;
import com.azhar.university.magles.domain.models.Order;
import com.azhar.university.magles.domain.models.OrderHistory;
import com.azhar.university.magles.domain.utils.Constants;
import com.azhar.university.magles.domain.views.OrderView;
import com.azhar.university.magles.presentation.presenters.order.OrderPresenter;
import com.azhar.university.magles.presentation.presenters.order.OrderPresenterImp;
import com.azhar.university.magles.presentation.ui.adapters.OrderHistoryAdapter;
import com.azhar.university.magles.presentation.ui.custom.CustomDividerItemDecoration;
import com.azhar.university.magles.presentation.ui.utils.DatesUtils;
import com.azhar.university.magles.presentation.ui.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderDetailsActivity extends BaseActivity implements OrderView {
    @BindView(R.id.container_view)
    ConstraintLayout container;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.owner)
    TextView owner;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.current_status)
    TextView status;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.note)
    TextView note;

    private OrderPresenter presenter;
    private OrderHistoryAdapter adapter;
    private List<OrderHistory> histories;
    private long orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        ButterKnife.bind(this);

        orderId = getIntent().getLongExtra(Constants.KEY_ORDER_ID, 0);

        presenter = new OrderPresenterImp(this);
        histories = new ArrayList<>();
        adapter = new OrderHistoryAdapter(histories);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CustomDividerItemDecoration dividerItemDecoration = new CustomDividerItemDecoration(this, R.dimen.divider_mid);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);

        refreshLayout.setColorSchemeResources(R.color.refreshColor1, R.color.refreshColor2,
                R.color.refreshColor3, R.color.refreshColor4);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                load();
            }
        });

        load();
    }

    private void load() {
        presenter.details(orderId);
    }

    @Override
    protected View getSnackBarAnchorView() {
        return container;
    }

    @Override
    public void onListComplete(List<Order> orders) {

    }

    @Override
    public void onGetDetailsComplete(Order order) {
        owner.setText(order.getOwnerId().getName());
        title.setText(order.getTitle());
        date.setText(DatesUtils.formatDateOnly(order.getDate()));
        int color = UIUtils.getStatusColor(order.getId());
        status.setText(order.getCurrentStatus().getStatus());
        status.setTextColor(color);
        content.setText(order.getContent());
        note.setText(order.getNote());

        histories.clear();
        histories.addAll(order.getHistory());
        adapter.notifyDataSetChanged();
    }
}
