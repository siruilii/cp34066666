package com.example.cp34066666;

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException

// 数据库中的CRUD操作
class UserDao(context: Context) {
    private var context: Context = context // 上下文
    private var dbHelper: DataBaseHelper? = null // 数据库访问对象
    private var db: SQLiteDatabase? = null // 可对数据库进行读写的操作对象

    // 创建并打开数据库（如果数据库已存在直接打开）
    fun open() {
        dbHelper = DataBaseHelper(context)
        try {
            db = dbHelper?.writableDatabase
        } catch (exception: SQLiteException) {
            db = dbHelper?.readableDatabase
        }
    }

    // 关闭数据库
    fun close() {
        db?.close()
        db = null
    }

    // 添加用户信息
    fun addUser(user: Yonghu) {
        val values = ContentValues().apply {
            put("account", user.account)
            put("password", user.password)
        }
        db?.insert("User", null, values)
    }

    // 删除用户信息
    fun deleteUser(user: Yonghu) {
        db?.delete("User", "account = ?", arrayOf(user.account))
    }

    // 修改用户信息
    fun updateUser(user: Yonghu) {
        val values = ContentValues().apply {
            put("password", user.password)
        }
        db?.update("User", values, "account = ?", arrayOf(user.account))
    }

    // 查询用户信息
    fun findUser(user: Yonghu): Boolean {
        // 查询user表中where指定列元素的记录
        val cursor: Cursor? = db?.query("User", null, "account = ?", arrayOf(user.account), null, null, null)
        return if (cursor == null || cursor.count < 1) {
            false
        } else {
            cursor.close()
            true
        }
    }

    // 判断账号是否存在
    fun isExist(account: String): Boolean {
        val cursor: Cursor? = db?.query("User", null, "account = ?", arrayOf(account), null, null, null)
        return cursor != null && cursor.count > 0
    }

    // 根据账号来查找用户密码，登录时校验密码
    @SuppressLint("Range")
    fun getPassword(account: String): String {
        val cursor: Cursor? = db?.query("User", null, "account = ?", arrayOf(account), null, null, null)
        cursor?.moveToFirst()
        val password = cursor?.getString(cursor.getColumnIndex("password")) ?: ""
        cursor?.close()
        return password
    }
}