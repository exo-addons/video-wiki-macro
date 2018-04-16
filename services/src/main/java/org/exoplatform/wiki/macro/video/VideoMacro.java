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

import org.exoplatform.commons.embedder.Embedder;
import org.exoplatform.commons.embedder.EmbedderFactory;
import org.exoplatform.commons.embedder.ExoMedia;
import org.exoplatform.commons.utils.MimeTypeResolver;
import org.xwiki.component.annotation.Component;
import org.xwiki.rendering.block.Block;
import org.xwiki.rendering.block.RawBlock;
import org.xwiki.rendering.macro.AbstractMacro;
import org.xwiki.rendering.macro.MacroExecutionException;
import org.xwiki.rendering.syntax.Syntax;
import org.xwiki.rendering.transformation.MacroTransformationContext;

import java.util.Collections;
import java.util.List;

/**
 * Manage an video node
 */
@Component("video")
public class VideoMacro extends AbstractMacro<VideoMacroParameters> {
  public static final String MACRO_CATEGORY_OTHER = "Other";

  /**
   * The description of the macro.
   */
  private static final String DESCRIPTION = "Displays a video";

  /**
   * Create and initialize the descriptor of the macro.
   */
  public VideoMacro() {
    super("Video", DESCRIPTION, VideoMacroParameters.class);
    setDefaultCategory(MACRO_CATEGORY_OTHER);
  }

  public List<Block> execute(VideoMacroParameters parameters, String content, MacroTransformationContext context)
      throws MacroExecutionException {
    StringBuilder rawContent = new StringBuilder(100);

    String src = parameters.getSrc();

    // calculate video's width and height
    int widthParameter = parameters.getWidth();
    int heightParameter = parameters.getHeight();
    int width = 600;
    int height = 400;

    if(widthParameter > 0) {
      width = widthParameter;

      if(heightParameter > 0) {
        height = heightParameter;
      } else {
        height = (int) (width / 1.5);
      }
    } else if(heightParameter > 0) {
      height = heightParameter;
      width = (int) (heightParameter * 1.5);
    }

    // use eXo embedder service for videos on Internet (Youtube, Dailymotion, ...)
    Embedder embedder = EmbedderFactory.getInstance(src);
    ExoMedia exoSocialMedia = embedder.getExoMedia();

    if(exoSocialMedia != null) {
      rawContent.append(exoSocialMedia.getHtml());
    } else {
      int indexQuery = src.indexOf("?");
      String srcWithoutQuery = indexQuery > 0 ? src.substring(0, indexQuery) : src;

      MimeTypeResolver mimeTypeResolver = new MimeTypeResolver();
      String mimeType = mimeTypeResolver.getMimeType(srcWithoutQuery);

      rawContent.append("<object width=\"")
              .append(width)
              .append("\" height=\"")
              .append(height)
              .append("\" type=\"")
              .append(mimeType)
              .append("\" data=\"")
              .append(src)
              .append("\"><param name=\"movie\" value=\"")
              .append(src)
              .append("\"></param><param name=\"allowFullScreen\" value=\"true\"></param>");
      rawContent.append("<embed width=\"")
              .append(width)
              .append("\" height=\"")
              .append(height)
              .append("\" type=\"")
              .append(mimeType)
              .append("\" src=\"")
              .append(src)
              .append("\" allowfullscreen=\"true\"></embed>");
      rawContent.append("</object>");
    }

    RawBlock panelBlock = new RawBlock(rawContent.toString(), Syntax.XHTML_1_0);

    return Collections.singletonList((Block) panelBlock);
  }

  public boolean supportsInlineMode() {
    return true;
  }
}
