package com.jr.superPlayerAuction.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val playerName: String,
    val age: Int,
    val speciality: Int,
    val amount: String,
    val batType: Int,
    val bowlType: Int,
    val teamName: String,
) : Parcelable
