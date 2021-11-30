package com.example.aboutme

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val myName: MyName = MyName("Renato Rocha")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName = myName

        binding.buttonDone.setOnClickListener {
            addNickname(it)
        }
    }

    private fun addNickname(view: View) {
        val editText: EditText = findViewById<EditText>(R.id.nickname_edit)
        val nicknameTextView: TextView = findViewById<TextView>(R.id.nickname_text)

        binding.apply {
            // nicknameText.text = editText.text
            myName?.nickname = nicknameEdit.text.toString()
            invalidateAll()
            nicknameEdit.visibility = View.GONE
            buttonDone.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }


        // Hide the keyboard
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
