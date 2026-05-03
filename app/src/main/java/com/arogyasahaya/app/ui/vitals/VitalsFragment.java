package com.arogyasahaya.app.ui.vitals;

import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.arogyasahaya.app.R;
import com.arogyasahaya.app.data.entity.VitalLog;
import com.arogyasahaya.app.databinding.FragmentVitalsBinding;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.*;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.material.snackbar.Snackbar;
import java.text.SimpleDateFormat;
import java.util.*;

public class VitalsFragment extends Fragment {

    private FragmentVitalsBinding binding;
    private VitalsViewModel viewModel;
    private VitalsAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentVitalsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(VitalsViewModel.class);

        setupChart();
        setupRecyclerView();
        observeData();

        binding.fabAddVital.setOnClickListener(v -> showAddVitalDialog());
    }

    private void setupChart() {
        LineChart chart = binding.lineChart;
        chart.setDescription(null);
        chart.setTouchEnabled(true);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setDrawGridBackground(false);
        chart.setBackgroundColor(Color.WHITE);
        chart.setPinchZoom(true);
        chart.getLegend().setTextSize(14f);
        chart.getLegend().setWordWrapEnabled(true);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(12f);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTextSize(12f);
        leftAxis.setAxisMinimum(0f);

        chart.getAxisRight().setEnabled(false);
    }

    private void setupRecyclerView() {
        adapter = new VitalsAdapter(vital -> {
            new AlertDialog.Builder(requireContext())
                    .setTitle("Delete Entry?")
                    .setMessage("Remove this vital log entry?")
                    .setPositiveButton("Delete", (d, w) -> viewModel.delete(vital))
                    .setNegativeButton("Cancel", null)
                    .show();
        });
        binding.rvVitals.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvVitals.setAdapter(adapter);
    }

    private void observeData() {
        viewModel.getLast7DaysVitals().observe(getViewLifecycleOwner(), vitals -> {
            updateChart(vitals);
            adapter.submitList(vitals);

            if (vitals == null || vitals.isEmpty()) {
                binding.tvNoData.setVisibility(View.VISIBLE);
                binding.lineChart.setVisibility(View.GONE);
            } else {
                binding.tvNoData.setVisibility(View.GONE);
                binding.lineChart.setVisibility(View.VISIBLE);
            }
        });
    }

    private void updateChart(List<VitalLog> vitals) {
        if (vitals == null || vitals.isEmpty()) {
            binding.lineChart.clear();
            return;
        }

        List<VitalLog> reversed = new ArrayList<>(vitals);
        Collections.reverse(reversed);

        List<Entry> bpSystolicEntries = new ArrayList<>();
        List<Entry> bpDiastolicEntries = new ArrayList<>();
        List<Entry> heartRateEntries = new ArrayList<>();
        final List<String> labels = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("d/M", Locale.getDefault());

        for (int i = 0; i < reversed.size(); i++) {
            VitalLog v = reversed.get(i);
            labels.add(sdf.format(new Date(v.recordedAt)));
            if (v.systolic > 0) bpSystolicEntries.add(new Entry(i, v.systolic));
            if (v.diastolic > 0) bpDiastolicEntries.add(new Entry(i, v.diastolic));
            if (v.heartRate > 0) heartRateEntries.add(new Entry(i, v.heartRate));
        }

        List<ILineDataSet> datasets = new ArrayList<>();

        if (!bpSystolicEntries.isEmpty()) {
            LineDataSet bpSystolic = new LineDataSet(bpSystolicEntries, "BP Systolic");
            bpSystolic.setColor(Color.parseColor("#F44336"));
            bpSystolic.setCircleColor(Color.parseColor("#F44336"));
            bpSystolic.setLineWidth(2.5f);
            bpSystolic.setCircleRadius(5f);
            bpSystolic.setValueTextSize(11f);
            datasets.add(bpSystolic);
        }

        if (!bpDiastolicEntries.isEmpty()) {
            LineDataSet bpDiastolic = new LineDataSet(bpDiastolicEntries, "BP Diastolic");
            bpDiastolic.setColor(Color.parseColor("#2196F3"));
            bpDiastolic.setCircleColor(Color.parseColor("#2196F3"));
            bpDiastolic.setLineWidth(2.5f);
            bpDiastolic.setCircleRadius(5f);
            bpDiastolic.setValueTextSize(11f);
            datasets.add(bpDiastolic);
        }

        if (!heartRateEntries.isEmpty()) {
            LineDataSet hr = new LineDataSet(heartRateEntries, "Heart Rate");
            hr.setColor(Color.parseColor("#4CAF50"));
            hr.setCircleColor(Color.parseColor("#4CAF50"));
            hr.setLineWidth(2.5f);
            hr.setCircleRadius(5f);
            hr.setValueTextSize(11f);
            hr.enableDashedLine(10f, 5f, 0f);
            datasets.add(hr);
        }

        binding.lineChart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int idx = (int) value;
                return (idx >= 0 && idx < labels.size()) ? labels.get(idx) : "";
            }
        });

        if (!datasets.isEmpty()) {
            binding.lineChart.setData(new LineData(datasets));
            binding.lineChart.invalidate();
            binding.lineChart.animateX(800);
        }
    }

    private void showAddVitalDialog() {
        View dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_vital, null);

        EditText etSystolic = dialogView.findViewById(R.id.et_systolic);
        EditText etDiastolic = dialogView.findViewById(R.id.et_diastolic);
        EditText etHeartRate = dialogView.findViewById(R.id.et_heart_rate);
        EditText etGlucose = dialogView.findViewById(R.id.et_glucose);
        EditText etWeight = dialogView.findViewById(R.id.et_weight);
        EditText etNotes = dialogView.findViewById(R.id.et_notes);

        new AlertDialog.Builder(requireContext(), R.style.Theme_ArogyaSahaya)
                .setTitle("📊 Record Today's Vitals")
                .setView(dialogView)
                .setPositiveButton("Save", (dialog, which) -> {
                    VitalLog vital = new VitalLog();
                    try { vital.systolic = Integer.parseInt(etSystolic.getText().toString()); } catch (Exception ignored) {}
                    try { vital.diastolic = Integer.parseInt(etDiastolic.getText().toString()); } catch (Exception ignored) {}
                    try { vital.heartRate = Integer.parseInt(etHeartRate.getText().toString()); } catch (Exception ignored) {}
                    try { vital.glucoseLevel = Integer.parseInt(etGlucose.getText().toString()); } catch (Exception ignored) {}
                    try { vital.weight = Float.parseFloat(etWeight.getText().toString()); } catch (Exception ignored) {}
                    vital.notes = etNotes.getText().toString().trim();

                    viewModel.insert(vital);
                    Snackbar.make(binding.getRoot(), "✓ Vitals recorded!", Snackbar.LENGTH_SHORT).show();
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
