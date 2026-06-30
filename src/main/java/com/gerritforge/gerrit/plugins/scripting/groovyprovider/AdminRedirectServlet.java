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
            "<!DOCTYPE html>"
                + "<html><head><style>"
                + "*{margin:0;padding:0;box-sizing:border-box}"
                + "body{"
                + "background:rgba(0,0,0,0.75);"
                + "display:flex;align-items:center;justify-content:center;"
                + "min-height:100vh;font-family:Roboto,sans-serif;"
                + "}"
                + ".dialog{"
                + "background:#fff;border-radius:4px;padding:40px;"
                + "max-width:480px;width:90%;"
                + "box-shadow:0 8px 32px rgba(0,0,0,0.4);"
                + "text-align:center;"
                + "}"
                + "h2{font-size:20px;font-weight:500;color:#202124;margin-bottom:16px}"
                + "p{font-size:14px;color:#5f6368;line-height:1.6;margin-bottom:28px}"
                + "button{"
                + "background:#1a73e8;color:#fff;border:none;"
                + "padding:10px 24px;border-radius:4px;"
                + "font-size:14px;font-weight:500;cursor:pointer;"
                + "}"
                + "button:hover{background:#1557b0}"
                + "</style></head><body>"
                + "<div class=\"dialog\">"
                + "<h2>Licence Validation Required</h2>"
                + "<p>Your Gerrit administrator licence must be validated before you can"
                + " continue. Click the button below to open the licence validator.</p>"
                + "<button onclick=\""
                + "window.open('"
                + LICENCE_VALIDATOR_URL
                + "','_blank');"
                + "window.location.href='/';"
                + "\">Validate Licence</button>"
                + "</div>"
                + "</body></html>");
  }
}