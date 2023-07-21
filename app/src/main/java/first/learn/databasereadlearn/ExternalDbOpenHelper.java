package first.learn.databasereadlearn;



import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class ExternalDbOpenHelper extends SQLiteOpenHelper {

    //���� � ����� � ������ �� ����������
    public static String DB_PATH;
    public static String DB_NAME;
    public SQLiteDatabase database;
    public final Context mContext;



    public SQLiteDatabase getDb() {
        return database;
    }

    public ExternalDbOpenHelper(Context context, String databaseName) {
        super(context, databaseName, null, 1);
        this.mContext = context;

        System.out.println("Database Name:-----3 = "+ databaseName);
        String packageName = context.getPackageName();
        DB_PATH = String.format("//data//data//%s//databases//", packageName);
        DB_NAME = databaseName;
        openDataBase();
    }



    public SQLiteDatabase openDataBase() throws SQLException {
        String path = DB_PATH + DB_NAME;
        if (database == null) {
            createDataBase();
            database = SQLiteDatabase.openDatabase(path, null,
                    SQLiteDatabase.OPEN_READWRITE);
        }
        System.out.println("Database Path:-----2 = "+ database.getPath());
        return database;
    }



    public void createDataBase() {
        boolean dbExist = checkDataBase();
        if (!dbExist) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDataBase();
            } catch (IOException e) {
                Log.e(this.getClass().toString(), "Copying error");
                throw new Error("Error copying database!");
            }
        } else {
            Log.i("tag", "Token ExternalDbOpenHelper Database already exists");
            //Log.i(this.getClass().toString(), "Database already exists");

        }
    }
    private boolean checkDataBase() {
        boolean isValid = false ;
        SQLiteDatabase checkDb = null;
        try {
            String path = DB_PATH + DB_NAME;
          //  checkDb = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
            isValid = isValidSQLite(path, 32);

        } catch (SQLException e) {
            Log.e(this.getClass().toString(), "Error while checking db");
            Log.i("tag", "check db Execption");
        }
        if (checkDb != null) {
            checkDb.close();
        }
       // return checkDb != null;

        return isValid;
    }

    private void copyDataBase() throws IOException {

        InputStream externalDbStream = mContext.getAssets().open(DB_NAME);

        String outFileName = DB_PATH + DB_NAME;

        OutputStream localDbStream = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = externalDbStream.read(buffer)) > 0) {
            localDbStream.write(buffer, 0, bytesRead);
        }
        localDbStream.close();
        externalDbStream.close();
        Log.i("tag", "Database copied");

    }



    @Override
    public synchronized void close() {
        if (database != null) {
            database.close();
        }
        super.close();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {}
    //	@Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.disableWriteAheadLogging();
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.disableWriteAheadLogging();
    }




    ///////////////////////////////////////////////////////





    public boolean isValidSQLite(String dbPath, int minSizeInMb) {
        File file = new File(dbPath);

        if (!file.exists() || !file.canRead()) {
            return false;
        }

        boolean isReadable = false ;

        try {
            FileReader fr = new FileReader(file);
            char[] buffer = new char[16];

            try {
                fr.read(buffer, 0, 16);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String str = String.valueOf(buffer);
            fr.close();

            isReadable = str.equals("SQLite format 3\u0000");

        } catch (Exception e) {
            e.printStackTrace();

        }

        if (file.length() > (1024 * 1024 * minSizeInMb) && isReadable) {
            return true;
        }else {
            try {
                file.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return  false ;
        }
    }


}
