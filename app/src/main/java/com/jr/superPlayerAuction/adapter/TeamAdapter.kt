package com.jr.superPlayerAuction.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jr.superPlayerAuction.databinding.ItemViewTeamListBinding
import com.jr.superPlayerAuction.holders.TeamsViewHolder
import com.jr.superPlayerAuction.model.Team

class TeamAdapter(private val teamList: ArrayList<Team>,private val listener: (String) -> Unit) :
    RecyclerView.Adapter<TeamsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        return TeamsViewHolder(
            ItemViewTeamListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = teamList.size

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        holder.bind(teamList[position],listener)
    }
}