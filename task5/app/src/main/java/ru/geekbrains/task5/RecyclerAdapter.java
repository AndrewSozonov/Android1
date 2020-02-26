package ru.geekbrains.task5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private String [] time;
    private String [] temperature;

    RecyclerAdapter(String[] time, String[] temperature){
        this.time = time;
        this.temperature = temperature;
    }

    @NonNull
    @Override
    public RecyclerAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.RecyclerViewHolder holder, int position) {
        holder.setData(time[position], temperature[position]);
    }

    @Override
    public int getItemCount() {
        return time.length;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTime;
        private TextView textViewTemperature;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTime = itemView.findViewById(R.id.recycler_textview_time);
            textViewTemperature = itemView.findViewById(R.id.recycler_textview_temperature);
        }

        void setData(String time, String temperature) {
            textViewTime.setText(time);
            textViewTemperature.setText(temperature);
        }
    }
}
