package activities.controller

import activities.AptPackageModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainController{
    private var _packageList  = MutableStateFlow(mapOf<AptPackageModel, String>())
    val packageList: StateFlow<Map<AptPackageModel, String>> = _packageList

    fun setPackageList(value: Map<AptPackageModel, String>){
        _packageList.value = value
    }
}