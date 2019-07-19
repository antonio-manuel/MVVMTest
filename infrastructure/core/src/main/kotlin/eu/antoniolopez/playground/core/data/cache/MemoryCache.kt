package eu.antoniolopez.playground.core.data.cache

import android.content.Context
import android.content.SharedPreferences
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import eu.antoniolopez.playground.core.extension.timestampInMs
import eu.antoniolopez.playground.exceptions.InternalErrorException
import eu.antoniolopez.playground.exceptions.NotFoundException
import io.reactivex.Single

class MemoryCache<T>(
    private val context: Context,
    private val moshi: Moshi,
    private val clazz: Class<T>
) : Cache<T> {

    companion object {
        const val FIELD_KEY = "key::field"
        const val TIMESTAMP_KEY = "key::timestsamp"
        const val NO_FIELD: String = ""
        const val NO_TIMESTAMP: Long = -1
        const val CACHE_IN_MS = 86400000
    }

    private val path: String = clazz.name

    override fun updateValue(value: T) {
        val jsonAdapter: JsonAdapter<T> = moshi.adapter<T>(clazz)
        val parsed: String = jsonAdapter.toJson(value)
        context.getSharedPreferences(path, Context.MODE_PRIVATE)?.edit()?.apply {
            putString(FIELD_KEY, parsed)
            putLong(TIMESTAMP_KEY, timestampInMs())
        }?.apply()
    }

    override fun getValue(): Single<T> =
        Single.create { emitter ->
            try {
                val prefs: SharedPreferences = context.getSharedPreferences(path, Context.MODE_PRIVATE)
                    ?: throw InternalErrorException()
                val timestamp: Long = prefs.getLong(TIMESTAMP_KEY, NO_TIMESTAMP)
                if (timestamp != NO_TIMESTAMP && (timestamp - timestampInMs()) < CACHE_IN_MS) {
                    prefs.getString(FIELD_KEY, NO_FIELD)?.let { raw ->
                        val jsonAdapter: JsonAdapter<T> = moshi.adapter<T>(clazz)
                        jsonAdapter.fromJson(raw)?.let { value ->
                            emitter.onSuccess(value)
                        } ?: throw NotFoundException()
                    } ?: throw NotFoundException()
                } else {
                    throw NotFoundException()
                }
            } catch (exception: Exception) {
                emitter.onError(exception)
            }
        }
}
