package com.gitrepotestapp.presentation.extensions

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity

// get instance of error alert dialog called on errors in different fragments
fun FragmentActivity.getErrorDialog(title: String, text: String): AlertDialog.Builder {
    val builder: AlertDialog.Builder = AlertDialog.Builder(this)
    builder.setTitle(title)
        .setMessage(text)
        .setPositiveButton(
            "OK"
        ) { dialog, _ ->
            dialog.cancel()
        }

    return builder
}

fun FragmentActivity.showError(title: String, text: String) {
    val builder = getErrorDialog(title, text)
    builder.create()
    builder.show()
}

fun AppCompatActivity.checkSelfPermissionCompat(permission: String) =
    ActivityCompat.checkSelfPermission(this, permission)

fun AppCompatActivity.shouldShowRequestPermissionRationaleCompat(permission: String) =
    ActivityCompat.shouldShowRequestPermissionRationale(this, permission)

fun AppCompatActivity.requestPermissionsCompat(
    permissionsArray: Array<String>,
    requestCode: Int
) {
    ActivityCompat.requestPermissions(this, permissionsArray, requestCode)
}
