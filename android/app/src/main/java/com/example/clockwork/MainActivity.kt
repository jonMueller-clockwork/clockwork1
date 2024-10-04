package com.example.clockwork

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.amplifyframework.core.Amplify
import com.amplifyframework.ui.authenticator.ui.Authenticator
import com.example.clockwork.ui.theme.ClockworkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ClockworkTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Authenticator { state ->
                        Log.d("Login", state.user.userId)
                        Column {
                            Text(
                                text = "Hello ${state.user.userId}!",
                            )
                            Button(onClick = {
                                Amplify.Auth.signOut { }
                            }) {
                                Text(text = "Sign Out")
                            }
                        }

                    }
                }
            }
        }
    }
}
