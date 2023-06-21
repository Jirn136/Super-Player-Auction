package com.jr.superPlayerAuction.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jr.superPlayerAuction.model.Team
import com.jr.superPlayerAuction.repositories.TeamListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamListViewModel @Inject constructor(private val teamListRepository: TeamListRepository) :
    ViewModel() {

    val teamList: LiveData<ArrayList<Team>> get() = mTeamList
    private val mTeamList = MutableLiveData<ArrayList<Team>>()

    fun retrieveTeamList() {
        viewModelScope.launch {
            mTeamList.apply {
                postValue(teamListRepository.retrieveTeamList())
            }
        }
    }
}