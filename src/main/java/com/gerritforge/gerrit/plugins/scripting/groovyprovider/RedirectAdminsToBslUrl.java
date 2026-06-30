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

import com.google.common.flogger.FluentLogger;
import com.google.gerrit.extensions.annotations.PluginName;
import com.google.gerrit.httpd.WebLoginListener;
import com.google.gerrit.server.IdentifiedUser;
import com.google.gerrit.server.permissions.GlobalPermission;
import com.google.gerrit.server.permissions.PermissionBackend;
import com.google.gerrit.server.permissions.PermissionBackendException;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Singleton
class RedirectAdminsToBslUrl implements WebLoginListener {

  private static final FluentLogger logger = FluentLogger.forEnclosingClass();
  private static final String BSL_BASE_URL = "https://bsl.gerritforge.com";

  private final PermissionBackend permissionBackend;
  private final String pluginName;

  @Inject
  RedirectAdminsToBslUrl(PermissionBackend permissionBackend, @PluginName String pluginName) {
    this.permissionBackend = permissionBackend;
    this.pluginName = pluginName;
  }

  @Override
  public void onLogin(
      IdentifiedUser user, HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    try {
      if (permissionBackend.user(user).test(GlobalPermission.ADMINISTRATE_SERVER)
          && !hasValidLicence()) {
        String redirectUrl = buildRedirectUrl(request.getRequestURL().toString());
        logger.atInfo().log(
            "Redirecting admin %s to BSL page: %s", user.getAccountId(), redirectUrl);
        response.sendRedirect(redirectUrl);
      }
    } catch (PermissionBackendException e) {
      logger.atWarning().withCause(e).log(
          "Cannot check admin permission for account %s; skipping BSL redirect",
          user.getAccountId());
    }
  }

  private String buildRedirectUrl(String requestUrl) {
    StringBuilder url = new StringBuilder(BSL_BASE_URL);
    url.append("?plugin=").append(URLEncoder.encode(pluginName, StandardCharsets.UTF_8));
    url.append("&return=").append(URLEncoder.encode(requestUrl, StandardCharsets.UTF_8));
    return url.toString();
  }

  private boolean hasValidLicence() {
    return false;
  }

  @Override
  public void onLogout(
      IdentifiedUser user, HttpServletRequest request, HttpServletResponse response) {}
}
