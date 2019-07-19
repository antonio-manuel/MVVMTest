package eu.antoniolopez.playground.core.utils

import eu.antoniolopez.playground.core.testing.UnitTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TimeUtilsTest : UnitTest() {

    private lateinit var sut: TimeUtils

    @Test
    fun `when HAS NOT passed enough time the isTimeOlderThanNow returns false`() {
        sut = TimeUtils()
        val minDifferencesInMs: Long = 2000
        val current: Long = sut.timestampInMs()

        assertEquals(false, sut.isTimeOlderThanNow(current, minDifferencesInMs))
    }

    @Test
    fun `when it HAS passed enough time the isTimeOlderThanNow returns true`() {
        sut = TimeUtils()
        val minDifferencesInMs: Long = 2000
        val current: Long = sut.timestampInMs()

        Thread.sleep(minDifferencesInMs)
        assertEquals(true, sut.isTimeOlderThanNow(current, minDifferencesInMs))
    }
}
