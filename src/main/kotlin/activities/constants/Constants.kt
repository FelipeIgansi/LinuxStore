package activities.constants

sealed class Constants {
  companion object {
    const val APT = "apt"
    const val SEARCH = "search"
    const val SECTION = "section"
    const val SHOW = "show"
    const val INSTALL = "install"
    const val REMOVE = "remove"
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