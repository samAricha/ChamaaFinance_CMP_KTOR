package com.teka.chamaa_finance.navigation

sealed class AppDestinations(val route: String) {
    object Home : AppDestinations("home_page")
    object AboutDestination : AppDestinations("about_destination")
    object HomeScreenDestination : AppDestinations("home_screen_destination")
    object GroupMembersScreenDestination : AppDestinations("group_members_screen_destination")
    object ContributionsScreenDestination : AppDestinations("contributions_screen_destination")
    object CreateNotes : AppDestinations("create_note")
    object EditNotes : AppDestinations("edit_note")
    object CensorDestination : AppDestinations("censor_destination")
    object CreateGroupDestination : AppDestinations("create_group_destination")
    object CreateMemberDestination : AppDestinations("create_member_destination")
    object CreateContributionDestination : AppDestinations("create_contribution_destination")
}