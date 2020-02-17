package com.demo.test.noteapp.view.activity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

import android.widget.EditText
import com.demo.test.noteapp.R
import com.demo.test.noteapp.databinding.ActivityNewNoteBinding
import com.demo.test.noteapp.handler.AddNewNoteClickHandler
import com.demo.test.noteapp.model.NoteData
import com.demo.test.noteapp.util.Constant
import com.demo.test.noteapp.viewmodel.AllNoteViewModel
import kotlinx.android.synthetic.main.activity_new.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * This class is use for Add Update ,Delete Note
 */
class AddUpdateNoteActivity : AppCompatActivity() , View.OnClickListener{
    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding:ActivityNewNoteBinding=DataBindingUtil.setContentView(this,R.layout.activity_new)
        val cDate = Date()
        val fDate: String = SimpleDateFormat("MM/dd/yyyy hh:mm").format(cDate)
       var handler=AddNewNoteClickHandler(this)
        binding.handler=handler
        var data=NoteData()
        binding.etDate.setOnClickListener(this)
        binding.isUpdate = intent.hasExtra(Constant.PURPOSE)
        if(intent.hasExtra(Constant.PURPOSE)) {
            if (intent.hasExtra("note_id")) {
                var noteId = intent.getStringExtra("note_id")
                var noteModel: AllNoteViewModel? =
                    ViewModelProviders.of(this).get(AllNoteViewModel::class.java)
                var note = noteModel!!.getNote(noteId)
                note!!.observe(this, android.arch.lifecycle.Observer { note ->

                    note?.let {
                        data.noteid=noteId
                        data.date=it.date!!+" "+it.time
                        data.description=it.note
                        data.title=it.title
                    }

                })

            }
        }else{
            data.date=fDate

        }

        binding.data=data

    }



    private fun setDate(etDemoDate: EditText) {
        val cal = Calendar.getInstance()
        val calmin = Calendar.getInstance()
        cal.time = calmin.time
        cal.add(Calendar.DATE, -1)
        val mMinYear = calmin[Calendar.YEAR]
        val mMinMonth = calmin[Calendar.MONTH]
        val mMinDay = calmin[Calendar.DAY_OF_MONTH]
        val mDatePicker = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val mymoonth_start = month + 1
                var data=""
                if (mymoonth_start < 10) {
                    data="0$mymoonth_start/$dayOfMonth/$year"
                   // etDemoDate.setText("0$mymoonth_start/$dayOfMonth/$year")
                    showDateTimePicker(etDemoDate,data)
                } else {
                    data="$mymoonth_start/$dayOfMonth/$year"
                    showDateTimePicker(etDemoDate,data)
                   // etDemoDate.setText("$mymoonth_start/$dayOfMonth/$year")
                }


            },
            cal[Calendar.YEAR],
            cal[Calendar.MONTH],
            cal[Calendar.DAY_OF_MONTH]
        )

        mDatePicker.datePicker.minDate = calmin.timeInMillis - 1000

        mDatePicker.show()
    }

    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.etDate->{
                setDate(etDate)
            }
        }
    }

    fun showDateTimePicker(editTextTime: EditText,dateStr:String) {
        val currentDate = Calendar.getInstance()
        val date = Calendar.getInstance()
        // Log.e("calender date", "date " +Calendar.newInstance());
        TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                var hourOfDay = hourOfDay
                date[Calendar.HOUR_OF_DAY] = hourOfDay
                date[Calendar.MINUTE] = minute
                val AM_PM: String
                val myMinut: String
                val myHour: String

                  var  starTime = "$hourOfDay:$minute"

                AM_PM = if (hourOfDay < 12) {
                    "AM"
                } else if (hourOfDay == 12) {
                    "AM"
                } else {
                    "PM"
                }
                if (hourOfDay > 12) {
                    hourOfDay = hourOfDay - 12
                } else if (hourOfDay == 12) {
                    hourOfDay = 0
                }
                myHour = if (hourOfDay < 10) {
                    "0$hourOfDay"
                } else {
                    hourOfDay.toString()
                }
                myMinut = if (minute < 10) {
                    "0$minute"
                } else {
                    minute.toString()
                }
               // editTextTime.setText("$dateStr $myHour : $myMinut $AM_PM")
                editTextTime.setText("$dateStr  $starTime")
                // editTextTime.setText(dateType.getTime());
                // Log.e("Time", "The choosen one " + date.getTime());
            }, currentDate[Calendar.HOUR_OF_DAY], currentDate[Calendar.MINUTE], true
        ).show()
    }
}