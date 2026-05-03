package com.arogyasahaya.app.ui.profile;

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.arogyasahaya.app.R;
import com.arogyasahaya.app.data.entity.UserProfile;
import com.arogyasahaya.app.databinding.FragmentProfileBinding;
import com.google.android.material.snackbar.Snackbar;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ProfileViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        viewModel.getProfile().observe(getViewLifecycleOwner(), profile -> {
            if (profile != null) {
                populateForm(profile);
            }
        });

        binding.btnSaveProfile.setOnClickListener(v -> saveProfile());
    }

    private void populateForm(UserProfile profile) {
        if (profile.name != null) binding.etName.setText(profile.name);
        if (profile.age > 0) binding.etAge.setText(String.valueOf(profile.age));
        if (profile.bloodGroup != null) binding.etBloodGroup.setText(profile.bloodGroup);
        if (profile.chronicConditions != null) binding.etChronicConditions.setText(profile.chronicConditions);
        if (profile.allergies != null) binding.etAllergies.setText(profile.allergies);
        if (profile.emergencyContactName != null) binding.etEmergencyName.setText(profile.emergencyContactName);
        if (profile.emergencyContactPhone != null) binding.etEmergencyPhone.setText(profile.emergencyContactPhone);
        if (profile.ashaWorkerName != null) binding.etAshaName.setText(profile.ashaWorkerName);
        if (profile.ashaWorkerPhone != null) binding.etAshaPhone.setText(profile.ashaWorkerPhone);

        // Gender spinner
        if (profile.gender != null) {
            String[] genders = {"Male", "Female", "Other"};
            for (int i = 0; i < genders.length; i++) {
                if (genders[i].equals(profile.gender)) {
                    binding.spinnerGender.setSelection(i);
                    break;
                }
            }
        }
    }

    private void saveProfile() {
        String name = binding.etName.getText().toString().trim();
        if (name.isEmpty()) {
            binding.etName.setError("Name is required");
            return;
        }

        UserProfile profile = new UserProfile();
        profile.name = name;
        try { profile.age = Integer.parseInt(binding.etAge.getText().toString().trim()); } catch (Exception ignored) {}
        profile.gender = binding.spinnerGender.getSelectedItem().toString();
        profile.bloodGroup = binding.etBloodGroup.getText().toString().trim();
        profile.chronicConditions = binding.etChronicConditions.getText().toString().trim();
        profile.allergies = binding.etAllergies.getText().toString().trim();
        profile.emergencyContactName = binding.etEmergencyName.getText().toString().trim();
        profile.emergencyContactPhone = binding.etEmergencyPhone.getText().toString().trim();
        profile.ashaWorkerName = binding.etAshaName.getText().toString().trim();
        profile.ashaWorkerPhone = binding.etAshaPhone.getText().toString().trim();

        viewModel.saveProfile(profile);
        Snackbar.make(binding.getRoot(), "✓ Profile saved successfully!", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
