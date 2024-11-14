package activities.controller

import activities.AptCommandExecutor
import activities.AptPackageModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProgramListItemController {

  private var _selectedPackage = MutableStateFlow(AptPackageModel())

  private var _showProgressBar = MutableStateFlow(false)
  val showProgressBar: StateFlow<Boolean> = _showProgressBar

  private var _buttonIsEnable = MutableStateFlow(false)
  val buttonIsEnable: StateFlow<Boolean> = _buttonIsEnable

  private var _installationIcon = MutableStateFlow("download.png")
  val installationIcon: StateFlow<String> = _installationIcon

  private val aptCommandExecutor = AptCommandExecutor()

  private val ioScope = CoroutineScope(Dispatchers.IO)


  fun installPackage() {
    if (!aptCommandExecutor.isPackageInstalled(_selectedPackage.value.packageName)) {
      setShowProgressBar(true)
      ioScope.launch {
        aptCommandExecutor.installPackage(_selectedPackage.value.packageName)
        setShowProgressBar(false)
        updateState(isInstalled = true)
      }
    } else {
      updateState(isInstalled = false)
    }
  }

  fun updateButtonState(aptPackageModel: AptPackageModel) {
    _buttonIsEnable.value = aptCommandExecutor.isPackageInstalled(aptPackageModel.packageName)
    _installationIcon.value = chooseTheIcon(_buttonIsEnable.value)
  }

  private fun updateState(isInstalled: Boolean) {
    _buttonIsEnable.value = isInstalled
    _installationIcon.value = chooseTheIcon(isInstalled)
  }

  private fun chooseTheIcon(condition:Boolean) = if (condition)"check.png" else "download.png"

  fun setSelectedPackage(value: AptPackageModel) {
    _selectedPackage.value = value
  }

  private fun setShowProgressBar(value: Boolean) {
    _showProgressBar.value = value
  }
}