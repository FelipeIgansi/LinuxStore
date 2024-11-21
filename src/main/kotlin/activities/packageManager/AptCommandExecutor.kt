package activities.packageManager

import activities.constants.Constants
import activities.constants.ModelName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader

class AptCommandExecutor {

  fun executeSearchPackages(section: String): List<String> {
    val processBuilder = ProcessBuilder(
      "/bin/sh",
      "-c",
      "apt-cache show $(apt-cache search . | cut -d' ' -f1) | grep -A 10 '^Section: $section'"
    )

    val process = processBuilder.start()
    val output = mutableListOf<String>()

    BufferedReader(InputStreamReader(process.inputStream)).use { reader ->
      var line: String?
      while (reader.readLine().also { line = it } != null) {
        if (line!!.startsWith(ModelName.PACKAGE_MODEL)) {
          output.add(line!!.split(": ").last().trim())
        }
      }
    }

    process.waitFor()
    return output
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

  fun searchPackagesBySection(section: String): List<String> {
    val aptOutput = executeSearchPackages(section)
    return aptOutput
  }

  suspend fun executeCommand(
    packageName: String,
    callBack: (Int) -> Unit,
    command: String
  ): Boolean {
    return try {
      withContext(Dispatchers.IO) {
        val processBuilder = ProcessBuilder("pkexec", "apt", command, packageName, "-y")
        var stopProcess = false
        processBuilder.redirectErrorStream(true)

        val process = processBuilder.start()

        val reader = BufferedReader(InputStreamReader(process.inputStream))
        var line: String?

        while (reader.readLine().also { line = it } != null) {
          val percentageMatch = line?.let { Regex("""(\d+)%""").find(it) }
          if (percentageMatch != null) {
            if (!stopProcess) {
              withContext(Dispatchers.IO) {
                val percent = (percentageMatch.groupValues[1]).toInt()
                if (percent <= 100) {
                  callBack(percent)
                  delay(100)
                }
                if (percent == 100) stopProcess = true
              }
            } else break
          } else continue
        }
        val exitCode = process.waitFor()
        return@withContext exitCode == 0
      }
    } catch (e: Exception) {
      e.printStackTrace()
      return false
    }
  }

  fun isPackageInstalled(packageName: String): Boolean {
    return try {
      val processBuilder = ProcessBuilder("dpkg", "-l", packageName)
      processBuilder.redirectErrorStream(true)
      val process = processBuilder.start()

      val reader = BufferedReader(InputStreamReader(process.inputStream))
      val output = reader.readLines()

      // Se a saída contiver o nome do pacote e o status estiver marcado como "ii",
      // significa que o pacote está instalado.
      output.any { it.startsWith("ii") && it.contains(packageName) }

    } catch (e: Exception) {
      e.printStackTrace()
      false
    }
  }

}