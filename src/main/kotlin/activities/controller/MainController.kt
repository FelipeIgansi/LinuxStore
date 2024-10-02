package activities.controller

import activities.AptPackageModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainController{
    private var _packageList  = MutableStateFlow(listOf<AptPackageModel>())
    val packageList: StateFlow<List<AptPackageModel>> = _packageList

    fun setPackageList(value: List<AptPackageModel>){
        _packageList.value = value
    }
}