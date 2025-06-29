package com.carcar.backhome;



import android.app.AlertDialog;
import android.app.Notification;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.*;

public class itemAdapter extends RecyclerView.Adapter<itemAdapter.viewHolder> {
    List<item> itemlist;Context context;
    itemAdapter(Context context, List<item> itemlist){
        this.itemlist=itemlist;
        this.context=context;
    }
    public static class viewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView text;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            text=itemView.findViewById(R.id.text);
        }
    }

    @NonNull
    @Override
    public itemAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        item item=itemlist.get(position);
        holder.text.setText(item.gettext());
        String a=item.getimage();
        int resid = holder.itemView.getResources()
                .getIdentifier(a, "drawable", holder.itemView.getContext().getPackageName());
       holder.image.setImageResource(resid);
       holder.itemView.setOnLongClickListener(view ->{
           showDialog(item.gettext(), position);
            return true;
       });
    }

    private void showDialog(String gettext, int position) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Delete");
        builder.setMessage("Sure to delete!");
        builder.setPositiveButton("ok",(dialoge,which)->{
            itemlist.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, itemlist.size());
            Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("cancel",(dialogue,which)->{
            dialogue.dismiss();
        });
        builder.show();

    }

    @Override
    public int getItemCount() {

        return itemlist == null ? 0 : itemlist.size();
    }
}
