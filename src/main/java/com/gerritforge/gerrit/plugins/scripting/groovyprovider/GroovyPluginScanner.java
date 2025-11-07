// Copyright (C) 2014 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.gerritforge.gerrit.plugins.scripting.groovyprovider;

import com.google.common.collect.Sets;
import com.google.gerrit.server.plugins.AbstractPreloadedPluginScanner;
import com.google.gerrit.server.plugins.InvalidPluginException;
import com.google.gerrit.server.plugins.Plugin.ApiType;
import com.google.gerrit.server.plugins.PluginEntry;
import groovy.lang.Binding;
import groovy.lang.Script;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import org.codehaus.groovy.runtime.InvokerHelper;

public class GroovyPluginScanner extends AbstractPreloadedPluginScanner {

  public GroovyPluginScanner(String pluginName, GroovyPluginScriptEngine scriptEngine, Path srcFile)
      throws InvalidPluginException {
    super(pluginName, getPluginVersion(srcFile), load(scriptEngine, srcFile), ApiType.PLUGIN);
  }

  private static String getPluginVersion(Path srcFile) {
    String srcFileName = srcFile.getFileName().toString();
    int dashPos = srcFileName.lastIndexOf('-');
    int dotPos = srcFileName.lastIndexOf('.');
    return dashPos > 0 ? srcFileName.substring(dashPos + 1, dotPos) : "0";
  }

  @Override
  public Optional<PluginEntry> getEntry(String resourcePath) {
    return Optional.empty();
  }

  @Override
  public InputStream getInputStream(PluginEntry entry) throws IOException {
    throw new FileNotFoundException();
  }

  @Override
  public Stream<PluginEntry> entries() {
    return Stream.empty();
  }

  public static Set<Class<?>> load(GroovyPluginScriptEngine scriptEngine, Path srcFile)
      throws InvalidPluginException {
    try {
      return scanGroovyScriptBindings(scriptEngine.loadScriptByName(srcFile.toString()));
    } catch (ResourceException | ScriptException e) {
      throw new InvalidPluginException("Cannot compile and execute Groovy script " + srcFile, e);
    }
  }

  private static Set<Class<?>> scanGroovyScriptBindings(Class<?> scriptClass)
      throws InvalidPluginException {
    Set<Class<?>> classes = Sets.newHashSet();

    try {
      Method mainMethod = scriptClass.getMethod("main", String[].class);
      int modifiers = mainMethod.getModifiers();
      if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)) {
        classes.addAll(getMainBindings(scriptClass));
      }
    } catch (NoSuchMethodException e) {
      classes.add(scriptClass);

    } catch (SecurityException e) {
      throw new InvalidPluginException(
          "Cannot access Groovy script class " + scriptClass.getName(), e);
    }
    return classes;
  }

  private static Set<Class<?>> getMainBindings(Class<?> scriptClass) {
    Set<Class<?>> classes = Sets.newHashSet();
    Binding binding = new Binding();
    Script script = InvokerHelper.createScript(scriptClass, binding);
    script.run();

    for (Object variable : binding.getVariables().keySet()) {
      Object value = binding.getVariable(variable.toString());
      if (value == null) {
        continue;
      }

      if (ArrayList.class.isAssignableFrom(value.getClass())) {
        classes.addAll(scanArrayOfObjectOrClass(value));
      } else {
        classes.add(scanObjectOrClass(value));
      }
    }

    return classes;
  }

  private static Set<Class<?>> scanArrayOfObjectOrClass(Object value) {
    Set<Class<?>> classes = Sets.newHashSet();
    ArrayList<?> list = (ArrayList<?>) value;
    for (Object element : list) {
      if (element == null) {
        continue;
      }
      classes.add(scanObjectOrClass(element));
    }
    return classes;
  }

  private static Class<?> scanObjectOrClass(Object element) {
    if (Class.class.isAssignableFrom(element.getClass())) {
      return (Class<?>) element;
    }
    return element.getClass();
  }
}
