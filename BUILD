load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "groovy-provider",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Implementation-Title: Groovy Provider",
        "Implementation-URL: https://github.com/GerritForge/groovy-provider",
        "Gerrit-PluginName: groovy-provider",
        "Gerrit-Module: com.gerritforge.gerrit.plugins.scripting.groovyprovider.Module",
        "Gerrit-HttpModule: com.gerritforge.gerrit.plugins.bsl.HttpModule",
    ],
    resources = glob(["src/main/resources/**/*"]),
    deps = [
<<<<<<< HEAD   (bbae81143737ff056974348000957ba9760d1246 Redirect admin users to GerritForge's BSL awareness page)
        "@groovy//jar",
=======
        "//plugins/gerrit-bsl-license",
        "@groovy-provider_plugin_deps//:org_codehaus_groovy_groovy_all",
>>>>>>> CHANGE (5f75e86146f381f22a19e7035d82758d5e5ebc73 Use gerrit-bsl-license's provided HttpModule)
    ],
)
