package com.example.lavanderiairaque

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // PEGA O VALOR REAL DO LOGIN.
        // DICA DE TESTE: Se quiser que apareça TUDO para testar, mude temporariamente para: val isAdmin = true

        //val isAdmin = intent.getBooleanExtra("IS_ADMIN", false)

        val isAdmin = true

        // SEÇÃO: BOTÕES PADRÕES (Removido o finish() para permitir voltar ao menu)
        findViewById<LinearLayout>(R.id.menuHome).setOnClickListener {
            startActivity(Intent(this, home::class.java))
        }
        findViewById<LinearLayout>(R.id.menuSobreNos).setOnClickListener {
            startActivity(Intent(this, sobrenos::class.java))
        }
        findViewById<LinearLayout>(R.id.menuServicos).setOnClickListener {
            startActivity(Intent(this, servico::class.java))
        }
        findViewById<LinearLayout>(R.id.menuContato).setOnClickListener {
            startActivity(Intent(this, contato::class.java))
        }

        // SEÇÃO: ADMINISTRADOR
        val secaoAdmin = findViewById<LinearLayout>(R.id.secaoAdmin)

        if (isAdmin) {
            secaoAdmin.visibility = View.VISIBLE

            findViewById<LinearLayout>(R.id.menuPainelAdmin).setOnClickListener {
                startActivity(Intent(this, PainelAdminActivity::class.java))
            }
            findViewById<LinearLayout>(R.id.menuUsuarios).setOnClickListener {
                startActivity(Intent(this, ListarUsuariosActivity::class.java))
            }
            findViewById<LinearLayout>(R.id.menuAdmins).setOnClickListener {
                startActivity(Intent(this, ListarAdminsActivity::class.java))
            }
            findViewById<LinearLayout>(R.id.menuPedidos).setOnClickListener {
                startActivity(Intent(this, ListarPedidosActivity::class.java))
            }
        } else {
            secaoAdmin.visibility = View.GONE
        }
    }
}