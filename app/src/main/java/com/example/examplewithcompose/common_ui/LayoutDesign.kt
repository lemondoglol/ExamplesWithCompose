package com.example.examplewithcompose.common_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.examplewithcompose.R

/**
 * XXXXXXXXXXX
 *        XX
 * */
@Composable
fun ChatBubbleRight(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Column(modifier = Modifier.fillMaxWidth().background(Color.Cyan)) {
            Text("xx")
        }
        Row {
            Spacer(modifier = Modifier.weight(8f))
            Icon(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null
            )
        }
    }
}

@Composable
fun AlignToRight(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        ChatBubbleRight(
            modifier = Modifier.align(Alignment.End),
        )
    }
}

@Composable
fun SeparateRowWithEqualSpace(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier.weight(1f).background(color = Color.Cyan),
            horizontalArrangement = Arrangement.Start,
        ) {
            Text("Some Thing")
        }

        Row(
            modifier = Modifier.weight(1f).background(color = Color.Green),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text("Middle Thing")
        }

        Row(
            modifier = Modifier.weight(1f).background(color = Color.Blue),
            horizontalArrangement = Arrangement.End,
        ) {
            Text("Another Thing")
        }
    }
}

@Composable
fun TwoElementsWithOneIcon(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier.weight(4f),
            horizontalArrangement = Arrangement.Start,
        ) {
            Text(text = "Some Thing")
        }

        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(modifier = Modifier.weight(1f), text = "Another Thing")
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        }
    }
}

@Composable
fun SemanticsIcon(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.semantics(mergeDescendants = true) {},
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(modifier = Modifier.weight(1f), text = "Another Thing")
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null
        )
    }
}

@Composable
fun ScrollToOutSide(
    modifier: Modifier = Modifier,
) {
    val myItems = listOf(1, 2, 3, 4, 5, 6, 7)
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(items = myItems) { index, item ->
            Text(text = "Hello World $index $item")
        }
    }
}