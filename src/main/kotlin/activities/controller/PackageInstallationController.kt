package activities.controller

import activities.AptCommandExecutor
import activities.AptPackageModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PackageInstallationController {

  private var currentPackageState = MutableStateFlow(AptPackageModel())

  private var _isProgressBarVisible = MutableStateFlow(false)
  val isProgressBarVisible: StateFlow<Boolean> = _isProgressBarVisible

  private var _isInstallButtonEnabled = MutableStateFlow(false)
  val isInstallButtonEnabled: StateFlow<Boolean> = _isInstallButtonEnabled

  private var _installButtonIcon = MutableStateFlow("download.png")
  val installButtonIcon: StateFlow<String> = _installButtonIcon

  private val commandExecutor = AptCommandExecutor()

  private val ioScope = CoroutineScope(Dispatchers.IO)

  private var _percent = MutableStateFlow(0)
  val percent :StateFlow<Int> = _percent


  fun executeInstallation() {
    if (!commandExecutor.isPackageInstalled(currentPackageState.value.packageName)) {
      updateProgressBarVisiblility(true)
      ioScope.launch {
        commandExecutor.installPackage(currentPackageState.value.packageName){ percent ->
          _percent.value = percent
        }
        updateProgressBarVisiblility(false)
        updateState(isInstalled = true)
      }
    } else {
      updateState(isInstalled = false)
    }
  }

  fun updateButtonState(aptPackageModel: AptPackageModel) {
    _isInstallButtonEnabled.value = commandExecutor.isPackageInstalled(aptPackageModel.packageName)
    _installButtonIcon.value = chooseTheIcon(_isInstallButtonEnabled.value)
  }

  private fun updateState(isInstalled: Boolean) {
    _isInstallButtonEnabled.value = isInstalled
    _installButtonIcon.value = chooseTheIcon(isInstalled)
  }

  private fun chooseTheIcon(condition:Boolean) = if (condition)"check.png" else "download.png"

  fun updateCurrentPackageState(value: AptPackageModel) {
    currentPackageState.value = value
  }

  private fun updateProgressBarVisiblility(value: Boolean) {
    _isProgressBarVisible.value = value
  }
}