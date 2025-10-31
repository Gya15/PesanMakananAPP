package lat.pam.pesanmakananapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lat.pam.pesanmakananapp.databinding.ItemLocationBinding

class LocationAdapter(
    private val locations: List<Location>,
    private val onItemClick: (Location) -> Unit
) : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    inner class LocationViewHolder(val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding = ItemLocationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LocationViewHolder(binding)
    }

    override fun getItemCount() = locations.size

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location = locations[position]
        holder.binding.tvCity.text = location.city
        holder.binding.tvProvince.text = location.province

        holder.itemView.setOnClickListener {
            onItemClick(location)
        }
    }
}