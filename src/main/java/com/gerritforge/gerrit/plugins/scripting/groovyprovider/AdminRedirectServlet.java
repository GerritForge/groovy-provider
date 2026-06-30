// Copyright (C) 2025 GerritForge, Inc.
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

import com.google.inject.Singleton;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Singleton
class AdminRedirectServlet extends HttpServlet {

  private static final String LICENCE_VALIDATOR_URL =
      "https://pippo-pluto-paperino-licence-validator.com";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("text/html;charset=UTF-8");
    resp.getWriter()
        .write(
            "<!DOCTYPE html><html><body>"
                + "<p>Your licence must be validated before you can continue.</p>"
                + "<a href=\"#\" onclick=\""
                + "window.open('"
                + LICENCE_VALIDATOR_URL
                + "', '_blank');"
                + "window.location.href='/';"
                + "return false;\">"
                + "Click here to validate your licence and continue to Gerrit"
                + "</a>"
                + "</body></html>");
  }
}