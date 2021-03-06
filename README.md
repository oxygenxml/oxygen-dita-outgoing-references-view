# Oxygen DITA References View
Side view in Oxygen XML Editor which shows all outgoing references and incoming references (only for Oxygen 23 or newer) for the current opened DITA topic. Available in the Editor and DITA perspectives.

The following outgoing references are presented in the side view for an opened DITA topic:

* Image References (image with href or keyref)
* Other media resources (DITA objects pointing to video, audio or embeddable frames)
* Cross references (xref with href or keyref)
* Key references (various elements with keyref)
* Content References (conref or conkeyref)
* Related links (link with href or keyref)
* Related links defined in relationship tables (Oxygen 22 or newer)

The incoming references are presented for the current opened DITA topic. If a reference is expanded, the references to that topic will be displayed.

Features of the side view:

* Side view user interface is translated in English, German, French, Japanese and Dutch.
* Works both in the Author and the Text editing modes.
* Selection in the outgoing references side view is synchronized with the caret location in the editor area for outgoing references.
* Double click or ENTER on a reference to open the target location.


How to install
--------------
1. In Oxygen, go to **Help->Install new add-ons** to open an add-on selection dialog box.
1. Enter or paste https://raw.githubusercontent.com/oxygenxml/oxygen-dita-outgoing-references-view/master/build/addon.xml in the **Show add-ons** from field.
1. Select the **DITA Outgoing References View** add-on and click **Next**.
1. Read the end-user license agreement. Then select the **I accept all terms of the end-user license agreement** option and click **Finish**.
1. Restart the application.

Result: A **DITA References** side-view will now be available. This side-view includes actions in the contextual menu to open the selected outgoing reference or show the definition location in case of a related link defined in a relationship table in DITA Map.

Copyright and License
---------------------
Copyright 2019 Syncro Soft SRL.

This project is licensed under [Apache License 2.0](https://github.com/oxygenxml/oxygen-dita-outgoing-references-view/blob/master/LICENSE)
