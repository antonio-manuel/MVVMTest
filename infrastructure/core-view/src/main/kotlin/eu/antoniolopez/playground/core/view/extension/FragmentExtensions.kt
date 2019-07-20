package eu.antoniolopez.playground.core.view.extension

import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment

fun Fragment.getCompatColor(@ColorRes color: Int): Int =
    requireContext().getCompatColor(color)
