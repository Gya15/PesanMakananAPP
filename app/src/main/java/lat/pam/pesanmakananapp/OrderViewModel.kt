package lat.pam.pesanmakananapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Location(val city: String, val province: String) {
    override fun toString(): String {
        return "$city, $province"
    }
}

class OrderViewModel : ViewModel() {
    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    fun setUsername(name: String) {
        _username.value = name
    }

    private val _orderList = MutableLiveData<List<MenuItem>>(emptyList())
    val orderList: LiveData<List<MenuItem>> = _orderList

    fun addItemToOrder(menuItem: MenuItem) {
        val currentList = _orderList.value ?: emptyList()
        _orderList.value = currentList + menuItem
    }

    fun clearOrder() {
        _orderList.value = emptyList()
    }

    private val _selectedLocation = MutableLiveData<Location>()
    val selectedLocation: LiveData<Location> = _selectedLocation

    init {
        _selectedLocation.value = Location("Cimahi", "Jawa Barat")
    }

    fun setSelectedLocation(location: Location) {
        _selectedLocation.value = location
    }
}