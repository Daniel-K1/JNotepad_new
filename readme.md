# Overwiev

JNotepad - simple and lightweight notepad. Project evolved from school project. I'm stil developing it despite it's using quite old Swing UI, 
so any suggestions on new functionalities are more than welcome.

## Features added to v03:
- architecture improved (classes regrouped into three packages to reflect MVC architecture)
- save method is checking if the file already exist in given directory
- save split into "save" ad "save as...", also "save" deactivated until first USE OF "save as..." OR "open" 
- method added to check if text area is updated from last save operation - if it does, prompt window is called 
  an user is requested to confirm before he exits program. Method is also requesting for user confirmation 
  if opens a new file without saving current one.
- global working directory is create after first use of "save" or "open"

## features to be added in next release:
- word wrap (Menu/Format) - needs to be improved (mechanical implementation, needs to work in line with natural language rules)
- "save as..." - reopen chooser if user drops overwriting the file (for user convenience)
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

## known bugs to be fixed:
-(null)
