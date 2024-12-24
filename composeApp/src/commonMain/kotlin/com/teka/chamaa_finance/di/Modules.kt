package com.teka.chamaa_finance.di

import com.teka.chamaa_finance.data_layer.datasource.def.ContributionLocalDataSource
import com.teka.chamaa_finance.data_layer.datasource.def.GroupLocalDataSource
import com.teka.chamaa_finance.data_layer.datasource.def.MemberLocalDataSource
import com.teka.chamaa_finance.data_layer.datasource.impl.GroupLocalDataSourceImpl
import com.teka.chamaa_finance.data_layer.datasource.def.NoteLocalDataSource
import com.teka.chamaa_finance.data_layer.datasource.impl.ContributionLocalDataSourceImpl
import com.teka.chamaa_finance.data_layer.datasource.impl.MemberLocalDataSourceImpl
import com.teka.chamaa_finance.data_layer.datasource.impl.NoteLocalDataSourceImpl
import com.teka.chamaa_finance.data_layer.repository_impl.ContributionRepositoryImpl
import com.teka.chamaa_finance.data_layer.repository_impl.GroupRepositoryImpl
import com.teka.chamaa_finance.data_layer.repository_impl.MemberRepositoryImpl
import com.teka.chamaa_finance.domain.repositories.NoteRepository
import com.teka.chamaa_finance.data_layer.repository_impl.NoteRepositoryImpl
import com.teka.chamaa_finance.domain.repositories.ContributionRepository
import com.teka.chamaa_finance.domain.repositories.GroupRepository
import com.teka.chamaa_finance.domain.repositories.MemberRepository
import com.teka.chamaa_finance.domain.usecase.CreateNoteUseCase
import com.teka.chamaa_finance.domain.usecase.DeleteNoteUseCase
import com.teka.chamaa_finance.domain.usecase.GetAllNotesUseCase
import com.teka.chamaa_finance.domain.usecase.GetNoteUseCase
import com.teka.chamaa_finance.domain.usecase.UpdateNoteUseCase
import com.teka.chamaa_finance.networking.ApiService
import com.teka.chamaa_finance.networking.InsultCensorClient
import com.teka.chamaa_finance.screens.censor.CensorViewModel
import com.teka.chamaa_finance.screens.contribution.contribution_list.ContributionListViewModel
import com.teka.chamaa_finance.screens.group_members.forms.create_contribution.CreateContributionViewModel
import com.teka.chamaa_finance.screens.group_members.forms.create_group.CreateGroupViewModel
import com.teka.chamaa_finance.screens.group_members.forms.create_member.CreateMemberViewModel
import com.teka.chamaa_finance.screens.group_members.tabs.groups.GroupsTabViewModel
import com.teka.chamaa_finance.screens.group_members.tabs.members.MembersTabViewModel
import com.teka.chamaa_finance.screens.viewmodel.CreateNoteViewModel
import com.teka.chamaa_finance.screens.viewmodel.HomeViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module


val provideDataSourceModule = module {
    singleOf(::NoteLocalDataSourceImpl).bind(NoteLocalDataSource::class)
    singleOf(::GroupLocalDataSourceImpl).bind(GroupLocalDataSource::class)
    singleOf(::MemberLocalDataSourceImpl).bind(MemberLocalDataSource::class)
    singleOf(::ContributionLocalDataSourceImpl).bind(ContributionLocalDataSource::class)
}

val provideRepositoryModule = module {
    singleOf(::NoteRepositoryImpl).bind(NoteRepository::class)
    singleOf(::GroupRepositoryImpl).bind(GroupRepository::class)
    singleOf(::MemberRepositoryImpl).bind(MemberRepository::class)
    singleOf(::ContributionRepositoryImpl).bind(ContributionRepository::class)
}

val provideViewModelModule = module {
    viewModelOf(::CreateNoteViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::CensorViewModel)
    viewModelOf(::CreateGroupViewModel)
    viewModelOf(::CreateMemberViewModel)
    viewModelOf(::CreateContributionViewModel)
    viewModelOf(::GroupsTabViewModel)
    viewModelOf(::MembersTabViewModel)
    viewModelOf(::ContributionListViewModel)
}

val provideUseCaseModule = module {
    singleOf(::CreateNoteUseCase)
    singleOf(::GetAllNotesUseCase)
    singleOf(::DeleteNoteUseCase)
    singleOf(::UpdateNoteUseCase)
    singleOf(::GetNoteUseCase)
}

val provideKtorClient = module {
    singleOf(::InsultCensorClient)
}

val provideApiService = module {
    singleOf(::ApiService)
}


