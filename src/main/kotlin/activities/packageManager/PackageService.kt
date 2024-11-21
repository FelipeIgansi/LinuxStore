package activities.packageManager

import activities.AptPackageModel
import activities.constants.ModelName
import java.util.*

object PackageService {
  /*    fun listAllPackages(): List<String> {
          val aptOutput = AptCommandExecutor.executeAptCommand(activities.constants.Constants.APT, activities.constants.Constants.LIST, "--all-versions")
          return aptOutput.map { it.substringBefore('/') }
      }*/

  fun getPackageDetails(
    aptCommandExecutor: AptCommandExecutor,
    packageName: String
  ): AptPackageModel {
    val aptShowOutput = aptCommandExecutor.executeShowPackage(packageName)
    var currentPackage = AptPackageModel()
    val contentDescription = StringBuilder()
    var isDescription = false

    aptShowOutput.forEach { line ->
      if (line.isNotEmpty()) {
        val mapContentLine = line.split(':', limit = 2).toList()
        when (mapContentLine.first()) {
          ModelName.PACKAGE_MODEL -> currentPackage = currentPackage.copy(packageName = mapContentLine[1].trim())
          ModelName.VERSION_MODEL -> currentPackage = currentPackage.copy(version = mapContentLine[1].trim())
          ModelName.PRIORITY_MODEL -> currentPackage = currentPackage.copy(priority = mapContentLine[1].trim())
          ModelName.SECTION_MODEL -> currentPackage = currentPackage.copy(section = mapContentLine[1].trim())
          ModelName.ORIGIN_MODEL -> currentPackage = currentPackage.copy(origin = mapContentLine[1].trim())
          ModelName.MAINTAINER_MODEL -> currentPackage = currentPackage.copy(maintainer = mapContentLine[1].trim())
          ModelName.ORIGINAL_MAINTAINER_MODEL -> currentPackage = currentPackage.copy(originalMaintainer = mapContentLine[1].trim())
          ModelName.BUGS_MODEL -> currentPackage = currentPackage.copy(bugs = mapContentLine[1].trim())
          ModelName.INSTALLED_SIZE_MODEL -> currentPackage = currentPackage.copy(installedSize = mapContentLine[1].trim())
          ModelName.DEPENDS_MODEL -> currentPackage = currentPackage.copy(depends = mapContentLine[1].trim())
          ModelName.RECOMMENDS_MODEL -> currentPackage = currentPackage.copy(recommends = mapContentLine[1].trim())
          ModelName.SUGGESTS_MODEL -> currentPackage = currentPackage.copy(suggests = mapContentLine[1].trim())
          ModelName.CONFLICTS_MODEL -> currentPackage = currentPackage.copy(conflicts = mapContentLine[1].trim())
          ModelName.BREAKS_MODEL -> currentPackage = currentPackage.copy(breaks = mapContentLine[1].trim())
          ModelName.REPLACES_MODEL -> currentPackage = currentPackage.copy(replaces = mapContentLine[1].trim())
          ModelName.HOMEPAGE_MODEL -> currentPackage = currentPackage.copy(homepage = mapContentLine[1].trim())
          ModelName.TASK_MODEL -> currentPackage = currentPackage.copy(task = mapContentLine[1].trim())
          ModelName.DOWNLOAD_SIZE_MODEL -> currentPackage = currentPackage.copy(downloadSize = mapContentLine[1].trim())
          ModelName.APT_MANUAL_INSTALLED_MODEL -> currentPackage = currentPackage.copy(aptManualInstalled = mapContentLine[1].trim())
          ModelName.APT_SOURCES_MODEL -> currentPackage = currentPackage.copy(aptSources = mapContentLine[1].trim())
          ModelName.DESCRIPTION_MODEL -> {
            contentDescription.append(mapContentLine[1].trimStart()
              .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
            isDescription = true
          }
          else -> if (isDescription) contentDescription.append(mapContentLine.first())
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
  ): List<AptPackageModel> {
    val aptOutput = aptCommandExecutor.searchPackagesBySection(section)
    val packageData = mutableListOf<AptPackageModel>()

    aptOutput.forEach { line ->
      val packageName = line.substringBefore('/')
      val aptPackage = getPackageDetails(aptCommandExecutor, packageName)
      if (aptPackage.description.isNotEmpty()) packageData.add(aptPackage)
    }

    return packageData
  }
}
