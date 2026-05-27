package com.example.lavanderiairaque

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminAdapter(
    private val dataSet: MutableList<Admin>,
    private val api: ApiService
) : RecyclerView.Adapter<AdminAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nome: TextView     = view.findViewById(R.id.tvNomeAdmin)
        val email: TextView    = view.findViewById(R.id.tvEmailAdmin)
        val btnEditar: Button  = view.findViewById(R.id.btnEditarAdmin)
        val btnDeletar: Button = view.findViewById(R.id.btnDeletarAdmin)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_admin, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val a = dataSet[position]
        holder.nome.text  = a.ADMIN_NOME
        holder.email.text = a.ADMIN_EMAIL

        holder.btnEditar.setOnClickListener {
            it.context.startActivity(Intent(it.context, EditarAdminActivity::class.java).apply {
                putExtra("ADMIN_ID",    a.ADMIN_ID)
                putExtra("ADMIN_NOME",  a.ADMIN_NOME)
                putExtra("ADMIN_EMAIL", a.ADMIN_EMAIL)
            })
        }

        holder.btnDeletar.setOnClickListener {
            val currentPosition = holder.adapterPosition
            if (currentPosition == RecyclerView.NO_ID.toInt()) return@setOnClickListener
            api.deletarAdmin(a.ADMIN_ID).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    val pos = holder.adapterPosition
                    if (pos != RecyclerView.NO_POSITION) {
                        dataSet.removeAt(pos)
                        notifyItemRemoved(pos)
                    }
                    Toast.makeText(holder.itemView.context, "Admin deletado!", Toast.LENGTH_SHORT).show()
                }
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(holder.itemView.context, "Erro ao deletar", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    override fun getItemCount() = dataSet.size
}
