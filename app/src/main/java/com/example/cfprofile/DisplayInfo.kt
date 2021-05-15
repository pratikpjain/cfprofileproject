package com.example.cfprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide

class DisplayInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_info)
        val handle = intent.getStringExtra("handle").toString()
        loadData(handle)
    }
    private fun loadData(handle : String) {
        val titlephoto = findViewById<View>(R.id.titlephoto) as ImageView
        val hdl = findViewById<View>(R.id.hdl) as TextView
        val rate = findViewById<View>(R.id.rate) as TextView
        val rank = findViewById<View>(R.id.rank) as TextView
        val fname = findViewById<View>(R.id.fname) as TextView
        val lname = findViewById<View>(R.id.lname) as TextView
        val org = findViewById<View>(R.id.org) as TextView
        val maxR = findViewById<View>(R.id.maxR) as TextView
        val maxRT = findViewById<View>(R.id.maxRT) as TextView



        val queue = Volley.newRequestQueue(this)
        val url = "https://codeforces.com/api/user.info?handles=$handle&key=6b2b9c56e6e5811b17a63cd66ec4bdc8b9e9401b"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val url1 = response.getJSONArray("result");
                val url2 = url1.getJSONObject(0);
                if(url2.has("titlePhoto") == true)
                    Glide.with(this).load(url2.opt("titlePhoto").toString()).into(titlephoto)
                if(url2.has("handle") == true)
                    hdl.setText(url2.opt("handle").toString())
                if(url2.has("rating") == true)
                    rate.setText(url2.opt("rating").toString())
                if(url2.has("rank") == true)
                    rank.setText(url2.opt("rank").toString())
                if(url2.has("firstName") == true)
                    fname.setText(url2.opt("firstName").toString())
                if(url2.has("lastName") == true)
                    lname.setText(url2.opt("lastName").toString())
                if(url2.has("organization") == true)
                    org.setText(url2.opt("organization").toString())
                if(url2.has("maxRank") == true)
                    maxR.setText(url2.opt("maxRank").toString())
                if(url2.has("maxRating") == true)
                    maxRT.setText(url2.opt("maxRating").toString())

            },
            { error ->
            }
        )
        queue.add(jsonObjectRequest)
    }

    fun mybtn(view: View) {
        val handle = intent.getStringExtra("handle").toString()
        intent = Intent(applicationContext , OneMore::class.java)
        intent.putExtra("handle", handle)
        startActivity(intent)
    }

}