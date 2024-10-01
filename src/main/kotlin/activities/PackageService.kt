package activities

import Constants

object PackageService {
  /*    fun listAllPackages(): List<String> {
          val aptOutput = AptCommandExecutor.executeAptCommand(Constants.APT, Constants.LIST, "--all-versions")
          return aptOutput.map { it.substringBefore('/') }
      }*/

  private fun getPackageDetails(
    aptCommandExecutor: AptCommandExecutor,
    packageName: String
  ): AptPackageModel {
    val aptShowOutput = aptCommandExecutor.executeShowPackage(packageName)
    var currentPackage = AptPackageModel()
    val contentDescription = StringBuilder()

    aptShowOutput.forEach { line ->
      if (line.isNotEmpty()) {
        val mapContentLine = line.split(':', limit = 2).toList()
        when (mapContentLine.first()) {
          Constants.PACKAGE_NAME -> currentPackage = currentPackage.copy(packageName = mapContentLine[1].trim())
          Constants.VERSION_MODEL -> currentPackage = currentPackage.copy(version = mapContentLine[1].trim())
          Constants.PRIORITY_MODEL -> currentPackage = currentPackage.copy(priority = mapContentLine[1].trim())
          Constants.SECTION_MODEL -> currentPackage = currentPackage.copy(section = mapContentLine[1].trim())
          Constants.MAINTAINER_MODEL -> currentPackage = currentPackage.copy(maintainer = mapContentLine[1].trim())
          Constants.INSTALLED_SIZE_MODEL -> currentPackage =
            currentPackage.copy(installedSize = mapContentLine[1].trim())

          Constants.DEPENDS_MODEL -> currentPackage = currentPackage.copy(depends = mapContentLine[1].trim())
          Constants.HOMEPAGE_MODEL -> currentPackage = currentPackage.copy(homepage = mapContentLine[1].trim())
          Constants.DOWNLOAD_SIZE_MODEL -> currentPackage = currentPackage.copy(downloadSize = mapContentLine[1].trim())
          Constants.APT_SOURCES_MODEL -> currentPackage = currentPackage.copy(aptSources = mapContentLine[1].trim())
          Constants.DESCRIPTION_MODEL -> contentDescription.append(mapContentLine[1].trimStart())
          else -> contentDescription.append(mapContentLine.first())
        }
      }
    }
    if (contentDescription.isNotEmpty()) currentPackage =
      currentPackage.copy(description = contentDescription.toString())

    return currentPackage
  }


  fun listPackagesBySection(
    aptCommandExecutor: AptCommandExecutor,
    section: String,
    page: Int,
    size: Int
  ): List<AptPackageModel> {
    val aptOutput = aptCommandExecutor.searchPackagesBySection(section, page, size)
    val aptPackageModels = mutableListOf<AptPackageModel>()

    aptOutput.forEach { line ->
      val packageName = line.substringBefore('/')
      val aptPackage = getPackageDetails(aptCommandExecutor, packageName)
      if (aptPackage.description.isNotEmpty()) aptPackageModels.add(aptPackage)
    }

    return aptPackageModels
  }
}
