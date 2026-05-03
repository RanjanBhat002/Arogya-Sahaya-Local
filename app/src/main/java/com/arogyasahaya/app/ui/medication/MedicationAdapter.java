package com.arogyasahaya.app.ui.medication;

import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.*;
import com.arogyasahaya.app.R;
import com.arogyasahaya.app.data.entity.Medication;
import java.util.List;

public class MedicationAdapter extends ListAdapter<Medication, MedicationAdapter.ViewHolder> {

    public interface OnEditListener { void onEdit(Medication med); }
    public interface OnDeleteListener { void onDelete(Medication med); }

    private final OnEditListener editListener;
    private final OnDeleteListener deleteListener;

    public MedicationAdapter(OnEditListener edit, OnDeleteListener delete) {
        super(DIFF_CALLBACK);
        this.editListener = edit;
        this.deleteListener = delete;
    }

    private static final DiffUtil.ItemCallback<Medication> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<>() {
                @Override public boolean areItemsTheSame(@NonNull Medication a, @NonNull Medication b) { return a.id == b.id; }
                @Override public boolean areContentsTheSame(@NonNull Medication a, @NonNull Medication b) {
                    return a.name.equals(b.name) && a.dosage.equals(b.dosage)
                            && a.morning == b.morning && a.afternoon == b.afternoon && a.night == b.night;
                }
            };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medication, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Medication med = getItem(position);
        holder.tvName.setText(med.name);
        holder.tvDosage.setText(med.dosage != null && !med.dosage.isEmpty() ? med.dosage : "");
        holder.tvSchedule.setText(med.getScheduleText());

        // Time indicators
        holder.chipMorning.setVisibility(med.morning ? View.VISIBLE : View.GONE);
        holder.chipAfternoon.setVisibility(med.afternoon ? View.VISIBLE : View.GONE);
        holder.chipNight.setVisibility(med.night ? View.VISIBLE : View.GONE);

        holder.btnEdit.setOnClickListener(v -> editListener.onEdit(med));
        holder.btnDelete.setOnClickListener(v -> deleteListener.onDelete(med));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDosage, tvSchedule;
        TextView chipMorning, chipAfternoon, chipNight;
        ImageButton btnEdit, btnDelete;

        ViewHolder(View v) {
            super(v);
            tvName = v.findViewById(R.id.tv_med_name);
            tvDosage = v.findViewById(R.id.tv_med_dosage);
            tvSchedule = v.findViewById(R.id.tv_med_schedule);
            chipMorning = v.findViewById(R.id.chip_morning);
            chipAfternoon = v.findViewById(R.id.chip_afternoon);
            chipNight = v.findViewById(R.id.chip_night);
            btnEdit = v.findViewById(R.id.btn_edit);
            btnDelete = v.findViewById(R.id.btn_delete);
        }
    }
}
