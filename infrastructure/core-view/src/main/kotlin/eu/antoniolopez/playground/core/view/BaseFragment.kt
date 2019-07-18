package eu.antoniolopez.playground.core.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    private val layoutResourceId by lazy {
        onRequestLayoutResourceId()
    }

    private var onFragmentReadyListener: (() -> Unit)? = null
    private var isReady = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(layoutResourceId, container, false)

    @LayoutRes
    abstract fun onRequestLayoutResourceId(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onSetupListeners()
        onViewReady(savedInstanceState)
        isReady = true
        onFragmentReadyListener?.invoke()
    }

    open fun onSetupListeners() {}

    open fun onViewReady(savedInstanceState: Bundle?) {}
}
