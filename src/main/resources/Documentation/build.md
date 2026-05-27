Build
=====

This plugin is built with Bazel.

Clone (or link) this plugin to the `plugins` directory of Gerrit's source tree.

Wire this plugin's Bazel module into Gerrit's plugin dependency module fragment.
If this is the only external plugin, copy the fragment:

```
  cd gerrit/plugins
  cp @PLUGIN@/external_plugin_deps.MODULE.bazel external_plugin_deps.MODULE.bazel
```

If `external_plugin_deps.MODULE.bazel` already contains entries for other
plugins, merge the contents of `@PLUGIN@/external_plugin_deps.MODULE.bazel`
into it instead.

Then issue:

```
  bazelisk build //plugins/@PLUGIN@
```

in the root of Gerrit's source tree to build

The output is created in

```
  bazel-bin/plugins/@PLUGIN@/@PLUGIN@.jar
```

This project can be imported into the Eclipse IDE.
Add the plugin name to the `CUSTOM_PLUGINS` set in
Gerrit core in `tools/bzl/plugins.bzl`, and execute:

```
  ./tools/eclipse/project.py
```
