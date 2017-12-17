package com.example.tetsuhiro.myapplication

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import org.json.JSONArray
import org.json.JSONObject
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

//    var b: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // http通信するfunction読んできてる
        findViewById<Button>(R.id.button).setOnClickListener(View.OnClickListener {
            hogeget3()
        })
    }

    fun hogeget3() {
        "http://zipcloud.ibsnet.co.jp/api/search".httpGet(listOf("zipcode" to "7830060")).responseJson { request, response, result ->
            when (result) {
            // ステータスコード 2xx
                is Result.Success -> {
                    val json = result.value.obj()
                    val results =json.get("results") as JSONArray
                    val data1 = results[0] as JSONObject
                    Log.d(TAG, data1["address1"] as String?) // 高知県
//                    b = data1["address1"] as String?

                    val listView = findViewById<ListView>(R.id.listView)
//                    val dataArray = arrayOf("Kotlin","Android","iOS","Swift","Java")
                    val dataArray = arrayOf(data1["address1"] as String?, data1["address2"]as String? ,data1["address3"] as String?)
                    val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataArray)
                    listView.adapter = adapter
                }
            // ステータスコード 2xx以外
                is Result.Failure -> {
                    Log.d(TAG, "Error!afewfawae") // 高知県
                }
            }
        }
    }
}
