package com.example.sarinoorderingsystem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.DatePickerDialog
import android.widget.EditText
import java.text.SimpleDateFormat
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.AdapterView
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var editTextDate: EditText
    private val calendar: Calendar = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val paymentModes = arrayOf("Cash on Delivery", "Online Payment", "Promotional Voucher")

        val spinnerModeOfPayment: Spinner = findViewById(R.id.spinnerModeOfPayment)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, paymentModes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerModeOfPayment.adapter = adapter

        spinnerModeOfPayment.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedItemView: android.view.View?,
                position: Int,
                id: Long
            ) {
                val selectedMode = paymentModes[position]

            }

            override fun onNothingSelected(parentView: AdapterView<*>) {

            }
        }

        editTextDate = findViewById(R.id.editTextDate)

        editTextDate.setOnClickListener {
            showDatePickerDialog()
        }
    }



    private fun showDatePickerDialog() {
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateEditText()
        }

        DatePickerDialog(
            this,
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun updateEditText() {
        val myFormat = "MM/dd/yyyy" // choose your desired date format
        val sdf = SimpleDateFormat(myFormat, Locale.US)

        editTextDate.setText(sdf.format(calendar.time))
    }
}