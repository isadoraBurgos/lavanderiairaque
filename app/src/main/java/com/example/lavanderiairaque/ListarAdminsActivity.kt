package com.example.lavanderiairaque

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListarAdminsActivity : AppCompatActivity() {

    private lateinit var rv: RecyclerView
    private lateinit var api: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_admins)
        rv = findViewById(R.id.recyclerViewAdmins)
        rv.layoutManager = LinearLayoutManager(this)
        api = RetrofitClient.apiService
        findViewById<Button>(R.id.btnNovoAdmin).setOnClickListener {
            startActivity(Intent(this, IncluirAdminActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        api.getAdmins().enqueue(object : Callback<List<Admin>> {
            override fun onResponse(call: Call<List<Admin>>, response: Response<List<Admin>>) {
                if (response.isSuccessful) rv.adapter = AdminAdapter(response.body()!!.toMutableList(), api)
            }
            override fun onFailure(call: Call<List<Admin>>, t: Throwable) { Log.e("LISTAR_ADMIN", t.message ?: "") }
        })
    }
}
