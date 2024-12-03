package com.example.bmicalc

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SimpleToolbar(
    title: String,
    onBackAction: (() -> Unit)? = null
) {
    Column(verticalArrangement = Arrangement.Top) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
        ) {
            if (onBackAction != null) {
                Box(
                    modifier = Modifier
                        .clickable { onBackAction() }
                        .clip(shape = RoundedCornerShape(12.dp))
                        .background(shape = RoundedCornerShape(12.dp), color = Color.Transparent)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_back),
                        contentDescription = "arrow back",
                        tint = Color(0xFF5777b4),
                        modifier = Modifier.align(Alignment.Center).padding(4.dp).size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))

            }
            Text(
                text = title,
                //fontSize = 30.sp,
                modifier = Modifier.padding(20.dp),
                color = Color(0xFF5777b4),
                style = MaterialTheme.typography.displaySmall

            )

        }

    }

}