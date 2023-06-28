package com.jr.superPlayerAuction.activities

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.jr.superPlayerAuction.R
import com.jr.superPlayerAuction.databinding.ActivityAddPlayerBinding
import com.jr.superPlayerAuction.di.MediaPreferences
import com.jr.superPlayerAuction.model.Player
import com.jr.superPlayerAuction.utils.Constants
import com.jr.superPlayerAuction.utils.applySrcInColorFilter
import com.jr.superPlayerAuction.utils.gone
import com.jr.superPlayerAuction.utils.show
import com.jr.superPlayerAuction.utils.showViews
import com.jr.superPlayerAuction.utils.toToast
import com.jr.superPlayerAuction.viewmodels.PlayerListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddPlayerActivity : AppCompatActivity() {
    @Inject
    lateinit var mediaPreferences: MediaPreferences

    private val playerViewModel: PlayerListViewModel by viewModels()

    private lateinit var binding: ActivityAddPlayerBinding
    private var playerName: String = Constants.EMPTY_STRING
    private var soldAmount: String = Constants.EMPTY_STRING
    private var teamName: String = Constants.EMPTY_STRING
    private var checkedItem = 0
    private var playerAge: Int = 0
    private var batType: Int = 0
    private var bowlType: Int = 0

    private var fromList: Boolean = false

    private lateinit var playerModel: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.apply {
            teamName = getStringExtra(Constants.TEAM_NAME).toString()
            fromList = getBooleanExtra(Constants.FROM_LIST, false)
        }
        setupToolbar()


        if (fromList) {
            intent.apply {
                playerModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    getParcelableExtra(Constants.PLAYER, Player::class.java)!!
                } else getParcelableExtra(Constants.PLAYER)!!
            }
            updateLayout()
        }

        validateFields()
        validateRadioType()
        initObserver()
        initClickListeners()
    }

    private fun setupToolbar() {
        binding.customToolbar.toolbar.apply {
            setSupportActionBar(binding.customToolbar.toolbar)
            setNavigationIcon(R.drawable.ic_back)
            navigationIcon!!.applySrcInColorFilter(
                ContextCompat.getColor(
                    this@AddPlayerActivity,
                    R.color.white
                )
            )
            setNavigationOnClickListener { finish() }
            supportActionBar?.let {
                it.title = teamName
                it.setDisplayHomeAsUpEnabled(true)
                it.setDisplayShowHomeEnabled(true)
            }
        }
    }

    private fun updateLayout() {
        binding.apply {
            setEnabledFalse(
                edtAgeLayout,
                edtAmountLayout,
                edtNameLayout,
                edtPlayerName,
                edtAgeLayout,
                edtPlayerAge,
                radioGroupType,
                radioGroupBatType,
                radioGroupBowlType,
                edtSoldAmount,
                btnSave,
                radioButtonLeftBatsman,
                radioButtonRightBatsman,
                radioButtonSeam,
                radioButtonSpinner,
                radioButtonAllRounder,
                radioButtonBatsman,
                radioButtonBowler
            )
            playerModel.apply {
                when {
                    speciality == 2 -> showViews(radioGroupBatType, radioGroupBowlType)
                    bowlType != 0 -> radioGroupBowlType.show()
                    batType != 0 -> radioGroupBatType.show()
                }
            }

            btnSave.gone()
            loadDetails()
        }
    }

    private fun loadDetails() {
        binding.apply {
            with(playerModel) {
                edtPlayerName.setText(this.playerName)
                edtPlayerAge.setText(this.age.toString())
                setCheckBox()
                edtSoldAmount.setText(this.amount)
            }
        }
    }

    private fun RadioButton.isCheckedTrue() {
        this.isChecked = true
    }

    private fun setCheckBox() {
        binding.apply {
            playerModel.apply {
                when (speciality) {
                    Constants.ALL_ROUNDER -> radioButtonAllRounder.isCheckedTrue()
                    Constants.BATSMAN -> radioButtonBatsman.isCheckedTrue()
                    Constants.BOWLER -> radioButtonBowler.isCheckedTrue()
                }
                when (bowlType) {
                    Constants.BOWL_SEAM -> radioButtonSeam.isCheckedTrue()
                    Constants.BOWL_SPIN -> radioButtonSpinner.isCheckedTrue()
                }
                when (batType) {
                    Constants.RIGHT_HAND_BATSMAN -> radioButtonRightBatsman.isCheckedTrue()
                    Constants.LEFT_HAND_BATSMAN -> radioButtonLeftBatsman.isCheckedTrue()
                }
            }
        }
    }

    private fun setEnabledFalse(vararg view: View) = view.map {
        it.isEnabled = false
    }

    private fun initClickListeners() {
        binding.apply {

            btnSave.setOnClickListener {
                when {
                    teamName.isEmpty() -> {
                        finish()
                        "Unable to create player, Please try again later".toToast(this@AddPlayerActivity)
                    }

                    playerName.isEmpty() -> "Player name needed".toToast(this@AddPlayerActivity)

                    !(radioButtonBatsman.isChecked || radioButtonBowler.isChecked || radioButtonAllRounder.isChecked) ->
                        "Check any one player speciality type".toToast(this@AddPlayerActivity)

                    edtSoldAmount.text.toString()
                        .isEmpty() -> "Add player sold amount".toToast(this@AddPlayerActivity)

                    else -> {
                        val playerModel = Player(
                            playerName = playerName,
                            age = playerAge,
                            speciality = checkedItem,
                            amount = soldAmount,
                            batType = batType,
                            bowlType = bowlType,
                            teamName = teamName,
                        )

                        playerViewModel.insertPlayer(playerModel)
                    }

                }
            }
        }
    }

    private fun initObserver() {
        playerViewModel.insertPlayer.observe(this) {
            if (it)
                "Player created".toToast(this)
            else
                "Unable to create player, Please try again later".toToast(this)

            finish()
        }
    }

    private fun validateRadioType() {
        binding.apply {
            radioGroupType.setOnCheckedChangeListener { _, checkedId ->
                checkedItem = when (checkedId) {
                    R.id.radio_button_all_rounder -> {
                        showViews(radioGroupBatType, radioGroupBowlType)
                        Constants.ALL_ROUNDER
                    }

                    R.id.radio_button_bowler -> {
                        radioGroupBowlType.show()
                        radioGroupBatType.gone()
                        Constants.BOWLER
                    }

                    else -> {
                        radioGroupBowlType.gone()
                        radioGroupBatType.show()
                        Constants.BATSMAN
                    }
                }
            }
            radioGroupBatType.setOnCheckedChangeListener { _, checkedId ->
                batType = when (checkedId) {
                    R.id.radio_button_left_batsman -> Constants.LEFT_HAND_BATSMAN
                    R.id.radio_button_right_batsman -> Constants.RIGHT_HAND_BATSMAN
                    else -> Constants.LEFT_HAND_BATSMAN
                }
            }
            radioGroupBowlType.setOnCheckedChangeListener { _, checkedId ->
                bowlType = when (checkedId) {
                    R.id.radio_button_seam -> Constants.BOWL_SEAM
                    R.id.radio_button_spinner -> Constants.BOWL_SPIN
                    else -> Constants.BOWL_SEAM
                }
            }
        }
    }

    private fun validateFields() {
        binding.apply {
            edtPlayerName.doOnTextChanged { text, _, _, count ->
                if (count == 0) edtPlayerName.error = "Please enter a name"
                else playerName = text.toString()

            }

            edtSoldAmount.doOnTextChanged { text, _, _, count ->
                if (count == 0) edtSoldAmount.error = "Sold amount needed"
                else soldAmount = text.toString()
            }

            edtPlayerAge.doOnTextChanged { text, _, _, count ->
                when {
                    count == 0 -> edtPlayerAge.error = "Player age needed"
                    text.toString().isNotEmpty() && Integer.parseInt(text.toString()) < 10 ->
                        edtPlayerAge.error = "Player age should be more than 10"

                    text.toString().isNotEmpty() && Integer.parseInt(text.toString()) > 60 ->
                        edtPlayerAge.error = "Player age should be less than 60"

                    else -> playerAge = Integer.parseInt(text.toString())
                }
            }
        }
    }
}