include_defs('//lib/maven.defs')

gerrit_plugin(
  name = 'groovy-provider',
  srcs = glob(['src/main/java/**/*.java']),
  resources = glob(['src/main/**/*']),
  manifest_entries = [
    'Implementation-Title: Groovy Provider',
    'Implementation-URL: https://gerrit.googlesource.com/plugins/scripting/groovy-provider',
    'Gerrit-PluginName: groovy-provider',
    'Gerrit-Module: com.googlesource.gerrit.plugins.scripting.groovyprovider.Module'
  ],
  deps = [
    ':groovy',
  ],
)

maven_jar(
  name = 'groovy',
  id = 'org.codehaus.groovy:groovy-all:2.2.1',
  sha1 = '07ae565d50d24167c4e5d74b96f4126d4c56f16f',
  license = 'Apache2.0',
)
