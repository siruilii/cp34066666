package com.example.cp34066666;

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE, null, VERSION) {

    companion object {

        const val DATABASE = "title.db"

        const val VERSION = 1


        const val CREATE_USER = """create table User (
            account text primary key,
            password text)"""
    }


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_USER)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop table if exists User")
        onCreate(db)
    }
}