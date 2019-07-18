package eu.antoniolopez.playground.core.data.cache

import android.content.Context
import android.content.SharedPreferences
import arrow.core.Try
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import eu.antoniolopez.playground.core.extension.timestampInMs
import eu.antoniolopez.playground.exceptions.InternalErrorException
import eu.antoniolopez.playground.exceptions.NotFoundException

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

    override fun getValue(): Try<T> {
        val prefs: SharedPreferences = context.getSharedPreferences(path, Context.MODE_PRIVATE)
            ?: return Try.Failure(InternalErrorException())
        val timestamp: Long = prefs.getLong(TIMESTAMP_KEY, NO_TIMESTAMP)
        return if (timestamp != NO_TIMESTAMP && (timestamp - timestampInMs()) < CACHE_IN_MS) {
            Try {
                val value: String = prefs.getString(FIELD_KEY, NO_FIELD)
                val jsonAdapter: JsonAdapter<T> = moshi.adapter<T>(clazz)
                val parsed: T? = jsonAdapter.fromJson(value)
                parsed ?: throw NotFoundException()
            }
        } else {
            Try.Failure(NotFoundException())
        }
    }
}
