package com.example.alterinminuten

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDataPicker.setOnClickListener{ view ->
            onClickDatePicker(view)
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun onClickDatePicker(view:View){
        //Current Date
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        var month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)


        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                    datePicker, year, month, dayOfMonth ->
                Toast.makeText(this,"Geburtsdatum: $dayOfMonth.${month + 1}.$year",Toast.LENGTH_LONG).show()
                var selectedDate = "$dayOfMonth.${month + 1}.$year"
                tvSelectedDate.setText(selectedDate)
                var sdf = SimpleDateFormat("dd.mm.yyyy", Locale.GERMAN)
                val sdfDate = sdf.parse(selectedDate)

                val selectedDateToMinute = sdfDate!!.time / 60000   //calculate Tag in Statt minuten : 86400000 statt 60000
                var currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateToMinute = currentDate!!.time / 60000
                var differenceInMinute = currentDateToMinute-selectedDateToMinute
                tvSelectedDateMinutes.setText(differenceInMinute.toString())

            },year,month,day).show()
    }
}