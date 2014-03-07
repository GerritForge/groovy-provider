include_defs('//lib/maven.defs')

gerrit_plugin(
  name = 'groovy-provider',
  srcs = glob(['src/main/java/**/*.java']),
  manifest_entries = [
    'Implementation-Title: Groovy Provider',
    'Implementation-URL: https://github.com/davido/gerrit-groovy-provider-plugin',
    'Gerrit-PluginName: groovy-provider',
    'Gerrit-Module: com.googlesource.gerrit.plugins.groovyprovider.Module'
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
