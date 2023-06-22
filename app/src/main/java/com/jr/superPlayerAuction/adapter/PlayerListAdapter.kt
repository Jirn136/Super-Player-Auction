package com.jr.superPlayerAuction.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.jr.superPlayerAuction.databinding.ItemViewPlayerProfileBinding
import com.jr.superPlayerAuction.holders.PlayerViewHolder
import com.jr.superPlayerAuction.model.Player
import com.jr.superPlayerAuction.model.Team

class PlayerListAdapter(
    private val context: Context,
    private val listener: (player: Player) -> Unit,
) : ListAdapter<Player, PlayerViewHolder>(PlayerDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(
            ItemViewPlayerProfileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(context, getItem(position), listener)
    }

}

private class PlayerDiffUtil : DiffUtil.ItemCallback<Team>() {
    override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
        return newItem.teamName == oldItem.teamName
    }

    override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
        return oldItem == newItem
    }

}