package org.pointyware.painteddogs.core.navigation

import androidx.navigation.NavType
import androidx.savedstate.SavedState
import androidx.savedstate.read
import androidx.savedstate.write
import kotlin.reflect.typeOf
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 *
 * TODO: replace Strings with Uuid when bug that ignores typeMap parameter is resolved
 */
@OptIn(ExperimentalUuidApi::class)
fun navTypeMap() = mapOf(
    typeOf<Uuid>() to object: NavType<Uuid>(false) {
        override fun get(
            bundle: SavedState,
            key: String
        ): Uuid? {
            return bundle.read {
                Uuid.parse(getString(key))
            }
        }

        override fun parseValue(value: String): Uuid {
            return Uuid.parse(value)
        }

        override fun put(
            bundle: SavedState,
            key: String,
            value: Uuid
        ) {
            bundle.write {
                putString(key, value.toString())
            }
        }
    }
)
