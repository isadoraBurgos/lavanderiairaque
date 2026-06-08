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

class ListarUsuariosActivity : AppCompatActivity() {

    private lateinit var rv: RecyclerView
    private lateinit var api: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_usuarios)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        rv = findViewById(R.id.recyclerViewUsuarios)
        rv.layoutManager = LinearLayoutManager(this)
        api = RetrofitClient.apiService
        findViewById<Button>(R.id.btnNovoUsuario).setOnClickListener {
            startActivity(Intent(this, IncluirUsuarioActivity::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onResume() {
        super.onResume()
        api.getUsuarios().enqueue(object : Callback<List<Usuario>> {
            override fun onResponse(call: Call<List<Usuario>>, response: Response<List<Usuario>>) {
                if (response.isSuccessful) rv.adapter = UsuarioAdapter(response.body()!!.toMutableList(), api)
            }
            override fun onFailure(call: Call<List<Usuario>>, t: Throwable) { Log.e("LISTAR_USUARIO", t.message ?: "") }
        })
    }
}
