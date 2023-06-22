package com.jr.superPlayerAuction.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jr.superPlayerAuction.model.Team
import com.jr.superPlayerAuction.repositories.interfaces.TeamListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamListViewModel @Inject constructor(private val teamListRepository: TeamListRepository) :
    ViewModel() {

    val teamList: LiveData<Pair<Boolean, ArrayList<Team>>> get() = mTeamList
    private val mTeamList = MutableLiveData<Pair<Boolean, ArrayList<Team>>>()

    val insertTeam: LiveData<Boolean> get() = mInsertTeam
    private val mInsertTeam = MutableLiveData<Boolean>()

    fun retrieveTeamList(afterInsert: Boolean = false) {
        viewModelScope.launch {
            mTeamList.apply {
                postValue(Pair(afterInsert, teamListRepository.retrieveTeamList()))
            }
        }
    }

    fun insertTeam(teamName: String) {
        viewModelScope.launch {
            mInsertTeam.postValue(teamListRepository.insertTeam(Team(teamName)))
        }
    }
}