package com.demo.test.noteapp.view.activity

import android.app.DatePickerDialog
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
import com.demo.test.noteapp.viewmodel.EditNoteViewModel
import kotlinx.android.synthetic.main.activity_new.*
import java.text.SimpleDateFormat
import java.util.*

class NewNoteActivity : AppCompatActivity() , View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding:ActivityNewNoteBinding=DataBindingUtil.setContentView(this,R.layout.activity_new)
        val cDate = Date()
        val fDate: String = SimpleDateFormat("MM/dd/yyyy").format(cDate)
       var handler=AddNewNoteClickHandler(this)
        binding.handler=handler
        var data=NoteData()
        binding.etDate.setOnClickListener(this)
        binding.isUpdate = intent.hasExtra(Constant.PURPOSE)
        if(intent.hasExtra(Constant.PURPOSE)) {
            if (intent.hasExtra("note_id")) {
                var noteId = intent.getStringExtra("note_id")
                var noteModel: EditNoteViewModel? =
                    ViewModelProviders.of(this).get(EditNoteViewModel::class.java)
                var note = noteModel!!.getNote(noteId)
                note!!.observe(this, android.arch.lifecycle.Observer { note ->

                    note?.let {
                        data.noteid=noteId
                        data.date=it!!.date!!
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
                if (mymoonth_start < 10) {
                    etDemoDate.setText("0$mymoonth_start/$dayOfMonth/$year")
                } else {
                    etDemoDate.setText("$mymoonth_start/$dayOfMonth/$year")
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
}