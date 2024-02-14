package com.taposek322.todoapp.ui

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.taposek322.todoapp.R
import com.taposek322.todoapp.ToDoViewModel
import com.taposek322.todoapp.data.database.ToDo

@Composable
fun AddTaskDialog(
    onDismissDialog: ()->Unit,
    viewModel: ToDoViewModel,
    modifier: Modifier = Modifier,
    context:Context
){
    Dialog(
        onDismissRequest = { onDismissDialog()}
        ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(16.dp),
        ) {
            Column(
                modifier = modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = context.getString(R.string.add_task),
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 28.sp
                )
                Divider(
                    modifier = modifier,
                    color = MaterialTheme.colorScheme.primary,
                    thickness = 3.dp
                )
                Text(
                    text = context.getString(R.string.title),
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    textAlign = TextAlign.Left,
                    fontSize = 20.sp
                )

                var title by rememberSaveable { mutableStateOf("") }
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { context.getString(R.string.enter_title) },
                    modifier = modifier
                        .fillMaxWidth(),
                    textStyle = TextStyle.Default.copy(fontSize = 20.sp)
                )
                Text(
                    text = context.getString(R.string.text),
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    textAlign = TextAlign.Left,
                    fontSize = 20.sp
                )
                var text by rememberSaveable { mutableStateOf("") }
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { context.getString(R.string.enter_text) },
                    modifier = modifier
                        .fillMaxWidth(),
                    textStyle = TextStyle.Default.copy(fontSize = 20.sp)
                )
                Column(
                    modifier = modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            viewModel.insertTask(ToDo(title, text))
                            onDismissDialog()
                                  },
                        modifier = modifier
                            .padding(6.dp)
                    ) {
                        Text(
                            text = context.getString(R.string.add_task),
                            modifier = modifier
                                .padding(6.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }
}