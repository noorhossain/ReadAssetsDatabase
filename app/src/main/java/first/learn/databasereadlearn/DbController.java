package first.learn.databasereadlearn;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DbController {

    Context mContext ;
    SQLiteDatabase database ;
    String DBName ;


    public DbController(Context mContext, String DBName) {
        this.mContext = mContext;
        this.DBName = DBName;
    }


    public  DbController openAssetsDb () throws SQLException {

        ExternalDbOpenHelper externalDbOpenHelper = new ExternalDbOpenHelper(mContext, DBName);
        database =   externalDbOpenHelper.openDataBase();

        return  this ;
    }



    /*

    CREATE TABLE "Student" (
	"studentId"	INTEGER,
	"RollNumber"	TEXT,
	"Name"	TEXT,
	"Age"	TEXT,
	"Address"	TEXT,
	"PhoneNumber"	TEXT,
	PRIMARY KEY("studentId" AUTOINCREMENT)
);


     */




    public ArrayList<ModelStudent> getDatabaseContent () {

        ArrayList<ModelStudent> modelStudentArrayList = new ArrayList<>();

        if(database!=null){

            System.out.println( "Database Not Null ");

            Cursor cursor ;

            cursor = database.rawQuery("select * from Student", null );

            if(cursor!=null){
                cursor.moveToFirst();
            }

            if(cursor!=null && cursor.moveToFirst()){

                if(!cursor.isAfterLast()){

                    do {

                      String rollNumber = cursor.getString(cursor.getColumnIndexOrThrow("RollNumber"));
                      String name =  cursor.getString(cursor.getColumnIndexOrThrow("Name"));
                      String age = cursor.getString(cursor.getColumnIndexOrThrow("Age"));
                      String address = cursor.getString(cursor.getColumnIndexOrThrow("Address"));
                      String phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow("PhoneNumber"));

                      ModelStudent m = new ModelStudent();
                      m.setRollNumber(rollNumber);
                      m.setName(name);
                      m.setAge(age);
                      m.setAddress(address);
                      m.setPhoneNumber(phoneNumber);

                      modelStudentArrayList.add(m);

                    }while (cursor.moveToNext());

                    cursor.close();



                }

            }


        } else  {

            System.out.println( "Database Null ");

        }

        return  modelStudentArrayList;


    }


}
