package activities

data class AptPackageModel(
  val packageName: String = "",
  val version: String = "",
  val priority: String = "",
  val section: String = "",
  val maintainer: String = "",
  val installedSize: String = "",
  val depends: String = "",
  val homepage: String = "",
  val downloadSize: String = "",
  val aptSources: String = "",
  val description: String = ""
)

