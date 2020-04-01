package com.example.roomdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "roomDb";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //数据库的操作应该是在子线程
        DbTest t=new DbTest();
        t.start();
    }

    public class DbTest extends Thread{
        @Override
        public void run() {
            //数据库操作在这里进行
            AppDatabase roomDB= Room.databaseBuilder(getApplicationContext()
                    ,AppDatabase.class
                    ,"roomDB").build();
            StudentDao dao=roomDB.userDao();
            dao.insert(new Student("wangwang","123",1));
            dao.insert(new Student("shasha","123",2));
            dao.insert(new Student("siqi","123",3));
            dao.insert(new Student("wangwang2","123",4));
            List<Student> list=dao.getAll();
            Log.i(TAG,list.toString());
            Student jett2=dao.findByName("jett3");
            Log.i(TAG,jett2.toString());
            List<Student> allId=dao.getAllId(new int[]{2,3,4});
            Log.i(TAG,allId.toString());
            List<StudentTuple> record=dao.getRecord();
            Log.i(TAG,record.toString());
        }
    }
}
