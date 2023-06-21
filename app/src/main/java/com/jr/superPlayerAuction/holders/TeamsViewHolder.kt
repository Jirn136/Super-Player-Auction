package com.jr.superPlayerAuction.holders

import androidx.recyclerview.widget.RecyclerView
import com.jr.superPlayerAuction.databinding.ItemViewTeamListBinding
import com.jr.superPlayerAuction.model.Team

class TeamsViewHolder(private val binding: ItemViewTeamListBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(team: Team, listener: (String) -> Unit) {
        binding.apply {
            txtTitle.text = team.teamName
            root.setOnClickListener {
                listener(team.teamName)
            }
        }
    }
}