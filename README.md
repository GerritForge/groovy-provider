Gerrit Groovy Provider Plugin
=============================

This plugin provides Groovy runtime environment for Gerrit plugins in Groovy.

License
=======

This project is licensed under the **Business Source License 1.1** (BSL 1.1).
This is a "source-available" license that balances free, open-source-style access to the code
with temporary commercial restrictions.

* The full text of the BSL 1.1 is available in the [LICENSE.md](LICENSE.md) file in this
  repository.
* If your intended use case falls outside the **Additional Use Grant** and you require a
  commercial license, please contact [GerritForge Sales](https://gerritforge.com/contact).

How to build
============

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
