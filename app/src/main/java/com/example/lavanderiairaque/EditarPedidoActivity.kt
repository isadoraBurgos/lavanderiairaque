package com.example.lavanderiairaque

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditarPedidoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_pedido)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        toolbar.setNavigationOnClickListener { finish() }
        val id     = intent.getIntExtra("PEDIDO_ID", 0)
        val etDesc = findViewById<EditText>(R.id.etDescricaoPedido)
        val etStat = findViewById<EditText>(R.id.etStatusPedido)
        etDesc.setText(intent.getStringExtra("PEDIDO_DESCRICAO"))
        etStat.setText(intent.getStringExtra("PEDIDO_STATUS"))
        findViewById<Button>(R.id.btnSalvarPedido).setOnClickListener {
            RetrofitClient.apiService.editarPedido(id, etDesc.text.toString(), etStat.text.toString())
                .enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) { Toast.makeText(this@EditarPedidoActivity, "Atualizado!", Toast.LENGTH_SHORT).show(); finish() }
                    override fun onFailure(call: Call<Void>, t: Throwable) { Toast.makeText(this@EditarPedidoActivity, "Erro", Toast.LENGTH_SHORT).show() }
                })
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
