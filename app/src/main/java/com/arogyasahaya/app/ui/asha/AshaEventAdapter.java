package com.arogyasahaya.app.ui.asha;

import android.graphics.Color;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.*;
import com.arogyasahaya.app.R;
import com.arogyasahaya.app.data.entity.AshaEvent;
import java.text.SimpleDateFormat;
import java.util.*;

public class AshaEventAdapter extends ListAdapter<AshaEvent, AshaEventAdapter.ViewHolder> {

    public interface OnRegisterListener { void onRegister(AshaEvent event); }
    private final OnRegisterListener registerListener;

    public AshaEventAdapter(OnRegisterListener listener) {
        super(DIFF_CALLBACK);
        this.registerListener = listener;
    }

    private static final DiffUtil.ItemCallback<AshaEvent> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<>() {
                @Override public boolean areItemsTheSame(@NonNull AshaEvent a, @NonNull AshaEvent b) { return a.id == b.id; }
                @Override public boolean areContentsTheSame(@NonNull AshaEvent a, @NonNull AshaEvent b) {
                    return a.title.equals(b.title) && a.isRegistered == b.isRegistered;
                }
            };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_asha_event, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AshaEvent event = getItem(position);
        holder.tvTitle.setText(event.title);
        holder.tvDescription.setText(event.description);
        holder.tvLocation.setText("📍 " + event.location);

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, d MMMM yyyy  •  h:mm a", Locale.getDefault());
        holder.tvDate.setText("🗓 " + sdf.format(new Date(event.eventDate)));

        // Event type badge
        switch (event.eventType) {
            case "health_camp":
                holder.tvType.setText("🏥 Health Camp");
                holder.tvType.setBackgroundResource(R.drawable.bg_badge_green);
                break;
            case "asha_visit":
                holder.tvType.setText("👩‍⚕️ ASHA Visit");
                holder.tvType.setBackgroundResource(R.drawable.bg_badge_blue);
                break;
            case "vaccination":
                holder.tvType.setText("💉 Vaccination");
                holder.tvType.setBackgroundResource(R.drawable.bg_badge_orange);
                break;
            default:
                holder.tvType.setText("📋 Event");
                break;
        }

        if (event.isRegistered) {
            holder.btnRegister.setText("✓ Registered");
            holder.btnRegister.setEnabled(false);
            holder.btnRegister.setAlpha(0.6f);
        } else {
            holder.btnRegister.setText("Register Interest");
            holder.btnRegister.setEnabled(true);
            holder.btnRegister.setAlpha(1f);
            holder.btnRegister.setOnClickListener(v -> registerListener.onRegister(event));
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription, tvLocation, tvDate, tvType;
        Button btnRegister;

        ViewHolder(View v) {
            super(v);
            tvTitle = v.findViewById(R.id.tv_event_title);
            tvDescription = v.findViewById(R.id.tv_event_description);
            tvLocation = v.findViewById(R.id.tv_event_location);
            tvDate = v.findViewById(R.id.tv_event_date);
            tvType = v.findViewById(R.id.tv_event_type);
            btnRegister = v.findViewById(R.id.btn_register);
        }
    }
}
