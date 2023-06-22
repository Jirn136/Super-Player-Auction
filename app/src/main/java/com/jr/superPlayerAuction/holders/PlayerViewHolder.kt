package com.jr.superPlayerAuction.holders

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jr.superPlayerAuction.R
import com.jr.superPlayerAuction.databinding.ItemViewPlayerProfileBinding
import com.jr.superPlayerAuction.model.Player

class PlayerViewHolder(private val binding: ItemViewPlayerProfileBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(context: Context, player: Player, listener: (player: Player) -> Unit) {
        binding.apply {
            tvName.text = player.playerName
            imgType.setImageResource(
                when (player.speciality) {
                    1 -> R.drawable.ic_ball
                    2 -> R.drawable.ic_all_rounder
                    else -> R.drawable.ic_cricket_bat
                }
            )
            tvSpeciality.text = when {
                player.bowlType == 1 -> "Seam"
                player.bowlType == 0 -> "Spin"
                player.batType == 0 -> "Left hand"
                else -> "Right hand"
            }

            Glide.with(context).load(player.playerProfile).centerCrop()
                .placeholder(R.drawable.ic_place_holder).into(imgProfile)

            root.setOnClickListener {
                listener(player)
            }
        }
    }
}