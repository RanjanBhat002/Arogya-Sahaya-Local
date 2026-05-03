package com.arogyasahaya.app.ui.medication;

import android.app.Dialog;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.arogyasahaya.app.R;
import com.arogyasahaya.app.data.entity.Medication;
import com.arogyasahaya.app.databinding.FragmentMedicationBinding;
import com.arogyasahaya.app.worker.AlarmScheduler;
import com.google.android.material.snackbar.Snackbar;

public class MedicationFragment extends Fragment {

    private FragmentMedicationBinding binding;
    private MedicationViewModel viewModel;
    private MedicationAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMedicationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(MedicationViewModel.class);

        setupRecyclerView();
        observeData();

        binding.fabAddMedicine.setOnClickListener(v -> showAddMedicineDialog(null));
    }

    private void setupRecyclerView() {
        adapter = new MedicationAdapter(
                medication -> showAddMedicineDialog(medication),  // Edit
                medication -> confirmDelete(medication)            // Delete
        );
        binding.rvMedications.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvMedications.setAdapter(adapter);
    }

    private void observeData() {
        viewModel.getAllMedications().observe(getViewLifecycleOwner(), medications -> {
            adapter.submitList(medications);
            if (medications == null || medications.isEmpty()) {
                binding.tvEmptyState.setVisibility(View.VISIBLE);
                binding.rvMedications.setVisibility(View.GONE);
            } else {
                binding.tvEmptyState.setVisibility(View.GONE);
                binding.rvMedications.setVisibility(View.VISIBLE);
            }
        });
    }

    private void showAddMedicineDialog(@Nullable Medication existingMed) {
        View dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_medicine, null);

        EditText etName = dialogView.findViewById(R.id.et_medicine_name);
        EditText etDosage = dialogView.findViewById(R.id.et_dosage);
        CheckBox cbMorning = dialogView.findViewById(R.id.cb_morning);
        CheckBox cbAfternoon = dialogView.findViewById(R.id.cb_afternoon);
        CheckBox cbNight = dialogView.findViewById(R.id.cb_night);
        EditText etNotes = dialogView.findViewById(R.id.et_notes);

        if (existingMed != null) {
            etName.setText(existingMed.name);
            etDosage.setText(existingMed.dosage);
            cbMorning.setChecked(existingMed.morning);
            cbAfternoon.setChecked(existingMed.afternoon);
            cbNight.setChecked(existingMed.night);
            if (existingMed.notes != null) etNotes.setText(existingMed.notes);
        }

        String title = existingMed == null ? "Add Medicine" : "Edit Medicine";

        new AlertDialog.Builder(requireContext(), R.style.Theme_ArogyaSahaya)
                .setTitle(title)
                .setView(dialogView)
                .setPositiveButton("Save", (dialog, which) -> {
                    String name = etName.getText().toString().trim();
                    String dosage = etDosage.getText().toString().trim();

                    if (name.isEmpty()) {
                        Snackbar.make(binding.getRoot(), "Please enter medicine name", Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    if (!cbMorning.isChecked() && !cbAfternoon.isChecked() && !cbNight.isChecked()) {
                        Snackbar.make(binding.getRoot(), "Please select at least one time", Snackbar.LENGTH_SHORT).show();
                        return;
                    }

                    if (existingMed == null) {
                        Medication med = new Medication(name, dosage, cbMorning.isChecked(),
                                cbAfternoon.isChecked(), cbNight.isChecked());
                        med.notes = etNotes.getText().toString().trim();

                        viewModel.insert(med, id -> {
                            med.id = id;
                            AlarmScheduler.scheduleMedicationAlarm(requireContext(), med);
                            requireActivity().runOnUiThread(() ->
                                    Snackbar.make(binding.getRoot(), "✓ Medicine reminder saved!", Snackbar.LENGTH_SHORT).show());
                        });
                    } else {
                        existingMed.name = name;
                        existingMed.dosage = dosage;
                        existingMed.morning = cbMorning.isChecked();
                        existingMed.afternoon = cbAfternoon.isChecked();
                        existingMed.night = cbNight.isChecked();
                        existingMed.notes = etNotes.getText().toString().trim();

                        AlarmScheduler.cancelMedicationAlarm(requireContext(), existingMed);
                        viewModel.update(existingMed);
                        AlarmScheduler.scheduleMedicationAlarm(requireContext(), existingMed);
                        Snackbar.make(binding.getRoot(), "✓ Medicine updated!", Snackbar.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void confirmDelete(Medication medication) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Delete Medicine?")
                .setMessage("Remove " + medication.name + " from your reminders?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    AlarmScheduler.cancelMedicationAlarm(requireContext(), medication);
                    viewModel.softDelete(medication.id);
                    Snackbar.make(binding.getRoot(), medication.name + " removed", Snackbar.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
