package com.ucb.app.form.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ucb.app.form.presentation.state.FormEffect
import com.ucb.app.form.presentation.state.FormEvent
import com.ucb.app.form.presentation.viewmodel.FormViewModel
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.form_auto_saved
import kotlinproject.composeapp.generated.resources.form_email_hint
import kotlinproject.composeapp.generated.resources.form_message_hint
import kotlinproject.composeapp.generated.resources.form_name_hint
import kotlinproject.composeapp.generated.resources.form_submit_btn
import kotlinproject.composeapp.generated.resources.form_submitted_ok
import kotlinproject.composeapp.generated.resources.form_title
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FormScreen(viewModel: FormViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    // Leemos los strings AQUI (contexto Composable), antes del LaunchedEffect
    // porque stringResource() es @Composable y no puede llamarse desde una coroutine
    val textSubmittedOk = stringResource(Res.string.form_submitted_ok)
    val textDraftSaved = stringResource(Res.string.form_auto_saved)

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                FormEffect.SubmitSuccess -> {
                    snackbarHostState.showSnackbar(textSubmittedOk)
                }
                is FormEffect.ShowError -> {
                    snackbarHostState.showSnackbar(effect.message)
                }
                FormEffect.DraftSaved -> {
                    snackbarHostState.showSnackbar(textDraftSaved)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A2E))
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(Res.string.form_title),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (state.lastSavedAt > 0L) {
            Text(
                text = stringResource(Res.string.form_auto_saved),
                fontSize = 12.sp,
                color = Color(0xFF4CAF50),
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.name,
            onValueChange = { viewModel.onEvent(FormEvent.OnNameChanged(it)) },
            label = { Text(stringResource(Res.string.form_name_hint), color = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Color(0xFF4FC3F7),
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color(0xFF4FC3F7)
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = state.email,
            onValueChange = { viewModel.onEvent(FormEvent.OnEmailChanged(it)) },
            label = { Text(stringResource(Res.string.form_email_hint), color = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Color(0xFF4FC3F7),
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color(0xFF4FC3F7)
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = state.message,
            onValueChange = { viewModel.onEvent(FormEvent.OnMessageChanged(it)) },
            label = { Text(stringResource(Res.string.form_message_hint), color = Color.Gray) },
            modifier = Modifier.fillMaxWidth().height(140.dp),
            maxLines = 6,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Color(0xFF4FC3F7),
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color(0xFF4FC3F7)
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.onEvent(FormEvent.OnSubmitClick) },
            enabled = !state.isLoading && !state.isSubmitted,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4FC3F7),
                contentColor = Color(0xFF1A1A2E)
            )
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(color = Color(0xFF1A1A2E), strokeWidth = 2.dp)
            } else {
                Text(
                    text = if (state.isSubmitted) "✓ Enviado"
                    else stringResource(Res.string.form_submit_btn),
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        SnackbarHost(hostState = snackbarHostState)
    }
}
