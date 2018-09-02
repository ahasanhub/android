package com.contextidea.tabiangifts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.contextidea.tabiangifts.databinding.FragmentViewProductBinding;
import com.contextidea.tabiangifts.util.Products;

public class ViewProductFragment extends Fragment {
    private static final String TAG = "ViewProductFragment";

    //Data binding
    FragmentViewProductBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding=FragmentViewProductBinding.inflate(inflater,container,false);
        Products products=new Products();
        mBinding.setProduct(products.PRODUCTS[0]);
        mBinding.setQty(1);
        return mBinding.getRoot();
    }
}
