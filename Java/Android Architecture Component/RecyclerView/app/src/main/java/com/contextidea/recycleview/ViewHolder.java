package com.contextidea.recycleview;

import android.arch.lifecycle.ViewModel;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.contextidea.recycleview.databinding.SportDataBinding;

public class ViewHolder extends RecyclerView.ViewHolder {
    private SportDataBinding mDataBinding;
    public ViewHolder(SportDataBinding dataBinding) {
        super(dataBinding.getRoot());
        mDataBinding=dataBinding;
    }
    public void Bind(SportViewModel viewModel)
    {
        mDataBinding.setModelView(viewModel);
    }

    public SportDataBinding getDataBinding() {
        return mDataBinding;
    }
}
