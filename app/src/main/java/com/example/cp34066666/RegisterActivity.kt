package com.example.cp34066666;

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.cp34066666.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var btnRegister: TextView
    private lateinit var btnCancel: TextView
    private lateinit var etAccount: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initView()
    }

    private fun initView() {
        // 去除默认标题栏
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        // 绑定控件
        etAccount = findViewById(R.id.et_account)
        etPassword = findViewById(R.id.et_password)
        etConfirmPassword = findViewById(R.id.et_confirm_password)
        btnRegister = findViewById(R.id.btn_register)
        btnCancel = findViewById(R.id.btn_cancel)

        // 设置注册按钮点击事件
        btnRegister.setOnClickListener {
            val acc = etAccount.text.toString().trim()
            val pass = etPassword.text.toString().trim()
            val confirm = etConfirmPassword.text.toString().trim()
            val user = Yonghu(acc, pass)
            userDao = UserDao(applicationContext)
            userDao.open()

            when {
                userDao.findUser(user) -> {
                    Toast.makeText(this, "Account already exists", Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(pass) || TextUtils.isEmpty(confirm) -> {
                    Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show()
                }
                pass != confirm -> {
                    Toast.makeText(this, "The passwords entered twice are different", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    userDao.addUser(user)
                    Toast.makeText(this, "registered successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    // 将账号和密码传递过去
                    intent.putExtra("acc", acc)
                    intent.putExtra("pass", pass)
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
            userDao.close()
        }

        // 设置取消按钮点击事件
        btnCancel.setOnClickListener {
            finish()
        }
    }

    companion object {
        private const val RESULT_OK = 1
    }
}