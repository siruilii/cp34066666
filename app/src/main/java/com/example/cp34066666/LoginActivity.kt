package com.example.cp34066666

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.cp34066666.R
import com.example.cp34066666.RegisterActivity

class LoginActivity<UserDao> : AppCompatActivity() {

    companion object {
        private const val RESULT_OK = 1
        const val ACCOUNT = "account"
        const val PASSWORD = "password"
    }

    private lateinit var btnLogin: TextView
    private lateinit var etAccount: EditText
    private lateinit var etPassword: EditText
    private lateinit var tvRegister: TextView
    private var userDao: UserDao? = null


    private lateinit var radiogroup: RadioGroup
    private lateinit var radio_one: RadioButton
    private lateinit var radio_two: RadioButton

    private var str: String? = null // 存放点击的按钮的值

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    private fun initView() {
        // 去除默认标题栏
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        // 绑定控件
        btnLogin = findViewById(R.id.btn_login)
        tvRegister = findViewById(R.id.tv_register)
        etAccount = findViewById(R.id.et_account)
        etPassword = findViewById(R.id.et_password)

        radiogroup = findViewById(R.id.id_radiogroup)
        radio_one = findViewById(R.id.radio_1)
        radio_two = findViewById(R.id.radio_2)

        // 设置 radio_one 的点击事件
        radio_one.setOnClickListener {
            btnLogin.setOnClickListener {
                handleLogin()
            }
        }

        // 设置 radio_two 的点击事件
        radio_two.setOnClickListener {
            btnLogin.setOnClickListener {
                handleLogin()
            }
        }

        // 设置注册按钮的点击事件
        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    private fun handleLogin() {
        val acc = etAccount.text.toString().trim()
        val pass = etPassword.text.toString().trim()

        val userDao = UserDao(this)
        userDao.open()

        if (!userDao.isExist(acc)) {
            Toast.makeText(this, "The account does not exist, please re-enter!", Toast.LENGTH_SHORT).show()
        } else {
            if (userDao.getPassword(acc) == pass) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("ACCOUNT", acc)
                intent.putExtra("PASSWORD", pass)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Password error, please re-enter!", Toast.LENGTH_SHORT).show()
            }
        }

        userDao.close()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> {
                if (resultCode == RESULT_OK) {
                    val acc = data?.getStringExtra("acc")
                    val pass = data?.getStringExtra("pass")
                    etAccount.setText(acc)
                    etPassword.setText(pass)
                }
            }
            else -> {

            }
        }
    }
}