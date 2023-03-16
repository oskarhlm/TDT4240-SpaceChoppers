package com.mygdx.spacechoppers

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class WorkingTest {
    @Test
    fun thisAlwaysPasses() {
        assertTrue(true)
    }

    @Test
    @Disabled
    fun thisIsIgnored() {
    }
}
