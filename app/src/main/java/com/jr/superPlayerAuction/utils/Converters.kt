package com.jr.superPlayerAuction.utils

import com.jr.superPlayerAuction.model.Team
import com.jr.superPlayerAuction.roomdb.model.TeamEntity

internal fun List<TeamEntity>.convertEntityToModel(): ArrayList<Team> {
    val team = arrayListOf<Team>()
    this.forEach {
        team.add(Team(teamName = it.teamName))
    }
    return team
}

internal fun Team.convertTeamToTeamEntity():TeamEntity {
    return TeamEntity(teamName = this.teamName)
}