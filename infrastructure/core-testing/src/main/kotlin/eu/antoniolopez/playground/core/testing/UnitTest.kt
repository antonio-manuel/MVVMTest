package eu.antoniolopez.playground.core.testing

import io.mockk.clearAllMocks
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance

@Suppress("IllegalIdentifier")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class UnitTest {

    @BeforeEach
    fun onBefore() {
        onPrepareBeforeEachTest()
    }

    @AfterEach
    fun onAfter() {
        clearAllMocks()
    }

    open fun onPrepareBeforeEachTest() {}
}
