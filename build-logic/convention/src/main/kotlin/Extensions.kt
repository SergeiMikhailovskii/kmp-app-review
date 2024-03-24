import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionContainer

internal fun ExtensionContainer.getPluginId(alias: String): String =
    getByType(VersionCatalogsExtension::class.java).named("libs").findPlugin(alias).get().get().pluginId

internal fun ExtensionContainer.getVersion(alias: String): String =
    getByType(VersionCatalogsExtension::class.java).named("libs").findVersion(alias).get().toString()
