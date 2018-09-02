package com.contextidea.recycleview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.contextidea.recycleview.databinding.SportDataBinding;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    private ArrayList<SportViewModel> mList;
    private LayoutInflater inflater;

    public RecyclerViewAdapter(Context mContext, ArrayList<SportViewModel> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if (inflater==null)
       {
           inflater= LayoutInflater.from(parent.getContext());
       }
        SportDataBinding dataBinding=SportDataBinding.inflate(inflater,parent,false);
       return new ViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      final SportViewModel viewModel=mList.get(position);
      holder.Bind(viewModel);

      final SportDataBinding dataBinding=holder.getDataBinding();
      dataBinding.setHandler(new FabImgClickHandler() {
          @Override
          public void onFabImgClick() {
              if (viewModel.imgSrcId.get()==R.drawable.star)
              {
              viewModel.imgSrcId.set(R.drawable.star_outline);
                  Toast.makeText(mContext,"sport is removed to faveroutes",Toast.LENGTH_SHORT).show();
              }
              else
              {
                  viewModel.imgSrcId.set(R.drawable.star);
                  Toast.makeText(mContext,"sport is added to faveroutes",Toast.LENGTH_SHORT).show();
              }

          }
      });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
