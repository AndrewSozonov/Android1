package ru.geekbrains.task5;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import ru.geekbrains.task5.dataBase.History;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.ViewHolder> {

    private HistorySource dataSource;
    private long menuPosition;
    private Activity activity;


    HistoryRecyclerAdapter(HistorySource dataSource, Activity activity)
    {
        this.dataSource = dataSource;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_recycler_item, parent, false);
        return new HistoryRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<History> histories = dataSource.getHistories();
        History history = histories.get(position);

        holder.textViewHistoryCities.setText(history.city);
        holder.textViewHistoryTemperature.setText(String.format("%.1f С°",history.temperature));
        holder.textViewHistoryDate.setText(history.date);

        holder.cardView.setOnLongClickListener(view -> {
            menuPosition = position;
            return false;
        });


        if (activity != null){
            activity.registerForContextMenu(holder.cardView);
        }
    }

    @Override
    public int getItemCount() {
        return (int)dataSource.getCountHistory();
    }

    public long getMenuPosition() {
        return menuPosition;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewHistoryCities;
        private TextView textViewHistoryTemperature;
        private TextView textViewHistoryDate;
        View cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView;
            textViewHistoryCities = cardView.findViewById(R.id.recycler_textview_history_cities);
            textViewHistoryTemperature = cardView.findViewById(R.id.recycler_textview_history_temperature);
            textViewHistoryDate = cardView.findViewById(R.id.recycler_textview_history_date);
        }

        public TextView getTextViewHistoryCities() {
            return textViewHistoryCities;
        }
        public TextView getTextViewHistoryTemperature() {
            return textViewHistoryTemperature;
        }

    }
}


