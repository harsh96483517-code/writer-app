package com.writer.app.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.writer.app.design.components.GlassLoadingIndicator
import com.writer.app.design.theme.WriterTheme
import com.writer.app.di.AppContainer
import com.writer.app.model.UserPreferences
import com.writer.app.navigation.Routes
import com.writer.app.navigation.WriterNavHost

/** Root composable: applies the saved theme and hosts navigation. */
@Composable
fun WriterApp(container: AppContainer) {
    val prefs by container.prefs.preferences.collectAsState(initial = UserPreferences())
    val onboardingDone by container.prefs.onboardingCompleted.collectAsState(initial = null)

    WriterTheme(themeMode = prefs.themeMode) {
        Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
            // Wait for the persisted onboarding flag before choosing a start
            // destination, so a returning user never flashes the onboarding.
            val start = when (onboardingDone) {
                null -> null
                true -> Routes.HOME
                false -> Routes.ONBOARDING
            }
            if (start != null) {
                val navController = rememberNavController()
                Box(Modifier.fillMaxSize()) {
                    WriterNavHost(
                        navController = navController,
                        container = container,
                        startDestination = start
                    )
                }
            } else {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    GlassLoadingIndicator("Writer शुरू हो रहा है...")
                }
            }
        }
    }
}
