package com.example.foodscanner.data

<<<<<<< Updated upstream:app/src/main/java/com/example/foodscanner/models.kt
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

=======
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "foods_table")
>>>>>>> Stashed changes:app/src/main/java/com/example/foodscanner/data/models.kt
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val latestScanDate: String,
    val description: String,
<<<<<<< Updated upstream:app/src/main/java/com/example/foodscanner/models.kt
    val imageId: Int
)


@Entity(tableName = "food_table")
data class ScannedFood (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "latestScanDate") val latestScanDate: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "image") val image: Int




    //@ColumnInfo(name = "first_name") val firstName: String?,
    //@ColumnInfo(name = "last_name") val lastName: String?

=======
    val imageUrl: String
>>>>>>> Stashed changes:app/src/main/java/com/example/foodscanner/data/models.kt
)