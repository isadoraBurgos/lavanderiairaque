package com.example.lavanderiairaque

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class ParceriaEmpresa : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parceria_empresa)

        // 1. Mapeando componentes
        val etNome = findViewById<EditText>(R.id.et_nome_empresa)
        val etEndereco = findViewById<EditText>(R.id.et_endereco_empresa)
        val spinnerQtd = findViewById<Spinner>(R.id.spinner_qtd_empresa)
        val etDescricao = findViewById<EditText>(R.id.et_descricao_empresa)
        val etDataHora = findViewById<EditText>(R.id.et_data_hora_empresa)
        val spinnerPagamento = findViewById<Spinner>(R.id.spinner_pagamento_empresa)
        val btnEnviar = findViewById<Button>(R.id.btn_realizar_pedido_empresa)

        // 2. Listas para os Spinners
        val listaQtd = listOf("Selecione a quantidade...", "10 a 30 peças", "30 a 50 peças", "Acima de 50 peças")
        spinnerQtd.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listaQtd)

        val listaPagamentos = listOf("Selecione o pagamento...", "Pix (Empresarial)", "Boleto Faturado", "Cartão de Crédito", "Dinheiro")
        spinnerPagamento.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listaPagamentos)

        // 3. Lógica do Calendário e Relógio (A mesma das anteriores)
        etDataHora.setOnClickListener {
            val cal = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, { _, y, m, d ->
                val data = "$d/${m + 1}/$y"

                val timePicker = TimePickerDialog(this, { _, hr, min ->
                    val minFormat = String.format("%02d", min)
                    etDataHora.setText("$data às $hr:$minFormat")
                }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true)
                timePicker.show()

            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }

        // 4. Validação e Envio
        btnEnviar.setOnClickListener {
            if (etNome.text.isEmpty() || etEndereco.text.isEmpty() ||
                spinnerQtd.selectedItemPosition == 0 || etDataHora.text.isEmpty() ||
                spinnerPagamento.selectedItemPosition == 0) {

                Toast.makeText(this, "Preencha todos os campos da empresa!", Toast.LENGTH_SHORT).show()
            } else {
                // Navega para a tela final do WhatsApp
                val intent = Intent(this, FinalPedido::class.java)
                startActivity(intent)
            }
        }
    }
}