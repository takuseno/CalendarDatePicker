# CalendarPicker
Library of date picker dialog for Android

##Usage

Add repository to your gradle file
```gradle
repositories {
    maven { url {'https://raw.github.com/takuseno/CalendarPicker/master/repository'}}
}

dependencies {
    compile 'jp.gr.java_conf.androtaku.calendarpicker:calendarpicker:1.0.0'
}
```

In your Java Code
```java
CalendarPicker calendarPicker = new CalendarPicker();
calendarPicker.setDateSelectedListenr(new DateSelectedListener() {
    @Override
    public void onDateSelectedListener(int year, int month, int dayOfMonth) {
        String date = "" + year+ "/" + month + "/" + dayOfMonth;
        Toast.makeText(getApplicationContext(),date,Toast.LENGTH_SHORT).show();
    }
});
calendarPicker.show(getSupportFragmentManager(),"calendar");
```
##Screenshots
<p>
    <img src="screenshots/day.jpg" alt="day" width="270" height="480"/>
    <img src="screenshots/month.jpg" alt="month" width="270" height="480"/>
    <img src="screenshots/year.jpg" alt="year" width="270" height="480"/>
</p>

