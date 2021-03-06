package com.wxsoft.fcare.data.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.wxsoft.fcare.data.entity.Patient
import io.reactivex.Flowable

@Dao
interface PatientDao{
    @Query("SELECT * FROM patients order by attack_Time desc")
    fun getAll(): Flowable<List<Patient>>

    @Query("SELECT * FROM patients where id =:id")
    fun getOne(id:String): Flowable<Patient>

    @Query("SELECT * FROM patients WHERE id IN (:patientIds)")
    fun loadAllByIds(patientIds: IntArray): List<Patient>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg patients: Patient)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(patients: List<Patient>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(patient: Patient)

    @Delete
    fun delete(patient: Patient)
}