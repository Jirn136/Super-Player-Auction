package com.jr.superPlayerAuction.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    val teamName: String,
) : Parcelable
