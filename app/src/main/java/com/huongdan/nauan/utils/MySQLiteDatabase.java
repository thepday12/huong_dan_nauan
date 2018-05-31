package com.huongdan.nauan.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.huongdan.nauan.model.MonAn;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

//    CREATE TABLE tbl_mon_an (id INteger PRIMARY KEY AUTOINCREMENT, ten varchar, nguyen_lieu varchar,che_bien varchar, thoi_gian int,loai varchar, hinh_anh varchar, video varchar, yeu_thich integer default 0);
/*
INSERT INTO tbl_mon_an (ten , nguyen_lieu ,che_bien , thoi_gian ,loai , hinh_anh , video)
                         VALUES ('Cánh gà chiên nước mắm tỏi siêu ngon','10 củ lớn tỏi, băm nhỏ ;½ thìa đường;2 muỗng canh dấm gạo;½ chén nước mắm;3 muỗng canh nước cốt chanh tươi;1kg cánh gà;1 chén bột gạo;Dầu ăn;Tương ớt',
                                 'Chuẩn bị một chảo dầu nóng lên bếp, đun cho nóng già, cho tỏi băm nhuyễn vào phi thơm lên trong vòng 5 phút.;http://toinayangi.vn/wp-content/uploads/2016/11/cach-lam-ga-chien-nuoc-mam-3.jpg;Lấy một bát ăn cơm trộn hỗn hợp gồm:  đường, giấm và ¼ chén nước vào cùng. Khuấy thật đều tay. Sau đó, đổ vào nồi đang đun trên bếp.;http://toinayangi.vn/wp-content/uploads/2016/11/cach-lam-ga-chien-nuoc-mam-4.jpg;Cho nước mắm, nước cốt chanh và tương ớt vào một bát ăn cơm sạch. Sau đó bật bếp đun nóng hỗn hợp bên trên, cho hỗn hợp mắm vào đun cùng đến khi sôi thì tắt bếp.;http://toinayangi.vn/wp-content/uploads/2016/11/cach-lam-ga-chien-nuoc-mam-5.jpg;Lấy một hộp nhựa nắp kín đổ hỗn hợp đã vừa đun sôi vào sau đó để vào trong tủ lạnh 30 phút.;Chuẩn bị khay nướng và đặt cánh gà lên trên. Sau đó, rưới hỗn hợp mắm tỏi đã pha để trong tủ lạnh lên trên bề mặt cánh gà. Bọc kín lại bằng màng bọc thực phẩm để qua đêm trong tủ lạnh.;http://toinayangi.vn/wp-content/uploads/2016/11/cach-lam-ga-chien-nuoc-mam-2.jpg;Khi cánh gà đã ngấm sốt qua đêm, lấy cánh gà ra và để trong nhiệt độ phòng trong 15 phút. Đổ bột gạo vào bát và lắn cánh gà qua bột.;http://toinayangi.vn/wp-content/uploads/2016/11/cach-lam-ga-chien-nuoc-mam-7.jpg; Bật bếp, chiên cánh gà đến khi chúng chuyển sang màu vàng nâu khoảng 10 phút. Vớt ra để đĩa đã chuẩn bị lót 1 tờ giấy thấm dầu.;http://toinayangi.vn/wp-content/uploads/2016/11/cach-lam-ga-chien-nuoc-mam-8.jpg;Cho ½ sốt sốt còn lại vào đun trên lửa lớn trong 1 phút hoặc đến khi nó hơi sệt lại. Cho cánh gà vừa chiên và đảo cùng.;http://toinayangi.vn/wp-content/uploads/2016/11/cach-lam-ga-chien-nuoc-mam-9.jpg',
                                 30,
                                 'mon_chien,viet_nam',
                                 'http://toinayangi.vn/wp-content/uploads/2016/11/cach-lam-ga-chien-nuoc-mam-10.jpg',
                                 'WdPZB-ESW4k');
 */
public class MySQLiteDatabase extends SQLiteOpenHelper{
    static final String DB_NAME = "my_database.db";
    static final String DB_PATH = "/data/data/com.huongdan.nauan/databases/";
    static final int VERSION = 1;

    private SQLiteDatabase mDatabase;
    private Context mContext;
    public MySQLiteDatabase(Context context) {
        super(context, DB_NAME, null, VERSION);
        mContext = context;
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        openDataBase();
    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();

            }
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = mContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[10];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase()  {
        String myPath = DB_PATH + DB_NAME;
        if(mDatabase!=null && mDatabase.isOpen()){
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
//        mDatabase = this.getReadableDatabase();

    }

    @Override
    public synchronized void close() {
        if (mDatabase != null)
            mDatabase.close();
        super.close();
    }



    public List<MonAn> getListMonAn() {
        List<MonAn> result = new ArrayList<>();
//        mDatabase = this.getReadableDatabase();
        mDatabase.beginTransaction();
        Cursor cursor = mDatabase.rawQuery("select * from tbl_mon_an", null);
        try {
            if (cursor.moveToFirst()) {
                while (cursor.isAfterLast() == false) {//Cách lấy dữ liệu từ curssor
                    int id = cursor.getInt(cursor
                            .getColumnIndex("id"));
                    //(ten , nguyen_lieu ,che_bien , thoi_gian ,loai , hinh_anh , video)
                    String ten = cursor.getString(cursor.getColumnIndex("ten"));
                    String nguyen_lieu = cursor.getString(cursor.getColumnIndex("nguyen_lieu"));
                    String che_bien = cursor.getString(cursor.getColumnIndex("che_bien"));
                    int thoi_gian = cursor.getInt(cursor.getColumnIndex("thoi_gian"));
                    String loai = cursor.getString(cursor.getColumnIndex("loai"));
                    String hinh_anh = cursor.getString(cursor.getColumnIndex("hinh_anh"));
                    String video = cursor.getString(cursor.getColumnIndex("video"));
                    int yeu_thich = cursor.getInt(cursor.getColumnIndex("yeu_thich"));

                    for(int i=0;i<10;i++) {
                        result.add(new MonAn(id, ten, nguyen_lieu, che_bien, thoi_gian, loai, hinh_anh, video,yeu_thich));
                    }
                    cursor.moveToNext();
                }
            }


        } finally {
            mDatabase.setTransactionSuccessful();
            mDatabase.endTransaction();
            cursor.close();
            return result;
        }

    }
    public List<MonAn> getListMonAnSang() {
        List<MonAn> result = new ArrayList<>();
        mDatabase.beginTransaction();
        Cursor cursor = mDatabase.rawQuery("select * from tbl_mon_an order by thoi_gian ASC LIMIT 20", null);
        try {
            if (cursor.moveToFirst()) {
                while (cursor.isAfterLast() == false) {//Cách lấy dữ liệu từ curssor
                    int id = cursor.getInt(cursor
                            .getColumnIndex("id"));
                    //(ten , nguyen_lieu ,che_bien , thoi_gian ,loai , hinh_anh , video)
                    String ten = cursor.getString(cursor.getColumnIndex("ten"));
                    String nguyen_lieu = cursor.getString(cursor.getColumnIndex("nguyen_lieu"));
                    String che_bien = cursor.getString(cursor.getColumnIndex("che_bien"));
                    int thoi_gian = cursor.getInt(cursor.getColumnIndex("thoi_gian"));
                    String loai = cursor.getString(cursor.getColumnIndex("loai"));
                    String hinh_anh = cursor.getString(cursor.getColumnIndex("hinh_anh"));
                    String video = cursor.getString(cursor.getColumnIndex("video"));
                    int yeu_thich = cursor.getInt(cursor.getColumnIndex("yeu_thich"));
                    result.add(new MonAn(id, ten, nguyen_lieu, che_bien, thoi_gian, loai, hinh_anh, video,yeu_thich));
                    cursor.moveToNext();
                }
            }


        } finally {
            mDatabase.setTransactionSuccessful();
            mDatabase.endTransaction();
            cursor.close();
            return result;
        }

    }
    public List<MonAn> getListMonAnTruaToi() {
        List<MonAn> result = new ArrayList<>();
        mDatabase.beginTransaction();
        Cursor cursor = mDatabase.rawQuery("select * from tbl_mon_an order by RANDOM() ASC LIMIT 40", null);
        try {
            if (cursor.moveToFirst()) {
                while (cursor.isAfterLast() == false) {//Cách lấy dữ liệu từ curssor
                    int id = cursor.getInt(cursor
                            .getColumnIndex("id"));
                    //(ten , nguyen_lieu ,che_bien , thoi_gian ,loai , hinh_anh , video)
                    String ten = cursor.getString(cursor.getColumnIndex("ten"));
                    String nguyen_lieu = cursor.getString(cursor.getColumnIndex("nguyen_lieu"));
                    String che_bien = cursor.getString(cursor.getColumnIndex("che_bien"));
                    int thoi_gian = cursor.getInt(cursor.getColumnIndex("thoi_gian"));
                    String loai = cursor.getString(cursor.getColumnIndex("loai"));
                    String hinh_anh = cursor.getString(cursor.getColumnIndex("hinh_anh"));
                    String video = cursor.getString(cursor.getColumnIndex("video"));
                    int yeu_thich = cursor.getInt(cursor.getColumnIndex("yeu_thich"));
                    result.add(new MonAn(id, ten, nguyen_lieu, che_bien, thoi_gian, loai, hinh_anh, video,yeu_thich));
                    cursor.moveToNext();
                }
            }


        } finally {
            mDatabase.setTransactionSuccessful();
            mDatabase.endTransaction();
            cursor.close();
            return result;
        }

    }
    public List<MonAn> getListMonRau() {
        return getListMonAnByLoai("ic_mon_luoc");

    }

    public List<MonAn> getListMonAnByLoai(String loai) {
        List<MonAn> result = new ArrayList<>();
        mDatabase.beginTransaction();
        Cursor cursor = mDatabase.rawQuery("select * from tbl_mon_an where loai like '%"+loai+"%' order by RANDOM() ASC LIMIT 10", null);
        try {
            if (cursor.moveToFirst()) {
                while (cursor.isAfterLast() == false) {//Cách lấy dữ liệu từ curssor
                    int id = cursor.getInt(cursor
                            .getColumnIndex("id"));
                    //(ten , nguyen_lieu ,che_bien , thoi_gian ,loai , hinh_anh , video)
                    String ten = cursor.getString(cursor.getColumnIndex("ten"));
                    String nguyen_lieu = cursor.getString(cursor.getColumnIndex("nguyen_lieu"));
                    String che_bien = cursor.getString(cursor.getColumnIndex("che_bien"));
                    int thoi_gian = cursor.getInt(cursor.getColumnIndex("thoi_gian"));
                    String hinh_anh = cursor.getString(cursor.getColumnIndex("hinh_anh"));
                    String video = cursor.getString(cursor.getColumnIndex("video"));
                    int yeu_thich = cursor.getInt(cursor.getColumnIndex("yeu_thich"));
                    result.add(new MonAn(id, ten, nguyen_lieu, che_bien, thoi_gian, loai, hinh_anh, video,yeu_thich));
                    cursor.moveToNext();
                }
            }


        } finally {
            mDatabase.setTransactionSuccessful();
            mDatabase.endTransaction();
            cursor.close();
            return result;
        }

    }
    public List<MonAn> getListMonAnYeuThich() {
        List<MonAn> result = new ArrayList<>();
//        mDatabase = this.getReadableDatabase();
        mDatabase.beginTransaction();
        Cursor cursor = mDatabase.rawQuery("select * from tbl_mon_an where yeu_thich=1", null);
        try {
            if (cursor.moveToFirst()) {
                while (cursor.isAfterLast() == false) {//Cách lấy dữ liệu từ curssor
                    int id = cursor.getInt(cursor
                            .getColumnIndex("id"));
                    //(ten , nguyen_lieu ,che_bien , thoi_gian ,loai , hinh_anh , video)
                    String ten = cursor.getString(cursor.getColumnIndex("ten"));
                    String nguyen_lieu = cursor.getString(cursor.getColumnIndex("nguyen_lieu"));
                    String che_bien = cursor.getString(cursor.getColumnIndex("che_bien"));
                    int thoi_gian = cursor.getInt(cursor.getColumnIndex("thoi_gian"));
                    String loai = cursor.getString(cursor.getColumnIndex("loai"));
                    String hinh_anh = cursor.getString(cursor.getColumnIndex("hinh_anh"));
                    String video = cursor.getString(cursor.getColumnIndex("video"));
                    int yeu_thich = cursor.getInt(cursor.getColumnIndex("yeu_thich"));

                        result.add(new MonAn(id, ten, nguyen_lieu, che_bien, thoi_gian, loai, hinh_anh, video,yeu_thich));
                    cursor.moveToNext();
                }
            }


        } finally {
            mDatabase.setTransactionSuccessful();
            mDatabase.endTransaction();
            cursor.close();
            return result;
        }

    }

    public MonAn getMonAn(int id) {
        MonAn result = null;
        mDatabase.beginTransaction();
        Cursor cursor = mDatabase.rawQuery("select * from tbl_mon_an where id = "+id, null);
        try {
            if (cursor.moveToFirst()) {
                while (cursor.isAfterLast() == false) {//Cách lấy dữ liệu từ curssor

                    //(ten , nguyen_lieu ,che_bien , thoi_gian ,loai , hinh_anh , video)
                    String ten = cursor.getString(cursor.getColumnIndex("ten"));
                    String nguyen_lieu = cursor.getString(cursor.getColumnIndex("nguyen_lieu"));
                    String che_bien = cursor.getString(cursor.getColumnIndex("che_bien"));
                    int thoi_gian = cursor.getInt(cursor.getColumnIndex("thoi_gian"));
                    String loai = cursor.getString(cursor.getColumnIndex("loai"));
                    String hinh_anh = cursor.getString(cursor.getColumnIndex("hinh_anh"));
                    String video = cursor.getString(cursor.getColumnIndex("video"));
                    int yeu_thich = cursor.getInt(cursor.getColumnIndex("yeu_thich"));

                        result= new MonAn(id, ten, nguyen_lieu, che_bien, thoi_gian, loai, hinh_anh, video,yeu_thich);
                    cursor.moveToNext();
                }
            }


        } finally {
            mDatabase.setTransactionSuccessful();
            mDatabase.endTransaction();
            cursor.close();
            return result;
        }

    }


    public boolean updateYeuThich(int id,int isLove) {
        mDatabase.beginTransaction();
        boolean result = true;
        try {
            ContentValues messageContentValues = new ContentValues();
            messageContentValues.put("yeu_thich", isLove);
            mDatabase.update("tbl_mon_an", messageContentValues, "id=" + id, null);

        } catch (Exception ex) {
            result = false;
            return false;
        } finally {
            mDatabase.setTransactionSuccessful();
            mDatabase.endTransaction();
            return result;
        }
    }


}
