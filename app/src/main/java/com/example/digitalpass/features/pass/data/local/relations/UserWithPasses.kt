package com.example.digitalpass.features.pass.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.digitalpass.features.pass.data.local.entities.Pass
import com.example.digitalpass.features.pass.data.local.entities.User

class UserWithPasses (
    @Embedded val user: User,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "user_id"
    )
    val passes: List<Pass>
)