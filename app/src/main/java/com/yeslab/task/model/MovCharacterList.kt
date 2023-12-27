package com.yeslab.task.model

import java.io.Serializable

data class MovCharacterList(
    val name: String,
    val realname: String,
    val team: String,
    val firstappearance: String,
    val createdby: String,
    val publisher: String,
    val imageurl: String,
    val bio: String
) : Serializable


