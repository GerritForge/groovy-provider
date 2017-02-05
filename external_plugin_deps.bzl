load("//tools/bzl:maven_jar.bzl", "maven_jar")

def external_plugin_deps():
  maven_jar(
    name = 'groovy',
    artifact = 'org.codehaus.groovy:groovy-all:2.2.1',
    sha1 = '07ae565d50d24167c4e5d74b96f4126d4c56f16f',
  )
