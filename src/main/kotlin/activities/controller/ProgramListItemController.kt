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

  private var _installationIcon = MutableStateFlow("")
  val installationIcon: StateFlow<String> = _installationIcon

  private var _aptCommandExecutor = MutableStateFlow(AptCommandExecutor())


  fun installPackage(){
    if (!_aptCommandExecutor.value.isPackageInstalled(_selectedPackage.value.packageName)) {
      setShowProgressBar(true)
      CoroutineScope(Dispatchers.IO).launch {
        _aptCommandExecutor.value.installPackage(_selectedPackage.value.packageName)
        setShowProgressBar(false)
      }
    } else {
      setInstallationIcon("icons/check.png")
      setButtonIsEnable(false)
    }
  }

  fun setSelectedPackage(value: AptPackageModel) {
    _selectedPackage.value = value
  }

  private fun setShowProgressBar(value: Boolean) {
    _showProgressBar.value = value
  }

  fun setButtonIsEnable(value: Boolean) {
    _buttonIsEnable.value = value
  }

  fun setInstallationIcon(value: String) {
    _installationIcon.value = value
  }
}