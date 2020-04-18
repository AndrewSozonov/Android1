package ru.geekbrains.task5;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.ViewHolder> {


    ArrayList<String> citiesHistory;
    float[] temperatureHistory;

    HistoryRecyclerAdapter(ArrayList<String> cH, float[] tH)
    {
        citiesHistory = cH;
        temperatureHistory = tH;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_recycler_item, parent, false);
        return new HistoryRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView textViewHistoryCities = holder.getTextViewHistoryCities();
        textViewHistoryCities.setText(citiesHistory.get(position));
        TextView textViewHistoryTemperature = holder.getTextViewHistoryTemperature();
        textViewHistoryTemperature.setText(String.format("%.1f С°",temperatureHistory[position]));
    }

    @Override
    public int getItemCount() {
        return citiesHistory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewHistoryCities;
        private TextView textViewHistoryTemperature;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewHistoryCities = itemView.findViewById(R.id.recycler_textview_history_cities);
            textViewHistoryTemperature = itemView.findViewById(R.id.recycler_textview_history_temperature);
        }

        public TextView getTextViewHistoryCities() {
            return textViewHistoryCities;
        }
        public TextView getTextViewHistoryTemperature() {
            return textViewHistoryTemperature;
        }

    }
}


