package com.writer.app.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.writer.app.di.AppContainer
import com.writer.app.features.editor.EditorScreen
import com.writer.app.features.files.FilesScreen
import com.writer.app.features.home.CreateSheet
import com.writer.app.features.home.HomeScreen
import com.writer.app.features.onboarding.OnboardingScreen
import com.writer.app.features.preview.PreviewScreen
import com.writer.app.features.profile.ProfileScreen
import com.writer.app.features.settings.SettingsScreen
import com.writer.app.features.templates.TemplateDetailScreen
import com.writer.app.features.templates.TemplatesScreen
import kotlinx.coroutines.launch

@Composable
fun WriterNavHost(
    navController: NavHostController,
    container: AppContainer,
    startDestination: String
) {
    val scope = rememberCoroutineScope()
    var showCreateSheet by remember { mutableStateOf(false) }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Routes.ONBOARDING) {
            OnboardingScreen(onFinish = {
                scope.launch { container.prefs.setOnboardingCompleted(true) }
                navController.navigate(Routes.HOME) {
                    popUpTo(Routes.ONBOARDING) { inclusive = true }
                }
            })
        }

        composable(Routes.HOME) {
            HomeScreen(
                container = container,
                currentRoute = currentRoute,
                onNavigate = { navController.navigateSingleTop(it) },
                onCreateClick = { showCreateSheet = true }
            )
        }

        composable(Routes.TEMPLATES) {
            TemplatesScreen(
                container = container,
                currentRoute = currentRoute,
                onNavigate = { navController.navigateSingleTop(it) },
                onCreateClick = { showCreateSheet = true }
            )
        }

        composable(
            Routes.TEMPLATE_DETAIL,
            arguments = listOf(navArgument("templateId") { type = NavType.StringType })
        ) { entry ->
            val id = entry.arguments?.getString("templateId").orEmpty()
            TemplateDetailScreen(
                container = container,
                templateId = id,
                onBack = { navController.popBackStack() },
                onStartWriting = { templateId, _ ->
                    navController.navigate(Routes.editor("new", templateId))
                }
            )
        }

        composable(
            Routes.EDITOR,
            arguments = listOf(
                navArgument("docId") { type = NavType.StringType },
                navArgument("templateId") { type = NavType.StringType }
            )
        ) { entry ->
            val docId = entry.arguments?.getString("docId").orEmpty()
            EditorScreen(
                container = container,
                onBack = { navController.popBackStack() },
                onPreview = { id -> navController.navigate(Routes.preview(id)) }
            )
        }

        composable(
            Routes.PREVIEW,
            arguments = listOf(navArgument("docId") { type = NavType.StringType })
        ) { entry ->
            val docId = entry.arguments?.getString("docId").orEmpty()
            PreviewScreen(
                container = container,
                documentId = docId,
                onBack = { navController.popBackStack() },
                onEdit = { id -> navController.navigate(Routes.editor(id, "none")) }
            )
        }

        composable(Routes.FILES) {
            FilesScreen(
                container = container,
                currentRoute = currentRoute,
                onNavigate = { navController.navigateSingleTop(it) },
                onCreateClick = { showCreateSheet = true }
            )
        }

        composable(Routes.PROFILE) {
            ProfileScreen(
                container = container,
                currentRoute = currentRoute,
                onNavigate = { navController.navigateSingleTop(it) },
                onCreateClick = { showCreateSheet = true }
            )
        }

        composable(Routes.SETTINGS) {
            SettingsScreen(container = container, onBack = { navController.popBackStack() })
        }
    }

    if (showCreateSheet) {
        CreateSheet(
            onDismiss = { showCreateSheet = false },
            onBlank = {
                showCreateSheet = false
                navController.navigate(Routes.editor("new", "none"))
            },
            onBrowseTemplates = {
                showCreateSheet = false
                navController.navigateSingleTop(Routes.TEMPLATES)
            },
            onComplaint = {
                showCreateSheet = false
                navController.navigateSingleTop(Routes.TEMPLATES)
            },
            onPersonal = {
                showCreateSheet = false
                navController.navigateSingleTop(Routes.TEMPLATES)
            },
            onContinueRecent = {
                showCreateSheet = false
                navController.navigateSingleTop(Routes.FILES)
            }
        )
    }
}

private fun NavHostController.navigateSingleTop(route: String) {
    navigate(route) {
        launchSingleTop = true
        popUpTo(Routes.HOME) { saveState = true }
        restoreState = true
    }
}
