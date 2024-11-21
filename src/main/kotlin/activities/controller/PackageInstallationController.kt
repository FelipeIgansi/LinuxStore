package activities.controller

import activities.AptPackageModel
import activities.constants.Constants
import activities.constants.IconName
import activities.packageManager.AptCommandExecutor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PackageInstallationController {

  private var currentPackageState = MutableStateFlow(AptPackageModel())

  private var _isProgressBarVisible = MutableStateFlow(false)
  val isProgressBarVisible: StateFlow<Boolean> = _isProgressBarVisible

  private var _iconButton = MutableStateFlow(IconName.INSTALL)
  val iconButton: StateFlow<String> = _iconButton

  private val commandExecutor = AptCommandExecutor()

  private val ioScope = CoroutineScope(Dispatchers.IO)

  private var _percent = MutableStateFlow(0)
  val percent: StateFlow<Int> = _percent


  fun packageIsInstalled(isInstalled: Boolean){
    if (isInstalled) executeUninstallPackage()
    else executeInstallPackage()
  }


  private fun executeInstallPackage() {
    if (!commandExecutor.isPackageInstalled(currentPackageState.value.packageName)) {
      updateProgressBarVisiblility(true)
      ioScope.launch {
        commandExecutor.executeCommand(
          packageName = currentPackageState.value.packageName,
          callBack = { percent -> _percent.value = percent },
          command = Constants.INSTALL
        )
        updateProgressBarVisiblility(false)
        updateIconButton(isInstalled = true)
      }
    } else {
      updateIconButton(isInstalled = false)
    }
  }

  private fun executeUninstallPackage() {
    if (commandExecutor.isPackageInstalled(currentPackageState.value.packageName)) {
      updateProgressBarVisiblility(true)
      ioScope.launch {
        commandExecutor.executeCommand(
          packageName = currentPackageState.value.packageName,
          callBack = { percent -> _percent.value = percent },
          command = Constants.REMOVE
        )
        updateProgressBarVisiblility(false)
        updateIconButton(isInstalled = false)
      }
    } else {
      updateIconButton(isInstalled = true)
    }
  }

  fun updateIconButton(packageData: AptPackageModel) {
    _iconButton.value = chooseTheIcon(commandExecutor.isPackageInstalled(packageData.packageName))
  }

  private fun updateIconButton(isInstalled: Boolean) {
    _iconButton.value = chooseTheIcon(isInstalled)
  }

  private fun chooseTheIcon(isInstalled: Boolean) = if (isInstalled) IconName.UNINSTALL else IconName.INSTALL

  fun updateCurrentPackageState(value: AptPackageModel) {
    currentPackageState.value = value
  }

  private fun updateProgressBarVisiblility(value: Boolean) {
    _isProgressBarVisible.value = value
  }
}