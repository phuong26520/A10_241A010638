package vn.edu.vhu.ltdd.a10gallery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // TODO: thay 2201234567 bằng MSSV của bạn
    private static final String TAG = "A10_241A010638";
    public static final String EXTRA_VI_TRI = "extra_vi_tri";
    public static final String EXTRA_DANH_SACH_ANH = "extra_danh_sach_anh";

    public static final int[] ANH = {
            R.drawable.anh1, R.drawable.anh2, R.drawable.anh3, R.drawable.anh4,
            R.drawable.anh5, R.drawable.anh6, R.drawable.anh7, R.drawable.anh8,
    };

    private final List<Integer> danhSachAnh = new ArrayList<>();
    private final List<String> chuThich = new ArrayList<>();
    private AnhAdapter adapter;
    private boolean dangDungLuoiSoLe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        for (int i = 0; i < ANH.length; i++) {
            danhSachAnh.add(ANH[i]);
            chuThich.add(getString(R.string.anh_so, i + 1));
        }

        RecyclerView rv = findViewById(R.id.rvAnh);
        rv.setLayoutManager(new GridLayoutManager(this, 3));
        rv.setHasFixedSize(true);

        adapter = new AnhAdapter(danhSachAnh, chuThich,
                viTri -> {
                    Log.d(TAG, "Mở ảnh lớn tại vị trí " + viTri);
                    Intent i = new Intent(this, ViewerActivity.class);
                    i.putExtra(EXTRA_VI_TRI, viTri);
                    int[] anhDangCo = new int[danhSachAnh.size()];
                    for (int j = 0; j < danhSachAnh.size(); j++) {
                        anhDangCo[j] = danhSachAnh.get(j);
                    }
                    i.putExtra(EXTRA_DANH_SACH_ANH, anhDangCo);
                    startActivity(i);
                },
                viTri -> {
                    danhSachAnh.remove(viTri);
                    chuThich.remove(viTri);
                    adapter.notifyItemRemoved(viTri);
                    Toast.makeText(this, "Đã xóa ảnh " + (viTri + 1), Toast.LENGTH_SHORT).show();
                });
        rv.setAdapter(adapter);

        findViewById(R.id.btnDoiCot).setOnClickListener(v -> doiSoCot(rv));
        findViewById(R.id.btnDoiKieuLuoi).setOnClickListener(v -> doiKieuLuoi(rv));
    }

    private void doiSoCot(RecyclerView rv) {
        if (dangDungLuoiSoLe) return;
        GridLayoutManager lm = (GridLayoutManager) rv.getLayoutManager();
        if (lm == null) return;
        int soCot = lm.getSpanCount() + 1;
        if (soCot > 4) soCot = 2;
        lm.setSpanCount(soCot);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, getString(R.string.so_cot, soCot), Toast.LENGTH_SHORT).show();
    }

    private void doiKieuLuoi(RecyclerView rv) {
        dangDungLuoiSoLe = !dangDungLuoiSoLe;
        adapter.setLuoiSoLe(dangDungLuoiSoLe);
        if (dangDungLuoiSoLe) {
            rv.setLayoutManager(new StaggeredGridLayoutManager(
                    2, StaggeredGridLayoutManager.VERTICAL));
            Toast.makeText(this, "Đã chuyển sang lưới so le", Toast.LENGTH_SHORT).show();
        } else {
            rv.setLayoutManager(new GridLayoutManager(this, 3));
            Toast.makeText(this, "Đã chuyển về lưới đều", Toast.LENGTH_SHORT).show();
        }
        rv.setAdapter(adapter);
    }
}
