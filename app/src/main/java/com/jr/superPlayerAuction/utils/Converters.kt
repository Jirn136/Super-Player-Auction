package com.jr.superPlayerAuction.utils

import com.jr.superPlayerAuction.model.Player
import com.jr.superPlayerAuction.model.Team
import com.jr.superPlayerAuction.roomdb.model.PlayerEntity
import com.jr.superPlayerAuction.roomdb.model.TeamEntity

internal fun List<TeamEntity>.convertEntityToModel(): ArrayList<Team> {
    val team = arrayListOf<Team>()
    this.map {
        team.add(Team(teamName = it.teamName))
    }
    return team
}

internal fun Team.convertTeamToTeamEntity(): TeamEntity {
    return TeamEntity(teamName = this.teamName)
}

internal fun List<PlayerEntity>.convertPlayerEntityToModel(): ArrayList<Player> {
    val player = arrayListOf<Player>()
    this.map {
        player.add(
            Player(
                playerName = it.playerName,
                age = it.age,
                contactNumber = it.contactNumber,
                speciality = it.speciality,
                amount = it.amount,
                batType = it.batType,
                bowlType = it.bowlType,
                teamName = it.teamName,
                playerProfile = it.playerProfile
            )
        )
    }
    return player
}

internal fun Player.convertToPlayerEntity(): PlayerEntity {
    return PlayerEntity(
        playerName = this.playerName,
        age = this.age,
        contactNumber = this.contactNumber,
        speciality = this.speciality,
        amount = this.amount,
        batType = this.batType,
        bowlType = this.bowlType,
        teamName = this.teamName,
        playerProfile = this.playerProfile
    )
}