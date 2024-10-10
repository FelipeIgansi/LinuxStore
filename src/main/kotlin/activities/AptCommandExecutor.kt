package activities

import java.io.BufferedReader
import java.io.InputStreamReader

class AptCommandExecutor {
  fun executeSearchPackages(section: String): List<String> {
//    val processBuilder = ProcessBuilder(Constants.APT, Constants.SEARCH, "?section($section)")
    val processBuilder = ProcessBuilder(
      "/bin/sh",
      "-c",
      "apt-cache show $(apt-cache search . | cut -d' ' -f1) | grep -A 10 '^Section: $section'"
    )
    /* TODO(Existe uma inconsistencia aqui, onde o comando exibe corretamente os pacotes com section web,
        mas ao consultar os detalhes do pacote ele não é de fato dessa section)*/

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
}