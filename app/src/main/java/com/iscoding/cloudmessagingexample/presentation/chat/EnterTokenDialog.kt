package com.iscoding.cloudmessagingexample.presentation.chat

import android.provider.Settings.Global.getString
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
import com.iscoding.cloudmessagingexample.R
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun EnterTokenDialog(
    token: String,
    onTokenChange: (String) -> Unit,
    onSubmit: () -> Unit
) {
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = token,
                onValueChange = onTokenChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Remote user token")
                },
                maxLines = 1
            )
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedButton(
                    onClick = {
                        scope.launch {
                            val localToken = Firebase.messaging.token.await()
                            clipboardManager.setText(AnnotatedString(localToken))

                            Toast.makeText(
                                context,
                                "Copied local token!",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                ) {
                    Text("Copy token")
                }
                Spacer(Modifier.width(16.dp))
                Button(
                    onClick = onSubmit
                ) {
                    Text("Submit")
                }
            }
        }
    }
    // this way is better for error handling
    //instead we use this Firebase.messaging.token.await() for simplicity
//    FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
//        if (!task.isSuccessful) {
//            Log.w("ISLAM", "Fetching FCM registration token failed", task.exception)
//            return@OnCompleteListener
//        }
//
//        // Get new FCM registration token
//        val token = task.result
//
//        // Log and toast
//        val msg = getString(R.string.msg_token_fmt, token)
//        Log.d("ISLAM", msg)
//        Toast.makeText(LocalContext.current, msg, Toast.LENGTH_SHORT).show()
//    })
}