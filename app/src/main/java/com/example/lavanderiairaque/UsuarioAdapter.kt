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

class UsuarioAdapter(
    private val dataSet: MutableList<Usuario>,
    private val api: ApiService
) : RecyclerView.Adapter<UsuarioAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nome: TextView     = view.findViewById(R.id.tvNomeUsuario)
        val email: TextView    = view.findViewById(R.id.tvEmailUsuario)
        val telefone: TextView = view.findViewById(R.id.tvTelefoneUsuario)
        val btnEditar: Button  = view.findViewById(R.id.btnEditarUsuario)
        val btnDeletar: Button = view.findViewById(R.id.btnDeletarUsuario)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_usuario, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val u = dataSet[position]
        holder.nome.text     = u.USUARIO_NOME
        holder.email.text    = u.USUARIO_EMAIL
        holder.telefone.text = u.USUARIO_TELEFONE

        holder.btnEditar.setOnClickListener {
            it.context.startActivity(Intent(it.context, EditarUsuarioActivity::class.java).apply {
                putExtra("USUARIO_ID",       u.USUARIO_ID)
                putExtra("USUARIO_NOME",     u.USUARIO_NOME)
                putExtra("USUARIO_EMAIL",    u.USUARIO_EMAIL)
                putExtra("USUARIO_TELEFONE", u.USUARIO_TELEFONE)
            })
        }

        holder.btnDeletar.setOnClickListener {
            api.deletarUsuario(u.USUARIO_ID).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    val pos = holder.adapterPosition
                    if (pos != RecyclerView.NO_POSITION) {
                        dataSet.removeAt(pos)
                        notifyItemRemoved(pos)
                    }
                    Toast.makeText(holder.itemView.context, "Usuário deletado!", Toast.LENGTH_SHORT).show()
                }
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(holder.itemView.context, "Erro ao deletar", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    override fun getItemCount() = dataSet.size
}
