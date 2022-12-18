package com.example.agencycomposeapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.agencycomposeapp.room.daos.AgencyDao
import com.example.agencycomposeapp.room.entities.Agency
import com.example.agencycomposeapp.room.entities.Client
import com.example.agencycomposeapp.room.entities.Director
import com.example.agencycomposeapp.room.entities.Flat
import com.example.agencycomposeapp.room.relations.ClientsFlatCrossRef

@Database(
    entities = [
        Agency::class,
        Client::class,
        Director::class,
        Flat::class,
        ClientsFlatCrossRef::class
    ],
    version = 11
)

abstract class AgencyDatabase : RoomDatabase() {

    abstract val agencyDao: AgencyDao

    companion object {
        @Volatile
        private var INSTANCE: AgencyDatabase? = null

        fun getInstance(context: Context): AgencyDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AgencyDatabase::class.java,
                    "agency_db"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build().also {
                        INSTANCE = it
                    }
            }
        }
    }
}