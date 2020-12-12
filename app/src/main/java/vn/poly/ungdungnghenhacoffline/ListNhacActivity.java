package vn.poly.ungdungnghenhacoffline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import vn.poly.ungdungnghenhacoffline.adapter.SongAdapter;
import vn.poly.ungdungnghenhacoffline.R;

import java.util.ArrayList;

public class ListNhacActivity extends AppCompatActivity {
    ListView lvSong;
    ArrayList<Song> songArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nhac);
        setTitle("Dream Music");
        lvSong = findViewById(R.id.lvSong);
        songArrayList = new ArrayList<>();
        songArrayList.add(new Song(R.drawable.onlyc, "Anh không đòi quà", R.raw.anh_khong_doi_qua, "OnlyC ft Karik"));
        songArrayList.add(new Song(R.drawable.karik, "Anh là sinh viên", R.raw.anh_la_sinh_vien, "Karik"));
        songArrayList.add(new Song(R.drawable.denvau, "Anh đếch cần gì nhiều ngoài em", R.raw.anhdechcanginhieungoaiem, "Đen"));
        songArrayList.add(new Song(R.drawable.jack, "Bạc phận", R.raw.bacphan1, "Jack"));
        songArrayList.add(new Song(R.drawable.bainaychillphet, "Bài này chill phết", R.raw.bai_nay_chill_phet, "Đen ft Min"));
        songArrayList.add(new Song(R.drawable.baytynguoi, "7 tỷ người", R.raw.baytynguoi1, "Lynk Lee"));
        songArrayList.add(new Song(R.drawable.bigcitiboi, "Bigcityboi", R.raw.bigcityboi, "Binz"));
        songArrayList.add(new Song(R.drawable.buonthicukhocdi, "Buồn thì cứ khóc đi", R.raw.buonthicukhocdi1, "Lynk Lee"));
        SongAdapter adapter = new SongAdapter(songArrayList);
        lvSong.setAdapter(adapter);
        lvSong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent1 = new Intent(ListNhacActivity.this, PlayMusicActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("stt", position);
                intent1.putExtra("bundle", bundle);
                startActivity(intent1);


            }
        });

    }

    }

