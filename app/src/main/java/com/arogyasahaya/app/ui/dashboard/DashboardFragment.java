package com.arogyasahaya.app.ui.dashboard;

import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.arogyasahaya.app.R;
import com.arogyasahaya.app.databinding.FragmentDashboardBinding;
import com.arogyasahaya.app.ui.medication.MedicationViewModel;
import com.arogyasahaya.app.ui.profile.ProfileViewModel;
import com.arogyasahaya.app.ui.vitals.VitalsViewModel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private MedicationViewModel medicationViewModel;
    private VitalsViewModel vitalsViewModel;
    private ProfileViewModel profileViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        medicationViewModel = new ViewModelProvider(this).get(MedicationViewModel.class);
        vitalsViewModel = new ViewModelProvider(this).get(VitalsViewModel.class);
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        setupGreeting();
        setupDate();
        observeData();
        setupClickListeners();
    }

    private void setupGreeting() {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        String greeting;
        if (hour < 12) greeting = "Good Morning";
        else if (hour < 17) greeting = "Good Afternoon";
        else greeting = "Good Evening";
        binding.tvGreeting.setText(greeting);
    }

    private void setupDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault());
        binding.tvDate.setText(sdf.format(Calendar.getInstance().getTime()));
    }

    private void observeData() {
        // Profile
        profileViewModel.getProfile().observe(getViewLifecycleOwner(), profile -> {
            if (profile != null && profile.name != null && !profile.name.isEmpty()) {
                binding.tvUserName.setText(profile.name);
            } else {
                binding.tvUserName.setText("Friend");
            }
        });

        // Medication count
        medicationViewModel.getAllMedications().observe(getViewLifecycleOwner(), medications -> {
            int count = medications != null ? medications.size() : 0;
            binding.tvMedCount.setText(count + " active medicine" + (count != 1 ? "s" : ""));

            // Morning/Afternoon/Night counts
            int morning = 0, afternoon = 0, night = 0;
            if (medications != null) {
                for (var med : medications) {
                    if (med.morning) morning++;
                    if (med.afternoon) afternoon++;
                    if (med.night) night++;
                }
            }
            binding.tvMorningCount.setText(morning + " Morning");
            binding.tvAfternoonCount.setText(afternoon + " Afternoon");
            binding.tvNightCount.setText(night + " Night");
        });

        // Latest vitals
        vitalsViewModel.getLatestVital().observe(getViewLifecycleOwner(), vital -> {
            if (vital != null) {
                binding.tvLatestBp.setText(vital.getBPText());
                binding.tvLatestHr.setText(vital.getHeartRateText());
                binding.tvLatestGlucose.setText(vital.getGlucoseText());

                SimpleDateFormat sdf = new SimpleDateFormat("d MMM, h:mm a", Locale.getDefault());
                binding.tvVitalsTime.setText("Recorded: " + sdf.format(vital.recordedAt));
                binding.tvBpStatus.setText(vital.getBPStatus());
            } else {
                binding.tvLatestBp.setText("Not recorded");
                binding.tvLatestHr.setText("Not recorded");
                binding.tvLatestGlucose.setText("Not recorded");
                binding.tvVitalsTime.setText("Record your first vitals today");
                binding.tvBpStatus.setText("");
            }
        });
    }

    private void setupClickListeners() {
        binding.cardMedication.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.navigation_medication));

        binding.cardVitals.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.navigation_vitals));

        binding.cardAsha.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.navigation_asha));

        binding.btnSos.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.navigation_emergency));

        binding.btnAddVitals.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.navigation_vitals));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
