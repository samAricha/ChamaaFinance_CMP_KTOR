package com.teka.chamaa_finance.screens.group_members.tabs.members

import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import chamaafinance.composeapp.generated.resources.Res
import chamaafinance.composeapp.generated.resources.savings_group
import com.teka.chamaa_finance.data_layer.entities.MemberEntity
import com.teka.chamaa_finance.ui.theme.PureWhiteColor
import com.teka.chamaa_finance.ui.theme.TextSizeMedium
import com.teka.chamaa_finance.ui.theme.TextSizeXLarge
import com.teka.chamaa_finance.ui.theme.appShapes
import com.teka.chamaa_finance.widgets.CustomButton
import com.teka.chamaa_finance.widgets.CustomText
import com.teka.chamaa_finance.widgets.LabelValueTextWidget
import org.jetbrains.compose.resources.painterResource

@Composable
fun MemberItemCard(
    member: MemberEntity,
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
                        .padding(start = 16.dp, end = 8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CustomText(
                            text ="${member.firstName} ${member.lastName}",
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = TextSizeXLarge
                        )
                        CustomText(
                            text = member.dateJoined,
                            fontSize = TextSizeMedium
                        )
                    }
                    LabelValueTextWidget(label = "Phone: ", value = member.phone )
                }
            }
            Spacer(modifier = Modifier.height(14.dp))
            HorizontalDivider(thickness = 1.dp)


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 8.dp)
                    .visibleIf(true),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                CustomButton (
                    modifier = Modifier.weight(1f),
                    onClick = {
//                        navController.navigate(AppScreens.IntakeFormScreen.createRoute(vehicle.id, vehicle.gatepass_no))
                    },
                    btnText = "Add Intake"
                )
                CustomButton (
                    modifier = Modifier.weight(1f),
                    onClick = {},
                    btnText = "Check Out"
                )

            }
        }
    }
}


@Composable
fun Modifier.visibleIf(condition: Boolean): Modifier = if (condition) this else this.size(0.dp)
