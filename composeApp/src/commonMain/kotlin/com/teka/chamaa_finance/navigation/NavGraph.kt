package com.teka.chamaa_finance.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.teka.chamaa_finance.screens.about.AboutScreen
import com.teka.chamaa_finance.screens.censor.CensorScreen
import com.teka.chamaa_finance.screens.contribution.contribution_list.ContributionsScreen
import com.teka.chamaa_finance.screens.group_members.GroupMembersScreen
import com.teka.chamaa_finance.screens.group_members.forms.create_contribution.CreateContributionScreen
import com.teka.chamaa_finance.screens.group_members.forms.create_group.CreateGroupScreen
import com.teka.chamaa_finance.screens.group_members.forms.create_member.CreateMemberScreen
import com.teka.chamaa_finance.screens.home.HomeScreen
import com.teka.chamaa_finance.screens.page.CreateNotePage
import com.teka.chamaa_finance.screens.page.HomePage
import com.teka.chamaa_finance.util.Constant.EDIT_NOTES_ARGUMENT


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppDestinations.HomeScreenDestination.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(200)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(200)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(200)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(200)
            )
        },
    ) {
        composable(
            route = AppDestinations.Home.route,
        ) {
            HomePage(navController)
        }

        composable(
            route = AppDestinations.HomeScreenDestination.route,
        ) {
            HomeScreen(navController)
        }

        composable(
            route = AppDestinations.GroupMembersScreenDestination.route,
        ) {
            GroupMembersScreen(navController)
        }

        composable(
            route = AppDestinations.ContributionsScreenDestination.route,
        ) {
            ContributionsScreen(navController)
        }

        composable(
            route = AppDestinations.AboutDestination.route,
        ) {
            AboutScreen(navController)
        }

        composable(
            route = AppDestinations.CreateNotes.route,
        ) {
            CreateNotePage(navController)
        }

        composable(
            route = AppDestinations.CensorDestination.route,
        ) {
            CensorScreen(navController)
        }

        composable(
            route = AppDestinations.CreateGroupDestination.route,
        ) {
            CreateGroupScreen(navController)
        }

        composable(
            route = AppDestinations.CreateMemberDestination.route,
        ) {
            CreateMemberScreen(navController)
        }

        composable(
            route = AppDestinations.CreateContributionDestination.route,
        ) {
            CreateContributionScreen(navController)
        }

        composable(
            route = AppDestinations.EditNotes.route
        ) {
            val noteId = navController.previousBackStackEntry?.savedStateHandle?.get<Long>(EDIT_NOTES_ARGUMENT)
            CreateNotePage(
                navController,
                isEditNote = true,
                noteId = noteId
            )
        }

    }
}