package com.gitrepotestapp.di

import android.app.Application
import android.content.Context
import androidx.annotation.StringRes
import com.gitrepotestapp.db.ReposDb
import com.gitrepotestapp.db.mapper.RepoToDownloadedRepoMapper
import com.gitrepotestapp.db.repository.DownloadedRepository
import com.gitrepotestapp.network.ReposApi
import com.gitrepotestapp.network.interactor.NetworkInteractor
import com.gitrepotestapp.network.usecase.UserReposUseCase
import com.gitrepotestapp.util.ReposDownLoadManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val SERVER_MAIN = "https://api.github.com"

    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(SERVER_MAIN)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp)
        .build()

    @Singleton
    @Provides
    fun provideRetrofitClient(): ReposApi {
        return retrofit.create(ReposApi::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = okHttp

    @Singleton
    class ResourcesProvider @Inject constructor(
        @ApplicationContext private val context: Context
    ) {
        fun getString(@StringRes stringResId: Int): String {
            return context.getString(stringResId)
        }
    }

    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Singleton
    fun getDatabase(@ApplicationContext context: Context): ReposDb =
        ReposDb.getInstance(context)

    @Provides
    @Singleton
    fun provideDownloadedRepository(reposDb: ReposDb) = DownloadedRepository(reposDb)

    @Provides
    @Singleton
    fun provideDownLoadManager(application: Application) = ReposDownLoadManager(application)

    @Provides
    fun repoToDownloadedRepoMapper(): RepoToDownloadedRepoMapper = RepoToDownloadedRepoMapper()

    @Provides
    @Singleton
    fun provideUserRepoUseCase(networkInteractor: NetworkInteractor): UserReposUseCase =
        UserReposUseCase(networkInteractor)
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher
