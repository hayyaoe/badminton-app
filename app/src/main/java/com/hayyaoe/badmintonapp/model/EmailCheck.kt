package com.hayyaoe.badmintonapp.model

import kotlinx.serialization.Serializable

@Serializable
data class EmailCheck(
    val isValid: Boolean = false
)