package com.ednerdaza.activitytechnology.testapplicationbinary.mvc.controllers.adapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ednerdaza.activitytechnology.testapplicationbinary.R;
import com.ednerdaza.activitytechnology.testapplicationbinary.classes.helpers.Helpers;
import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.controllers.Interfaces.DelegateItemAdapter;
import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.entities.ClientesEntity;

import java.util.List;

/**
 * Created by administrador on 9/06/16.
 */
public class AdapterItems extends RecyclerView.Adapter<AdapterItems.ItemsViewHolder> {

    // delegate para que reciba el onclick desde fuera del adapter
    private DelegateItemAdapter mDelegateItemAdapter;
    public DelegateItemAdapter getDelegate() {
        return mDelegateItemAdapter;
    }
    public void setDelegate(DelegateItemAdapter mDelegateItemAdapter) {
        this.mDelegateItemAdapter = mDelegateItemAdapter;
    }

    List<ClientesEntity> items;
    Activity context;

    public AdapterItems(Activity context, List<ClientesEntity> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public ItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //return null;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_items, parent, false);
        ItemsViewHolder itemsViewHolder = new ItemsViewHolder(v);
        return itemsViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemsViewHolder holder, final int position) {

        holder.tvName.setText(items.get(position).getNombre().trim());

        holder.tvCC.setText(items.get(position).getCedula().toString().trim());

        holder.tvPhone.setText(items.get(position).getTelefono().trim());

        holder.tvEmail.setText(items.get(position).getEmail());

        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if(mDelegateItemAdapter!=null){
                    mDelegateItemAdapter.onDeleteClicked(items.get(position), view);
                }*/
                String message = String.format(context.getResources().getString(R.string.message_delete),
                        items.get(position).getNombre().trim());
                Helpers.customDialogDelete(context,
                        message,
                        items.get(position),
                        view).show();
            }
        });

        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDelegateItemAdapter!=null){
                    mDelegateItemAdapter.onEditClicked(items.get(position), view);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = items.get(position).getNombre().trim();

                if(mDelegateItemAdapter!=null){
                    mDelegateItemAdapter.onItemClicked(items.get(position));
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        //return 0;
        return items.size();
    }

    public static class ItemsViewHolder extends RecyclerView.ViewHolder {
        CardView cvRoot;
        TextView tvName;
        TextView tvCC;
        TextView tvPhone;
        TextView tvEmail;
        Button btDelete;
        Button btEdit;

        ItemsViewHolder(View itemView) {
            super(itemView);
            cvRoot = (CardView)itemView.findViewById(R.id.cv_layout);
            tvName = (TextView)itemView.findViewById(R.id.tv_name);
            tvCC = (TextView)itemView.findViewById(R.id.tv_cc);
            tvPhone = (TextView)itemView.findViewById(R.id.tv_phone);
            tvEmail = (TextView)itemView.findViewById(R.id.tv_email);
            btDelete = (Button)itemView.findViewById(R.id.button_delete);
            btEdit = (Button)itemView.findViewById(R.id.button_edit);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
