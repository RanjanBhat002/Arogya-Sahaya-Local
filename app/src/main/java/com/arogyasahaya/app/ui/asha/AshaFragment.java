package com.arogyasahaya.app.ui.asha;

import android.os.Bundle;
import android.view.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.arogyasahaya.app.databinding.FragmentAshaBinding;
import com.google.android.material.snackbar.Snackbar;

public class AshaFragment extends Fragment {

    private FragmentAshaBinding binding;
    private AshaViewModel viewModel;
    private AshaEventAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAshaBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(AshaViewModel.class);

        adapter = new AshaEventAdapter(event -> {
            viewModel.registerForEvent(event.id);
            Snackbar.make(binding.getRoot(),
                    "✓ Interest registered for: " + event.title,
                    Snackbar.LENGTH_LONG).show();
        });

        binding.rvAshaEvents.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvAshaEvents.setAdapter(adapter);

        viewModel.getUpcomingEvents().observe(getViewLifecycleOwner(), events -> {
            adapter.submitList(events);
            if (events == null || events.isEmpty()) {
                binding.tvNoEvents.setVisibility(View.VISIBLE);
            } else {
                binding.tvNoEvents.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
