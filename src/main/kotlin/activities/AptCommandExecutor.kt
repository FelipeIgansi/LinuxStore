package activities

import Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.BufferedReader
import java.io.InputStreamReader

class AptCommandExecutor {
  private var _packageSize = MutableStateFlow(0)
  val packageSize :StateFlow<Int> = _packageSize

  fun executeSearchPackages(section: String, page: Int, size:Int): List<String> {
    val processBuilder = ProcessBuilder(Constants.APT, Constants.SEARCH, Constants.SECTION, section)
    val process = processBuilder.start()
    val output = mutableListOf<String>()

    BufferedReader(InputStreamReader(process.inputStream)).use { reader ->
      var line: String?
      while (reader.readLine().also { line = it } != null) {
        if (!line!!.lowercase().startsWith(Constants.SORTING) &&
          !line!!.lowercase().startsWith(Constants.FULL_TEXT) &&
          line!!.isNotEmpty() &&
          line!!.contains('/')
        ) {
          output.add(line!!.trim())
        }
      }
    }

    process.waitFor()
    _packageSize.value = output.size
    val subList = output.subList((page*size)-size,page*size)
    return subList
  }

  fun executeShowPackage(packageName: String): List<String> {
    val processBuilder = ProcessBuilder(Constants.APT, Constants.SHOW, packageName)
    val process = processBuilder.start()
    val output = mutableListOf<String>()

    BufferedReader(InputStreamReader(process.inputStream)).use { reader ->
      var line: String?
      while (reader.readLine().also { line = it } != null) {
        output.add(line!!.trim())
      }
    }

    process.waitFor()
    return output
  }


  fun searchPackagesBySection(section: String, page: Int, size:Int): List<String> {
    val aptOutput = executeSearchPackages(section, page, size)
    return aptOutput
  }
}