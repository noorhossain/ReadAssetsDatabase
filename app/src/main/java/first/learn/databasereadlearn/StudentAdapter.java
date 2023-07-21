package first.learn.databasereadlearn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends ArrayAdapter<ModelStudent> {

    Context mContext ;
    ArrayList <ModelStudent> modelStudentArrayList ;

    public StudentAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ModelStudent> objects) {
        super(context, resource, objects);

        mContext  = context ;
        modelStudentArrayList  = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view ;

        if(convertView == null){

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, null);
        }else  {
            view = convertView ;
        }


       TextView tvMobile = ( TextView) view.findViewById(R.id.tvMobile);
       TextView tvAge = ( TextView) view.findViewById(R.id.tvAge);
       TextView tvName = ( TextView) view.findViewById(R.id.tvName);
       TextView tvRoll = ( TextView) view.findViewById(R.id.tvRoll);
       TextView tvAddress = (TextView)view.findViewById(R.id.tvAddress);

       ModelStudent modelStudent = modelStudentArrayList.get(position);

       tvName.setText(modelStudent.getName());
       tvAge.setText(modelStudent.getAge());
       tvMobile.setText(modelStudent.getPhoneNumber());
       tvRoll.setText(modelStudent.getRollNumber());
        tvAddress.setText(modelStudent.getAddress());


        return view ;
    }
}
