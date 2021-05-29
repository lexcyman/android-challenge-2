package com.mobdeve.mangunea.androidchallenge2;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderListViewHolder> {

    private OnItemClickListener listener;
    private ArrayList<OrderList> orderListArrayList;

    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class OrderListViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTv;
        private TextView dateTv;
        private TextView idTv;
        private TextView itemTv;
        private TextView priceTv;
        private Button orderBtn;
        private Button pickupBtn;

        public OrderListViewHolder(@NonNull View listView, final OnItemClickListener listener) {
            super(listView);

            this.nameTv = (TextView) listView.findViewById(R.id.nameTv);
            this.dateTv = (TextView) listView.findViewById(R.id.dateTv);
            this.idTv = (TextView) listView.findViewById(R.id.idTv);
            this.itemTv = (TextView) listView.findViewById(R.id.itemTv);
            this.priceTv = (TextView) listView.findViewById(R.id.priceTv);
            this.orderBtn = (Button) listView.findViewById(R.id.orderBtn);
            this.pickupBtn = (Button) listView.findViewById(R.id.pickupBtn);

            this.orderBtn.setOnClickListener((view) -> {
                Intent intent = new Intent(view.getContext(), ViewOrderActivity.class);

                intent.putExtra("name", this.nameTv.getText().toString());
                intent.putExtra("date", this.dateTv.getText().toString());
                intent.putExtra("id", this.idTv.getText().toString());
                intent.putExtra("item", this.itemTv.getText().toString());
                intent.putExtra("price", this.priceTv.getText().toString());

                view.getContext().startActivity(intent);
            });

            this.pickupBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    listener.onDeleteClick(position);
                }
            });
        }

        public void setNameTv(String name) {
            this.nameTv.setText(name);
        }

        public void setDateTv(String date) {
            this.dateTv.setText(date);
        }

        public void setIdTv(String id) {
            this.idTv.setText(id);
        }

        public void setItemTv(String item) {
            this.itemTv.setText(item);
        }

        public void setPriceTv(String price) {
            this.priceTv.setText(price);
        }
    }

    public OrderListAdapter(ArrayList<OrderList> orderLists) {
        this.orderListArrayList = orderLists;
    }

    @NonNull
    @Override
    public OrderListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.order_layout, parent, false);

        OrderListViewHolder orderListViewHolder = new OrderListViewHolder(view, listener);
        return orderListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListViewHolder holder, int position) {
        holder.setNameTv(this.orderListArrayList.get(position).getName());
        holder.setDateTv(this.orderListArrayList.get(position).getDate());
        holder.setIdTv(this.orderListArrayList.get(position).getId());
        holder.setItemTv(this.orderListArrayList.get(position).getItem());
        holder.setPriceTv(this.orderListArrayList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return this.orderListArrayList.size();
    }
}
