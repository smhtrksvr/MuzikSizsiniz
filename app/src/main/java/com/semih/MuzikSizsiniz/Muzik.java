package com.semih.MuzikSizsiniz;

public class Muzik {
    private String muzikAdi;
    private String muzikUrl;
    private String sanatciAdSoyad;
    private String album;
    private int muzikId;
    private String tur;
    public int getMuzikId(){return muzikId;}
    public void setMuzikId(){this.muzikId = muzikId;}
    public String getTur(){return tur;}
    public void setTur(String tur){this.tur=tur;}
    @Override
    public String toString(){return muzikAdi;}
    public Muzik(){}
    public Muzik(String muzikAdi,String muzikUrl,String sanatciAdSoyad,String album,int muzikId){
        this.muzikAdi = muzikAdi;
        this.muzikUrl = muzikUrl;
        this.sanatciAdSoyad = sanatciAdSoyad;
        this.album = album;
        this.muzikId = muzikId;
    }
    public String getMuzikAdi(){return muzikAdi;}
    public void setMuzikAdi(String muzikAdi){this.muzikAdi = muzikAdi;}
    public String getMuzikUrl(){return muzikUrl;}
    public void setMuzikUrl(String muzikUrl){this.muzikUrl = muzikUrl;}
    public String getSanatciAdSoyad(){return sanatciAdSoyad;}
    public void setSanatciAdSoyad(String sanatciAdSoyad) {this.sanatciAdSoyad = sanatciAdSoyad;}
    public String getAlbum() {return album;}
    public void setAlbum(String album) {this.album = album;}

}
