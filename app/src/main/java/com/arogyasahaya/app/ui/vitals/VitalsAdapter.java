package com.arogyasahaya.app.ui.vitals;

import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.*;
import com.arogyasahaya.app.R;
import com.arogyasahaya.app.data.entity.VitalLog;
import java.text.SimpleDateFormat;
import java.util.*;

public class VitalsAdapter extends ListAdapter<VitalLog, VitalsAdapter.ViewHolder> {

    public interface OnDeleteListener { void onDelete(VitalLog vital); }
    private final OnDeleteListener deleteListener;

    public VitalsAdapter(OnDeleteListener delete) {
        super(DIFF_CALLBACK);
        this.deleteListener = delete;
    }

    private static final DiffUtil.ItemCallback<VitalLog> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<>() {
                @Override public boolean areItemsTheSame(@NonNull VitalLog a, @NonNull VitalLog b) { return a.id == b.id; }
                @Override public boolean areContentsTheSame(@NonNull VitalLog a, @NonNull VitalLog b) {
                    return a.systolic == b.systolic && a.diastolic == b.diastolic
                            && a.heartRate == b.heartRate && a.recordedAt == b.recordedAt;
                }
            };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vital_log, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VitalLog vital = getItem(position);
        SimpleDateFormat sdf = new SimpleDateFormat("d MMM, h:mm a", Locale.getDefault());
        holder.tvDate.setText(sdf.format(new Date(vital.recordedAt)));
        holder.tvBP.setText("BP: " + vital.getBPText());
        holder.tvHR.setText("HR: " + vital.getHeartRateText());
        holder.tvGlucose.setText("Glucose: " + vital.getGlucoseText());
        holder.tvBPStatus.setText(vital.getBPStatus());
        holder.btnDelete.setOnClickListener(v -> deleteListener.onDelete(vital));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvBP, tvHR, tvGlucose, tvBPStatus;
        ImageButton btnDelete;

        ViewHolder(View v) {
            super(v);
            tvDate = v.findViewById(R.id.tv_vital_date);
            tvBP = v.findViewById(R.id.tv_vital_bp);
            tvHR = v.findViewById(R.id.tv_vital_hr);
            tvGlucose = v.findViewById(R.id.tv_vital_glucose);
            tvBPStatus = v.findViewById(R.id.tv_bp_status);
            btnDelete = v.findViewById(R.id.btn_delete_vital);
        }
    }
}
