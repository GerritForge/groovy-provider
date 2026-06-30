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

import com.google.gerrit.httpd.WebLoginListener;
import com.google.gerrit.server.IdentifiedUser;
import com.google.gerrit.server.permissions.GlobalPermission;
import com.google.gerrit.server.permissions.PermissionBackend;
import com.google.gerrit.server.permissions.PermissionBackendException;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Singleton
class AdminLoginRedirectListener implements WebLoginListener {

  private static final String ADMIN_REDIRECT_URL =
      "https://bsl.gerritforge.com";

  private final PermissionBackend permissionBackend;

  @Inject
  AdminLoginRedirectListener(PermissionBackend permissionBackend) {
    this.permissionBackend = permissionBackend;
  }

  @Override
  public void onLogin(
      IdentifiedUser user, HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    try {
      if (permissionBackend.user(user).test(GlobalPermission.ADMINISTRATE_SERVER)
          && !hasValidLicence()) {
        response.sendRedirect(ADMIN_REDIRECT_URL);
      }
    } catch (PermissionBackendException e) {
      // fall through and let the normal post-login redirect happen
    }
  }

  private boolean hasValidLicence() {
    return false;
  }

  @Override
  public void onLogout(
      IdentifiedUser user, HttpServletRequest request, HttpServletResponse response) {}
}
