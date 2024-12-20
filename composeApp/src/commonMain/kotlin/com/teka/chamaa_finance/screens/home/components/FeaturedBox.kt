package com.teka.chamaa_finance.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import chamaafinance.composeapp.generated.resources.Res
import chamaafinance.composeapp.generated.resources.chamaa_logo_no_bg
import com.teka.chamaa_finance.domain.models.UserData
import com.teka.chamaa_finance.ui.theme.GreenStart
import com.teka.chamaa_finance.ui.theme.TextSizeMedium
import com.teka.chamaa_finance.ui.theme.appShapes
import com.teka.chamaa_finance.util.getGradient
import com.teka.chamaa_finance.widgets.LabelValueTextWidget
import org.jetbrains.compose.resources.painterResource


@Composable
fun FeaturedBox(
    userInfo: UserData?
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = appShapes.medium)
            .background(
                getGradient(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.primaryContainer
                )
            )
    ) {
        Row(
            modifier = Modifier.matchParentSize(),
            verticalAlignment = Alignment.Bottom
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(Res.drawable.chamaa_logo_no_bg),
                contentDescription = "chamaa logo",
                modifier = Modifier
                    .padding(start = 30.dp)
                    .size(120.dp),
            )
        }

        Row(
            verticalAlignment = Alignment.Companion.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .padding(start = 20.dp),
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    text = "Heximas Packhouse",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(modifier = Modifier.size(0.05.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    text = "automating packhouses",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                )
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(GreenStart)
                                    .padding(6.dp)

                            ) {
                                Icon(
                                    imageVector = Icons.Default.AccountCircle,
                                    contentDescription = "User Icon",
                                    tint = Color.White,
                                    modifier = Modifier.size(12.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            LabelValueTextWidget(
                                label = "",
                                value = (userInfo?.user_name ?: "").lowercase(),
                                valueFontSize = TextSizeMedium,
                                valueFontWeight = FontWeight.Light,
                                valueColor = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(GreenStart)
                                    .padding(6.dp)
                                    .size(12.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Build,
                                    contentDescription = "Role Icon",
                                    tint = Color.White
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            LabelValueTextWidget(
                                label = "",
                                value = "Admin".lowercase(),
                                valueFontSize = TextSizeMedium,
                                valueFontWeight = FontWeight.Light,
                                valueColor = Color.White
                            )
                        }

                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

            }

        }
    }
}
