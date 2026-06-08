package com.example.lavanderiairaque

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class PerfilConta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_conta)

        val isAdmin = intent.getBooleanExtra("IS_ADMIN", false)

        // Botão hamburguer → abre o menu
        findViewById<ImageView>(R.id.btnMenu).setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("IS_ADMIN", isAdmin)
            startActivity(intent)
        }

        // Botão Editar → abre tela de edição
        findViewById<Button>(R.id.btn_ir_para_edicao).setOnClickListener {
            val intent = Intent(this, EditarConta::class.java)
            intent.putExtra("IS_ADMIN", isAdmin)
            startActivity(intent)
        }
    }
}