package com.ucb.app.example.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.soft.designsystem.components.button.PrimaryButton
import com.soft.designsystem.components.divider.HorizontalDivider
import com.soft.designsystem.components.input.BasicInput
import com.soft.designsystem.theme.AppTheme

@Composable
fun DesignSystemExampleScreen() {
    var nickname by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
            .padding(16.dp)
    ) {
        Text(
            text = "Ejemplo de Design System",
            color = AppTheme.colors.primary,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        
        BasicInput(
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = "Introduce tu apodo",
            value = nickname,
            onValueChange = { nickname = it }
        )
        
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading,
            isLoading = isLoading,
            onClick = {
                // Simulación de carga
                isLoading = true
            },
            text = "Guardar cambios"
        )
        
        HorizontalDivider()
        
        Text(
            text = "Tokens actuales:",
            style = AppTheme.typography.bodyMedium,
            color = AppTheme.colors.textPrimary
        )
    }
}
