package org.pointyware.painteddogs.core.entities

import com.google.common.truth.Truth.assertThat
import kotlin.experimental.and
import kotlin.test.Test

/**
 */
class UuidUnitTest {

    @Test
    fun `version 4 - random uuid`() {
        val uuid = Uuid.v4()

        assertThat(uuid[6] and 0xF0.toByte()).isEqualTo(0x40.toByte())
    }
}
