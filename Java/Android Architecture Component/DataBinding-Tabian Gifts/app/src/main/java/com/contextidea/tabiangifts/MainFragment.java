package com.contextidea.tabiangifts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.contextidea.tabiangifts.adapters.ProductsAdapter;
import com.contextidea.tabiangifts.databinding.FragmentMainBinding;
import com.contextidea.tabiangifts.models.Product;
import com.contextidea.tabiangifts.util.Products;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "MainFragment";
    //Data binding
    FragmentMainBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding=FragmentMainBinding.inflate(inflater);
        mBinding.swipeRefreshLayout.setOnRefreshListener(this);
        setupProductsList();
       return mBinding.getRoot();
    }
    private void setupProductsList(){
        Products products = new Products();
        List<Product> productList = new ArrayList<>();
        productList.addAll(Arrays.asList(products.PRODUCTS));
        mBinding.setProducts(productList);
    }

    @Override
    public void onRefresh() {
        Products products = new Products();
        List<Product> productList = new ArrayList<>();
//        productList.addAll(Arrays.asList(products.PRODUCTS));
//        ((ProductsAdapter)mBinding.recyclervView.getAdapter()).refreshList(productList);
//        onItemsLoadComplete();
    }
    void onItemsLoadComplete() {
        (mBinding.recyclervView.getAdapter()).notifyDataSetChanged();
        mBinding.swipeRefreshLayout.setRefreshing(false);
    }
}
