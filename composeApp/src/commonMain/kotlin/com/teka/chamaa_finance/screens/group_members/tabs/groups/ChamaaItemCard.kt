package com.teka.chamaa_finance.screens.group_members.tabs.groups

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import chamaafinance.composeapp.generated.resources.Res
import chamaafinance.composeapp.generated.resources.group
import chamaafinance.composeapp.generated.resources.savings_group
import com.teka.chamaa_finance.data_layer.entities.ChamaEntity
import com.teka.chamaa_finance.ui.theme.PureWhiteColor
import com.teka.chamaa_finance.ui.theme.TextSizeMedium
import com.teka.chamaa_finance.ui.theme.TextSizeXLarge
import com.teka.chamaa_finance.ui.theme.appShapes
import com.teka.chamaa_finance.widgets.CustomText
import com.teka.chamaa_finance.widgets.LabelValueTextWidget
import org.jetbrains.compose.resources.painterResource

@Composable
fun ChamaaItemCard(
    chamaa: ChamaEntity,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = appShapes.medium,
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = PureWhiteColor
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Image(
                        painter = painterResource(Res.drawable.savings_group),
                        contentDescription = null,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(top = 10.dp)
                    )

                }

                Spacer(modifier = Modifier.width(5.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 16.dp, end = 8.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CustomText(
                            text = chamaa.chamaName,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = TextSizeXLarge
                        )
                        CustomText(
                            text = chamaa.dateFormed,
                            fontSize = TextSizeMedium
                        )
                    }
                    LabelValueTextWidget(label = "Desc: ", value = chamaa.chamaDescription )
                }
            }
        }
    }
}


@Composable
fun Modifier.visibleIf(condition: Boolean): Modifier = if (condition) this else this.size(0.dp)
