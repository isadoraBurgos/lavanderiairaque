package com.example.lavanderiairaque

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PainelAdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_painel_admin)
        findViewById<Button>(R.id.btnGerenciarUsuarios).setOnClickListener { startActivity(Intent(this, ListarUsuariosActivity::class.java)) }
        findViewById<Button>(R.id.btnGerenciarAdmins).setOnClickListener { startActivity(Intent(this, ListarAdminsActivity::class.java)) }
        findViewById<Button>(R.id.btnGerenciarPedidos).setOnClickListener { startActivity(Intent(this, ListarPedidosActivity::class.java)) }
    }
}
