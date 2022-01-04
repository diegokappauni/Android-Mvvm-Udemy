package com.example.convidados.service.repository

import android.content.ContentValues
import android.content.Context
import com.example.convidados.service.constants.DatabaseConstants
import com.example.convidados.service.model.GuestModel

class GuestRepository private constructor(context: Context){
    private var mGuestDataBaseHelper: GuestDataBaseHelper = GuestDataBaseHelper(context)
    // Singleton
    companion object {
        private lateinit var repository: GuestRepository
        fun getInstance(context: Context): GuestRepository{
            if(::repository.isInitialized){
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    //CRUD
    fun save(guest: GuestModel): Boolean{
        return try {
            val db = mGuestDataBaseHelper.writableDatabase
            val contentValues = ContentValues()
            contentValues.put(DatabaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DatabaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)
            db.insert(DatabaseConstants.GUEST.TABLE_NAME, null, contentValues)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun update(guest: GuestModel) : Boolean{

        return try {
            val db = mGuestDataBaseHelper.writableDatabase
            val contentValues = ContentValues()
            contentValues.put(DatabaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DatabaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)
            val selection = DatabaseConstants.GUEST.COLUMNS.ID + "= ?"
            val args = arrayOf(guest.id.toString())
            db.update(DatabaseConstants.GUEST.TABLE_NAME, contentValues, selection , args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun delete(id: Int) : Boolean{
        return try {
            val db = mGuestDataBaseHelper.writableDatabase

            val selection = DatabaseConstants.GUEST.COLUMNS.ID + "= ?"
            val args = arrayOf(id.toString())
            db.delete(DatabaseConstants.GUEST.TABLE_NAME, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }



    fun getAll() : List<GuestModel>{
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getPresent() : List<GuestModel>{
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getAbsent() : List<GuestModel>{
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }



}