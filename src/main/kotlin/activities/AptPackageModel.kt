package activities

data class AptPackageModel(
  val packageName: String = "",
  val version: String = "",
  val priority: String = "",
  val section: String = "",
  val origin: String = "",
  val maintainer: String = "",
  val originalMaintainer: String = "",
  val bugs: String = "",
  val installedSize: String = "",
  val depends: String = "",
  val recommends: String = "",
  val suggests: String = "",
  val conflicts: String = "",
  val breaks: String = "",
  val replaces: String = "",
  val homepage: String = "",
  val task: String = "",
  val downloadSize: String = "",
  val aptManualInstalled: String = "",
  val aptSources: String = "",
  val description: String = ""
)

