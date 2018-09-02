package com.contextidea.tabiangifts.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.contextidea.tabiangifts.R;
import com.contextidea.tabiangifts.models.Product;

import java.util.ArrayList;
import java.util.List;




/**
 * Created by User on 2/6/2018.
 */

public class ProductsAdapter extends  RecyclerView.Adapter<ProductsAdapter.BindingHolder>{

    private static final String TAG = "ProductsAdapter";
    private List<Product> mProducts=new ArrayList<>();
    private Context mContext;

    public ProductsAdapter(Context context, List<Product> products) {
        mProducts = products;
        mContext = context;
    }
    @NonNull
    @Override
    public BindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BindingHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        ProductItemBinding mBinding;
        public BindingHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}













