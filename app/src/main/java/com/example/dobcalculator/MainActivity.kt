package com.example.dobcalculator

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.dobcalculator.R.id.textview_age_in_minutes
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private var textVSelectedDate: TextView? = null
    private var textViewInMinutes: TextView? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateButton: Button = findViewById(R.id.button_date)
        textVSelectedDate = findViewById(R.id.textv_selected_date)
        textViewInMinutes = findViewById(R.id.textview_age_in_minutes)

        button_date.setOnClickListener {
           clickDatePicker()
        }
    }

    private fun clickDatePicker(){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, SelectedYear, SelectedMonth, dayOfMonth ->
                Toast.makeText(this,"Year: $SelectedYear, month: ${SelectedMonth + 1}, day: $dayOfMonth",
                    Toast.LENGTH_SHORT).show()

                val selectedDate = "$dayOfMonth/${SelectedMonth + 1}/$SelectedYear"

                textVSelectedDate?.text = selectedDate  // makes the text the selected date

                val setDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = setDateFormat.parse(selectedDate)  // converting it to the format we want to use

                theDate?.let {
                    val selectedDateInMinutes = theDate.time/60000  // theDate.let can be removed, it just means if its not empty then execute the code

                    val currentTime = setDateFormat.parse(setDateFormat.format(System.currentTimeMillis()))

                    currentTime?.let {
                        val currentTimeInMinutes = currentTime.time / 60000  // for hours * 60 = 3600000

                        val differenceInDate = currentTimeInMinutes - selectedDateInMinutes

                        textViewInMinutes?.text = differenceInDate.toString()
                    }
                }
            }, year, month, day)

        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 86400000
            datePickerDialog.show()
    }
}