package com.jr.superPlayerAuction.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.jr.superPlayerAuction.databinding.ItemViewTeamListBinding
import com.jr.superPlayerAuction.holders.TeamsViewHolder
import com.jr.superPlayerAuction.model.Team

class TeamAdapter(private val listener: (String) -> Unit) :
    ListAdapter<Team, TeamsViewHolder>(TeamDiffUtil()) {

    fun submit(team: ArrayList<Team>) {
        super.submitList(team)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        return TeamsViewHolder(
            ItemViewTeamListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }
}

private class TeamDiffUtil : DiffUtil.ItemCallback<Team>() {
    override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
        return newItem.teamName == oldItem.teamName
    }

    override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
        return oldItem == newItem
    }

}