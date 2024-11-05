package activities

sealed class Constants {
  companion object {
    const val PACKAGE_MODEL = "Package"
    const val VERSION_MODEL = "Version"
    const val PRIORITY_MODEL = "Priority"
    const val SECTION_MODEL = "Section"
    const val ORIGIN_MODEL = "Origin"
    const val MAINTAINER_MODEL = "Maintainer"
    const val ORIGINAL_MAINTAINER_MODEL = "Original-Maintainer"
    const val BUGS_MODEL = "Bugs"
    const val INSTALLED_SIZE_MODEL = "Installed-Size"
    const val DEPENDS_MODEL = "Depends"
    const val RECOMMENDS_MODEL = "Recommends"
    const val SUGGESTS_MODEL = "Suggests"
    const val CONFLICTS_MODEL = "Conflicts"
    const val BREAKS_MODEL = "Breaks"
    const val REPLACES_MODEL = "Replaces"
    const val HOMEPAGE_MODEL = "Homepage"
    const val TASK_MODEL = "Task"
    const val DOWNLOAD_SIZE_MODEL = "Download-Size"
    const val APT_MANUAL_INSTALLED_MODEL = "APT-Manual-Installed"
    const val APT_SOURCES_MODEL = "APT-Sources"
    const val DESCRIPTION_MODEL = "Description"
    const val APT = "apt"
    const val SEARCH = "search"
    const val SECTION = "section"
    const val SHOW = "show"
  }
  object MenuItems {
    object EN {
      const val OFFICE = "Office"
      const val PRODUCTIVITY = "Productivity"
      const val DEVELOPMENT = "Development"
      const val GAMES = "Games"
      const val GRAPHICS = "Graphics"
      const val MULTIMEDIA = "Multimedia"
      const val INTERNET = "Internet"
    }
  }
}