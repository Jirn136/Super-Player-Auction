package com.jr.superPlayerAuction.dialog

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import com.jr.superPlayerAuction.R
import com.jr.superPlayerAuction.databinding.DialogCreateTeamBinding
import com.jr.superPlayerAuction.utils.toToast

class CreateTeamDialog(private val activity: Activity) {

    fun showDialog(listener: (String) -> Unit) {
        val dialog = AlertDialog.Builder(activity, R.style.CustomDialog)
        val inflater: LayoutInflater = activity.layoutInflater
        val dialogBinding = DialogCreateTeamBinding.inflate(inflater)
        dialog.apply {
            setCancelable(true)
            setView(dialogBinding.root)
        }
        val alertDialog = dialog.create()
        alertDialog.show()
        dialogBinding.apply {
            btnCreateTeam.setOnClickListener {
                if (edtTeamName.text.toString().isNotEmpty()) {
                    listener(edtTeamName.text.toString())
                    alertDialog.dismiss()
                } else
                    "Please Enter a name".toToast(activity)
            }
        }
    }
}