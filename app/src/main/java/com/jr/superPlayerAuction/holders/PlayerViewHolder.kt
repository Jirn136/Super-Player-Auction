package com.jr.superPlayerAuction.holders

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jr.superPlayerAuction.R
import com.jr.superPlayerAuction.databinding.ItemViewPlayerProfileBinding
import com.jr.superPlayerAuction.model.Player
import com.jr.superPlayerAuction.utils.Constants

class PlayerViewHolder(private val binding: ItemViewPlayerProfileBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(context: Context, player: Player, listener: (player: Player) -> Unit) {
        binding.apply {
            player.apply {
                tvName.text = playerName
                imgType.setImageResource(
                    when (speciality) {
                        Constants.BOWLER -> R.drawable.ic_ball
                        Constants.ALL_ROUNDER -> R.drawable.ic_all_rounder
                        else -> R.drawable.ic_cricket_bat
                    }
                )
                tvSpeciality.text = when {
                    speciality == Constants.ALL_ROUNDER -> {
                        when {
                            bowlType == Constants.BOWL_SPIN && batType == Constants.LEFT_HAND_BATSMAN -> "Spin & Left hand batsman"
                            bowlType == Constants.BOWL_SPIN && batType == Constants.RIGHT_HAND_BATSMAN -> "Spin & Right hand batsman"
                            bowlType == Constants.BOWL_SEAM && batType == Constants.RIGHT_HAND_BATSMAN -> "Seam & Right hand batsman"
                            else -> "Seam & Left hand batsman"
                        }
                    }

                    bowlType == Constants.BOWL_SEAM -> "Seam"
                    bowlType == Constants.BOWL_SPIN -> "Spin"
                    batType == Constants.LEFT_HAND_BATSMAN -> "Left hand batsman"
                    else -> "Right hand batsman"

                }

                Glide.with(context).load(player.playerProfile).centerCrop()
                    .placeholder(R.drawable.ic_place_holder).into(imgProfile)

                root.setOnClickListener {
                    listener(player)
                }
            }
        }
    }
}