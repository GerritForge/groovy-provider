load(
    "@com_googlesource_gerrit_bazlets//:gerrit_plugin.bzl",
    "gerrit_plugin",
    "gerrit_plugin_dependency_tests",
)

gerrit_plugin(
    name = "groovy-provider",
    srcs = glob(["src/main/java/**/*.java"]),
    resources = glob(["src/main/resources/**/*"]),
    manifest_entries = [
        "Implementation-Title: Groovy Provider",
        "Implementation-URL: https://github.com/GerritForge/groovy-provider",
        "Gerrit-PluginName: groovy-provider",
        "Gerrit-Module: com.gerritforge.gerrit.plugins.scripting.groovyprovider.Module",
        "Gerrit-HttpModule: com.gerritforge.bsl.licence.manager.BslLicenceHttpModule",
    ],
    deps = [
        "@gerrit-bsl-license//:gerrit-bsl-license",
        "@groovy-provider_plugin_deps//:org_codehaus_groovy_groovy_all",
    ],
)

gerrit_plugin_dependency_tests(plugin = "groovy-provider")
