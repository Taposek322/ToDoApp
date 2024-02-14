package com.taposek322.todoapp.ui

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.taposek322.todoapp.R
import com.taposek322.todoapp.data.database.ToDo
import kotlinx.coroutines.delay


@Composable
fun TaskContainer(
    toDo: ToDo,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    )
    {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            //Title
            Text(
                text = toDo.title,
                modifier = modifier,
                textAlign = TextAlign.Left,
                fontSize = 28.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )

            if (!toDo.text.isNullOrBlank()) {
                Divider(
                    modifier = modifier,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                //Text
                Text(
                    text = toDo.text,
                    modifier = modifier,
                    textAlign = TextAlign.Left,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun<T> SwipeToDelete(
    context: Context,
    item:T,
    onDelete: (T)->Unit,
    modifier: Modifier = Modifier,
    animationDuration: Int = 500,
    content: @Composable (T)->Unit,
){
    var isRemoved by remember{
        mutableStateOf(false)
    }
    val dismissState = rememberDismissState(
        confirmValueChange = {value ->
            if(value == DismissValue.DismissedToStart){
                isRemoved = true
                true
            }else{
                false
            }
        }
    )

    LaunchedEffect(key1 = isRemoved){
        if(isRemoved){
            delay(animationDuration.toLong())
            onDelete(item)
        }
    }
    
    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = animationDuration),
            shrinkTowards = Alignment.Top
        )+ fadeOut()
    ) {
        Box(
            modifier = modifier
                .padding(16.dp)
        ) {
            SwipeToDismiss(
                state = dismissState,
                background = {
                    DeleteBackground(
                        dismissState,
                        modifier = modifier,
                        context = context
                    )
                },
                dismissContent = {
                    content(item)
                },
                directions = setOf(DismissDirection.EndToStart)
            )
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteBackground(
    swipeDismissState: DismissState,
    modifier: Modifier = Modifier,
    context: Context){
    val color = if(swipeDismissState.dismissDirection == DismissDirection.EndToStart){
        Color.Red
    }else{
      Color.Transparent
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = color)
            .padding(16.dp),
        contentAlignment = Alignment.CenterEnd
    ){
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = context.getString(R.string.delete_task),
            tint = Color.White
        )
    }
}