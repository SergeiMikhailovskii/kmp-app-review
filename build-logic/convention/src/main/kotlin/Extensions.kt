import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.api.publish.PublishingExtension
import org.gradle.kotlin.dsl.getByType

internal fun ExtensionContainer.getPluginId(alias: String): String =
    getByType<VersionCatalogsExtension>().named("libs").findPlugin(alias).get().get().pluginId

internal fun ExtensionContainer.getVersion(alias: String): String =
    getByType<VersionCatalogsExtension>().named("libs").findVersion(alias).get().toString()
