package com.example.sqlitedatabase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context : Context) : SQLiteOpenHelper(context , "testdb" , null , 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        // 테이블 구성
        val studentSql = """ 
           create table tb_member(
            _id integer primary key autoincrement,
            name not null,
            email,
            phone)
        """

        // sql실행
        db?.execSQL(studentSql)
        // data insert
        db?.execSQL("insert into tb_member (name , email , phone) " +
                "values ('kkang' , 'kkang104@gmail.com' , '111111')")


    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // 상위 클래스에 들어가는 버전정보가 바뀌엇을 때 - 테이블을 삭제
        db?.execSQL("drop table tb_student")
        onCreate(db)

    }


}