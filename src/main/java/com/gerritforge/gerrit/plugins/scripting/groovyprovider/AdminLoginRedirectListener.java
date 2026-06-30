// Copyright (C) 2026 GerritForge, Inc.
//
// Licensed under the BSL 1.1 (the "License");
// you may not use this file except in compliance with the License.
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.gerritforge.gerrit.plugins.scripting.groovyprovider;

import com.google.gerrit.extensions.annotations.PluginName;
import com.google.gerrit.httpd.WebLoginListener;
import com.google.gerrit.server.IdentifiedUser;
import com.google.gerrit.server.config.CanonicalWebUrl;
import com.google.gerrit.server.permissions.GlobalPermission;
import com.google.gerrit.server.permissions.PermissionBackend;
import com.google.gerrit.server.permissions.PermissionBackendException;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Singleton
class AdminLoginRedirectListener implements WebLoginListener {

  private static final String BSL_BASE_URL = "https://bsl.gerritforge.com";

  private final PermissionBackend permissionBackend;
  private final String pluginName;
  private final String canonicalWebUrl;

  @Inject
  AdminLoginRedirectListener(
      PermissionBackend permissionBackend,
      @PluginName String pluginName,
      @CanonicalWebUrl @Nullable String canonicalWebUrl) {
    this.permissionBackend = permissionBackend;
    this.pluginName = pluginName;
    this.canonicalWebUrl = canonicalWebUrl;
  }

  @Override
  public void onLogin(
      IdentifiedUser user, HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    try {
      if (permissionBackend.user(user).test(GlobalPermission.ADMINISTRATE_SERVER)
          && !hasValidLicence()) {
        response.sendRedirect(buildRedirectUrl());
      }
    } catch (PermissionBackendException e) {
      // fall through and let the normal post-login redirect happen
    }
  }

  private String buildRedirectUrl() {
    StringBuilder url = new StringBuilder(BSL_BASE_URL);
    url.append("?plugin=").append(URLEncoder.encode(pluginName, StandardCharsets.UTF_8));
    if (canonicalWebUrl != null) {
      url.append("&return=").append(URLEncoder.encode(canonicalWebUrl, StandardCharsets.UTF_8));
    }
    return url.toString();
  }

  private boolean hasValidLicence() {
    return false;
  }

  @Override
  public void onLogout(
      IdentifiedUser user, HttpServletRequest request, HttpServletResponse response) {}
}
