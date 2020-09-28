package com.example.madlevel2task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ItemQuestionBinding
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_question.view.*

    class QuestionsAdapter(private val questions: List<Questions>) : RecyclerView.Adapter<QuestionsAdapter.ViewHolder>(){


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = ItemQuestionBinding.bind(itemView)

        fun databind(questions: Questions) {
            binding.tvQuestion.text = questions.questionText
        }
    }

        //Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
            )
        }

        //Returns the size of the list
        override fun getItemCount(): Int {
            return questions.size
        }

        //Called by RecyclerView to display the data at the specified position.
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.databind(questions[position])
        }
    }