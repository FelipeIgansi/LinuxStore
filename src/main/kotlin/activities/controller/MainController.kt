package activities.controller

import activities.AptCommandExecutor
import activities.AptPackageModel
import activities.PackageService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class MainController(
  private val packageService: PackageService,
  private val aptCommandExecutor: AptCommandExecutor,
) {
  private var _packageList = MutableStateFlow(mutableMapOf<AptPackageModel, String>())
  val packageList: StateFlow<MutableMap<AptPackageModel, String>> = _packageList

  private fun setPackageList(value: MutableMap<AptPackageModel, String>) {
    _packageList.value = value
  }

  suspend fun buscaTodosOsPacotesAPT(packageName: String, packageMap: MutableMap<AptPackageModel, String>) {
    withContext(Dispatchers.IO) {
      val key = packageService.getPackageDetails(aptCommandExecutor, packageName)
      packageMap[key] = "icons/$packageName.svg"
      setPackageList(packageMap)
    }
  }
}