# CalendarPicker
Library of date picker dialog for Android

##Usage
Import /calendarpicker as a module
```java
final CalendarPicker calendarPicker = new CalendarPicker();
calendarPicker.setOnOkClickListener(new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int which) {
        String date = "" + calendarPicker.getYear()
                    + "/" + calendarPicker.getMonth()
                    + "/" + calendarPicker.getDayOfMonth();
        Toast.makeText(getApplicationContext(),date,Toast.LENGTH_SHORT).show();
    }
});
calendarPicker.show(getSupportFragmentManager(),"calendar");
```
##Screenshots
![day](screenshots/day.jpg)
![day](screenshots/month.jpg)

