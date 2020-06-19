package com.semih.MuzikSizsiniz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.Transliterator;
import android.location.Location;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;

import java.io.IOException;
import java.util.ArrayList;

public class ListingActivity extends AppCompatActivity {
    ListView lv;
    SeekBar seekBar;
    Button btnoncekiSarki, btnsonrakiSarki;
    MediaPlayer mediaPlayer;
    Context context;
    ArrayList<Muzik> muzikler;
    Handler handler;
    Runnable runnable;
    int position;


    public void muzikleriListele(String dbAdi, ArrayList<Muzik> muzikler,String aranan) {
        SQLiteDatabase db = openOrCreateDatabase(dbAdi, MODE_PRIVATE, null);
        String sorgu = "Create Table if not exists Muzikler(id INTEGER PRIMARY KEY,Baslik varchar,URL varchar, Sanatci varchar, Album varchar, Tur varchar)";
        db.execSQL(sorgu);
        Cursor c = db.rawQuery("Select * from Muzikler where Baslik like '%"+aranan+"%'",null);
        if (c.getCount()>0){
            c.moveToFirst();
            do{
                //muzikler.add(c.getString(1));
            }
            while (c.moveToNext());
        }
        else {
            Log.d("Log","Hiç şarkı bulunamadı !");
        }
    }
    String myUri;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_listing);

            context = this;
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            this.setTitle("Listelenen Müzikler");
            muzikler=new ArrayList<>();
            String aranan = getIntent().getStringExtra("aranan");
            muzikleriListele("myDB",muzikler,aranan);
            muzikler.add(new Muzik("Yıldızların Altında","https://firebasestorage.googleapis.com/v0/b/muzikapp-7a80f.appspot.com/o/Kargo%20-%20Yıldızların%20Altında%20Official%20Video.mp3?alt=media&token=b91a16ef-9b37-4a9b-8e6b-1c48901f1111","Kargo","Albüm",1));
            muzikler.add(new Muzik("Ay YÜzlüm","https://firebasestorage.googleapis.com/v0/b/muzikapp-7a80f.appspot.com/o/Nahidə%20Babaşli%20-%20Ay%20yüzlüm%20%20%2025%20Nisan%202018.mp3?alt=media&token=db545ed0-acfa-499b-8a5d-b2d87d3b3f50","Nahidə Babaşli","Albüm",2));
            muzikler.add(new Muzik("Yağmurlar","https://firebasestorage.googleapis.com/v0/b/muzikapp-7a80f.appspot.com/o/An%C4%B1l%20Piyanc%C4%B1%20-%20feat%20Perdenin%20Ard%C4%B1ndakiler-Ya%C4%9Fmurlar.mp3?alt=media&token=d45f801f-2573-4a80-8760-8b5cf2b99cec","Anıl Piyancı & Perdenin Ardındakiler","Albüm",3));
            muzikler.add(new Muzik("Ankara'yla Bozuşuruz","https://firebasestorage.googleapis.com/v0/b/muzikapp-7a80f.appspot.com/o/Perdenin%20Ardındakiler%20-%20Ankara%20yla%20Bozuşuruz.mp3?alt=media&token=496800d1-4c97-4114-8b0f-adce6be89f08","Perdenin Ardındakiler","Albüm",4));
            muzikler.add(new Muzik("Hatıralarım","https://firebasestorage.googleapis.com/v0/b/muzikapp-7a80f.appspot.com/o/Perdenin%20Ardındakiler%20-%20Hatıralarım.mp3?alt=media&token=7f1ce669-95ea-41f0-af79-e03555688e31","Perdenin Ardındakiler","Albüm",5));
            muzikler.add(new Muzik("Bul Bütün Denizleri","https://firebasestorage.googleapis.com/v0/b/muzikapp-7a80f.appspot.com/o/Perdenin%20Ardındakiler%20-%20Bul%20Bütün%20Denizleri.mp3?alt=media&token=eab191af-4351-4473-b6e2-ecac37d4fd88","Perdenin Ardındakiler","Albüm",6));
            muzikler.add(new Muzik("Bütün İstanbul Biliyo","https://firebasestorage.googleapis.com/v0/b/muzikapp-7a80f.appspot.com/o/İkiye%20On%20Kala%20-%20Bütün%20İstanbul%20Biliyo.mp3?alt=media&token=4f2596d2-371a-4482-b945-38163993e584","İkiye On Kala","Albüm",7));
            muzikler.add(new Muzik("Oh My Oh","https://firebasestorage.googleapis.com/v0/b/muzikapp-7a80f.appspot.com/o/Camila%20Cabello%20ft.%20DaBaby%20-%20My%20Oh%20My%20Danny%20Dove%20Remix%20INFINITY%20BASS%20%23enjoybeauty%20%23onevideveryday.mp3?alt=media&token=38f47d25-92e2-4e7a-8e14-b5a34b732603","Camila Cabello ft. DaBaby","Albüm",8));
            muzikler.add(new Muzik("Sen Gibi","https://firebasestorage.googleapis.com/v0/b/muzikapp-7a80f.appspot.com/o/Taladro%20-%20Sen%20Gibi%20(%20Bir%20Pişmanlık%20Hikayesi%20).mp3?alt=media&token=ea29b568-b281-4282-8827-bc710128c5a4","Taladro","Albüm",9));
            muzikler.add(new Muzik("Kayboluyorum","https://firebasestorage.googleapis.com/v0/b/muzikapp-7a80f.appspot.com/o/Sedef%20Sebüktekin%20-%20Kayboluyorum%20(Süt)%20(feat.%20Canozan).mp3?alt=media&token=48e34c8a-e1d9-40f7-8dde-15191109d116","Sedef Sebüktekim & Can Ozan","Albüm",10));
            muzikler.add(new Muzik("Gözleri Aşka Gülen","https://firebasestorage.googleapis.com/v0/b/muzikapp-7a80f.appspot.com/o/Nilipek.%20-%20Gözleri%20Aşka%20Gülen.mp3?alt=media&token=be0f676b-06ca-4a26-a7a9-e7fef0661e6f","Nil İpek","Albüm",11));
            muzikler.add(new Muzik("Yad Eller","https://firebasestorage.googleapis.com/v0/b/muzikapp-7a80f.appspot.com/o/maNga%20-%20Yad%20Eller.mp3?alt=media&token=f9ea2dc4-4c44-4a4a-a13b-8f790af634f8","Manga","Albüm",12));
            muzikler.add(new Muzik("İyi De Bana Ne","https://firebasestorage.googleapis.com/v0/b/muzikapp-7a80f.appspot.com/o/Duman%20-%20İyi%20De%20Bana%20Ne.mp3?alt=media&token=8b617e23-fe71-4798-b712-3c2e2b825372","Duman","Albüm",13));
            muzikler.add(new Muzik("Journey","https://firebasestorage.googleapis.com/v0/b/muzikapp-7a80f.appspot.com/o/Mark%20Eliyahu%20-%20Journey%20(official%20video).mp3?alt=media&token=d3c1cedb-ff50-445d-a5c8-da54b07a545d","Mark Eliyahu","Albüm",14));
            muzikler.add(new Muzik("Beni Benimle Bırak","https://firebasestorage.googleapis.com/v0/b/muzikapp-7a80f.appspot.com/o/Manga%20-%20Beni%20Benimle%20Bırak%202009%20(Şehr-i%20Hüzün).mp3?alt=media&token=b6b53cb3-c86c-435f-8430-3fb09f99796c","Manga","Albüm",15));

            ArrayAdapter<Muzik> musicArrayAdapter = new ArrayAdapter<Muzik>(this,android.R.layout.simple_list_item_1,muzikler);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            lv = (ListView)findViewById(R.id.listeGorunumu);
            lv.setAdapter(musicArrayAdapter);
            seekBar= findViewById(R.id.seekBar);
            handler= new Handler();
            ImageButton btnPlay = (ImageButton)findViewById(R.id.oynat);
            btnPlay.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    mediaPlayer.start();
                }
            });
            ImageButton btnPause = (ImageButton)findViewById(R.id.durdur);
            btnPause.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    mediaPlayer.pause();
                }
            });
            Button btnoncekiSarki = (Button)findViewById(R.id.oncekiSarki);
            btnoncekiSarki.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    try {
                        Uri myUri = Uri.parse(muzikler.get(position - 1).getMuzikUrl());
                        try {
                            mediaPlayer.setDataSource(context, myUri);
                            mediaPlayer.prepare();
                            if(!mediaPlayer.isPlaying()){
                                mediaPlayer.start();
                            }else{
                                mediaPlayer.reset();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    } catch (Exception e){

                    }
                    setTitle(muzikler.get(position - 1).getMuzikAdi());
                }
            });
            Button btnsonrakiSarki = (Button)findViewById(R.id.sonrakiSarki);
            btnsonrakiSarki.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    try {
                        Uri myUri = Uri.parse(muzikler.get(position + 1).getMuzikUrl());
                        try {
                            mediaPlayer.setDataSource(context, myUri);
                            mediaPlayer.prepare();
                            if(!mediaPlayer.isPlaying()){
                                mediaPlayer.start();
                            }else{
                                mediaPlayer.reset();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    } catch (Exception e){

                    }
                    setTitle(muzikler.get(position + 1).getMuzikAdi());
                }
            });
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    try {
                        Uri myUri = Uri.parse(muzikler.get(position).getMuzikUrl());
                        try {
                            mediaPlayer.setDataSource(context, myUri);
                            mediaPlayer.prepare();
                            seekBar.setMax(mediaPlayer.getDuration());
                            changeSeekbar();
                            if(!mediaPlayer.isPlaying()){
                                mediaPlayer.start();
                                changeSeekbar();
                            }else{
                                mediaPlayer.reset();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        setTitle(muzikler.get(position).getMuzikAdi());


                        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                                if (b){
                                    mediaPlayer.seekTo(i);
                                }
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {

                            }
                        });

                    }
                    catch (Exception e){}
                }

                private void changeSeekbar() {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());

                    if (mediaPlayer.isPlaying()){
                        runnable = new Runnable(){
                            @Override
                            public void run(){
                                changeSeekbar();
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            });

    }
}
