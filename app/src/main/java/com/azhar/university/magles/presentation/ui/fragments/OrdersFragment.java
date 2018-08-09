package com.azhar.university.magles.presentation.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.azhar.university.magles.R;
import com.azhar.university.magles.domain.communicator.OnAttachedHomeFragmentsCallback;
import com.azhar.university.magles.domain.communicator.OnListInteractionListener;
import com.azhar.university.magles.domain.models.Order;
import com.azhar.university.magles.domain.models.User;
import com.azhar.university.magles.domain.utils.Constants;
import com.azhar.university.magles.domain.utils.UserManager;
import com.azhar.university.magles.domain.views.OrderView;
import com.azhar.university.magles.presentation.presenters.order.OrderPresenter;
import com.azhar.university.magles.presentation.presenters.order.OrderPresenterImp;
import com.azhar.university.magles.presentation.ui.activities.OrderDetailsActivity;
import com.azhar.university.magles.presentation.ui.adapters.OrdersAdapter;
import com.azhar.university.magles.presentation.ui.custom.CustomDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListInteractionListener}
 * interface.
 */
public class OrdersFragment extends BaseFragment implements OrderView, OnListInteractionListener<Order> {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.empty)
    TextView empty;

    private OrderPresenter presenter;
    private OrdersAdapter adapter;
    private List<Order> orders;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public OrdersFragment() {
    }

    public static OrdersFragment newInstance() {
        OrdersFragment fragment = new OrdersFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        ButterKnife.bind(this, view);

        presenter = new OrderPresenterImp(this);
        orders = new ArrayList<>();
        adapter = new OrdersAdapter(orders, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        CustomDividerItemDecoration dividerItemDecoration = new CustomDividerItemDecoration(getContext(), R.dimen.divider_mid);
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
        return view;
    }

    private void load() {
        User user = UserManager.getInstance().getCurrentUser();
        presenter.getOrders(user.getId());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAttachedHomeFragmentsCallback) {
            ((OnAttachedHomeFragmentsCallback) context).onFragmentAttachedOrders(this);
        }
    }

    @Override
    public void showProgress() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    protected View getSnackBarAnchorView() {
        return recyclerView;
    }

    @Override
    public void showConnectionError(View.OnClickListener onClickListener) {
        showConnectionSnackBar(onClickListener);
    }

    @Override
    public void showError(String message, View.OnClickListener onClickListener) {
        showRetrySnackBar(message, onClickListener);
    }

    @Override
    public void onListComplete(List<Order> orders) {
        this.orders.clear();
        this.orders.addAll(orders);
        adapter.notifyDataSetChanged();
        if (orders.isEmpty()) {
            empty.setVisibility(View.VISIBLE);
        } else {
            empty.setVisibility(View.GONE);
        }
    }

    @Override
    public void onGetDetailsComplete(Order order) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
    }

    @Override
    public void onListInteraction(Order item) {
        Intent intent = new Intent(getContext(), OrderDetailsActivity.class);
        intent.putExtra(Constants.KEY_ORDER_ID, item.getId());
        startActivity(intent);
    }
}
