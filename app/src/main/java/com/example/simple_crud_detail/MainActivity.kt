package com.example.simple_crud_detail

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ItemAdapter = adapter(itemadapter,this)
        rv_item.layoutManager = LinearLayoutManager(this)
        rv_item.adapter = ItemAdapter
    }

    lateinit var ItemAdapter : adapter
    var itemadapter = mutableListOf<HashMap<String,String>>()
    val url = "http://192.168.1.7/Android_API/simple_crud_detail/showitem.php"

    fun showDataItem(){
        val request = object : StringRequest(
            Request.Method.POST,url,
            Response.Listener{ response ->
                itemadapter.clear()
                val jsonArray = JSONArray(response)

                for(x in 0 .. (jsonArray.length()-1)){
                    val jsonObject = jsonArray.getJSONObject(x)
                    var dt = HashMap<String,String>()

                    dt.put("title",jsonObject.getString("title"))
                    dt.put("id",jsonObject.getString("id"))

                    itemadapter.add(dt)
                }
                ItemAdapter.notifyDataSetChanged()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this,"Terjadi kesalahan Koneksi Server", Toast.LENGTH_LONG).show()
            }){
        }
        val queue = Volley.newRequestQueue(this)
        queue.add(request)
    }

    override fun onStart() {
        super.onStart()
        showDataItem()
    }
}
