# Overwiev

JNotepad - simple and lightweight notepad. Project evolved from school project. I'm stil developing it despite it's using quite old Swing UI, 
so any suggestions on new functionalities are more than welcome.

### Main view
program is divided into 3 sections: Menu Bar, Text Area and panel with radiobuttons in the bottom:

![screen_overview](screens/1_overalCodepages.jpg?raw=true "JNotepad - overview")

### File Menu:

Functions implemented so far: New, Open, Save, Save s..., Exit. Print - in progress

![screen_overview](screens/2_fileMenu.jpg?raw=true "File Menu")

### Open/Save/Save as... File Choosers

Each functionality have default swing FileChooser implemented:

![screen_overview](screens/3_save_asChooser.jpg?raw=true "File Chooser")

### Word Wrap

In Format File Menu - word wrap feature implemented:

<span style="display:block;text-align:center">![screen_overview](screens/4_formatWordWrap.jpg?raw=true "Word Wrap")</span>

### Skin change

Featured in Swing library - possbility to choose between the following skins:

![screen_overview](screens/5_skins.jpg?raw=true "Skin change menu")

### User warnings

Application have the following warnings implemented:

- File not save warnings - when user wants to exit without saving text area or to open new/existing file before saving text area:

![screen_overview](screens/6_notSavedWarning.jpg?raw=true "File not saved - warning")

- File already exist warning - when user wants to save file with the file name that already exists in current folder:

![screen_overview](screens/7_alreadyExistWarning.jpg?raw=true "JNotepad - overview")



## Features added to v03:
- architecture improved (classes regrouped into three packages to reflect MVC architecture)
- save method is checking if the file already exist in given directory
- save split into "save" ad "save as...", also "save" deactivated until first USE OF "save as..." OR "open" 
- method added to check if text area is updated from last save operation - if it does, prompt window is called 
  an user is requested to confirm before he exits program. Method is also requesting for user confirmation 
  if opens a new file without saving current one.
- global working directory is create after first use of "save" or "open"

## Features to be added in next release:
- word wrap (Menu/Format) - needs to be improved (mechanical implementation, needs to work in line with natural language rules)
- "save as..." - reopen chooser if user drops overwriting the file (for user convenience)
- continous save
- *.txt as default file format during "save as..." operation
- edit (Menu/Edit: Cut, Copy, Paste) - with both mouse ad keyboard support
- text navigation (Menu/Edit: find, replace, select all)
- font settings
- text statistics
- program should be taking control over the open file (blocked for cut/copy delete from OS shell, unavailable for other apps while opened)
- hints to be added
- helpfile to be added
- (optional) - welcome graphics before any User action taken
- JUnit tests
- javadoc class descriptions

## Known bugs to be fixed:
- skin radio buttons are not refreshing during skin change - to be fixed in next update
- skin change doesn't affect application top bar - to be fixed in net update
