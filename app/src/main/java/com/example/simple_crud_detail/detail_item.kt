package com.example.simple_crud_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_item.*
import org.json.JSONArray

class detail_item : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_item)

        var hm = intent.extras!!
        idhm = hm.getString("id").toString()
//        tv1.setText(idhm)
    }
    var idhm = ""

    val url = "http://192.168.1.7/Android_API/simple_crud_detail/showitem_detail.php"

    fun showDataItem(idhm : String){
        val request = object : StringRequest(
            Request.Method.POST,url,
            Response.Listener{ response ->
                val jsonArray = JSONArray(response)

                for(x in 0 .. (jsonArray.length()-1)){
                    val jsonObject = jsonArray.getJSONObject(x)
                    var dt = HashMap<String,String>()
                    dt.put("title",jsonObject.getString("title"))
                    dt.put("descript",jsonObject.getString("descript"))

                    tv1.setText(dt.get("title").toString())
                    tv2.setText( dt.get("descript").toString())
                    if (!dt.get("img").equals(""))
                        Picasso.get().load(dt.get("img")).into(imv1)
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(this,"Terjadi kesalahan Koneksi Server", Toast.LENGTH_LONG).show()
            }){
                override fun getParams(): MutableMap<String, String> {
                    val hm = HashMap<String,String>()
                    hm.put("id",idhm)
                    return hm
                }
        }
        val queue = Volley.newRequestQueue(this)
        queue.add(request)
    }

    override fun onStart() {
        super.onStart()
        showDataItem(idhm)
    }
}
