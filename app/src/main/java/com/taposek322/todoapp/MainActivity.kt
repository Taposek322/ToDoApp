package com.taposek322.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.taposek322.todoapp.ui.AddTaskDialog
import com.taposek322.todoapp.ui.TasksList
import com.taposek322.todoapp.ui.theme.ToDoAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel :ToDoViewModel by viewModels()
        setContent {
            var showDialog by rememberSaveable {
                mutableStateOf(false)
            }
            ToDoAppTheme {
                Scaffold(
                    bottomBar = {
                        BottomAppBar(
                            modifier = Modifier
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center
                            ) {
                                IconButton(
                                    onClick = {
                                        showDialog = true
                                    },
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Add,
                                        contentDescription = getString(R.string.add_task)
                                    )
                                }
                            }
                        }
                    }
                ) {innerPadding->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = innerPadding.calculateBottomPadding()),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Column {
                            TasksList(
                                modifier = Modifier,
                                viewModel = viewModel,
                                context = LocalContext.current
                            )

                        }
                        if (showDialog) {
                            AddTaskDialog(
                                onDismissDialog = { showDialog = false },
                                viewModel = viewModel,
                                context = LocalContext.current,
                                modifier = Modifier
                            )
                        }
                    }
                }
            }
        }
    }
}

