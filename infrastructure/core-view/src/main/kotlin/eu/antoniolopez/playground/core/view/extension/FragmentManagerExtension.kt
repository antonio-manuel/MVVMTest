package eu.antoniolopez.playground.core.view.extension

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlin.reflect.KClass

fun FragmentManager.attach(@IdRes placeHolder: Int, fragment: Fragment) {
    val tag = getTag(fragment::class)
    beginTransaction()
        .replace(placeHolder, fragment, tag)
        .commitNow()
}

fun getTag(type: KClass<*>): String = type.java.name
