package com.getstarted.flower.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.getstarted.flower.utils.Constants.DATABASE_TABLE

@Entity(tableName = DATABASE_TABLE)
data class Plant(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val plantId: String,
    val name: String,
    val description: String,
    val growZoneNumber: String,
    val wateringInterval: String = "7", // how often the plant should be watered, in days
    val imageUrl: String = ""
) {
    /**
     * Determines if the plant should be watered.  Returns true if [since]'s date > date of last
     * watering + watering Interval; false otherwise.
     */
    /*fun shouldBeWatered(since: Calendar, lastWateringDate: Calendar) =
        since > lastWateringDate.apply { add(Calendar.DAY_OF_YEAR, wateringInterval) }

    override fun toString() = name*/
}