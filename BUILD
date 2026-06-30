load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "groovy-provider",
    srcs = glob(["src/main/java/**/*.java"]),
    resources = glob(["src/main/resources/**/*"]),
    manifest_entries = [
        "Implementation-Title: Groovy Provider",
        "Implementation-URL: https://github.com/GerritForge/groovy-provider",
        "Gerrit-PluginName: groovy-provider",
        "Gerrit-Module: com.gerritforge.gerrit.plugins.scripting.groovyprovider.Module",
        "Gerrit-HttpModule: com.gerritforge.gerrit.plugins.scripting.groovyprovider.HttpModule",
    ],
    deps = [
        "@groovy//jar",
    ],
)
