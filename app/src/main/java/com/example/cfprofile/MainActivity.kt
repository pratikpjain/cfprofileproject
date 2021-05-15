package com.example.cfprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val err = findViewById<View>(R.id.errmsg) as TextView
        err.setText("")
    }
    fun go(view: View) {
        val err = findViewById<View>(R.id.errmsg) as TextView
        err.setText("")
        Check()
    }
    private fun Check() {
        val err = findViewById<View>(R.id.errmsg) as TextView
        err.setText("")
        val myhdl = findViewById<View>(R.id.handle) as EditText
        val handle = myhdl.text.toString();
        val queue = Volley.newRequestQueue(this)
        val url = "https://codeforces.com/api/user.info?handles=$handle&key=6b2b9c56e6e5811b17a63cd66ec4bdc8b9e9401b"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val url1 = response.getString("status");
                if(url1.toString() == "OK") {
                    err.setText("")
                    call_intent()
                }
                else {
                    err.setText("Error! Handle doesn't exist")
                }
            },
            { error ->
                err.setText("Error! Handle doesn't exist")
            }
        )
        queue.add(jsonObjectRequest)
    }
    fun call_intent() {
        val err = findViewById<View>(R.id.errmsg) as TextView
        err.setText("")
        val myhdl = findViewById<View>(R.id.handle) as EditText
        val handle = myhdl.text.toString();
        intent = Intent(applicationContext , DisplayInfo::class.java)
        intent.putExtra("handle", handle)
        startActivity(intent)
    }
}