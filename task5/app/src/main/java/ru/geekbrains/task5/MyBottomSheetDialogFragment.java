package ru.geekbrains.task5;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import androidx.annotation.Nullable;

public class MyBottomSheetDialogFragment extends BottomSheetDialogFragment {
    private String currentCity;

    private OnDialogListener dialogListener;
    public void setOnDialogListener(OnDialogListener dialogListener){
        this.dialogListener = dialogListener;
    }

    public static MyBottomSheetDialogFragment newInstance() {
        return new MyBottomSheetDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);
        setCancelable(false);
        final SearchView searchView = view.findViewById(R.id.searchView_Dialog);
        view.findViewById(R.id.button_dialog_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentCity = searchView.getQuery().toString();
                if (currentCity == "") {
                    dismiss();
                } else if (dialogListener != null) dialogListener.onDialogOk();
                dismiss();
            }
        });
        return view;
    }

    public String getCurrentCity() {
        return currentCity;
    }
}
