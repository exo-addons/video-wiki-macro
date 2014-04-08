Video Wiki Macro
================

This addon adds a macro in the wiki which allows to insert a video in a wiki page.
This addon has been inspired by the [XWiki Video Macro](http://extensions.xwiki.org/xwiki/bin/view/Extension/Video+Macro).

Version 1.0 : eXoPlatform 4.0.1, 4.0.2, 4.0.3  
Version 1.0.1 : eXoPlatform 4.0.4, 4.0.5

Getting Started
===============

Step 1 :  Build 
----------------

Prerequisite : install [Maven 3](http://maven.apache.org/download.html)

Clone the project with

    git clone https://github.com/exo-addons/video-wiki-macro.git
    cd video-wiki-macro

Build it with

    mvn clean package

Step 2 : Deploy 
---------------

Prerequisite : install [eXo Platform 4.0 Tomcat bundle](http://www.exoplatform.com/company/en/download-exo-platform) (EXO\_TOMCAT\_ROOT\_FOLDER will be used to designate the eXo Tomcat root folder).

Copy the extension binary :

    cp target/wiki-macro-video-*.jar EXO_TOMCAT_ROOT_FOLDER/lib

Step 3 : Run
------------

    cd EXO_TOMCAT_ROOT_FOLDER 
    ./start_eXo.sh


User Guide
===============

- Go to the wiki
- Create or Edit a page

In Rich Text mode
-----------------

- Click on the menu Macro > Insert Macro...
- Select the Video macro
- Fill the src field with the URL of the video, and optionally the width and/or the height of the video (in pixels)
- Click on the Insert Macro button

In Source Editor mode
---------------------

- Add the following snippet

    {{video src="https://www.youtube.com/watch?v=9s5pAfIYNO0" width="600" height="400"}}

