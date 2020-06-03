package com.semih.MuzikSizsiniz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ListingActivity extends AppCompatActivity {
    ListView lv;
    MediaPlayer mediaPlayer;
    Context context;
    ArrayList<Muzik> muzikler;

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
            FirebaseStorage storage=FirebaseStorage.getInstance();
            StorageReference storageRef=storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/muzikapp-7a80f.appspot.com/o/An%C4%B1l%20Piyanc%C4%B1%20-%20feat%20Perdenin%20Ard%C4%B1ndakiler-Ya%C4%9Fmurlar.mp3?alt=media&token=d45f801f-2573-4a80-8760-8b5cf2b99cec");
            storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {myUri=uri.toString();}});
            FirebaseStorage storage1=FirebaseStorage.getInstance();
            StorageReference storageRef1=storage1.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/muzikapp-7a80f.appspot.com/o/Perdenin%20Ardındakiler%20-%20Ankara%20yla%20Bozuşuruz.mp3?alt=media&token=496800d1-4c97-4114-8b0f-adce6be89f08");
            storageRef1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {myUri=uri.toString(); }});
            FirebaseStorage storage2=FirebaseStorage.getInstance();
            StorageReference storageRef2=storage2.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/muzikapp-7a80f.appspot.com/o/Perdenin%20Ardındakiler%20-%20Hatıralarım.mp3?alt=media&token=7f1ce669-95ea-41f0-af79-e03555688e31");
            storageRef2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {myUri=uri.toString();}});
            FirebaseStorage storage3=FirebaseStorage.getInstance();
            StorageReference storageRef3=storage3.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/muzikapp-7a80f.appspot.com/o/Perdenin%20Ardındakiler%20-%20Bul%20Bütün%20Denizleri.mp3?alt=media&token=eab191af-4351-4473-b6e2-ecac37d4fd88");
            storageRef3.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {myUri=uri.toString();}});
            FirebaseStorage storage4=FirebaseStorage.getInstance();
            StorageReference storageRef4=storage4.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/muzikapp-7a80f.appspot.com/o/İkiye%20On%20Kala%20-%20Bütün%20İstanbul%20Biliyo.mp3?alt=media&token=4f2596d2-371a-4482-b945-38163993e584");
            storageRef4.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {myUri=uri.toString();}});

            context = this;
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            this.setTitle("Listelenen Müzikler");
            muzikler=new ArrayList<>();
            String aranan = getIntent().getStringExtra("aranan");
            muzikleriListele("myDB",muzikler,aranan);

            muzikler.add(new Muzik("Yağmurlar","https://firebasestorage.googleapis.com/v0/b/muzikapp-7a80f.appspot.com/o/An%C4%B1l%20Piyanc%C4%B1%20-%20feat%20Perdenin%20Ard%C4%B1ndakiler-Ya%C4%9Fmurlar.mp3?alt=media&token=d45f801f-2573-4a80-8760-8b5cf2b99cec","Anıl Piyancı & Perdenin Ardındakiler","Albüm",10));
            muzikler.add(new Muzik("Ankara'yla Bozuşuruz","https://firebasestorage.googleapis.com/v0/b/muzikapp-7a80f.appspot.com/o/Perdenin%20Ardındakiler%20-%20Ankara%20yla%20Bozuşuruz.mp3?alt=media&token=496800d1-4c97-4114-8b0f-adce6be89f08","Perdenin Ardındakiler","Albüm",10));
            muzikler.add(new Muzik("Hatıralarım","https://firebasestorage.googleapis.com/v0/b/muzikapp-7a80f.appspot.com/o/Perdenin%20Ardındakiler%20-%20Hatıralarım.mp3?alt=media&token=7f1ce669-95ea-41f0-af79-e03555688e31","Perdenin Ardındakiler","Albüm",10));
            muzikler.add(new Muzik("Bul Bütün Denizleri","https://firebasestorage.googleapis.com/v0/b/muzikapp-7a80f.appspot.com/o/Perdenin%20Ardındakiler%20-%20Bul%20Bütün%20Denizleri.mp3?alt=media&token=eab191af-4351-4473-b6e2-ecac37d4fd88","Perdenin Ardındakiler","Albüm",10));
            muzikler.add(new Muzik("Bütün İstanbul Biliyo","https://firebasestorage.googleapis.com/v0/b/muzikapp-7a80f.appspot.com/o/İkiye%20On%20Kala%20-%20Bütün%20İstanbul%20Biliyo.mp3?alt=media&token=4f2596d2-371a-4482-b945-38163993e584","İkiye On Kala","Albüm",10));

            ArrayAdapter<Muzik> musicArrayAdapter = new ArrayAdapter<Muzik>(this,android.R.layout.simple_list_item_1,muzikler);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            lv = (ListView)findViewById(R.id.listeGorunumu);
            lv.setAdapter(musicArrayAdapter);
            ImageButton btnPlay = (ImageButton)findViewById(R.id.oynat);
            btnPlay.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    mediaPlayer.start();
                }
            });
            ImageButton btnStop = (ImageButton)findViewById(R.id.durdur);
            btnStop.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    mediaPlayer.stop();
                }
            });
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        Uri myUri = Uri.parse(muzikler.get(position).getMuzikUrl());
                        try {
                            mediaPlayer.setDataSource(context, myUri);
                            mediaPlayer.prepare(); //don't use prepareAsync for mp3 playback
                            //mediaPlayer.start();
                            if(!mediaPlayer.isPlaying()){
                                mediaPlayer.start();
                            }else{
                                mediaPlayer.reset();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        setTitle(muzikler.get(position).getMuzikAdi());
                    }catch (Exception e){}
                }
            });
    }
}
