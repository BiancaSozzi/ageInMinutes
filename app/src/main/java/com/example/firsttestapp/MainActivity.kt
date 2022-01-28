package com.example.firsttestapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvTimeInMinutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.button_date_picker)
        tvSelectedDate = findViewById(R.id.tv_selected_date)
        tvTimeInMinutes = findViewById(R.id.time_in_minutes)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, year, month, day ->
                val selectedDate = "$day/${month +1}/$year"
                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val date = sdf.parse(selectedDate) // Convert to date

                val selectedDateInMinutes = date?.time?.div(60000)

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))?.time?.div(
                    60000
                )

                val differenceInMinutes = selectedDateInMinutes?.let { currentDate?.minus(it) }

                tvTimeInMinutes?.text = differenceInMinutes.toString()
            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show()
    }

}