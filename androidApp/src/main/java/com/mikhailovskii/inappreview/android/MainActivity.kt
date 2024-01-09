package com.mikhailovskii.inappreview.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mikhailovskii.inappreviewkmp_shared.AndroidServiceLocator
import com.mikhailovskii.inappreviewkmp_shared.ReviewComponent

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Content()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        AndroidServiceLocator.activity = this
    }

    override fun onStop() {
        super.onStop()
        AndroidServiceLocator.activity = null
    }
}

@Composable
fun Content() {
    val reviewComponent = remember { ReviewComponent() }

    Column {
        Button(onClick = reviewComponent::requestInAppReview) {
            Text("Rate in app")
        }
        Button(onClick = reviewComponent::requestInMarketReview) {
            Text("Rate in market")
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun Content_Preview() {
    Content()
}
