package com.example.ungdungnghenhacoffline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ungdungnghenhacoffline.Adapter.SongAdapter;

import java.util.ArrayList;
import java.util.List;

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
        songArrayList.add(new Song(R.drawable.background, "Anh không đòi quà", R.raw.anh_khong_doi_qua, "OnlyC ft Karik"));
        songArrayList.add(new Song(R.drawable.background, "Anh là sinh viên", R.raw.anh_la_sinh_vien, "Karik"));
        songArrayList.add(new Song(R.drawable.background, "Anh đếch cần gì nhiều ngoài em", R.raw.anhdechcanginhieungoaiem, "Đen"));
        songArrayList.add(new Song(R.drawable.background, "Bạc phận", R.raw.bacphan1, "Jack"));
        songArrayList.add(new Song(R.drawable.background, "Bài này chill phết", R.raw.bai_nay_chill_phet, "Đen ft Min"));
        songArrayList.add(new Song(R.drawable.background, "Bảy tỷ người", R.raw.baytynguoi1, "Lynk Lee"));
        songArrayList.add(new Song(R.drawable.background, "Bigcityboi", R.raw.bigcityboi, "Binz"));
        songArrayList.add(new Song(R.drawable.background, "Buồn thì cứ khóc đi", R.raw.buonthicukhocdi1, "Lynk Lee"));
        songArrayList.add(new Song(R.drawable.background, "Cánh hồng phai", R.raw.canh_hong_phai1, "Trấn Thành"));
        songArrayList.add(new Song(R.drawable.background, "Có anh ở đây rồi", R.raw.coanhodayroi1, "Anh Quân Idol"));
        songArrayList.add(new Song(R.drawable.background, "Có chàng trai viết lên cây", R.raw.cochangtraivietlencay1, "Phan Mạnh Quỳnh"));
        songArrayList.add(new Song(R.drawable.background, "Có em chờ", R.raw.coemcho1, "Min"));
        songArrayList.add(new Song(R.drawable.background, "Cô gái vàng", R.raw.co_gai_vang, "HuyR"));
        songArrayList.add(new Song(R.drawable.background, "Đố em biết anh đang nghĩ gì", R.raw.doembietanhdangnghigi, "Đen ft Justatee"));
        songArrayList.add(new Song(R.drawable.background, "Em của ngày hôm qua", R.raw.em_cua_ngay_hom_qua, "Sơn Tùng MTP"));
        songArrayList.add(new Song(R.drawable.background, "Em gì ơi", R.raw.emgioi1, "Jack"));
        songArrayList.add(new Song(R.drawable.background, "Hai thế giới", R.raw.hai_the_gioi, "Wowy ft Karik"));
        songArrayList.add(new Song(R.drawable.background, "Hai triệu năm", R.raw.hai_trieu_nam, "Đen"));
        songArrayList.add(new Song(R.drawable.background, "Hãy trao cho anh", R.raw.hay_trao_cho_anh, "Sơn Tùng MTP"));
        songArrayList.add(new Song(R.drawable.background, "Hoa hải đường", R.raw.hoahaiduong1, "Jack"));
        songArrayList.add(new Song(R.drawable.background, "Hồng nhan", R.raw.hongnhan1, "Jack"));
        songArrayList.add(new Song(R.drawable.background, "Kém duyên", R.raw.kemduyen1, "RUM"));
        songArrayList.add(new Song(R.drawable.background, "Không phải dạng vừa đâu", R.raw.khongphaidangvuadau1, "Sơn Tùng MTP"));
        songArrayList.add(new Song(R.drawable.background, "Khu tao sống", R.raw.khu_tao_song, "Wowy ft Karik"));
        songArrayList.add(new Song(R.drawable.background, "Làm việc nước", R.raw.lam_viec_nuoc, "Various artists"));
        songArrayList.add(new Song(R.drawable.background, "Lối nhỏ", R.raw.loinho1, "Đen"));
        songArrayList.add(new Song(R.drawable.background, "Một bước yêu vạn dặm đau", R.raw.motbuocyeuvandamdau1, "Mr.Siro"));
        songArrayList.add(new Song(R.drawable.background, "Một triệu like", R.raw.mottrieulike, "Đen"));
        songArrayList.add(new Song(R.drawable.background, "Nguyên team đi vào hết", R.raw.nguyenteamdivaohet, "Binz"));
        songArrayList.add(new Song(R.drawable.background, "Như ngày hôm qua", R.raw.nhungayhomqua1, "Sơn Tùng MTP"));
        songArrayList.add(new Song(R.drawable.background, "Nơi này có anh", R.raw.noinaycoanh, "Sơn Tùng MTP"));
        songArrayList.add(new Song(R.drawable.background, "OK", R.raw.ok1, "Binz"));
        songArrayList.add(new Song(R.drawable.background, "Sóng gió", R.raw.songgio, "Jack"));
        songArrayList.add(new Song(R.drawable.background, "Thằng điên", R.raw.thangdien1, "Justatee"));
        songArrayList.add(new Song(R.drawable.background, "Thằng hầu", R.raw.thanghau1, "Nhật Phong"));
        songArrayList.add(new Song(R.drawable.background, "Tháng tư là lời nói dối của em", R.raw.thangtulaloinoidoicuaem1, "Hà Anh Tuấn"));
        songArrayList.add(new Song(R.drawable.background, "Thiên đàng", R.raw.thiendang1, "Wowy ft JoliPoli"));
        songArrayList.add(new Song(R.drawable.background, "Ừ có anh đây", R.raw.ucoanhday1, "Tino"));
        songArrayList.add(new Song(R.drawable.background, "Yêu 5", R.raw.yeu_nam, "Rhysmatic"));
        SongAdapter adapter = new SongAdapter(songArrayList);
        lvSong.setAdapter(adapter);
        lvSong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListNhacActivity.this, PlayMusicActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("stt", position);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });

    }

}