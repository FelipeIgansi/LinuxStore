package activities

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
        if (line!!.startsWith(Constants.PACKAGE_MODEL)) {
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

  fun installPackage(packageName: String): Boolean {
    return try {
      val processBuilder = ProcessBuilder("pkexec", "apt", "install", packageName, "-y")

      processBuilder.redirectErrorStream(true)

      val process = processBuilder.start()

      val reader = BufferedReader(InputStreamReader(process.inputStream))
      var line: String?

      while (reader.readLine().also { line = it } != null) {
        println(line)
      }

      val exitCOde = process.waitFor()
      return exitCOde == 0
    } catch (e: Exception) {
      e.printStackTrace()
      false
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