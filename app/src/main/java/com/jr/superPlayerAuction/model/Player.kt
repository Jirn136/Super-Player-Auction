package com.jr.superPlayerAuction.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val playerName: String,
    val age: Int,
    val contactNumber:String,
    val speciality: Int,
    val amount: String,
    val batType: Int,
    val bowlType: Int,
    val teamName: String,
    val playerProfile: String,
) : Parcelable
