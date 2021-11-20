package com.example.randomfood.appdrawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

import com.example.randomfood.screen.ListFoodsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.example.randomfood.R
import com.example.randomfood.domain.FoodModel
import com.example.randomfood.routing.JetFoodRouter
import com.example.randomfood.routing.Screen


import com.example.randomfood.ui.theme.JetFoodThemeSettings


@Composable
fun HomePageScreen() {
    val scope = rememberCoroutineScope()
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    //click button
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val colors = if (isPressed) Color.Blue else Color.Red

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(scaffoldState = scaffoldState, scope = scope)
        },
        content = {
              ListFoodsItem()
//                ListRamDomITem()
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                icon = {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "get random",
                        modifier = Modifier.background(color = Color.White)
                    )
                },
                text = {
                    Text("Random Lunch Food",color=Color.White)
                },
                onClick = {

                },
                elevation = FloatingActionButtonDefaults.elevation(8.dp),
                backgroundColor = colors
//                modifier = Modifier.background(MaterialTheme.colors.secondary)
            )
        },
        drawerContent = {
            AppDrawer(currentScreen = Screen.Home,
                closeDrawerAction = {
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                }
            )
        }
    )
}


@Composable
fun RamdomFoodLunch(
    food: List<FoodModel>,
    onClick: () -> Unit,

){

}


@Composable
fun TopAppBar(scaffoldState: ScaffoldState, scope: CoroutineScope) {
    val drawerState = scaffoldState.drawerState
    TopAppBar(
        navigationIcon = {
            IconButton(
                content = {
                    Icon(
                        Icons.Default.Menu,
                        tint = Color.White,
                        contentDescription = stringResource(R.string.app_name)
                    )
                },
                onClick = {
                    scope.launch {
                        if (drawerState.isClosed) drawerState.open()
                        else drawerState.close()
                    }
                }
            )
        },
        title = { Text(text = stringResource(R.string.app_name), color = Color.White) },
        backgroundColor = colorResource(id = R.color.design_default_color_primary)
    )
}

@Composable
private fun ScreenNavigationButton(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val colors = MaterialTheme.colors
    val imageAlpha = if (isSelected) {
        1f
    } else {
        0.6f
    }
    val textColor = if (isSelected) {
        colors.onSecondary
    } else {
        colors.onSurface.copy(alpha = 0.6f)
    }

    val backgroundColor = if (isSelected) {
        colors.onSecondary.copy(alpha = 0.12f)
    } else {
        colors.surface
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp),
        color = backgroundColor,
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable(onClick = onClick)
                .fillMaxWidth()
                .padding(2.dp)
        ) {
            Image(
                imageVector = icon,
                contentDescription = "Screen Navigation Button",
                colorFilter = ColorFilter.tint(textColor),
                alpha = imageAlpha
            )
            Spacer(Modifier.width(16.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.body2,
                color = textColor,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

private fun changeTheme() {
    JetFoodThemeSettings.isInDarkTheme.value = JetFoodThemeSettings.isInDarkTheme.value.not()
}

@Composable
fun AppDrawerHeader() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.miquang),
            contentDescription = "Drawer Food",
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
            modifier = Modifier
                .padding(16.dp)
                .size(32.dp)
                .clip(RoundedCornerShape(10))
                .border(1.dp, Color.Gray, RoundedCornerShape(10))
        )
        Text(
            text = "Ramdom Foods App",
            modifier = Modifier.align(Alignment.CenterVertically)

        )
    }
}

@Composable
fun AppDrawer(
    currentScreen: Screen,
    closeDrawerAction: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
    ) {
        AppDrawerHeader()
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .2f))
        ScreenNavigationButton(
            icon = Icons.Filled.Home,
            label = "Home",
            isSelected = currentScreen === Screen.Home,
            onClick = {
                JetFoodRouter.navigateTo(Screen.Home)
                closeDrawerAction()
            }
        )
        ScreenNavigationButton(
            icon = Icons.Filled.Notifications,
            label = "Notification",
            isSelected = currentScreen === Screen.FoodDetail,
            onClick = {
                closeDrawerAction()
            }
        )
        AppDrawerFooter()
    }
}

@Composable
private fun AppDrawerFooter(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = 16.dp,
                bottom = 16.dp,
                end = 16.dp
            )
    ) {
        val colors = MaterialTheme.colors
        val (settingsImage, settingsText, darkModeButton) = createRefs()
        Image(
            modifier = modifier.constrainAs(settingsImage) {
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            },
            imageVector = Icons.Default.Settings,
            contentDescription = stringResource(id = R.string.settings),
            colorFilter = ColorFilter.tint(colors.primaryVariant)
        )
        Text(
            fontSize = 10.sp,
            text = stringResource(R.string.settings),
            style = MaterialTheme.typography.body2,
            color = colors.primaryVariant,
            modifier = modifier
                .padding(start = 16.dp)
                .constrainAs(settingsText) {
                    start.linkTo(settingsImage.end)
                    centerVerticallyTo(settingsImage)
                }
        )

        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_moon),
            contentDescription = stringResource(id = R.string.app_name),
            modifier = modifier
                .clickable(onClick = { changeTheme() })
                .constrainAs(darkModeButton) {
                    end.linkTo(parent.end)
                    bottom.linkTo(settingsImage.bottom)
                },
            colorFilter = ColorFilter.tint(colors.primaryVariant)
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun AppPreviewer() {
//    AppDrawer(Screen.Home, {})
//}

