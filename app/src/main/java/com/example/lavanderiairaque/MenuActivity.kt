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

        val isAdmin = intent.getBooleanExtra("IS_ADMIN", false)

        findViewById<LinearLayout>(R.id.menuHome).setOnClickListener {
            startActivity(Intent(this, home::class.java))
            finish()
        }
        findViewById<LinearLayout>(R.id.menuSobreNos).setOnClickListener {
            startActivity(Intent(this, sobrenos::class.java))
            finish()
        }
        findViewById<LinearLayout>(R.id.menuServicos).setOnClickListener {
            startActivity(Intent(this, servico::class.java))
            finish()
        }
        findViewById<LinearLayout>(R.id.menuContato).setOnClickListener {
            startActivity(Intent(this, contato::class.java))
            finish()
        }

        val secaoAdmin = findViewById<LinearLayout>(R.id.secaoAdmin)
        if (isAdmin) {
            secaoAdmin.visibility = View.VISIBLE
            findViewById<LinearLayout>(R.id.menuPainelAdmin).setOnClickListener {
                startActivity(Intent(this, PainelAdminActivity::class.java))
                finish()
            }
            findViewById<LinearLayout>(R.id.menuUsuarios).setOnClickListener {
                startActivity(Intent(this, ListarUsuariosActivity::class.java))
                finish()
            }
            findViewById<LinearLayout>(R.id.menuAdmins).setOnClickListener {
                startActivity(Intent(this, ListarAdminsActivity::class.java))
                finish()
            }
            findViewById<LinearLayout>(R.id.menuPedidos).setOnClickListener {
                startActivity(Intent(this, ListarPedidosActivity::class.java))
                finish()
            }
        } else {
            secaoAdmin.visibility = View.GONE
        }
    }
}
