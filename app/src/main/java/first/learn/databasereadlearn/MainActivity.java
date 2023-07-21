package first.learn.databasereadlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DbController dbController ;
    Context mContext;

    ListView listView2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        listView2 = (ListView) findViewById(R.id.listView2);


        dbController = new DbController(mContext, "ahsan.db");

        dbController.openAssetsDb();


        ArrayList<ModelStudent> modelStudentArrayList = new ArrayList<>();

        modelStudentArrayList = dbController.getDatabaseContent();


        if(modelStudentArrayList.size()>0){

            ModelStudent m = modelStudentArrayList.get(0);

            System.out.println("Data Base : Name : "+ m.getName());


        }else {

            System.out.println("Data Base : No Data Found");

        }



        StudentAdapter adapter = new StudentAdapter(mContext, R.layout.list_item, modelStudentArrayList);
        listView2.setAdapter(adapter);





    }



}