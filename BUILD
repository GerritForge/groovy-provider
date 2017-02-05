load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "groovy-provider",
    srcs = glob(["src/main/java/**/*.java"]),
    resources = glob(["src/main/resources/**/*"]),
    manifest_entries = [
        "Implementation-Title: Groovy Provider",
        "Implementation-URL: https://gerrit.googlesource.com/plugins/scripting/groovy-provider",
        "Gerrit-PluginName: groovy-provider",
        "Gerrit-Module: com.googlesource.gerrit.plugins.scripting.groovyprovider.Module",
    ],
    deps = [
        "@groovy//jar",
    ],
)
