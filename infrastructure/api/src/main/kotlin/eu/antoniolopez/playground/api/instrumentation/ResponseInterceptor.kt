package eu.antoniolopez.playground.api.instrumentation

import eu.antoniolopez.playground.exceptions.*
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.net.HttpURLConnection

class ResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (!response.isSuccessful) {
            Timber.d("Response not successful :${response.code}, ${response.message}")
            when (response.code) {
                HttpURLConnection.HTTP_NOT_FOUND -> throw NotFoundException(response.message)
                HttpURLConnection.HTTP_FORBIDDEN -> throw ForbiddenException(response.message)
                HttpURLConnection.HTTP_INTERNAL_ERROR -> throw InternalErrorException(response.message)
                HttpURLConnection.HTTP_UNAUTHORIZED -> throw UnauthorizedException(response.message)
                HttpURLConnection.HTTP_CONFLICT -> throw ConflictException(response.message)
                HttpURLConnection.HTTP_BAD_REQUEST -> throw BadRequestException(response.message)
                else -> throw NetworkException()
            }
        }
        return response
    }
}
