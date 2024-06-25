package com.dshatz.composefonts

import Sample
import android.os.Bundle
import androidx.activity.compose.setContent

class MainActivity: androidx.activity.ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Sample()
        }

    }

}