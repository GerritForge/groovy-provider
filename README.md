Gerrit Groovy Provider Plugin
=============================

This plugin provides Groovy runtime environment for Gerrit plugins in Groovy.

To build, link this directory under Gerrit's tree plugins directory, merge
the dependencies from external_plugin_deps.bzl into the file of the same
name in the plugins directory, and from the root of the gerrit tree run:

```
  bazel build plugins/groovy-provider
```

The resulting artifact can be found under:

```
 bazel-bin/plugins/groovy-provider/groovy-provider.jar
```

To test deploy the review plugin [1] and copy this Groovy Provider plugin
under `$gerrit_site/plugins` directory.

Review plugin in Groovy can be used:

```
  ssh gerrit review approve I59302cbb
  Approve change: I59302cbb
```

* [1] https://github.com/davido/gerrit-groovy-plugin
