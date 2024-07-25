package com.example.yarusovashift.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.yarusovashift.data.db.model.LoanDBModel

@Database(
    entities = [LoanDBModel::class],
    version = 2,
    exportSchema = false
)
abstract class LoanDataBase : RoomDatabase() {
    companion object {
        private var db: LoanDataBase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(app: Application): LoanDataBase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(app, LoanDataBase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun loanDao(): LoanDao
}