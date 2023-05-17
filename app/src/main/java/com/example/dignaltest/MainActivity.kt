package com.example.dignaltest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dignaltest.components.CustomTextField
import com.example.dignaltest.ui.theme.DignalTestTheme

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DignalTestTheme {
                MainScreen()
            }
        }
    }

    @Composable
    private fun MainScreen() {
        Surface(
            color = Color.Black,
            modifier = Modifier.fillMaxSize()
        ) {
            Box {
                ItemList()
                TopBar()
            }
        }
    }

    @Composable
    private fun ItemList() {
        val images = listOf(
            R.drawable.poster1,
            R.drawable.poster2,
            R.drawable.poster3,
            R.drawable.poster4,
            R.drawable.poster5,
            R.drawable.poster6,
            R.drawable.poster7,
            R.drawable.poster8,
            R.drawable.poster9,
            R.drawable.poster1,
            R.drawable.poster2,
            R.drawable.poster3,
            R.drawable.poster4,
            R.drawable.poster5,
            R.drawable.poster6,
            R.drawable.poster7,
            R.drawable.poster8,
            R.drawable.poster9,
            R.drawable.poster1,
            R.drawable.poster2,
            R.drawable.poster3,
            R.drawable.poster4,
            R.drawable.poster5,
            R.drawable.poster6,
            R.drawable.poster7,
            R.drawable.poster8,
            R.drawable.poster9,
            R.drawable.poster1,
            R.drawable.poster2,
            R.drawable.poster3,
            R.drawable.poster4,
            R.drawable.poster5,
            R.drawable.poster6,
            R.drawable.poster7,
            R.drawable.poster8,
            R.drawable.poster9,
            R.drawable.poster1,
            R.drawable.poster2,
            R.drawable.poster3,
            R.drawable.poster4,
            R.drawable.poster5,
            R.drawable.poster6,
            R.drawable.poster7,
            R.drawable.poster8,
            R.drawable.poster9,
        )
        LazyVerticalGrid(
            contentPadding = PaddingValues(top = 15.dp, bottom = 10.dp),
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            content = {
                items(images) { item ->
                    val painter = painterResource(item)
                    Image(
                        painter = painter,
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .aspectRatio(painter.intrinsicSize.width / painter.intrinsicSize.height)

                    )
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp)
                .padding(horizontal = 16.dp)
        )

    }

    @Composable
    private fun TopBar() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .heightIn()
        ) {
            var showSearchBar by remember { mutableStateOf(false) }
            var searchedValue by remember {
                mutableStateOf("")
            }
            Image(painter = painterResource(id = R.drawable.nav_bar), contentDescription = null)
            if (showSearchBar)
                SearchBar({
                    searchedValue = ""
                    showSearchBar = false
                }, { query ->
                    searchedValue = query
                }, searchedValue, stringResource(id = R.string.search_here))
            else
                NormalTopBar(
                    iconButtonAlignment = Modifier
                        .align(Alignment.TopEnd),
                    rowAlignment = Modifier.align(Alignment.TopStart)
                ) {
                    showSearchBar = true
                }

        }
    }

    @Composable
    private fun SearchBar(
        onClearClick: () -> Unit = {},
        onSearchTextChanged: (String) -> Unit = {},
        searchText: String,
        placeholderText: String = "",
    ) {

        var showClearButton by remember { mutableStateOf(false) }
        val keyboardController = LocalSoftwareKeyboardController.current
        val focusRequester = remember { FocusRequester() }
        val interactionSource = remember { MutableInteractionSource() }
        val isFocused by interactionSource.collectIsFocusedAsState()

        val textFieldPadding = 6.dp //use this value to change the length of th e indicator
        val indicatorColor = Color.White
        val indicatorWidth = 1.dp

        CustomTextField(
            leadingIcon = null,
            trailingIcon = {
                AnimatedVisibility(
                    visible = showClearButton,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    IconButton(onClick = { onClearClick() }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            tint = Color.White,
                            contentDescription = null
                        )
                    }

                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(start = 10.dp)
                .onFocusChanged { focusState ->
                    showClearButton = (focusState.isFocused)
                }
                .focusRequester(focusRequester)
                .drawWithContent {
                    drawContent()
                    if (isFocused) {
                        val strokeWidth = indicatorWidth.value * density
                        val y = size.height - strokeWidth / 2
                        drawLine(
                            indicatorColor,
                            Offset((textFieldPadding).toPx(), y),
                            Offset(size.width - textFieldPadding.toPx(), y),
                            strokeWidth
                        )
                    }
                },
            fontSize = 16.sp,
            placeholderText = placeholderText,
            keyboardController = keyboardController,
            searchText = searchText,
            onSearchTextChanged = onSearchTextChanged,
            interactionSource = interactionSource
        )

        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }

    @Composable
    private fun NormalTopBar(
        iconButtonAlignment: Modifier,
        rowAlignment: Modifier,
        searchClicked: () -> Unit
    ) {
        IconButton(onClick = { searchClicked() }, modifier = iconButtonAlignment) {
            Icon(
                tint = Color.White,
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
        }
        Row(
            modifier = rowAlignment,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    tint = Color.White,
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
            Text(
                text = stringResource(id = R.string.romantic_comedy),
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }

    @Preview
    @Composable
    private fun PreviewScreen() {
        MainScreen()
    }
}
