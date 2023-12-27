package com.yeslab.task.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yeslab.task.R
import com.yeslab.task.databinding.DialogMovieLayoutBinding
import com.yeslab.task.databinding.ItemMovieRowBinding
import com.yeslab.task.model.MovCharacterList
import com.yeslab.task.util.getDialog
import com.yeslab.task.util.loadImage


class MovieAdapter(
    private val context: Context,
    private var movcharacterList: ArrayList<MovCharacterList>
) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemMovieRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MovCharacterList) {
            binding.txtCharname.text = data.name
            binding.layClick.setOnClickListener {
                showDialog(data)
            }
        }
    }

    private fun showDialog(data: MovCharacterList) {
        val dialog = getDialog(context, R.layout.dialog_movie_layout)
        val binding = DialogMovieLayoutBinding.bind(dialog.findViewById(R.id.main_layout))
        binding.imageViewCharacter.loadImage(
            context,
            data.imageurl,
            R.drawable.ic_launcher_background
        )
        binding.cancel.setOnClickListener {
            dialog.dismiss()
        }
        binding.textViewBio.text ="Bio : "+data.bio
        binding.textViewName.text = "Name : "+data.name
        binding.textViewRealName.text = "Real Name : "+data.realname
        binding.textViewCreatedBy.text ="Create By : "+ data.createdby
        binding.textViewTeam.text = "Team : "+data.team
        binding.textViewFirstAppearance.text ="First Appearance : "+ data.firstappearance
        binding.textViewPublisher.text ="Publisher : "+ data.publisher
        dialog.setCancelable(true)
        dialog.show()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMovieRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movcharacterList[position])
    }

    override fun getItemCount() = movcharacterList.size

    fun updateList(newList: ArrayList<MovCharacterList>) {
        movcharacterList = newList
        notifyDataSetChanged()
    }
}
