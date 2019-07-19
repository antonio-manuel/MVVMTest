package eu.antoniolopez.playground.presentation.viewmodel

import eu.antoniolopez.playground.core.testing.UnitTest
import org.junit.Assert
import org.junit.jupiter.api.Test

class MainViewModelTest : UnitTest() {
    private lateinit var sut: MainViewModel

    @Test
    fun `when created then immediately change state to a navigation command`() {
        sut = MainViewModel()

        Assert.assertTrue(sut.state.value is MainState.Navigate)
    }
}
