package vn.edu.vhu.ltdd.a10gallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AnhAdapter extends RecyclerView.Adapter<AnhAdapter.AnhViewHolder> {

    public interface OnAnhClickListener {
        void onAnhClick(int viTri);
    }

    public interface OnAnhDeleteListener {
        void onAnhDelete(int viTri);
    }

    static class AnhViewHolder extends RecyclerView.ViewHolder {
        final ImageView imgAnh;
        final TextView tvChuThich;
        final ImageButton btnXoa;

        AnhViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAnh = itemView.findViewById(R.id.imgAnh);
            tvChuThich = itemView.findViewById(R.id.tvChuThich);
            btnXoa = itemView.findViewById(R.id.btnXoa);
        }
    }

    private final List<Integer> anh;
    private final List<String> chuThich;
    private final OnAnhClickListener clickListener;
    private final OnAnhDeleteListener deleteListener;
    private boolean luoiSoLe;

    public AnhAdapter(List<Integer> anh, List<String> chuThich,
                      OnAnhClickListener clickListener, OnAnhDeleteListener deleteListener) {
        this.anh = anh;
        this.chuThich = chuThich;
        this.clickListener = clickListener;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public AnhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_anh, parent, false);
        return new AnhViewHolder(v);
    }

    public void setLuoiSoLe(boolean luoiSoLe) {
        this.luoiSoLe = luoiSoLe;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull AnhViewHolder holder, int position) {
        holder.imgAnh.setImageResource(anh.get(position));
        ViewGroup.LayoutParams params = holder.imgAnh.getLayoutParams();
        params.height = luoiSoLe ? 70 + (position % 3) * 35 : 110;
        holder.imgAnh.setLayoutParams(params);
        holder.tvChuThich.setText(chuThich.get(position));
        holder.itemView.setOnClickListener(v -> {
            int p = holder.getBindingAdapterPosition();
            if (p != RecyclerView.NO_POSITION) clickListener.onAnhClick(p);
        });
        holder.btnXoa.setOnClickListener(v -> {
            int p = holder.getBindingAdapterPosition();
            if (p != RecyclerView.NO_POSITION) deleteListener.onAnhDelete(p);
        });
    }

    @Override
    public int getItemCount() {
        return anh.size();
    }
}
