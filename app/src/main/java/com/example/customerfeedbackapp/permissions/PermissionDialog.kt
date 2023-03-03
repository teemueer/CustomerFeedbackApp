package com.example.customerfeedbackapp.permissions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/*
A composable that can be called for when asking for permissions
during runtime.

Idea and help from philipplackner
 */

@Composable
fun PermissionDialog(
    permissionTextProvider: PermissionTextProvider,
    isPermanentlyDeclined: Boolean,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    onGoToAppSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        buttons = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Divider()
                Text(
                    text = if (isPermanentlyDeclined) {
                        "Grant permission"
                    } else {
                        "OK"
                    },
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (isPermanentlyDeclined) {
                                onGoToAppSettingsClick()
                            } else {
                                onOkClick()
                            }
                        }
                        .padding(16.dp)
                )
            }
        },
        title = {
            Text(text = "Permission required")
        },
        text = {
            Text(text = permissionTextProvider.getDescription(
                isPermanentlyDeclined = isPermanentlyDeclined
            ))
        },
        modifier = modifier
    )
}
// To reduce the amount of hardcoding + future proofing.
// This is a way to get permission info without having to change the code above.
interface PermissionTextProvider{
    fun getDescription(isPermanentlyDeclined: Boolean): String
}

class CameraPermissionTextProvider: PermissionTextProvider{
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined){
            "You have permanently declined camera permissions."+
                    "You can enable them from the app settings."
        }else{
            "This app uses the camera so that you can scan your products" +
                    "to leave feedback!"
        }
    }
}

class RecordingPermissionTextProvider: PermissionTextProvider{
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined){
            "You have permanently declined Audio Recording permissions."+
                    "You can enable them from the app settings."
        }else{
            "This app uses Audio Recording to help people with disabilities" +
                    "to use the application!"
        }
    }
}

class FineLocationPermissionTextProvider: PermissionTextProvider{
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined){
            "You have permanently declined Fine Location permissions."+
                    "You can enable them from the app settings."
        }else{
            "This app uses Location data to find your closest" +
                    "store location!"
        }
    }
}