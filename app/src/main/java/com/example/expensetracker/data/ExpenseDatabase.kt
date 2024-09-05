package com.example.expensetracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomDatabase.Callback
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.expensetracker.data.ExpenseDatabase.Companion.getDatabase
import com.example.expensetracker.data.dao.ExpenseDao
import com.example.expensetracker.data.model.ExpenseEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [ExpenseEntity::class], version = 1)
abstract class ExpenseDatabase: RoomDatabase()  {

    abstract fun expenseDao(): ExpenseDao

    companion object {
        const val DATABASE_NAME = "expense_database"

        @JvmStatic
        fun getDatabase(context: Context): ExpenseDatabase {
            return Room.databaseBuilder(
                context,
                ExpenseDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}



//.addCallback(object : Callback(){
//    override fun onCreate(db: SupportSQLiteDatabase) {
//        super.onCreate(db)
//        populatingTestData(context)
//    }
//
//    fun populatingTestData(context: Context){
//        CoroutineScope(Dispatchers.IO).launch {
//            val dao = getDatabase(context).expenseDao()
//            dao.insertExpense(ExpenseEntity(1,"Salary",5000.40,System.currentTimeMillis(),"Salary","Income"))
//            dao.insertExpense(ExpenseEntity(2,"Paypal",2000.40,System.currentTimeMillis(),"Paypal","Income"))
//            dao.insertExpense(ExpenseEntity(3,"Netflix",100.40,System.currentTimeMillis(),"Netflix","Expense"))
//            dao.insertExpense(ExpenseEntity(4,"Starbucks",220.40,System.currentTimeMillis(),"Starbucks","Expense"))
//            dao.insertExpense(ExpenseEntity(5,"Paypal",100.40,System.currentTimeMillis(),"Paypal","Income"))
//            dao.insertExpense(ExpenseEntity(6,"Salary",5000.40,System.currentTimeMillis(),"Salary","Income"))
//            dao.insertExpense(ExpenseEntity(7,"Paypal",2000.40,System.currentTimeMillis(),"Paypal","Income"))
//            dao.insertExpense(ExpenseEntity(8,"Netflix",100.40,System.currentTimeMillis(),"Netflix","Expense"))
//            dao.insertExpense(ExpenseEntity(9,"Starbucks",220.40,System.currentTimeMillis(),"Starbucks","Expense"))
//            dao.insertExpense(ExpenseEntity(10,"Paypal",100.40,System.currentTimeMillis(),"Paypal","Income"))
//        }
//    }
//})