package com.example.cfprofile

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class latestSolved : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latest_solved)
        val handle = intent.getStringExtra("handle").toString()
        process3(handle);
    }
    private fun process3(handle : String) {
        val q1 = findViewById<View>(R.id.Q1) as TextView
        val q2 = findViewById<View>(R.id.Q2) as TextView
        val q3 = findViewById<View>(R.id.Q3) as TextView
        val q4 = findViewById<View>(R.id.Q4) as TextView
        val q5 = findViewById<View>(R.id.Q5) as TextView
        val warn = findViewById<View>(R.id.warn) as TextView
        val queue = Volley.newRequestQueue(this)
        val url = "https://codeforces.com/api/user.status?handle=$handle&key=6b2b9c56e6e5811b17a63cd66ec4bdc8b9e9401b"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val url1 = response.getJSONArray("result");
                val len = url1.length()
                var ans = 0;
                var rat = 0;
                var name = "";
                val arr = mutableSetOf<String>()
                for(i in 0..len-1) {
                    if(url1.getJSONObject(i).opt("verdict").toString() != "OK") continue
                    var temp1 = "";
                    var temp2 = "";
                    if(url1.getJSONObject(i).getJSONObject("problem").has("index") == true)
                        temp1 = url1.getJSONObject(i).getJSONObject("problem").opt("index").toString()
                    if(url1.getJSONObject(i).getJSONObject("problem").has("contestId") == true)
                        temp2 = url1.getJSONObject(i).getJSONObject("problem").opt("contestId").toString()
                    if(url1.getJSONObject(i).getJSONObject("problem").has("rating") == true)
                        rat = url1.getJSONObject(i).getJSONObject("problem").opt("rating").toString().toInt()
                    if(url1.getJSONObject(i).getJSONObject("problem").has("name") == true)
                        name = url1.getJSONObject(i).getJSONObject("problem").opt("name").toString()
                    val ele = temp2 + temp1;
                    val len1 = arr.size;
                    arr.add(ele)
                    val len2 = arr.size;
                    if(len2 != len1) {
                        if(temp1[0] == '1' || temp1[0] == '2' || temp1[0] == '3' || temp1[0] == '4' || temp1[0] == '5'
                            || temp1[0] == '6' || temp1[0] == '7' || temp1[0] == '8' || temp1[0] == '9')
                                continue
                        if(ans == 0) {
                            q1.setText(temp2 + " " +  temp1 + ". " + name + "\n" + "Rating: " + rat)
                            q1.setBackgroundResource(R.color.black)
                        }
                        if(ans == 1) {
                            q2.setText(temp2 + " " + temp1 + ". " + name + "\n" + "Rating: " + rat)
                            q2.setBackgroundResource(R.color.black)
                        }
                        if(ans == 2) {
                            q3.setText(temp2 + " " + temp1 + ". " + name + "\n" + "Rating: " + rat)
                            q3.setBackgroundResource(R.color.black)
                        }
                        if(ans == 3) {
                            q4.setText(temp2 + " " + temp1 + ". " + name + "\n" + "Rating: " + rat)
                            q4.setBackgroundResource(R.color.black)
                        }
                        if(ans == 4) {
                            q5.setText(temp2 + " " + temp1 + ". " + name + "\n" + "Rating: " + rat)
                            q5.setBackgroundResource(R.color.black)
                        }
                        else if(ans >= 6) break;
                        ans += 1;
                    }
                }
                if(ans == 0) {
                    warn.setText("GYM submissions are not included.\nOPPS! it seem you have no past submissions!!".toString())
                }

            },
            { error ->
            }
        )
        queue.add(jsonObjectRequest)
    }

    fun findQ1(view: View) {
        var Q = findViewById<View>(R.id.Q1) as TextView
        var q = Q.text.toString()
        MyFun(q)
    }
    fun findQ2(view: View) {
        var Q = findViewById<View>(R.id.Q2) as TextView
        var q = Q.text.toString()
        MyFun(q)
    }
    fun findQ3(view: View) {
        var Q = findViewById<View>(R.id.Q3) as TextView
        var q = Q.text.toString()
        MyFun(q)
    }
    fun findQ4(view: View) {
        var Q = findViewById<View>(R.id.Q4) as TextView
        var q = Q.text.toString()
        MyFun(q)
    }
    fun findQ5(view: View) {
        var Q = findViewById<View>(R.id.Q5) as TextView
        var q = Q.text.toString()
        MyFun(q)
    }
    fun MyFun(q : String) {
        if(q.toString() == "") return;
        var cid = ""
        var ind = ""
        val l = q.toString().length - 1
        var cnt = 0
        var temp = ""
        for(i in 0..l) {
            if(q.toString()[i] == ' ' || q.toString()[i] == '.') {
                cnt += 1
                if(cnt == 1) {
                    cid = temp
                    temp = ""
                }
                else if(cnt == 2) {
                    ind = temp
                }
                else if(cnt >= 3) break
            }
            else
                temp += q.toString()[i].toString()

        }
        val openURL = Intent(android.content.Intent.ACTION_VIEW)
        openURL.data = Uri.parse("https://codeforces.com/contest/" + cid + "/problem/" + ind);
        startActivity(openURL)
    }
}