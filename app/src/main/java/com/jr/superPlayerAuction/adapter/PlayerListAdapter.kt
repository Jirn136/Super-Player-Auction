package com.jr.superPlayerAuction.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.jr.superPlayerAuction.databinding.ItemViewPlayerProfileBinding
import com.jr.superPlayerAuction.holders.PlayerViewHolder
import com.jr.superPlayerAuction.model.Player

class PlayerListAdapter(
    private val context: Context,
    private val listener: (player: Player) -> Unit,
) : ListAdapter<Player, PlayerViewHolder>(PlayerDiffUtil()) {

    fun submitList(playerList: ArrayList<Player>) {
        super.submitList(playerList)
    }

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

private class PlayerDiffUtil : DiffUtil.ItemCallback<Player>() {
    override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
        return newItem.playerName == oldItem.playerName
    }

    override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem == newItem
    }

}