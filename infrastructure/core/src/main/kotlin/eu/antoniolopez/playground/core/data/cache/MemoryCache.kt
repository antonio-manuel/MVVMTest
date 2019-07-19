package eu.antoniolopez.playground.core.data.cache

import android.content.Context
import android.content.SharedPreferences
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import eu.antoniolopez.playground.core.utils.TimeUtils
import eu.antoniolopez.playground.exceptions.InternalErrorException
import eu.antoniolopez.playground.exceptions.NotFoundException
import io.reactivex.Single

class MemoryCache<T>(
    private val context: Context,
    private val moshi: Moshi,
    private val timeUtils: TimeUtils,
    private val clazz: Class<T>
) : Cache<T> {

    companion object {
        const val FIELD_KEY = "key::field"
        const val TIMESTAMP_KEY = "key::timestsamp"
        const val NO_FIELD: String = ""
        const val NO_TIMESTAMP: Long = -1
        const val CACHE_IN_MS: Long = 86400000
    }

    private val path: String = clazz.name

    override fun updateValue(value: T) {
        val parsed: String = getJsonAdapter().toJson(value)
        getSharedPreferences()?.edit()?.apply {
            putString(FIELD_KEY, parsed)
            putLong(TIMESTAMP_KEY, timeUtils.timestampInMs())
        }?.apply()
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun getValue(): Single<T> =
        Single.create { emitter ->
            try {
                getSharedPreferences()?.let { sharedPreferences ->
                    val timestamp: Long = sharedPreferences.getLong(TIMESTAMP_KEY, NO_TIMESTAMP)
                    val raw: String? = sharedPreferences.getString(FIELD_KEY, NO_FIELD)
                    if (timeUtils.isTimeOlderThanNow(timestamp, CACHE_IN_MS) && raw.isNullOrBlank()) {
                        throw NotFoundException()
                    } else {
                        getJsonAdapter().fromJson(raw)?.let { value ->
                            emitter.onSuccess(value)
                        } ?: throw NotFoundException()
                    }
                } ?: throw InternalErrorException()
            } catch (exception: Exception) {
                emitter.onError(exception)
            }
        }

    private fun getSharedPreferences(): SharedPreferences? =
        context.getSharedPreferences(path, Context.MODE_PRIVATE)

    private fun getJsonAdapter(): JsonAdapter<T> =
        moshi.adapter<T>(clazz)
}
