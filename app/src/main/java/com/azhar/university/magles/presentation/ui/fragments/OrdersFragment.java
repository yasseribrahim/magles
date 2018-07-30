package com.azhar.university.magles.presentation.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.azhar.university.magles.R;
import com.azhar.university.magles.domain.communicator.OnListInteractionListener;
import com.azhar.university.magles.domain.models.Order;
import com.azhar.university.magles.domain.models.User;
import com.azhar.university.magles.domain.utils.UserManager;
import com.azhar.university.magles.domain.views.OrderView;
import com.azhar.university.magles.presentation.presenters.order.OrderPresenter;
import com.azhar.university.magles.presentation.presenters.order.OrderPresenterImp;
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
public class OrdersFragment extends BaseFragment implements OrderView {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

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
        View view = inflater.inflate(R.layout.fragment_more, container, false);

        ButterKnife.bind(this, view);

        presenter = new OrderPresenterImp(this);
        orders = new ArrayList<>();
        adapter = new OrdersAdapter(orders);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        CustomDividerItemDecoration dividerItemDecoration = new CustomDividerItemDecoration(getContext(), R.dimen.divider_mid);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);

        User user = UserManager.getInstance().getCurrentUser();
        presenter.getOrders(user.getId());

        return view;
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
    }

    @Override
    public void onGetDetailsComplete(Order order) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
    }
}
