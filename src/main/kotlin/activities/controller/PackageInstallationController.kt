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

  private var _iconButton = MutableStateFlow("download.png")
  val iconButton: StateFlow<String> = _iconButton

  private val commandExecutor = AptCommandExecutor()

  private val ioScope = CoroutineScope(Dispatchers.IO)

  private var _percent = MutableStateFlow(0)
  val percent: StateFlow<Int> = _percent


  fun executeInstallation() {
    if (!commandExecutor.isPackageInstalled(currentPackageState.value.packageName)) {
      updateProgressBarVisiblility(true)
      ioScope.launch {
        commandExecutor.installPackage(currentPackageState.value.packageName) { percent ->
          _percent.value = percent
        }
        updateProgressBarVisiblility(false)
        updateIconButton(isInstalled = true)
      }
    } else {
      updateIconButton(isInstalled = false)
    }
  }

  fun updateIconButton(aptPackageModel: AptPackageModel) {
    _iconButton.value = chooseTheIcon(commandExecutor.isPackageInstalled(aptPackageModel.packageName))
  }

  private fun updateIconButton(isInstalled: Boolean) {
    _iconButton.value = chooseTheIcon(isInstalled)
  }

  private fun chooseTheIcon(isInstalled: Boolean) = if (isInstalled) "uninstall.svg" else "download.png"

  fun updateCurrentPackageState(value: AptPackageModel) {
    currentPackageState.value = value
  }

  private fun updateProgressBarVisiblility(value: Boolean) {
    _isProgressBarVisible.value = value
  }
}