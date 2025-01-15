package com.teka.chamaa_finance.screens.about

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import chamaafinance.composeapp.generated.resources.Res
import chamaafinance.composeapp.generated.resources.chama_base_logo_nobg
import chamaafinance.composeapp.generated.resources.chamaa_logo_no_bg
import com.teka.chamaa_finance.ui.theme.Cream1
import com.teka.chamaa_finance.ui.theme.Cream2
import com.teka.chamaa_finance.ui.theme.DarkBrown
import com.teka.chamaa_finance.ui.theme.DarkGreen
import com.teka.chamaa_finance.widgets.CustomTopAppBar
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource


@ExperimentalMaterial3Api
@Composable
fun AboutScreen(
    navController: NavController
) {
    val selectedTabIndex = remember {
        mutableIntStateOf(0)
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = {
                    Text(
                        text = "About Us",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = it.calculateTopPadding(), horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(20.dp))
                Box {
                    Image(
                        painter = painterResource(Res.drawable.chama_base_logo_nobg),
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .size(110.dp)
                            .clip(shape = CircleShape),
                    )
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .size(32.dp)
                            .background(
                                brush = Brush.linearGradient(
                                    colors =  listOf(Cream1, Cream2)
                                ),
                                shape = CircleShape
                            )
                            .border(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.background,
                                shape = CircleShape
                            )
                            .padding(0.dp)
                            .align(BiasAlignment(1f, .9f)),
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = "App Icon",
                            modifier = Modifier.size(16.dp)
                        )
                    }

                }
            }
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Teka Group", style = MaterialTheme.typography.labelMedium)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "#Made with Love \uD83D\uDC9A",
                        style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.outline)
                    )
                }
            }
            item {
                val tabItems = listOf("Coming Up", "Contact Us", "About Us")
                TabRow(
                    selectedTabIndex = selectedTabIndex.value,
                    containerColor = Color.Transparent,
                    divider = {},
                    indicator = {},
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    tabItems.forEach { item ->
                        val index = tabItems.indexOf(item)
                        val selected = selectedTabIndex.value == index
                        val textColor = if (selected)Color.White else Color.Gray
                        Box(
                            modifier = Modifier.clip(shape = MaterialTheme.shapes.medium)
                        ) {
                            Tab(
                                modifier = Modifier
                                    .padding(horizontal = 4.dp)
                                    .background(
                                        brush = Brush.linearGradient(
                                            colors = if (selected) listOf(
                                                DarkBrown,
                                                DarkGreen,
                                            ) else listOf(
                                                Cream1,
                                                Cream2,
                                            )
                                        ),
                                        shape = MaterialTheme.shapes.medium,
                                    )
                                    .height(46.dp),
                                selected = selected,
                                onClick = {
                                    selectedTabIndex.value = index
                                },
                            ) {
                                Text(
                                    text = item,
                                    style = MaterialTheme.typography.labelLarge,
                                    color = textColor
                                )
                            }
                        }
                    }
                }
            }
            item {
                Column {
                    when (selectedTabIndex.value) {
                        0 -> {
                            val items = listOf("iOS support", "Payment Automations", "Web Support", "Harmony OS support")
                            items.forEachIndexed { index, item ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp)
                                        .clickable {  }
                                        .animateContentSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    // Animated Icon
                                    Box(
                                        modifier = Modifier
                                            .size(25.dp)
                                            .clip(CircleShape)
                                            .background(
                                                Brush.radialGradient(
                                                    colors = listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.primaryContainer),
                                                    radius = 200f
                                                )
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Star,
                                            contentDescription = item,
                                            tint = Color.White,
                                            modifier = Modifier.scale(0.7f)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))

                                    Text(
                                        text = item,
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            MaterialTheme.colorScheme.outline
                                        ),
                                        modifier = Modifier.padding(horizontal = 8.dp)
                                    )
                                }
                            }
                        }

                        1 -> {
                            IconListTile(
                                icon = {
                                    ListTileIcon(icon = Icons.Outlined.Email)
                                },
                                title = "Email", subtitle = "samaricha001@gmail.com",
                            )
                            Divider(
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 45.dp, end = 20.dp, top = 20.dp, bottom = 20.dp)
                            )
                            IconListTile(
                                icon = {
                                    ListTileIcon(icon = Icons.Outlined.Call)
                                },
                                title = "Phone", subtitle = "+254708392326",
                            )
                        }

                        2 -> {
//                            Text(
//                                text = "We are a group of young individuals from Kenya on a Mission to change and transform Africa " +
//                                        "and the world for the better using Technology. \n\n",
//                                style = MaterialTheme.typography.bodyLarge,
//                                modifier = Modifier.padding(16.dp)
//                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                val offsetX = remember { Animatable(0f) }
                                LaunchedEffect(true) {
                                    // Animate the quote to move horizontally
                                    offsetX.animateTo(
                                        targetValue = 20f,
                                        animationSpec = tween(durationMillis = 3000, easing = FastOutSlowInEasing)
                                    )
                                }

                                Text(
                                    text = "\"We are a group of young individuals from Kenya on a Mission " +
                                            "to change and transform Africa and the world for the better, using Technology.\"",
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontStyle = FontStyle.Italic,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    ),
                                    modifier = Modifier
                                        .offset(x = with(LocalDensity.current) { offsetX.value.dp })
                                )
                            }

                        }
                    }
                }
            }

            item {
                val quotes = listOf(
                    "Many hands make light work. - African Proverb",
                    "Alone we can do so little; together we can do so much. - Helen Keller",
                    "If you want to go far, go together. - African Proverb",
                    "Unity is strength... when there is teamwork and collaboration, wonderful things can be achieved. - Mattie Stepanek",
                    "Collaboration is the essence of life. The wind, bees, and flowers work together to spread the pollen. - Amit Ray",
                    "The strength of the team is each individual member. The strength of each member is the team. - Phil Jackson",
                    "Trust is the glue of life. It’s the most essential ingredient in effective communication and relationships. - Stephen Covey",
                    "Blockchain builds trust in communities by making processes transparent and secure. - Unknown",
                    "Innovation is the ability to see change as an opportunity, not a threat. - Steve Jobs",
                    "The future is built on collaboration and shared resources. - Unknown"
                )


                val currentQuote = remember { mutableStateOf(0) }

                LaunchedEffect(key1 = currentQuote.value) {
                    // Animate to the next quote every 5 seconds
                    delay(5000)
                    currentQuote.value = (currentQuote.value + 1) % quotes.size
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    val offsetX = remember { Animatable(0f) }

                    LaunchedEffect(currentQuote.value) {
                        offsetX.snapTo(0f)  // Reset position when quote changes
                        offsetX.animateTo(
                            targetValue = 20f,
                            animationSpec = tween(durationMillis = 3000, easing = FastOutSlowInEasing)
                        )
                    }

                    Text(
                        text = "\"${quotes[currentQuote.value]}\"",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Light,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier
                            .offset(x = with(LocalDensity.current) { offsetX.value.dp })
                    )
                }
            }

        }
    }
}

@Composable
private fun ListTileIcon(icon: ImageVector) {
    Surface(
        modifier = Modifier.size(32.dp),
        shape = CircleShape,
        color = Cream1
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Profile Icon",
            modifier = Modifier
                .size(16.dp)
                .padding(8.dp),
            tint = Color.Gray
        )
    }
}
