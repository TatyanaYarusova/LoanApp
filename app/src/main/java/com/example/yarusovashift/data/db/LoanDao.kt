package com.example.yarusovashift.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.yarusovashift.data.db.model.LoanDBModel

@Dao
interface LoanDao {

    @Query("SELECT * from loans_table")
    suspend fun getLoanList(): List<LoanDBModel>

    @Query("DELETE FROM loans_table")
    suspend fun deleteLoans()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLoanList(data: List<LoanDBModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLoan(data: LoanDBModel)
}