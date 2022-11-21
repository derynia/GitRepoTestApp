package com.gitrepotestapp.network.interactor

import com.gitrepotestapp.R
import com.gitrepotestapp.di.AppModule
import com.gitrepotestapp.di.IoDispatcher
import com.gitrepotestapp.network.ReposApi
import com.gitrepotestapp.network.model.ErrorResponse
import com.gitrepotestapp.network.model.UserRepoItem
import com.gitrepotestapp.util.Resource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class NetworkInteractor @Inject constructor(
    private val reposApi: ReposApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val resourcesProvider: AppModule.ResourcesProvider
) {
    private val defaultMessage = resourcesProvider.getString(R.string.wrong_server_response)

    private fun getErrorMessageFromErrorBody(errorBody: ResponseBody?): String {
        return try {
            val errorResponse: ErrorResponse = Gson().fromJson(
                errorBody!!.charStream(),
                object : TypeToken<ErrorResponse>() {}.type
            )
            errorResponse.message
        } catch (e: Exception) {
            defaultMessage
        }
    }
    private fun <T> getResultFromResponse(response: Response<T>?): Resource<T> {
        val body = response?.body()
        val errorBody = response?.errorBody()
        return when {
            response == null -> Resource.Error(defaultMessage)
            response.isSuccessful && body != null -> Resource.Success(body)
            errorBody != null -> Resource.Error(
                getErrorMessageFromErrorBody(errorBody)
            )
            else -> Resource.Error(response.message())
        }
    }

    suspend fun getUserReposList(userName: String): Resource<List<UserRepoItem>> =
        withContext(ioDispatcher) {
            try {
                return@withContext when (val result =
                    getResultFromResponse(reposApi.getUserRepos(userName))) {
                    is Resource.Success -> Resource.Success(result.data ?: listOf())
                    is Resource.Error -> result
                }
            } catch (ex: Exception) {
                return@withContext Resource.Error(defaultMessage)
            }
        }
}