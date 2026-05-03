package com.arogyasahaya.app.ui.emergency;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.*;
import android.view.animation.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.arogyasahaya.app.databinding.FragmentEmergencyBinding;
import com.arogyasahaya.app.ui.profile.ProfileViewModel;
import com.google.android.material.snackbar.Snackbar;

public class EmergencyFragment extends Fragment {

    private FragmentEmergencyBinding binding;
    private ProfileViewModel profileViewModel;
    private String emergencyPhone = "";
    private String emergencyName = "";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEmergencyBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        // Load emergency contact
        profileViewModel.getProfile().observe(getViewLifecycleOwner(), profile -> {
            if (profile != null) {
                emergencyPhone = profile.emergencyContactPhone != null ? profile.emergencyContactPhone : "";
                emergencyName = profile.emergencyContactName != null ? profile.emergencyContactName : "Emergency Contact";
                if (!emergencyPhone.isEmpty()) {
                    binding.tvEmergencyContact.setText("📞 " + emergencyName + ": " + emergencyPhone);
                } else {
                    binding.tvEmergencyContact.setText("⚠ Set emergency contact in Profile");
                }
            }
        });

        // Pulse animation on SOS button
        startPulseAnimation();

        binding.btnSos.setOnClickListener(v -> {
            vibrateDevice();
            showSOSConfirmDialog();
        });

        binding.btnCallAsha.setOnClickListener(v -> {
            // Simulated ASHA worker call
            Snackbar.make(binding.getRoot(), "Calling ASHA Worker...", Snackbar.LENGTH_LONG).show();
        });
    }

    private void startPulseAnimation() {
        Animation pulse = new ScaleAnimation(
                1f, 1.08f, 1f, 1.08f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        pulse.setDuration(700);
        pulse.setRepeatMode(Animation.REVERSE);
        pulse.setRepeatCount(Animation.INFINITE);
        pulse.setInterpolator(new AccelerateDecelerateInterpolator());
        binding.btnSos.startAnimation(pulse);
    }

    private void showSOSConfirmDialog() {
        String msg = emergencyPhone.isEmpty()
                ? "This will simulate an emergency alert. (Set emergency contact in Profile to enable actual calling)"
                : "This will call " + emergencyName + " (" + emergencyPhone + ") immediately.";

        new AlertDialog.Builder(requireContext())
                .setTitle("🚨 Send Emergency Alert?")
                .setMessage(msg)
                .setPositiveButton("YES, CALL NOW", (dialog, which) -> triggerSOS())
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void triggerSOS() {
        if (!emergencyPhone.isEmpty()) {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + emergencyPhone));
                startActivity(callIntent);
            } else {
                // Fall back to dial
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:" + emergencyPhone));
                startActivity(dialIntent);
            }
        } else {
            Snackbar.make(binding.getRoot(),
                    "🚨 Emergency alert sent! (Simulated — please add contact in Profile)",
                    Snackbar.LENGTH_LONG).show();
        }
    }

    private void vibrateDevice() {
        try {
            Vibrator v = (Vibrator) requireContext().getSystemService(android.content.Context.VIBRATOR_SERVICE);
            if (v != null) v.vibrate(300);
        } catch (Exception ignored) {}
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
