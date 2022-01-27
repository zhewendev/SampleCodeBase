package com.zhewen.navigationcodelab.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.zhewen.navigationcodelab.R

class BasicDialogFragment:DialogFragment() {

    companion object{
        const val TAG = "BasicDialogFragment"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setMessage("this is BasicDialogFragment")
            .setPositiveButton(getString(R.string.positive_button),{_,_ ->})
            .create()
    }
}