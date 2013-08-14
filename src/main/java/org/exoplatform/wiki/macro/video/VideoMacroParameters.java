/*
 * Copyright (C) 2003-2012 eXo Platform SAS.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 */
package org.exoplatform.wiki.macro.video;

import org.apache.commons.lang.StringUtils;
import org.xwiki.properties.annotation.PropertyDescription;

/**
 * Manage an video node
 */
public class VideoMacroParameters {
  private String src = StringUtils.EMPTY;

  private int width;

  private int height;

  public String getSrc() {
    return src;
  }

  @PropertyDescription("The url of the video")
  public void setSrc(String src) {
    this.src = src;
  }

  public int getWidth() {
    return width;
  }

  @PropertyDescription("The width of the video")
  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  @PropertyDescription("The height of the video")
  public void setHeight(int height) {
    this.height = height;
  }
}
