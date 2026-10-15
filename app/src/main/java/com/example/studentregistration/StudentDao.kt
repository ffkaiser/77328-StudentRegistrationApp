package com.example.studentregistration

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDao {

    // Add a new student and return their generated ID
    @Insert
    suspend fun insertStudent(student: Student): Long

    // Search for a student using their ID
    @Query("SELECT * FROM students WHERE id = :studentId LIMIT 1")
    suspend fun findStudent(studentId: Int): Student?
}